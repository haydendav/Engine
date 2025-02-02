package com.chess.pieces;

public class Rook extends Piece {
    private String color;
    private int x;
    private int y;

    public String getColor() {
        return color;
    }

    public Rook(String color, int x, int y) {
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
        return (newX == getX() || newY == getY());
    }
}