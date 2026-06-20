# P-- Compiler — `feature/swap` branch
> Extends the base compiler with support for the **swap statement `<=>`**, allowing two variables to exchange values in a single statement.

## What this branch adds

P-- now supports the `<=>` operator as a statement, swapping the values of two LValue expressions of the same built-in type without needing a temporary variable.

```
def main()->None: {
    a: int;
    b: int;
    a = 1;
    b = 2;
    a <=> b;
    print 'a', a, '\n'; # Should print 2
    print 'b', b, '\n'; # Should print 1
}
```

Expected output:
```
a2
b1
```

## Language change

| | Before | After |
|---|---|---|
| Swapping two variables | ❌ Requires a temporary variable | ✅ `a <=> b;` |
| Operand requirements | ❌ Not supported | ✅ Both operands must be LValues of the same type |

## Type rules

| Constraint | Example violation |
|---|---|
| Both operands must be LValues | `5 <=> a;` → error |
| Both operands must have the same type | `a: int; b: char; a <=> b;` → error |

## MAPL output example

Given:
```
a <=> b;
```

Generated assembly:
```asm
#line 8
    push   bp
    pushi  -2
    addi
    push   bp
    pushi  -4
    addi
    loadi
    push   bp
    pushi  -4
    addi
    push   bp
    pushi  -2
    addi
    loadi
    storei
    storei
```

## Code generation structure

The swap pushes the address of the first operand, then the value of the second, then the address of the second, then the value of the first, and finally stores twice. Since the stack is LIFO, the two `store` instructions consume `(addr2, val1)` first and `(addr1, val2)` second, correctly exchanging the values:

```
execute[[ Swap : statement -> expression1  expression2 ]]() =
    address[[ expression1 ]]
    value[[ expression2 ]]
    address[[ expression2 ]]
    value[[ expression1 ]]
    <store> expression1.type.suffix()
    <store> expression1.type.suffix()
```

---

## Language specification

### 1. Abstract grammar

A new production is added to the statement rules:

```
(08)  Swap : statement -> expression1  expression2
```

### 2. Semantic rules (attribute grammar)

```
(08)  expression1.lvalue
      expression2.lvalue
      expression1.type.mustBeEqual(expression2.type)
```

`mustBeEqual` is dispatched polymorphically: `IntType`, `CharType`, and `RealType` override it to compare type identity (since these types are singletons), producing an `ErrorType` if the types differ. All other types inherit the default implementation from `AbstractType`, which produces an `ErrorType` since arrays and structs are not yet supported by this operation.

### 3. Code generation template

```
execute[[ Swap : statement -> expression1  expression2 ]]() =
    address[[ expression1 ]]
    value[[ expression2 ]]
    address[[ expression2 ]]
    value[[ expression1 ]]
    <store> expression1.type.suffix()
    <store> expression1.type.suffix()
```
