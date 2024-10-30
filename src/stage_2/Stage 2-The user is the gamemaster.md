## Work on project. Stage 2/5:The user is the gamemaster

Project: [Simple Tic-Tac-Toe (Java)](https://hyperskill.org/projects/48)

## The user is the gamemaster

## Description

Our program should be able to display the grid at all stages of the game. Now we’re going to write a program that allows the user to enter a string representing the game state and correctly prints the 3x3 game grid based on this input. We’ll also add some boundaries around the game grid.

## Objectives

In this stage, you will write a program that:

1. Reads a string of 9 symbols from the input and displays them to the user in a 3x3 grid. The grid can contain only `X`, `O` and `_` symbols.
2. Outputs a line of dashes `---------` above and below the grid, adds a pipe `|`symbol to the beginning and end of each line of the grid, and adds a space between all characters in the grid. 

## Examples

The greater-than symbol followed by a space (`> `) represents the user input. Note that it's not part of the input.

**Example 1:**

```no-highlight
> O_OXXO_XX
---------
| O _ O |
| X X O |
| _ X X |
---------
```

**Example 2:** 

```no-highlight
> OXO__X_OX
---------
| O X O |
| _ _ X |
| _ O X |
---------
```

**Example 3:** 

```no-highlight
> _XO__X___
---------
| _ X O |
| _ _ X |
| _ _ _ |
---------
```