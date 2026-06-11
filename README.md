# P-- Compiler — `feature/compound-assignment` branch

> Extends the base compiler with support for **compound assignment operators**.

## What this branch adds

P-- now supports the compound assignment operators `+=`, `-=`, `*=` and `/=`, allowing
arithmetic and assignment to be combined in a single statement.

```
def main()->None: {
    a, b: int;
    x: double;

    a = 10;
    b = 3;
    x = 2.5;

    a += 5;
    print a, '\n';

    a -= 3;
    print a, '\n';

    b *= 4;
    print b, '\n';

    x /= 2.0;
    print x, '\n';
}
```

Expected output:

```
15
12
12
1.25
```

## Language change

| | Before | After |
|---|---|---|
| Compound assignment | ❌ Requires `a = a + b` | ✅ `a += b` |
| Supported operators | ❌ Not supported | ✅ `+=`, `-=`, `*=`, `/=` |
| Type checking | ❌ Not supported | ✅ Both operands must be built-in types and right must promote to left |

## Type rules

| Constraint | Example violation |
|---|---|
| Left operand must be a built-in type | `v += 1` where `v` is an array → error |
| Right operand must be a built-in type | `a += v` where `v` is a struct → error |
| Right operand must promote to left | `x -= 10` where `x` is `char` and right is `double` → error |
| Left operand must be an lvalue | `(a + b) += 1` → error |

## MAPL output example

Given:

```
def main()->None: {
    a: int;
    a = 10;
    a += 5;
    print a, '\n';
}
```

Generated assembly:

```asm
#source "compound-assignment-in.txt"
' Invocation to the main function
call main
halt
#line 3
 main:
    ' * Parameters
    ' * Local variables
    ' * IntType a (offset -2)
    enter  2
#line 4
    ' * Assignment
    push   bp
    pushi  -2
    addi
    pushi  10
    storei
#line 5
    ' * Compound Assignment
    push   bp
    pushi  -2
    addi
    push   bp
    pushi  -2
    addi
    loadi
    pushi  5
    addi
    storei
#line 6
    ' * Write
    push   bp
    pushi  -2
    addi
    loadi
    outi
    ret    0, 2, 0
```

## Code generation structure

Each compound assignment compiles into the following pattern:

```
    address[[expression1]]   ← push address of left (for store)
    value[[expression1]]     ← push current value of left
    value[[expression2]]     ← push value of right
    <convert right to left type>
    <op suffix>              ← add/sub/mul/div
    <store suffix>           ← store result back into left
```

The address of the left operand is pushed first and kept on the stack until the
final `store`. The current value of the left operand is then loaded, the right
operand is evaluated, and the operation is performed. The result is stored back
into the original address.

---

## Language specification

### 1. Abstract grammar

A new production is added to the statement rules:

```
(08)  CompoundAssignment : statement -> expression1 (+|-|*|/) expression2
```

Where `expression1` is the left-hand side (must be an lvalue) and `expression2`
is the right-hand side.

### 2. Semantic rules (attribute grammar)

A single rule is added to the statement rules:

```
(08)  expression2.type.mustBeCompoundAssignable(expression1.type)
```

The `mustBeCompoundAssignable` method is defined in `AbstractType` and checks:
- `expression2.type` is a built-in type (Int, Char or Real)
- `expression2.type` can promote to `expression1.type`

Built-in types (`IntType`, `CharType`, `RealType`) inherit this implementation
from `AbstractType`. All other types inherit the default error from `AbstractType`.

### 3. Code generation template

```
execute[[ CompoundAssignment : statement -> expression1 (+|-|*|/) expression2 ]]() =
    address[[ expression1 ]]
    value[[ expression1 ]]
    value[[ expression2 ]]
    expression2.type.convertTo(cg, expression1.type)
    switch (OP) {
        case "+" -> <add> expression1.type.suffix()
        case "-" -> <sub> expression1.type.suffix()
        case "*" -> <mul> expression1.type.suffix()
        case "/" -> <div> expression1.type.suffix()
    }
    <store> expression1.type.suffix()
```
