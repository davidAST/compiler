# P-- Compiler — `feature/pass-by-reference` branch
> Extends the base compiler with support for **pass-by-reference parameters**, allowing functions to modify the caller's variables.

## What this branch adds

P-- now supports declaring function parameters as references using the `&` symbol. Reference parameters receive the address of the caller's variable instead of a copy of its value, so modifications inside the function are visible to the caller after the call returns.

```
def f(r & :int)->int: {
    r = 3;
    return 0;
}

def main()->None: {
    r: int;
    r = 5;
    print r;
    f(r);
    print r;
}
```

Expected output:
```
5
3
```

## Language change

| | Before | After |
|---|---|---|
| Parameter passing | ❌ Always by value | ✅ `&` marks a parameter as by reference |
| Argument requirements | ❌ Any expression | ✅ Reference arguments must be an LValue |
| Modifying caller state | ❌ Not possible through parameters | ✅ Reference parameters propagate changes back to the caller |

## Type rules

| Constraint | Example violation |
|---|---|
| The argument passed to a reference parameter must be an LValue | `f(5)` where `f(r &: int)` → error |
| The argument type must match the reference parameter's type exactly | `f(c)` where `c: char` and `f(r &: int)` → error |

## MAPL output example

Given:
```
def f(r & :int)->int: {
    r = 3;
    return 0;
}
def main()->None: {
    r: int;
    r = 5;
    print r;
    f(r);
    print r;
}
```

Generated assembly:
```asm
#source "ref-in.txt"
' Invocation to the main function
call main
halt

#line 1
 f:
    ' * Parameters
    ' * IntType r (offset 4)
    ' * Local variables
    enter  0

#line 2
    ' * Assignment
    push   bp
    pushi  4
    addi
    loadi
    pushi  3
    storei

#line 3
    ' * Return
    pushi  0
    ret    2, 0, 2

#line 7
 main:
    ' * Parameters
    ' * Local variables
    ' * IntType r (offset -2)
    enter  2

#line 9
    ' * Assignment
    push   bp
    pushi  -2
    addi
    pushi  5
    storei

#line 10
    ' * Write
    push   bp
    pushi  -2
    addi
    loadi
    outi

#line 11
    push   bp
    pushi  -2
    addi
call f
    popi

#line 12
    ' * Write
    push   bp
    pushi  -2
    addi
    loadi
    outi
    ret    0, 2, 0
```

Note that calling `f(r)` pushes the *address* of `r` (computed with `push bp; pushi -2; addi`) rather than its value, and inside `f` the parameter `r` is always loaded through an extra indirection (`loadi`) before use, since its offset holds an address rather than a value.

## Code generation structure

Reference parameters are always allocated 2 bytes (the size of an address) regardless of their declared type's actual size, since only the address is stored in the activation record.

**Calling a function with a reference argument:**
```
value[[ FuncInvocation: expression1 -> expression2 expression* ]]() =
    for (int i = 0; i < expression*.size; i++) {
        if (!expression2.type.params[i].isReference())
            value[[expression*[i]]]
            cg.convert(expression*[i].type, expression2.type.params[i].type)
        else
            address[[expression*[i]]]
    }
    <call> expression2.name
```

**Accessing a reference variable inside the function:**
```
address[[ Variable: expression -> ID ]]() =
    <push> bp
    <pushi> expression.definition.offset
    <addi>
    if (expression.definition.isReference)
        <load> expression.type.suffix()
```

The first three instructions compute the slot holding the address (not the value); the conditional `load` then dereferences it to obtain the actual address of the caller's variable, which subsequent `load`/`store` operations use as usual.

---

## Language specification

### 1. Abstract grammar

`VarDefinition` gains a new attribute marking whether the parameter is passed by reference:

```
Definitions (Abstract Grammar)
    (02) VarDefinition: definition -> ID type [reference]
```

### 2. Semantic rules

A new check is added when type-checking a function invocation, for each argument matched against a reference parameter:

```
Expressions (Semantic Rules)
    (09)    expression1.type = expression2.type.parenthesis(expression*.map(e->e.type))
            expression*.forEach((exp, i) -> {
                if (expression2.type.params[i].isReference) {
                    exp.lvalue.mustBeTrue()
                    exp.type.mustEqual(expression2.type.params[i].type)
                }
            })
```

### 3. Offset calculation

Reference parameters always occupy 2 bytes in the activation record (the size of an address), instead of their declared type's size:

```
Types (Semantic Rule)
    (07)    int fieldBytesSum = 0;
            for (int count = ft.getParams().size(); count >= 0; count--) {
                VarDefinition varDef = ft.getParams().get(count);
                if (!varDef.isReference())
                    fieldBytesSum += varDef.getType().numberOfBytes();
                else
                    fieldBytesSum += 2;
                varDef.setOffset(-fieldBytesSum);
            }
```

### 4. Grammar rule

A new `variableParam` rule replaces `variables` inside `params`, allowing each parameter to optionally be marked with `&`:

```antlr
variableParam returns [List<VarDefinition> ast = new ArrayList<>()]
    :  ID1=ID  ':' type
       {$ast.add(new VarDefinition($ID1.line, $ID1.getCharPositionInLine() + 1, $ID1.text, $type.ast, false));}
    |  ID1=ID '&' ':' type
       {$ast.add(new VarDefinition($ID1.line, $ID1.getCharPositionInLine() + 1, $ID1.text, $type.ast, true));}
    ;
```
