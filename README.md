# P-- Compiler — `feature/for-loop` branch

> Extends the base compiler with support for the **`for` loop statement**.

## What this branch adds

P-- now supports `for` loops, allowing iteration with an initializer, condition, and update expression in a single construct. This is equivalent to writing a `while` loop manually but with a more compact and readable syntax.

```
i: int;

def main()->None: {
    for (i = 1; i <= 10; i = i + 1) {
        print i, '\n';
    }

    print '\n';

    for (i = 10; i >= 0; i = i - 1) {
        print i, '\n';
    }
}
```

Expected output:

```
1
2
3
4
5
6
7
8
9
10

10
9
8
7
6
5
4
3
2
1
0
```

## Language change

| | Before | After |
|---|---|---|
| Count-up loop | Manual `while` with init + condition + update | ✅ `for (init; cond; update) { ... }` |
| Count-down loop | Manual `while` with init + condition + update | ✅ `for (init; cond; update) { ... }` |
| Arbitrary step | ❌ Verbose | ✅ Any assignment as update expression |

## MAPL output example

Given:

```
i: int;

def main()->None: {
    for (i = 1; i <= 10; i = i + 1) {
        print i, '\n';
    }

    print '\n';

    for (i = 10; i >= 0; i = i - 1) {
        print i, '\n';
    }
}
```

Generated assembly:

```asm
#source "for-loop-in.txt"
    ' * IntType i (offset 0)
' Invocation to the main function
call main
halt
#line 3
 main:
    ' * Parameters
    ' * Local variables
    enter  0
#line 4
    ' * Assignment
    pusha  0
    pushi  1
    storei
 label0:
    pusha  0
    loadi
    pushi  10
    lei
    jz     label1
#line 5
    ' * Write
    pusha  0
    loadi
    outi
#line 5
    ' * Write
    pushb  10
    outb
#line 4
    ' * Assignment (update: i = i + 1)
    pusha  0
    pusha  0
    loadi
    pushi  1
    addi
    storei
    jmp    label0
 label1:
#line 8
    ' * Write
    pushb  10
    outb
#line 10
    ' * Assignment
    pusha  0
    pushi  10
    storei
 label2:
    pusha  0
    loadi
    pushi  0
    gei
    jz     label3
#line 11
    ' * Write
    pusha  0
    loadi
    outi
#line 11
    ' * Write
    pushb  10
    outb
#line 10
    ' * Assignment (update: i = i - 1)
    pusha  0
    pusha  0
    loadi
    pushi  1
    subi
    storei
    jmp    label2
 label3:
    ret    0, 0, 0
```

## Code generation structure

Each `for` loop compiles into the following label pattern:

```
    <init>
 labelN:          ← loop start
    <condition>
    jz  labelN+1  ← exit if condition is false
    <body>
    <update>
    jmp labelN    ← back to condition check
 labelN+1:        ← loop exit
```

The initializer is emitted once before `labelN`. The condition is re-evaluated on every iteration at `labelN`. The update expression is emitted at the end of the body, before the unconditional `jmp` back.
