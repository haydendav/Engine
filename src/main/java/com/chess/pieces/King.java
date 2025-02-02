package com.chess.pieces;

public class King extends Piece {
    private int x;
    private int y;

    public King(String color, int x, int y) {
        super(color, x, y);

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean isValidMove(int newX, int newY) {
        int deltaX = Math.abs(newX - this.getX());
        int deltaY = Math.abs(newY - this.getY());
        return (deltaX <= 1 && deltaY <= 1);
    }
}