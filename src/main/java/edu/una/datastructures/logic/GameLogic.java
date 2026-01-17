package edu.una.datastructures.logic;

import edu.una.datastructures.model.Node;
import edu.una.datastructures.model.PieceColor;

/**
 * Contains the game rules and validation logic for Reversi. Traversals are
 * performed directly over the linked node structure.
 */
public class GameLogic {

    public boolean isValidMove(Node start, PieceColor currentPlayer) {

        if (start == null) {
            return false;
        }

        if (start.getPiece() != PieceColor.EMPTY) {
            return false;
        }

        PieceColor opponent = getOpponent(currentPlayer);

        return checkDirection(start.getNorth(), currentPlayer, opponent)
                || checkDirection(start.getSouth(), currentPlayer, opponent)
                || checkDirection(start.getEast(), currentPlayer, opponent)
                || checkDirection(start.getWest(), currentPlayer, opponent)
                || checkDirection(start.getNorthEast(), currentPlayer, opponent)
                || checkDirection(start.getNorthWest(), currentPlayer, opponent)
                || checkDirection(start.getSouthEast(), currentPlayer, opponent)
                || checkDirection(start.getSouthWest(), currentPlayer, opponent);
    }

    private boolean checkDirection(Node current, PieceColor player, PieceColor opponent) {

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

            current = getNextInSameDirection(current);
        }

        return false;
    }

    private Node getNextInSameDirection(Node current) {

        // Determine direction based on existing links
        if (current.getNorth() != null && current.getNorth().getSouth() == current) {
            return current.getNorth();
        }
        if (current.getSouth() != null && current.getSouth().getNorth() == current) {
            return current.getSouth();
        }
        if (current.getEast() != null && current.getEast().getWest() == current) {
            return current.getEast();
        }
        if (current.getWest() != null && current.getWest().getEast() == current) {
            return current.getWest();
        }
        if (current.getNorthEast() != null && current.getNorthEast().getSouthWest() == current) {
            return current.getNorthEast();
        }
        if (current.getNorthWest() != null && current.getNorthWest().getSouthEast() == current) {
            return current.getNorthWest();
        }
        if (current.getSouthEast() != null && current.getSouthEast().getNorthWest() == current) {
            return current.getSouthEast();
        }
        if (current.getSouthWest() != null && current.getSouthWest().getNorthEast() == current) {
            return current.getSouthWest();
        }

        return null;
    }

    /**
     * Returns the opponent color.
     */
    private PieceColor getOpponent(PieceColor player) {
        return player == PieceColor.BLACK ? PieceColor.WHITE : PieceColor.BLACK;
    }
}
