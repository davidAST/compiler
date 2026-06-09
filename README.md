# P-- Compiler — `feature/xor` branch
> Extends the base compiler with support for the **`^` (XOR) operator**.

## What this branch adds

P-- now supports the bitwise XOR operator (`^`) for `int` and `char` types. Since the target machine (MAPL) has no native `xor` instruction, it is compiled using the logical identity:

```
a ^ b  =  (a || b) && !(a && b)
```

```
def main()->None: {
    a: int;
    b: int;
    a = 1;
    b = 0;
    print a ^ b;  # Prints 1
    print b ^ 0;  # Prints 0
    print a ^ a;  # Prints 0
    print 0 ^ 1;  # Prints 1
    # print 0 ^ x;  -> Type error: XOR not supported for real types
}
```

Expected output:
```
1
0
0
1
```

## Language change

| | Before | After |
|---|---|---|
| Bitwise XOR | ❌ Not supported | ✅ `a ^ b` for `int` and `char` |
| Type error on `real` | ❌ Not supported | ✅ Compile-time error |
| Operator precedence | — | Between comparison and `&&` |

## Operator precedence

```
*, /, %
+, -
>, >=, <, <=, !=, ==
^                       ← here
&&
||
```

## Type rules

| Left | Right | Result |
|------|-------|--------|
| `int` | `int` | `int` |
| `int` | `char` | `int` |
| `char` | `char` | `int` |
| `char` | `int` | `int` |
| any | `double` | ❌ Type error |
| `double` | any | ❌ Type error |

## MAPL output example

Given:
```
def main()->None: {
    a: int;
    b: int;
    a = 1;
    b = 0;
    print a ^ b;
}
```

Generated assembly:
```asm
#source "xor-in.txt"
' Invocation to the main function
call main
halt

#line 1
 main:
    ' * Parameters
    ' * Local variables
    ' * IntType a (offset -2)
    ' * IntType b (offset -4)
    enter  8

#line 3
    ' * Assignment
    push   bp
    pushi  -2
    addi
    pushi  1
    storei

#line 4
    ' * Assignment
    push   bp
    pushi  -4
    addi
    pushi  0
    storei

#line 5
    ' * Write (a ^ b)
    push   bp
    pushi  -2
    addi
    loadi
    push   bp
    pushi  -4
    addi
    loadi
    or                  ; (a || b)
    push   bp
    pushi  -2
    addi
    loadi
    push   bp
    pushi  -4
    addi
    loadi
    and                 ; (a && b)
    not                 ; !(a && b)
    and                 ; (a || b) && !(a && b)
    outi
    ret    0, 8, 0
```

## Code generation structure

Each `^` expression compiles into the following pattern:

```
    <value of left>     ← push left operand
    <value of right>    ← push right operand
    or                  ← (left || right)
    <value of left>     ← push left operand again
    <value of right>    ← push right operand again
    and                 ← (left && right)
    not                 ← !(left && right)
    and                 ← (left || right) && !(left && right)
```

Both operands are evaluated **twice**: once for the `or` part and once for the `and` part, since MAPL has no native `xor` instruction.
