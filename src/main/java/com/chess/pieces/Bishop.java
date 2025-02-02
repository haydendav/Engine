package com.chess.pieces;

public class Bishop extends Piece {
    private int x;
    private int y; 

    public Bishop(String color, int x, int y) {
        super(color, x, y);
        this.x = x;
        this.y = y;
    }

    private int getX() {
        return x;
    }

    private int getY() {
        return y;
    }

    @Override
    public boolean isValidMove(int newX, int newY) {
        int deltaX = Math.abs(newX - this.getX());
        int deltaY = Math.abs(newY - this.getY());
        return deltaX == deltaY; // Bishops move diagonally
    }
}