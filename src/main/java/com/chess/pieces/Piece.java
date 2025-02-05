package com.chess.pieces;

import java.util.List;

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

    public abstract List<String> getLegalMoves(Piece[][] board);

    public abstract boolean isValidMove(int targetX, int targetY);

    public abstract String getSymbol();
}