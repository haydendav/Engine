package com.chessbot.model;

public abstract class Piece {
    public Color color;
    
    public Piece(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public abstract String getType();
}