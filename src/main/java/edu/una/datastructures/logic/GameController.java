package edu.una.datastructures.logic;

import edu.una.datastructures.model.Board;
import edu.una.datastructures.model.Node;
import edu.una.datastructures.model.PieceColor;

/**
 * Controls the overall flow of a Reversi game.
 * <p>
 * This class manages turn switching, move execution, game mode selection,
 * and end-of-game conditions, delegating rule validation to {@link GameLogic}.
 */
public class GameController {

    /** Game board represented as a linked structure of nodes. */
    private final Board board;

    /** Core game logic used for validation and piece flipping. */
    private final GameLogic logic;

    /** Current player whose turn is active. */
    private PieceColor currentPlayer;

    /** Indicates whether the game is played against a bot. */
    private boolean playAgainstBot;

    /** Color assigned to the bot player. */
    private PieceColor botColor;

    /**
     * Creates a new game controller for the given board.
     * The game always starts with the black player.
     *
     * @param board the game board
     */
    public GameController(Board board) {
        this.board = board;
        this.logic = new GameLogic();
        this.currentPlayer = PieceColor.BLACK;

        ensureValidTurn();
        markValidMoves();
    }

    /**
     * Configures the game mode.
     *
     * @param playAgainstBot true to enable human vs bot mode
     * @param botColor the color assigned to the bot player
     */
    public void setGameMode(boolean playAgainstBot, PieceColor botColor) {
        this.playAgainstBot = playAgainstBot;
        this.botColor = botColor;
    }

    /**
     * Returns the player whose turn is currently active.
     *
     * @return the current player
     */
    public PieceColor getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Attempts to play a move on the specified node.
     *
     * @param node the selected board position
     * @return true if the move was successfully applied
     */
    public boolean playMove(Node node) {

        if (logic.isValidMove(node, currentPlayer)) {
            logic.applyMove(node, currentPlayer);
            switchTurn();
            ensureValidTurn();
            markValidMoves();
            return true;
        }

        return false;
    }

    /**
     * Switches the turn to the opposing player if a valid move exists.
     */
    private void switchTurn() {

        PieceColor opponent = getOpponent(currentPlayer);

        if (hasValidMove(opponent)) {
            currentPlayer = opponent;
        }
    }

    /**
     * Determines whether the given player has at least one valid move.
     *
     * @param player the player to check
     * @return true if at least one valid move exists
     */
    public boolean hasValidMove(PieceColor player) {

        Node rowStart = board.getTopLeft();

        while (rowStart != null) {

            Node current = rowStart;

            while (current != null) {

                if (logic.isValidMove(current, player)) {
                    return true;
                }

                current = current.getEast();
            }

            rowStart = rowStart.getSouth();
        }

        return false;
    }

    /**
     * Determines whether the game has reached a terminal state.
     *
     * @return true if neither player has a valid move
     */
    public boolean isGameOver() {
        return !hasValidMove(PieceColor.BLACK) && !hasValidMove(PieceColor.WHITE);
    }

    /**
     * Returns the opponent color for a given player.
     *
     * @param player the current player
     * @return the opposing player's color
     */
    private PieceColor getOpponent(PieceColor player) {
        return player == PieceColor.BLACK ? PieceColor.WHITE : PieceColor.BLACK;
    }

    /**
     * Returns the game board.
     *
     * @return the board instance
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Marks all valid moves for the current player on the board.
     */
    public void markValidMoves() {

        clearValidMoves();

        Node rowStart = board.getTopLeft();

        while (rowStart != null) {
            Node current = rowStart;

            while (current != null) {
                if (logic.isValidMove(current, currentPlayer)) {
                    current.setValidMove(true);
                }
                current = current.getEast();
            }
            rowStart = rowStart.getSouth();
        }
    }

    /**
     * Clears all previously marked valid moves from the board.
     */
    private void clearValidMoves() {

        Node rowStart = board.getTopLeft();

        while (rowStart != null) {
            Node current = rowStart;

            while (current != null) {
                current.setValidMove(false);
                current = current.getEast();
            }
            rowStart = rowStart.getSouth();
        }
    }

    /**
     * Ensures that the current player has a valid move.
     * If not, the turn is passed to the opponent when possible.
     */
    private void ensureValidTurn() {

        if (!hasValidMove(currentPlayer)) {
            PieceColor opponent = getOpponent(currentPlayer);

            if (hasValidMove(opponent)) {
                currentPlayer = opponent;
            }
        }
    }

    /**
     * Executes a move for the bot player when applicable.
     * The bot selects the first available valid move.
     */
    public void playBotMoveIfNeeded() {

        if (!playAgainstBot || currentPlayer != botColor) {
            return;
        }

        Node rowStart = board.getTopLeft();

        while (rowStart != null) {
            Node current = rowStart;

            while (current != null) {
                if (logic.isValidMove(current, currentPlayer)) {
                    playMove(current);
                    return;
                }
                current = current.getEast();
            }
            rowStart = rowStart.getSouth();
        }
    }

    /**
     * Counts the number of pieces of a given color on the board.
     *
     * @param color the piece color to count
     * @return the number of pieces of the given color
     */
    public int countPieces(PieceColor color) {

        int count = 0;
        Node rowStart = board.getTopLeft();

        while (rowStart != null) {
            Node current = rowStart;

            while (current != null) {
                if (current.getPiece() == color) {
                    count++;
                }
                current = current.getEast();
            }
            rowStart = rowStart.getSouth();
        }

        return count;
    }

    /**
     * Returns a textual description of the game result.
     *
     * @return a string indicating the winner or a draw
     */
    public String getWinnerText() {

        int black = countPieces(PieceColor.BLACK);
        int white = countPieces(PieceColor.WHITE);

        if (black > white) {
            return "Black wins (" + black + " - " + white + ")";
        }

        if (white > black) {
            return "White wins (" + white + " - " + black + ")";
        }

        return "Draw (" + black + " - " + white + ")";
    }
}
