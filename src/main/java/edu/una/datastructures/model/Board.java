package edu.una.datastructures.model;

/**
 * Represents the Reversi game board implemented as a fully linked
 * two-dimensional structure of nodes.
 * <p>
 * The board does not use arrays or matrices. All traversals start
 * from the top-left node and proceed through node links.
 */
public class Board {

    /**
     * Reference to the top-left node of the board.
     * This node is the entry point for all traversals.
     */
    private Node topLeft;

    /**
     * Creates a new Reversi board.
     * Initializes the linked structure and places the initial pieces.
     */
    public Board() {
        initializeBoard();
        initializePieces();
    }

    /**
     * Returns the top-left node of the board.
     *
     * @return the top-left node
     */
    public Node getTopLeft() {
        return topLeft;
    }

    /**
     * Builds the 8x8 board structure by linking nodes horizontally,
     * vertically, and diagonally.
     */
    private void initializeBoard() {

        Node previousRowStart = null;

        for (int row = 0; row < 8; row++) {

            Node current = new Node();

            if (row == 0) {
                topLeft = current;
            }

            Node rowStart = current;

            if (previousRowStart != null) {
                linkVertical(previousRowStart, rowStart);
            }

            for (int col = 1; col < 8; col++) {
                Node next = new Node();

                current.setEast(next);
                next.setWest(current);

                if (previousRowStart != null) {
                    previousRowStart = previousRowStart.getEast();
                    linkVertical(previousRowStart, next);
                }

                current = next;
            }

            previousRowStart = rowStart;
        }
    }

    /**
     * Links two vertically adjacent nodes and establishes
     * their diagonal relationships when applicable.
     *
     * @param upper node in the row above
     * @param lower node in the current row
     */
    private void linkVertical(Node upper, Node lower) {

        upper.setSouth(lower);
        lower.setNorth(upper);

        if (upper.getWest() != null) {
            upper.getWest().setSouthEast(lower);
            lower.setNorthWest(upper.getWest());
        }

        if (upper.getEast() != null) {
            upper.getEast().setSouthWest(lower);
            lower.setNorthEast(upper.getEast());
        }
    }

    /**
     * Places the initial four pieces in the center of the board
     * following standard Reversi rules.
     */
    private void initializePieces() {

        Node center = topLeft;

        for (int i = 0; i < 3; i++) {
            center = center.getSouth();
        }

        for (int j = 0; j < 3; j++) {
            center = center.getEast();
        }

        center.setPiece(PieceColor.WHITE);
        center.getEast().setPiece(PieceColor.BLACK);
        center.getSouth().setPiece(PieceColor.BLACK);
        center.getSouth().getEast().setPiece(PieceColor.WHITE);
    }

    /**
     * Performs a basic verification of boundary links.
     * This method is intended only for debugging purposes.
     */
    public void checkLinks() {

        System.out.println("=== Board link verification ===");
        System.out.println("Top-left north is null: " + (topLeft.getNorth() == null));
        System.out.println("Top-left west is null: " + (topLeft.getWest() == null));

        Node node = topLeft;
        for (int i = 0; i < 7; i++) {
            node = node.getEast();
        }
        System.out.println("Top-right east is null: " + (node.getEast() == null));

        Node bottomLeft = topLeft;
        for (int i = 0; i < 7; i++) {
            bottomLeft = bottomLeft.getSouth();
        }
        System.out.println("Bottom-left south is null: " + (bottomLeft.getSouth() == null));

        System.out.println("================================");
    }
}
