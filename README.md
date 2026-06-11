# P-- Compiler — `feature/do-while` branch
> Extends the base compiler with support for the **do-while loop statement**.

## What this branch adds

P-- now supports the `do-while` loop, which guarantees that the body executes **at least once** before the condition is evaluated.

```
def main()->None: {
    i:int;

    input i;

    do: {
        print i, '\n';
        i = i + 1;
    } while i < 5;

    print 'E', '\n';
}
```

Expected output (for input `3`):
```
3
4
End
```

## Language change

| | Before | After |
|---|---|---|
| Loop with guaranteed first execution | ❌ Not supported | ✅ `do: { body } while condition;` |
| Condition evaluated after body | ❌ Only `while` (pre-check) | ✅ `do-while` (post-check) |
| Body always runs at least once | ❌ Not supported | ✅ Guaranteed |

## Syntax

```
do: block while expression ;
```

Where `block` follows the same rules as `while` — either a single statement or a `{ ... }` block.

## Semantic rules

The `do-while` follows the same type rule as `while` (rule 08):

| Constraint | Example violation |
|---|---|
| Condition must be logical/promotable to int | `do: { ... } while 3.5;` → error |

```
(08)  DoWhile: statement -> statement*  expression
      expression.type.mustBeLogical()
```

## MAPL output example

Given:
```
def main()->None: {
    i:int;
    input i;
    do: {
        print i, '\n';
        i = i + 1;
    } while i < 5;
    print 'E', '\n';
}
```

Generated assembly:
```asm
#source "doWhile-in.txt"

' Invocation to the main function
call main
halt


#line   1

 main:
    ' * Parameters
    ' * Local variables
    ' * IntType i (offset -2)
    enter  2

#line   4
    ' * Read
    push   bp
    pushi  -2
    addi
    ini
    storei

#line   9
    ' * Do While

 label0:
    ' * DoWhile body

#line   7
    ' * Write
    push   bp
    pushi  -2
    addi
    loadi
    outi

#line   7
    ' * Write
    pushb  10
    outb

#line   8
    ' * Assignment
    push   bp
    pushi  -2
    addi
    push   bp
    pushi  -2
    addi
    loadi
    pushi  1
    addi
    storei
    ' * DoWhile condition
    push   bp
    pushi  -2
    addi
    loadi
    pushi  5
    lti
    jnz    label0

#line   11
    ' * Write
    pushb  69
    outb
    ...
    ret    0, 2, 0
```

## Code generation structure

Each `do-while` compiles into the following label pattern:

```
 labelStart:
    <body>
    <condition>
    jnz labelStart    ← jump back if condition is true (≠ 0)
```

The body executes unconditionally on the first pass. After each iteration the condition is evaluated; if it is non-zero (true), execution jumps back to `labelStart`. Otherwise it falls through. Only **one label** is needed, compared to the two used by a standard `while`.

---

## Language specification

### 1. Abstract grammar

A new production is added to the statement rules:

```
(08)  DoWhile:  statement -> statement*  expression
```

Note the order: the body (`statement*`) comes before the condition (`expression`), reflecting post-check evaluation semantics.

### 2. Semantic rules (attribute grammar)

```
(08)  expression.type.mustBeLogical()
```

Identical rule to `while` (rule 05). The condition type is validated via `mustBeLogical()`, which accepts `IntType` and `CharType` (promotable to int) and rejects `RealType`, `VoidType`, `StructType`, and `ArrayType`.

### 3. Code generation template

```
execute[[ DoWhile : statement -> statement*  expression ]]() =
    String labelStart = cg.getLabel()

    labelStart <:>
    execute[[ statement* ]]
    value[[  expression  ]]
    <jnz> labelStart
```

---

## New class: `DoWhile`

A dedicated AST node was added at `ast/statements/DoWhile.java`:

```java
public class DoWhile extends AbstractStatement {
    // REPRESENTATION -> "do: { body } while condition;"

    private final Expression condition;
    private final List<Statement> body;

    public DoWhile(int line, int column, Expression condition, List<Statement> body) {
        super(line, column);
        this.condition = condition;
        this.body = body;
    }

    @Override
    public <TP, TR> TR accept(Visitor<TP, TR> visitor, TP parameter) {
        return visitor.visit(this, parameter);
    }
}
```

The `Visitor` interface gains one new method:

```java
TR visit(DoWhile doWhile, TP parameter);
```
