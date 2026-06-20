# P-- Compiler — `feature/array-init` branch
> Extends the base compiler with support for **array initialization with literal lists**, allowing arrays to be declared and filled in a single statement.

## What this branch adds

P-- now supports initializing an array variable directly at declaration with a brace-enclosed list of expressions, instead of assigning each element manually.

```
def main() -> None:
{
    a: [5]int = {1, 2, 3, 4, 5};
    print a[0], '\n';
    print a[1], '\n';
    print a[2], '\n';
    print a[3], '\n';
    print a[4], '\n';
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
| Array initialization | ❌ One assignment per element | ✅ `a: [5]int = {1, 2, 3, 4, 5};` |
| Size checking | ❌ Not supported | ✅ Number of initializer values must match the declared array size |
| Element type checking | ❌ Not supported | ✅ Each value must be promotable to the array's element type |

## Type rules

| Constraint | Example violation |
|---|---|
| The declared type must be an array | `a: int = {1, 2, 3};` → error |
| The number of values must match the array size | `a: [5]int = {1, 2, 3};` → error |
| Each value must be promotable to the element type | `a: [5]int = {1.0, 2, 3, 4, 5};` → error |

## MAPL output example

Given:
```
def main() -> None:
{
    a: [5]int = {1, 2, 3, 4, 5};
    ...
}
```

Generated assembly:
```asm
#source "array-init-in.txt"
' Invocation to the main function
call main
halt

#line 1
 main:
    ' * Parameters
    enter  10
    ' * Local variables
    ' * ArrayType[of:IntType,size:5] a (offset -10)
    push   bp
    pushi  -10
    addi
    pushi  0
    addi
    pushi  1
    storei
    push   bp
    pushi  -10
    addi
    pushi  2
    addi
    pushi  2
    storei
    push   bp
    pushi  -10
    addi
    pushi  4
    addi
    pushi  3
    storei
    push   bp
    pushi  -10
    addi
    pushi  6
    addi
    pushi  4
    storei
    push   bp
    pushi  -10
    addi
    pushi  8
    addi
    pushi  5
    storei
    ...
    ret    0, 10, 0
```

Note that `enter` is emitted **before** the initialization stores. The activation record must be allocated first, since the stores write into stack-relative offsets that only become valid memory after `enter` reserves the local variables' space — initializing before `enter` would write into memory that is later overwritten or invalid.

## Code generation structure

For each element in the initializer list, the array's base address is computed, offset by `i * elementType.numberOfBytes()`, and the corresponding value is stored:

```
execute[[ VarDefinition : definition -> ID type expression* ]]() =
    <` * ` > varDefinition.type ID <(offset > varDefinition.offset <)>
    if (!expression*.isEmpty()) {
        ArrayType type = (ArrayType) definition.type
        for (int i = 0; i < expression*.size; i++) {
            <push> bp
            <pushi> definition.offset
            <addi>
            <pushi> i * type.elementType.numberOfBytes()
            <addi>
            value[[expression*[i]]]
            cg.convert(expression*[i].type, type.elementType)
            <store> type.elementType.suffix()
        }
    }
```

The initialization instructions are emitted after `enter` is generated in `FuncDefinition`, so the order in the function body is: parameters → `enter` → local variable comments and initializations → statements.

---

## Language specification

### 1. Abstract grammar

`VarDefinition` gains an optional list of initializer expressions:

```
Definitions (Abstract Grammar)
    (02) VarDefinition: definition -> ID type expression*
```

### 2. Semantic rules (attribute grammar)

```
Definitions (Semantic Rules)
    (02)  if !expression*.isEmpty():
              type.mustBeArray()
              type.size.mustEqual(expression*.size)
              expression*.forEach(exp -> exp.type.mustPromoteTo(type.elementType))
```

### 3. Grammar rule

A new `arrayInitializer` rule is added, and `varDefinition` accepts an optional `= arrayInitializer`:

```antlr
varDefinition returns [List<VarDefinition> ast = new ArrayList<>()]
    locals [List<Token> tokens = new ArrayList<>()]
    :   ID1=ID { $tokens.add($ID1); }
        (',' ID2=ID { $tokens.add($ID2); })* ':' type ('=' init=arrayInitializer)? ';'
        {
            for (Token t : $tokens) {
                $ast.add(new VarDefinition(
                    t.getLine(), t.getCharPositionInLine() + 1,
                    t.getText(), $type.ast,
                    $init != null ? $init.ast : null
                ));
            }
        }
    ;

arrayInitializer returns [List<Expression> ast = new ArrayList<>()]
    : '{' exp1=expression {$ast.add($exp1.ast);}
      (',' exp2=expression {$ast.add($exp2.ast);})*
      '}'
    ;
```
