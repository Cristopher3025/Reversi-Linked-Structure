package edu.una.datastructures.logic;

import edu.una.datastructures.model.Node;
import edu.una.datastructures.model.PieceColor;

/**
 * Contains the core game rules and validation logic for Reversi.
 * <p>
 * All rule checks and piece transformations are performed by traversing
 * the linked node structure directly, without using arrays or matrices.
 */
public class GameLogic {

    /**
     * Direction constants used to preserve traversal direction
     * during validation and piece flipping.
     */
    private static final int NORTH = 0;
    private static final int SOUTH = 1;
    private static final int EAST  = 2;
    private static final int WEST  = 3;
    private static final int NE    = 4;
    private static final int NW    = 5;
    private static final int SE    = 6;
    private static final int SW    = 7;

    /**
     * Determines whether placing a piece on the given node
     * constitutes a valid move for the current player.
     *
     * @param start the node where the move is attempted
     * @param currentPlayer the player attempting the move
     * @return true if the move is valid according to Reversi rules
     */
    public boolean isValidMove(Node start, PieceColor currentPlayer) {

        if (start == null || start.getPiece() != PieceColor.EMPTY) {
            return false;
        }

        PieceColor opponent = getOpponent(currentPlayer);

        return checkDirection(start.getNorth(), currentPlayer, opponent, NORTH)
            || checkDirection(start.getSouth(), currentPlayer, opponent, SOUTH)
            || checkDirection(start.getEast(), currentPlayer, opponent, EAST)
            || checkDirection(start.getWest(), currentPlayer, opponent, WEST)
            || checkDirection(start.getNorthEast(), currentPlayer, opponent, NE)
            || checkDirection(start.getNorthWest(), currentPlayer, opponent, NW)
            || checkDirection(start.getSouthEast(), currentPlayer, opponent, SE)
            || checkDirection(start.getSouthWest(), currentPlayer, opponent, SW);
    }

    /**
     * Checks whether a move is valid in a specific direction.
     * A valid direction must contain at least one opponent piece
     * followed by a closing piece of the current player.
     *
     * @param current the first node in the selected direction
     * @param player the current player
     * @param opponent the opposing player
     * @param direction the direction of traversal
     * @return true if the direction satisfies capture rules
     */
    private boolean checkDirection(Node current,
                                   PieceColor player,
                                   PieceColor opponent,
                                   int direction) {

        if (current == null || current.getPiece() != opponent) {
            return false;
        }

        while (current != null) {

            if (current.getPiece() == PieceColor.EMPTY) {
                return false;
            }

            if (current.getPiece() == player) {
                return true;
            }

            current = advance(current, direction);
        }

        return false;
    }

    /**
     * Advances to the next node in the given traversal direction.
     *
     * @param node the current node
     * @param direction the direction of movement
     * @return the adjacent node in the given direction, or null if none exists
     */
    private Node advance(Node node, int direction) {

        switch (direction) {
            case NORTH: return node.getNorth();
            case SOUTH: return node.getSouth();
            case EAST:  return node.getEast();
            case WEST:  return node.getWest();
            case NE:    return node.getNorthEast();
            case NW:    return node.getNorthWest();
            case SE:    return node.getSouthEast();
            case SW:    return node.getSouthWest();
            default:    return null;
        }
    }

    /**
     * Applies a valid move to the board, placing the piece
     * and flipping all affected opponent pieces.
     *
     * @param start the node where the piece is placed
     * @param currentPlayer the player performing the move
     */
    public void applyMove(Node start, PieceColor currentPlayer) {

        if (!isValidMove(start, currentPlayer)) {
            return;
        }

        PieceColor opponent = getOpponent(currentPlayer);
        start.setPiece(currentPlayer);

        flipDirection(start.getNorth(), currentPlayer, opponent, NORTH);
        flipDirection(start.getSouth(), currentPlayer, opponent, SOUTH);
        flipDirection(start.getEast(), currentPlayer, opponent, EAST);
        flipDirection(start.getWest(), currentPlayer, opponent, WEST);
        flipDirection(start.getNorthEast(), currentPlayer, opponent, NE);
        flipDirection(start.getNorthWest(), currentPlayer, opponent, NW);
        flipDirection(start.getSouthEast(), currentPlayer, opponent, SE);
        flipDirection(start.getSouthWest(), currentPlayer, opponent, SW);
    }

    /**
     * Flips opponent pieces in a specific direction
     * once a valid closing piece is detected.
     *
     * @param current the first node in the selected direction
     * @param player the current player
     * @param opponent the opposing player
     * @param direction the direction of traversal
     */
    private void flipDirection(Node current,
                               PieceColor player,
                               PieceColor opponent,
                               int direction) {

        if (current == null || current.getPiece() != opponent) {
            return;
        }

        Node runner = current;

        while (runner != null && runner.getPiece() == opponent) {
            runner = advance(runner, direction);
        }

        if (runner == null || runner.getPiece() != player) {
            return;
        }

        while (current != runner) {
            current.setPiece(player);
            current = advance(current, direction);
        }
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
}
