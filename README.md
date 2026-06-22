# P-- Compiler — `feature/xor` branch
> Extends the base compiler with support for the **XOR operator `^`**, allowing exclusive-or operations between `int` and `char` expressions.

## What this branch adds

P-- now supports the `^` operator for logical/bitwise XOR between `int` and `char` operands. The result is always `IntType`. XOR is implemented as `(a | b) & !(a & b)`, using the existing logical instructions of the MAPL virtual machine.

```
def main()->None: {
    a: int;
    b: int;
    a = 1;
    b = 0;
    print a ^ b;
    print b ^ 0;
    print a ^ a;
    print 0 ^ 1;
}
```

Expected output:
```
1
0
0
1
```

## Language change

| | Before | After |
|---|---|---|
| XOR operation | ❌ Not supported | ✅ `a ^ b` between `int` and `char` operands |
| Result type | ❌ N/A | ✅ Always `IntType` |
| Type checking | ❌ N/A | ✅ Both operands must be `int` or `char` |

## Type rules

| Constraint | Example violation |
|---|---|
| Both operands must be `int` or `char` | `0 ^ x` where `x: double` → error |
| Result is always `IntType` | `'a' ^ 1` → `IntType` |

## MAPL output example

Given:
```
print a ^ b;
```

Generated assembly:
```asm
#line 7
    ' * Write
    push   bp
    pushi  -2
    addi
    loadi
    push   bp
    pushi  -4
    addi
    loadi
    or
    push   bp
    pushi  -2
    addi
    loadi
    push   bp
    pushi  -4
    addi
    loadi
    and
    not
    and
    outi
```

## Code generation structure

XOR is not a native instruction in MAPL, so it is implemented using the identity `a ^ b = (a | b) & !(a & b)`:

```
value[[ Xor : expression1 -> expression2  expression3 ]]() =
    value[[ expression2 ]]
    value[[ expression3 ]]
    <or>
    value[[ expression2 ]]
    value[[ expression3 ]]
    <and>
    <not>
    <and>
```

Both operands are evaluated twice — once for the `or` branch and once for the `and` branch.

---

## Language specification

### 1. Abstract grammar

A new production is added to the expression rules:

```
(15)  Xor : expression1 -> expression2  expression3
```

### 2. Semantic rules (attribute grammar)

```
(15)  expression1.type = expression2.type.xor(expression3.type)
```

The `xor` method is dispatched polymorphically:
- `IntType` accepts `IntType` or `CharType` as the other operand, returning `IntType`.
- `CharType` accepts `CharType` or `IntType` as the other operand, returning `IntType`.
- All other types inherit the default from `AbstractType`, which produces an `ErrorType`.

### 3. Grammar rule

```antlr
| left=expression '^' right=expression
    {$ast = new Xor($left.ast.getLine(), $left.ast.getColumn(), $left.ast, $right.ast);}
```

### 4. Code generation template

```
value[[ Xor : expression1 -> expression2  expression3 ]]() =
    value[[ expression2 ]]
    value[[ expression3 ]]
    <or>
    value[[ expression2 ]]
    value[[ expression3 ]]
    <and>
    <not>
    <and>
```
