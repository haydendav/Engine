package com.chess.pieces;

public class Pawn extends Piece {
    private String color;
    private int x;
    private int y;

    public Pawn(String color, int x, int y) {
        super(color, x, y);
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public String getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean isValidMove(int targetX, int targetY) {
        int direction = getColor().equals("white") ? 1 : -1;
        int startRow = getColor().equals("white") ? 1 : 6;

        if (targetX == getX() && targetY == getY() + direction) {
            return true; // Move forward by 1
        }
        if (getY() == startRow && targetX == getX() && targetY == getY() + 2 * direction) {
            return true; // Move forward by 2 from starting position
        }
        if (Math.abs(targetX - getX()) == 1 && targetY == getY() + direction) {
            return true; // Capture move
        }
        return false; // Invalid move
    }
}