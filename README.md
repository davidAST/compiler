# P-- Compiler — `feature/range-comparator` branch
> Extends the base compiler with support for the **range comparator operators `<<` and `>>`** (chained range checks).

## What this branch adds

P-- now supports two new infix operators for range checking: `<<` and `>>`. They allow checking whether a value falls within a range in a single expression, without needing to chain `&&` manually.

```
def main() -> None:
{
    a: int;
    b: int;
    a = 1;
    b = 10;

    if 1 << 5 << 10:
        print '1';
    else:
        print '0';

    if 1 >> 5 >> 10:
        print '1';
    else:
        print '0';

    if a << 5 << b:
        print '1';
    else:
        print '0';
}
```

Expected output:
```
1
0
1
```

## Language change

| | Before | After |
|---|---|---|
| Range check (ascending) | ❌ `a < x && x < b` | ✅ `a << x << b` |
| Range check (descending) | ❌ `a > x && x > b` | ✅ `a >> x >> b` |
| Works with variables | ❌ Verbose | ✅ `a << 5 << b` |

## Operator semantics

| Operator | Meaning | Example | Result |
|---|---|---|---|
| `a << x << b` | `a < x && x < b` | `1 << 5 << 10` | true (1) |
| `a >> x >> b` | `a > x && x > b` | `1 >> 5 >> 10` | false (0) |

## MAPL output example

Given:
```
if 1 << 5 << 10:
    print '1';
else:
    print '0';
```

Generated assembly:
```asm
#line 8
    ' * If else
    pushi  1
    pushi  5
    lti
    pushi  5
    pushi  10
    lti
    and
    jz     label0

#line 9
    ' * Write
    pushb  49
    outb
    jmp    label1

 label0:
#line 11
    ' * Write
    pushb  48
    outb
 label1:
```

## Code generation structure

Both operators desugar entirely at parse time into existing AST nodes — no new AST classes, visitors, or type checking rules are needed. The grammar action directly builds a `Logical(Comparison, Comparison, "&&")` node:

```
a << x << b  →  Logical(Comparison(a, x, "<"), Comparison(x, b, "<"), "&&")
a >> x >> b  →  Logical(Comparison(a, x, ">"), Comparison(x, b, ">"), "&&")
```

This means the generated MAPL is identical to what would be produced by writing `a < x && x < b` or `a > x && x > b` explicitly.

---

## Language specification

### 1. Abstract grammar

No new AST node is added. Both operators expand directly into existing nodes at parse time:

```
a << x << b  ≡  Logical : expression -> Comparison(a, x, "<")  Comparison(x, b, "<")  "&&"
a >> x >> b  ≡  Logical : expression -> Comparison(a, x, ">")  Comparison(x, b, ">")  "&&"
```

### 2. Semantic rules

No new semantic rules are needed. The desugared `Logical` and `Comparison` nodes reuse existing rules:

```
expression1.type = expression2.type.logic(expression3.type)
expression1.type = expression2.type.comparison(expression3.type)
```

### 3. Grammar rule

```antlr
| left=expression '<<' middle=expression '<<' right=expression
    {$ast = new Logical(
        $left.ast.getLine(), $left.ast.getColumn(),
        new Comparison($left.ast.getLine(), $left.ast.getColumn(), $left.ast, $middle.ast, "<"),
        new Comparison($left.ast.getLine(), $left.ast.getColumn(), $middle.ast, $right.ast, "<"),
        "&&");}

| left2=expression '>>' middle2=expression '>>' right2=expression
    {$ast = new Logical(
        $left2.ast.getLine(), $left2.ast.getColumn(),
        new Comparison($left2.ast.getLine(), $left2.ast.getColumn(), $left2.ast, $middle2.ast, ">"),
        new Comparison($left2.ast.getLine(), $left2.ast.getColumn(), $middle2.ast, $right2.ast, ">"),
        "&&");}
```
