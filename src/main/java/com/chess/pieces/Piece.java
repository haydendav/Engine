package com.chess.pieces;

public abstract class Piece {
    protected String color;
    protected int positionX;
    protected int positionY;

    public Piece(String color, int positionX, int positionY) {
        this.color = color;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public String getColor() {
        return color;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPosition(int x, int y) {
        this.positionX = x;
        this.positionY = y;
    }

    public abstract boolean isValidMove(int targetX, int targetY);

    public String getSymbol() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSymbol'");
    }
}