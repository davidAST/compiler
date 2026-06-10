# P-- Compiler — `feature/ternary` branch
> Extends the base compiler with support for the **ternary conditional operator**.

## What this branch adds

P-- now supports the ternary conditional operator `? :`, allowing inline conditional expressions. It is the operator with the lowest precedence and right associativity.

```
def main()->None: {
    i, max, h, j:int;
    d:double;

    i = 'a';
    d = i > 0 ? 1.1 : 0.1;
    print d, '\n';

    print d > 0 ? 'G' : 'L', '\n';

    j = 0;
    h = 100;
    max = i > j ? (i > h ? i : h) : (j > h ? j : h);
    print max, '\n';

    print i > 90 ? 'A' : i > 80 ? 'B' : 'C', '\n';
}
```

Expected output:
```
1.1
G
100
A
```

## Language change

| | Before | After |
|---|---|---|
| Inline conditional | ❌ Requires `if/else` statement | ✅ `condition ? thenExpr : elseExpr` |
| Right associativity | ❌ Not supported | ✅ `a ? b : c ? d : e` groups as `a ? b : (c ? d : e)` |
| Type checking | ❌ Not supported | ✅ Condition must be promotable to `int`; both branches must be equal built-in types |

## Type rules

| Constraint | Example violation |
|---|---|
| First operand must be promotable to `int` | `3.3 ? 'a' : 'b'` → error |
| Second and third operands must be equal built-in types | `i > 0 ? 1.1 : 'a'` → error |
| Second and third operands must not be `void` | `0 ? p() : p()` → error |

## MAPL output example

Given:
```
def main()->None: {
    i:int;
    d:double;
    i = 'a';
    d = i > 0 ? 1.1 : 0.1;
    print d, '\n';
}
```

Generated assembly:
```asm
#source "ternary-in.txt"
' Invocation to the main function
call main
halt

#line 4
 main:
    ' * Parameters
    ' * Local variables
    ' * IntType i (offset -2)
    ' * RealType d (offset -6)
    enter  6

#line 5
    ' * Assignment
    push   bp
    pushi  -2
    addi
    pushb  97
    b2i
    storei

#line 6
    ' * Assignment
    push   bp
    pushi  -6
    addi
    push   bp
    pushi  -2
    addi
    loadi
    pushi  0
    gti
    jz label0
    pushf  1.1
    jmp    label1

 label0:
    pushf  0.1

 label1:
    storef

#line 7
    ' * Write
    push   bp
    pushi  -6
    addi
    loadf
    outf
    ret    0, 6, 0
```

## Code generation structure

Each ternary expression compiles into the following label pattern:

```
    <condition>
    jz  labelElse     ← jump if condition is false (0)
    <thenExpression>
    jmp labelEnd      ← skip else branch
 labelElse:
    <elseExpression>
 labelEnd:            ← result is on top of the stack
```

The condition is evaluated once. If it is `0` (false), execution jumps to `labelElse` and evaluates the else expression. Otherwise the then expression is evaluated and execution jumps to `labelEnd`. The result of whichever branch was taken remains on top of the stack.

---

## Language specification

### 1. Abstract grammar

A new production is added to the expression rules:

```
(N)  Ternary : expression1 -> expression2  expression3  expression4
```

Where `expression2` is the condition, `expression3` is the then-branch and `expression4` is the else-branch.

### 2. Semantic rules (attribute grammar)

A single rule is added to the expression rules:

```
(N)  expression1.type = expression2.type.ternary(expression3.type, expression4.type)
```

The `ternary` method is dispatched on `expression2.type` (the condition) using polymorphism:
- `IntType` and `CharType` override it: both are promotable to `int`, so the condition is valid. The method then checks that `expression3.type` and `expression4.type` are both built-in and equal, and returns that type as the result.
- All other types (`RealType`, `VoidType`, `StructType`, `ArrayType`...) inherit the default implementation from `AbstractType`, which returns an `ErrorType` since they are not promotable to `int`.

### 3. Code generation template

```
value[[ Ternary : expression1 -> expression2  expression3  expression4 ]]() =
    String labelElse = cg.getLabel()
    String labelEnd  = cg.getLabel()

    value[[ expression2 ]]
    <jz>  labelElse

    value[[ expression3 ]]
    <jmp> labelEnd

    labelElse <:>
    value[[ expression4 ]]

    labelEnd <:>
```
