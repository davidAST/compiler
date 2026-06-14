# P-- Compiler — `feature/multiple-assignment` branch
> Extends the base compiler with support for **multiple assignment**: assigning several variables in a single statement.

## What this branch adds

P-- now supports multiple assignment, allowing several variables to be assigned in a single statement by listing expressions on both sides of `=`, separated by commas.

```
def main()->None: {
    a: int;
    b: char;
    c: double;
    a, b, c = 5, 'a', 3.2;
    print a, '\n';
    print b, '\n';
    print c, '\n';
}
```

Expected output:
```
5
a
3.2
```

## Language change

| | Before | After |
|---|---|---|
| Multiple assignment | ❌ Requires one `=` per variable | ✅ `a, b, c = 1, 'a', 2.3` |
| Mixed types | ❌ Not supported in one statement | ✅ Each pair is assigned independently |
| Error checking | ❌ Not supported | ✅ Number of left and right expressions must match |

## Type rules

| Constraint | Example violation |
|---|---|
| Number of left and right expressions must be equal | `a, b = 1, 2, 3` → error |
| Each right expression must be promotable to its corresponding left expression | `a: int; a, b = 3.2, 'x'` → error if `int` ← `double` not allowed |

## MAPL output example

Given:
```
a, b, c = 5, 'a', 3.2;
```

Generated assembly:
```asm
#line 6
    ' * Assignment
    push   bp
    pushi  -2
    addi
    pushi  5
    storei

#line 6
    ' * Assignment
    push   bp
    pushi  -3
    addi
    pushb  97
    storeb

#line 6
    ' * Assignment
    push   bp
    pushi  -7
    addi
    pushf  3.2
    storef
```

## Code generation structure

Multiple assignment desugars at parse time into a sequence of independent `Assignment` nodes — one per pair. No new AST node is introduced. The generated MAPL is identical to writing the assignments one by one:

```
a, b, c = 5, 'a', 3.2
  →  Assignment(a, 5)
     Assignment(b, 'a')
     Assignment(c, 3.2)
```

Each assignment is processed left-to-right in order.

---

## Language specification

### 1. Abstract grammar

No new AST node is added. The multiple assignment expands at parse time into a sequence of existing `Assignment` nodes:

```
a, b, c = e1, e2, e3
  ≡  Assignment : statement -> expression1  expression4
     Assignment : statement -> expression2  expression5
     Assignment : statement -> expression3  expression6
```

### 2. Semantic rules

No new semantic rules are needed. Each desugared `Assignment` reuses the existing rule:

```
(03)  expression2.type.mustPromoteTo(expression1.type)
```

### 3. Grammar rule

A new auxiliary rule `expressionsAssignment` is added to collect a comma-separated list of expressions, and a new `statement` alternative handles the multiple assignment:

```antlr
| leftExpr=expressionsAssignment '=' rightExpr=expressionsAssignment ';'
    {
        if ($leftExpr.ast.size() != $rightExpr.ast.size())
            new ErrorType("Number of expressions in the left side is not the same as in the right side", $leftExpr.ast.get(0));
        for (int i = 0; i < $leftExpr.ast.size(); i++) {
            $ast.add(new Assignment(
                $leftExpr.ast.get(i).getLine(),
                $leftExpr.ast.get(i).getColumn(),
                $leftExpr.ast.get(i),
                $rightExpr.ast.get(i)
            ));
        }
    }

expressionsAssignment returns [List<Expression> ast = new ArrayList<>()]
    : exp=expression {$ast.add($exp.ast);} (',' exp=expression {$ast.add($exp.ast);})*
    ;
```
