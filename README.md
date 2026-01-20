# Reversi – Linked Data Structure

This project was developed for the Data Structures course.
The Reversi game is implemented using a fully linked node-based board structure,
without using arrays or matrices to represent the board.

---

## 1. Data Structure Used

The board is modeled as a network of linked nodes.
Each board cell is represented by a `Node` object that maintains references
to its eight neighboring nodes:

- North
- South
- East
- West
- North-East
- North-West
- South-East
- South-West

This design allows traversing the board in any direction without using indexes,
applying linked data structure concepts directly.

The entry point to the board is the top-left node (`topLeft`),
from which all traversals are performed.

---

## 2. Description of Main Classes

### `Node`
Represents a single board cell.
It stores:
- The piece color (`BLACK`, `WHITE`, `EMPTY`)
- References to its eight neighboring nodes
- A flag indicating whether the cell is a valid move (used by the UI)

---

### `Board`
Builds the 8x8 board by linking nodes horizontally, vertically, and diagonally.
It also initializes the four center pieces according to Reversi rules.

---

### `GameLogic`
Contains the game rules:
- Move validation
- Directional traversal in all eight directions
- Piece flipping logic only when a valid closing piece is found

All logic is implemented by traversing the linked nodes.

---

### `GameController`
Controls the game flow:
- Current player management
- Turn switching
- Game over detection
- Piece counting
- Human vs Human mode
- Human vs Bot mode (simple pseudo-random selection)

---

### `App`
Implements the graphical interface using JavaFX.
It is responsible only for:
- Rendering the board
- Delegating actions to the controller
- Displaying game over messages

The UI does not contain game logic.

---

## 3. Execution Instructions

1. Open the project in Apache NetBeans.
2. Make sure Java JDK and JavaFX are properly configured.
3. Run the `App` class.
4. Select a game mode:
   - Human vs Human
   - Human vs Bot
5. Play Reversi following the standard rules.

---

## 4. Execution Evidence

The `evidences` folder contains screenshots showing:
- Game mode selection
- Board with valid move indicators
- Gameplay against the bot
- Game completion with the final result
