package com.chess.pieces;

public class Knight extends Piece {
    private int x;
    private int y;

    public Knight(String color, int x, int y) {
        super(color, x, y);
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    @Override
    public boolean isValidMove(int targetX, int targetY) {
        int deltaX = Math.abs(targetX - this.getX());
        int deltaY = Math.abs(targetY - this.getY());
        return (deltaX == 2 && deltaY == 1) || (deltaX == 1 && deltaY == 2);
    }
}