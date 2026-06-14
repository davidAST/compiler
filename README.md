# P-- Compiler — `feature/assignment-expression` branch
> Extends the base compiler with support for **assignment as an expression**, enabling chained assignments like `a = b = c = 1`.

## What this branch adds

P-- now treats assignment as an expression rather than a statement, allowing assignments to be chained and used in any expression context. The result of an assignment is the value that was assigned.

```
def main() -> None:
{
    a: int;
    b: int;
    c: int;
    a = b = c = 1;
    print a, '\n';
    print b, '\n';
    print c, '\n';
}
```

Expected output:
```
1
1
1
```

## Language change

| | Before | After |
|---|---|---|
| Assignment | ❌ Only as a statement | ✅ Also as an expression |
| Chained assignment | ❌ Requires one statement per variable | ✅ `a = b = c = 1` |
| Result of assignment | ❌ No value | ✅ Returns the assigned value |
| Expression statements | ❌ Not supported | ✅ Any expression can be used as a statement via `ExpressionStatement` |

## Type rules

| Constraint | Example violation |
|---|---|
| Left operand must be an LValue | `1 = 5` → error |
| Right type must be promotable to left type | `a: int; a = 3.2` → error |
| Result type is the left operand's type | `a = b = 1` → type of the whole expression is `int` |

## MAPL output example

Given:
```
a = b = c = 1;
```

Generated assembly:
```asm
#line 6
    push   bp
    pushi  -2
    addi
    push   bp
    pushi  -4
    addi
    push   bp
    pushi  -6
    addi
    pushi  1
    storei
    push   bp
    pushi  -6
    addi
    loadi
    storei
    push   bp
    pushi  -4
    addi
    loadi
    storei
    push   bp
    pushi  -2
    addi
    loadi
    popi
```

## Code generation structure

Assignment as an expression stores the value and then loads it back onto the stack so it can be used as the right-hand side of the next assignment in the chain. When used as a statement via `ExpressionStatement`, the leftover value on the stack is discarded with `pop`.

**Assignment (expression):**
```
value[[ Assignment : expression1 -> expression2  expression3 ]]() =
    address[[ expression2 ]]
    value[[ expression3 ]]
    convertTo(expression3.type, expression2.type)
    <store> expression2.type.suffix()
    value[[ expression2 ]]       ← leaves the assigned value on the stack
```

**ExpressionStatement (statement):**
```
execute[[ ExpressionStatement : statement -> expression ]]() =
    value[[ expression ]]
    <pop> expression.type.suffix()   ← discards the value
```

---

## Language specification

### 1. Abstract grammar

`Assignment` moves from Statements to Expressions, and a new `ExpressionStatement` is added to Statements:

```
Expressions (Abstract Grammar)
    (15)  Assignment : expression1 -> expression2  expression3

Statements (Abstract Grammar)
    (03)  ExpressionStatement : statement -> expression
```

### 2. Semantic rules (attribute grammar)

```
Expressions (Semantic Rules)
    (15)  expression3.type.mustPromoteTo(expression2.type)
          expression2.lvalue
          expression1.type = expression2.type
```

`ExpressionStatement` has no additional semantic rules — the expression is already type-checked.

### 3. Grammar rules

Assignment is added to `expression` with right associativity, and the old assignment statement is replaced by `ExpressionStatement`:

```antlr
// In expression:
| <assoc=right> left=expression '=' right=expression
    {$ast = new Assignment($left.ast.getLine(), $left.ast.getColumn(), $left.ast, $right.ast);}

// In statement (replaces old assignment rule):
| exp=expression ';'
    {$ast.add(new ExpressionStatement($exp.ast.getLine(), $exp.ast.getColumn(), $exp.ast));}
```
