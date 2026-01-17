package edu.una.datastructures.model;


public class Board {

    // Top-left node of the board (starting point for all traversals)
    private Node topLeft;

    public Board() {
        initializeBoard();
        initializePieces();
    }

    public Node getTopLeft() {
        return topLeft;
    }

   
    private void initializeBoard() {

        Node previousRowStart = null;

        for (int row = 0; row < 8; row++) {

            // Create first node of the current row
            Node current = new Node();

            if (row == 0) {
                topLeft = current;
            }

            Node rowStart = current;

            // Link with the row above
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
     * Places the initial four pieces in the center of the Reversi board.
     */
    private void initializePieces() {

        // Move to (3,3)
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
