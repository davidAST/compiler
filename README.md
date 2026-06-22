# P-- Compiler — `feature/void-return` branch
> Extends the base compiler with support for **`return;` without a value**, allowing an early exit from a `void` (`None`) function.

## What this branch adds

P-- now supports a bare `return;` statement (with no expression) to exit a function early, as long as the function's return type is `None`. The base compiler only allowed `return expression;`.

```
def checkIndex(index:int)->None: {
    if index < 5: {
        print 0,'\n';
        return;
    }
    print index,'\n';
}

def main()->None: {
    value: int;
    input value;
    checkIndex(value);
}
```

Expected output (for input `3`):
```
0
```

Expected output (for input `7`):
```
7
```

## Language change

| | Before | After |
|---|---|---|
| `return` without a value | ❌ Always requires an expression | ✅ `return;` allowed in `None` functions |
| Early exit from `void` functions | ❌ Not possible | ✅ `return;` stops execution immediately |
| Type checking | ❌ N/A | ✅ Bare `return;` only valid when the function's return type is `None` |

## Type rules

| Constraint | Example violation |
|---|---|
| Bare `return;` is only valid in functions returning `None` | `def f()->int: { return; }` → error |
| `return expression;` still requires the expression's type to be promotable to the return type | unchanged from base compiler |

## MAPL output example

Given:
```
def checkIndex(index:int)->None: {
    if index < 5: {
        print 0,'\n';
        return;
    }
    print index,'\n';
}
```

Generated assembly (excerpt):
```asm
#line 1
 checkIndex:
    ' * Parameters
    ' * IntType index (offset 4)
    ' * Local variables
    enter  0

#line 2
    ' * If else
    push   bp
    pushi  4
    addi
    loadi
    pushi  5
    lti
    jz     label0

#line 3
    ' * Write
    pushi  0
    outi
#line 3
    ' * Write
    pushb  10
    outb

#line 4
    ' * Return
    ret    0, 0, 2
    jmp    label1
 label0:
 label1:

#line 6
    ' * Write
    push   bp
    pushi  4
    addi
    loadi
    outi
#line 6
    ' * Write
    pushb  10
    outb
    ret    0, 0, 2
```

Note that the bare `return;` compiles directly to a `ret` instruction with no preceding `value[[expression]]`/`convert` pair, since there is no value to evaluate or convert.

---

## Language specification

### 1. Abstract grammar

`Return` now takes an optional expression instead of a mandatory one:

```
Statements (Abstract Grammar)
    (06)  Return : statement -> expression?
```

### 2. Semantic rules (attribute grammar)

```
Statements (Semantic Rules)
    (06)  if (expression != null):
              expression.type.mustPromoteTo(statement.returnType)
          else:
              statement.returnType must be VoidType
```

If the expression is present, the existing promotion rule applies as before. If it is absent, the function's declared return type must be `VoidType` — otherwise an error is raised.

### 3. Grammar rule (`Pmm.g4`)

The `return` rule is updated to make the expression optional using a local variable:

```antlr
statement returns [ List<Statement> ast = new ArrayList<>() ]
        locals [ List<Statement> statements = new ArrayList<>(),
                 List<Expression> expressions = new ArrayList<>(),
                 Expression returnExpression = null ]
        ...
        // Return
        | RET='return' (exp=expression { $returnExpression = $exp.ast; })? ';'
            { $ast.add(new Return($RET.line, $RET.getCharPositionInLine()+1, $returnExpression)); }
        ...
        ;
```

### 4. Code generation template

```
execute[[ Return : statement -> expression? ]](funcDef) =
    if (expression != null) {
        value[[ expression ]]
        cg.convert(expression.type, funcDef.type.returnType)
    }
    <ret>   funcDef.type.returnType.numberOfBytes <,>
            funcDef.bytesLocalSum <,>
            funcDef.type.bytesParamSum
```

---

## Full code changes

### `AbstractVisitor.visit(Return)`

```java
@Override
public TR visit(Return returnStatement, TP parameter) {
    if (returnStatement.getExpression() != null) {
        returnStatement.getExpression().accept(this, parameter);
    }
    return null;
}
```

### `TypeCheckingVisitor.visit(Return)`

```java
@Override
public Void visit(Return returnStatement, Type parameter) {
    if (returnStatement.getExpression() != null) {
        returnStatement.getExpression().accept(this, parameter);
        returnStatement.getExpression().getType().mustPromoteTo(parameter, returnStatement);
    } else if (!(parameter instanceof VoidType)) {
        new ErrorType("The function has no return type and expects: " + parameter.toString(), returnStatement);
    }
    return null;
}
```

### `ExecuteCGVisitor.visit(Return)`

```java
@Override
public Void visit(Return returnStatement, FuncDefinition funcDef) {
    /*
     * execute [[Return : statement -> expression?]](funcDef) =
     *      if (expression != null) {
     *          value[[expression]]
     *          cg.convert(expression.type, funcDef.type.returnType)
     *      }
     *      <ret>   funcDef.type.returnType.numberOfBytes <,>
     *              funcDef.bytesLocalSum <,>
     *              funcDef.type.bytesParamSum
     */
    cg.line(returnStatement.getLine());
    cg.comment("Return");

    FunctionType fType = (FunctionType) funcDef.getType();
    if (returnStatement.getExpression() != null) {
        returnStatement.getExpression().accept(valueV, null);
        cg.convert(returnStatement.getExpression().getType(), fType.getReturnType());
    }

    int bytesLocalSum = getBytesLocalSum(funcDef);
    int bytesParamSum = getBytesParamSum(fType);

    cg.ret(fType.getReturnType().numberOfBytes(),
            bytesLocalSum,
            bytesParamSum);

    return null;
}
```
