# P-- Compiler — `feature/contains` branch
> Extends the base compiler with support for the **`contains` expression** (search for an element inside an array).

## What this branch adds

P-- now supports the `.contains()` expression, allowing to check whether an array contains a given element. It returns `1` if the element is found and `0` otherwise, and can be used directly in conditions.

```
def main() -> None:
{
    a: [3]int;
    a[0] = 1;
    a[1] = 2;
    a[2] = 3;

    if a.contains(2):
        print '1';
    else:
        print '0';

    if a.contains(5):
        print '1';
    else:
        print '0';
}
```

Expected output:
```
1
0
```

## Language change

| | Before | After |
|---|---|---|
| Array search | ❌ Requires manual `while` loop with index | ✅ `array.contains(element)` |
| Result type | ❌ Not supported | ✅ Returns `IntType` (1 = found, 0 = not found) |
| Type checking | ❌ Not supported | ✅ Element type must be promotable to the array element type |

## Type rules

| Constraint | Example violation |
|---|---|
| Left operand must be an array type | `myInt.contains(1)` → error |
| Element type must be promotable to the array element type | `a: [3]int; a.contains(1.5)` → error |

## MAPL output example

Given:
```
def main() -> None:
{
    a: [3]int;
    a[0] = 1;
    a[1] = 2;
    a[2] = 3;
    if a.contains(2):
        print '1';
    else:
        print '0';
}
```

Generated assembly (excerpt for the `contains` expression):
```asm
#source "contains-in.txt"
' Invocation to the main function
call main
halt

#line 1
 main:
    ' * Parameters
    ' * Local variables
    ' * ArrayType[of:IntType,size:3] a (offset -6)
    enter  6

#line 8
    ' * If else
    push   bp
    pushi  -6
    addi
    pushi  0
    addi
    loadi
    pushi  2
    eqi
    jnz    label2
    push   bp
    pushi  -6
    addi
    pushi  2
    addi
    loadi
    pushi  2
    eqi
    jnz    label2
    push   bp
    pushi  -6
    addi
    pushi  4
    addi
    loadi
    pushi  2
    eqi
    jnz    label2
    pushi  0
    jmp    label3
 label2:
    pushi  1
 label3:
    jz     label0
    ...
    ret    0, 6, 0
```

## Code generation structure

The `contains` expression unrolls the search statically at compile time. For each index `i` from `0` to `array.size - 1`, the array element is loaded and compared with the searched element. If any comparison succeeds, execution jumps to `foundLabel` and leaves `1` on the stack. If no match is found, `0` is left instead:

```
    address[[ array ]]
    <pushi> i * elementType.numberOfBytes()
    <add i>
    <load> elementType.suffix()
    value[[ element ]]
    convertTo(element.type, elementType)
    <eq> elementType.suffix()
    <jnz> foundLabel

    <pushi> 0
    <jmp> endLabel

 foundLabel:
    <pushi> 1

 endLabel:
```

The loop is fully unrolled — no runtime branching per iteration. Each element is compared by its static offset, and execution short-circuits to `foundLabel` on the first match.

---

## Language specification

### 1. Abstract grammar

A new production is added to the expression rules:

```
(15)  Contains : expression1 -> expression2  expression3
```

Where `expression2` is the array and `expression3` is the element to search for.

### 2. Semantic rules (attribute grammar)

The following rule is added to the expression rules:

```
(15)  expression1.type = expression2.type.contains(expression3.type)
```

The `contains` method is dispatched polymorphically:
- `ArrayType` overrides it: checks that `expression3.type` is promotable to the array's element type, and returns `IntType` as the result.
- All other types inherit the default from `AbstractType`, which produces an `ErrorType` since they are not arrays.

### 3. Code generation template

```
value[[ Contains : expression1 -> expression2  expression3 ]]() =
    String foundLabel = cg.getLabel()
    String endLabel   = cg.getLabel()

    for i = 0; i < expression2.type.size; i++:
        address[[ expression2 ]]
        <pushi> i * expression2.type.elementType.numberOfBytes()
        <add i>
        <load> expression2.type.elementType.suffix()
        value[[ expression3 ]]
        convertTo(expression3.type, expression2.type.elementType)
        <eq> expression2.type.elementType.suffix()
        <jnz> foundLabel

    <pushi> 0
    <jmp> endLabel

    foundLabel <:>
    <pushi> 1

    endLabel <:>
```
