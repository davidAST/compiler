# P-- Compiler — `feature/forEach` branch
> Extends the base compiler with support for the **`each` statement** (forEach loop over arrays).

## What this branch adds

P-- now supports the `each` statement, allowing iteration over all elements of an array with a clean and concise syntax. The array elements are assigned one by one to a parameter variable, over which a body of statements is executed.

```
def main() -> None:
{
    a: [5]int;
    element: int;
    a[0] = 1;
    a[1] = 2;
    a[2] = 3;
    a[3] = 4;
    a[4] = 5;
    a.each(element -> print element;);
}
```

Expected output:
```
1
2
3
4
5
```

## Language change

| | Before | After |
|---|---|---|
| Array iteration | ❌ Requires manual `while` loop with index | ✅ `array.each(param -> body;)` |
| Element assignment | ❌ Manual indexing required | ✅ Automatic element-by-element assignment to parameter |
| Type checking | ❌ Not supported | ✅ Array element type must be promotable to parameter type; parameter must be an LValue |

## Type rules

| Constraint | Example violation |
|---|---|
| First operand must be an array type | `myInt.each(e -> print e;)` → error |
| Array element type must be promotable to parameter type | `a: [3]double; e: int; a.each(e -> ...)` → error |
| Parameter must be an LValue | `a.each(0 -> ...)` → error |

## MAPL output example

Given:
```
def main() -> None:
{
    a: [5]int;
    element: int;
    a[0] = 1;
    ...
    a.each(element -> print element;);
}
```

Generated assembly (excerpt for the `each` loop):
```asm
#source "each-in.txt"
' Invocation to the main function
call main
halt

#line 1
 main:
    ' * Parameters
    ' * Local variables
    ' * ArrayType[of:IntType,size:5] a (offset -10)
    ' * IntType element (offset -12)
    enter  12

#line 12
    ' * Each
    push   bp
    pushi  -12
    addi
    push   bp
    pushi  -10
    addi
    pushi  0
    addi
    loadi
    storei

#line 12
    ' * Write
    push   bp
    pushi  -12
    addi
    loadi
    outi
    ...
    ret    0, 12, 0
```

## Code generation structure

Each `each` statement unrolls the loop statically at compile time. For each index `i` from `0` to `array.size - 1`, the following pattern is emitted:

```
    address[[ param ]]
    address[[ array ]]
    <pushi> i * elementType.numberOfBytes()
    <add i>
    <load> elementType.suffix()
    convertTo(elementType, param.type)
    <store> param.type.suffix()
    execute[[ body ]]
```

The loop is fully unrolled — no runtime branching or jump labels are generated. Each element of the array is loaded by its static offset, assigned to the parameter variable, and the body is executed once per element.

---

## Language specification

### 1. Abstract grammar

A new production is added to the statement rules:

```
(08)  Each : statement1 -> expression1  expression2  statement
```

Where `expression1` is the array, `expression2` is the iteration parameter (must be an LValue), and `statement` is the body executed for each element.

### 2. Semantic rules (attribute grammar)

The following rules are added to the statement rules:

```
(08)  expression1.type.mustBeArray()
      expression1.type.elementType.mustPromoteTo(expression2.type)
      expression2.lvalue
```

The `mustBeArray` method is dispatched polymorphically:
- `ArrayType` overrides it with an empty (valid) implementation.
- All other types (`IntType`, `RealType`, `CharType`, etc.) inherit the default from `AbstractType`, which produces an `ErrorType`.

### 3. Code generation template

```
execute[[ Each : statement1 -> expression1  expression2  statement2 ]](funcDef) =
    for i = 0; i < expression1.type.size; i++:
        address[[ expression2 ]]
        address[[ expression1 ]]
        <pushi> i * expression1.type.elementType.numberOfBytes()
        <add i>
        <load> expression1.type.elementType.suffix()
        convertTo(expression1.type.elementType, expression2.type)
        <store> expression2.type.suffix()
        execute[[ statement2 ]]
```
