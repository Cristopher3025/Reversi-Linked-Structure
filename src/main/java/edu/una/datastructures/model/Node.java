package edu.una.datastructures.model;

/**
 * Represents a single cell in the Reversi board. Each node maintains references
 * to its eight neighbors.
 */
public class Node {

    private PieceColor piece;

    private Node north;
    private Node south;
    private Node east;
    private Node west;

    private Node northEast;
    private Node northWest;
    private Node southEast;
    private Node southWest;

    public Node() {
        this.piece = PieceColor.EMPTY;
    }

    // --- Piece state ---
    public PieceColor getPiece() {
        return piece;
    }

    public void setPiece(PieceColor piece) {
        this.piece = piece;
    }

    // --- Neighbors ---
    public Node getNorth() {
        return north;
    }

    public void setNorth(Node north) {
        this.north = north;
    }

    public Node getSouth() {
        return south;
    }

    public void setSouth(Node south) {
        this.south = south;
    }

    public Node getEast() {
        return east;
    }

    public void setEast(Node east) {
        this.east = east;
    }

    public Node getWest() {
        return west;
    }

    public void setWest(Node west) {
        this.west = west;
    }

    public Node getNorthEast() {
        return northEast;
    }

    public void setNorthEast(Node northEast) {
        this.northEast = northEast;
    }

    public Node getNorthWest() {
        return northWest;
    }

    public void setNorthWest(Node northWest) {
        this.northWest = northWest;
    }

    public Node getSouthEast() {
        return southEast;
    }

    public void setSouthEast(Node southEast) {
        this.southEast = southEast;
    }

    public Node getSouthWest() {
        return southWest;
    }

    public void setSouthWest(Node southWest) {
        this.southWest = southWest;
    }
}
