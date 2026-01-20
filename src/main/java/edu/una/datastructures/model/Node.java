package edu.una.datastructures.model;

/**
 * Represents a single cell of the Reversi board.
 * <p>
 * Each node stores the current piece placed on that position and maintains
 * references to its eight adjacent neighbors, forming a fully linked grid
 * structure without using arrays or matrices.
 */
public class Node {

    /**
     * Current piece occupying this board position.
     */
    private PieceColor piece;

    /**
     * Cardinal neighbors.
     */
    private Node north;
    private Node south;
    private Node east;
    private Node west;

    /**
     * Diagonal neighbors.
     */
    private Node northEast;
    private Node northWest;
    private Node southEast;
    private Node southWest;

    /**
     * Indicates whether this node represents a valid move for the current
     * player.
     */
    private boolean validMove;

    /**
     * Creates an empty board cell.
     */
    public Node() {
        this.piece = PieceColor.EMPTY;
    }

    /**
     * Returns whether this node is currently marked as a valid move.
     *
     * @return true if the node represents a valid move
     */
    public boolean isValidMove() {
        return validMove;
    }

    /**
     * Marks or unmarks this node as a valid move.
     *
     * @param validMove true to mark as valid, false otherwise
     */
    public void setValidMove(boolean validMove) {
        this.validMove = validMove;
    }

    /**
     * Returns the piece currently placed on this node.
     *
     * @return the piece color of this node
     */
    public PieceColor getPiece() {
        return piece;
    }

    /**
     * Sets the piece placed on this node.
     *
     * @param piece the piece color to assign
     */
    public void setPiece(PieceColor piece) {
        this.piece = piece;
    }

    /**
     * @return the north neighbor of this node
     */
    public Node getNorth() {
        return north;
    }

    public void setNorth(Node north) {
        this.north = north;
    }

    /**
     * @return the south neighbor of this node
     */
    public Node getSouth() {
        return south;
    }

    public void setSouth(Node south) {
        this.south = south;
    }

    /**
     * @return the east neighbor of this node
     */
    public Node getEast() {
        return east;
    }

    public void setEast(Node east) {
        this.east = east;
    }

    /**
     * @return the west neighbor of this node
     */
    public Node getWest() {
        return west;
    }

    public void setWest(Node west) {
        this.west = west;
    }

    /**
     * @return the northeast neighbor of this node
     */
    public Node getNorthEast() {
        return northEast;
    }

    public void setNorthEast(Node northEast) {
        this.northEast = northEast;
    }

    /**
     * @return the northwest neighbor of this node
     */
    public Node getNorthWest() {
        return northWest;
    }

    public void setNorthWest(Node northWest) {
        this.northWest = northWest;
    }

    /**
     * @return the southeast neighbor of this node
     */
    public Node getSouthEast() {
        return southEast;
    }

    public void setSouthEast(Node southEast) {
        this.southEast = southEast;
    }

    /**
     * @return the southwest neighbor of this node
     */
    public Node getSouthWest() {
        return southWest;
    }

    public void setSouthWest(Node southWest) {
        this.southWest = southWest;
    }
}
