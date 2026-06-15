# P-- Compiler — `feature/infer-variables` branch
> Extends the base compiler with support for **type inference via `var`**, allowing variables to be declared without an explicit type that is then inferred from the first assignment.

## What this branch adds

P-- now supports the `var` keyword as a type placeholder. Variables declared with `var` have their type inferred automatically from the first assignment to that variable.

```
def main()->None: {
    a, b, c: var;
    a = 0;
    b = 'a';
    c = 3.2;
    print a, '\n';
    print b, '\n';
    print c, '\n';
}
```

Expected output:
```
0
a
3.2
```

## Language change

| | Before | After |
|---|---|---|
| Variable declaration | ❌ Explicit type required: `a: int;` | ✅ Type can be inferred: `a: var;` |
| Type inference | ❌ Not supported | ✅ Type inferred from first assignment |
| Mixed types | ❌ One declaration per type | ✅ `a, b, c: var;` with different types |

## Type rules

| Constraint | Example violation |
|---|---|
| A `var` variable must be assigned before use | `a: var; print a;` → error (type still `VarType`) |
| The inferred type is the type of the right-hand side of the first assignment | `a: var; a = 0;` → `a` becomes `int` |

## MAPL output example

Given:
```
def main()->None: {
    a, b, c: var;
    a = 0;
    b = 'a';
    c = 3.2;
    ...
}
```

Generated assembly:
```asm
#source "var-in.txt"
' Invocation to the main function
call main
halt

#line 1
 main:
    ' * Parameters
    ' * Local variables
    ' * IntType a (offset -2)
    ' * CharType b (offset -3)
    ' * RealType c (offset -7)
    enter  7

#line 3
    ' * Assignment
    push   bp
    pushi  -2
    addi
    pushi  0
    storei

#line 4
    ' * Assignment
    push   bp
    pushi  -3
    addi
    pushb  97
    storeb

#line 5
    ' * Assignment
    push   bp
    pushi  -7
    addi
    pushf  3.2
    storef
    ...
    ret    0, 7, 0
```

Note that the generated assembly is identical to what would be produced with explicit types — `var` is resolved entirely at compile time during type checking, leaving no trace in the output.

---

## Language specification

### 1. Abstract grammar

A new type is added:

```
Types (Abstract Grammar)
    (08)  VarType : type ->
```

`VarType` is a singleton with no attributes, representing "type not yet inferred".

### 2. Semantic rules (attribute grammar)

The following rule is added to the Assignment statement:

```
Statements (Semantic Rules)
    (03)  if (expression1.type == VarType)
              expression1.definition.type = expression2.type
          expression2.type.mustPromoteTo(expression1.type)
```

When the left-hand side has type `VarType`, its `VarDefinition` is updated with the inferred type before the promotion check is performed.

### 3. Grammar rule

`var` is added as a new keyword in the `type` rule:

```antlr
type returns [Type ast]
    : 'var'  {$ast = VarType.getInstance();}
    | 'char' {$ast = CharType.getInstance();}
    | 'int'  {$ast = IntType.getInstance();}
    ...
```

### 4. Implementation notes

- `VarType` is a singleton that extends `AbstractType`. All type operations inherited from `AbstractType` return `ErrorType`, since `VarType` has no valid operations until it is resolved.
- `numberOfBytes()` throws `IllegalStateException` since the size is unknown until the type is inferred.
- The inference happens in `TypeCheckingVisitor.visit(Assignment)`: when the left side has `VarType`, the `VarDefinition.setType()` is called with the right-hand side type, and the left side is re-visited so its cached type is updated.
- The offset calculation works correctly because all `var` variables are declared at the top of the function body, and their sizes are resolved during type checking before code generation runs.
