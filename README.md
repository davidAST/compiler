# P-- Compiler — `feature/switch` branch
> Extends the base compiler with support for the **`switch` statement**.

## What this branch adds

P-- now supports `switch` statements, allowing branching based on the value of an integer expression. Each `case` is followed by a body and a `break` to exit the switch.

```
def main()->None: {
    a: int;
    input a;

    switch a {
        case 1:
            print 10;
            break;
        case 2:
            print 20;
            break;
        case 3:
            print 30;
            break;
    }
}
```

Expected output (for `a = 2`):
```
20
```

## Language change

| | Before | After |
|---|---|---|
| Value-based branching | Manual `if/else if` chain | ✅ `switch a { case N: ... break; }` |
| Fall-through prevention | — | ✅ `break` jumps to end of switch |
| No match | — | ✅ Skips all cases silently |

## Type rules

The switch expression must be of type `int` or `char`. Each `case` value must be an integer literal.

## MAPL output example

Given:
```
def main()->None: {
    a: int;
    input a;

    switch a {
        case 1:
            print 10;
            break;
        case 2:
            print 20;
            break;
        case 3:
            print 30;
            break;
    }
}
```

Generated assembly:
```asm
#source "switch-in.txt"
' Invocation to the main function
call main
halt

#line 1
 main:
    ' * Parameters
    ' * Local variables
    ' * IntType a (offset -2)
    enter  2

#line 3
    ' * Read
    push   bp
    pushi  -2
    addi
    ini
    storei

#line 5
    ' * Switch
    push   bp
    pushi  -2
    addi
    loadi
    pushi  1
    eqi
    jz     label1      ; if a != 1, skip to next case

#line 7
    ' * Write
    pushi  10
    outi
    jmp    label0       ; break → jump to end of switch

 label1:
    push   bp
    pushi  -2
    addi
    loadi
    pushi  2
    eqi
    jz     label2      ; if a != 2, skip to next case

#line 10
    ' * Write
    pushi  20
    outi
    jmp    label0       ; break → jump to end of switch

 label2:
    push   bp
    pushi  -2
    addi
    loadi
    pushi  3
    eqi
    jz     label3      ; if a != 3, skip to next case

#line 13
    ' * Write
    pushi  30
    outi
    jmp    label0       ; break → jump to end of switch

 label3:               ; no match falls through here

 label0:               ; end of switch
    ret    0, 2, 0
```

## Code generation structure

Each `switch` compiles into the following label pattern:

```
    <value of expression>
    pushi  <case1 value>
    eqi
    jz     labelN+1        ← skip if not equal
    <body of case 1>
    jmp    label0           ← break: jump to end
 labelN+1:
    <value of expression>
    pushi  <case2 value>
    eqi
    jz     labelN+2
    <body of case 2>
    jmp    label0           ← break: jump to end
 labelN+2:
    ...
 label0:                    ← end of switch
```

The switch expression is re-evaluated for **each case**. A `break` compiles to an unconditional `jmp label0` where `label0` is the exit label of the switch. If no case matches, execution falls through all checks and continues after `label0`.
