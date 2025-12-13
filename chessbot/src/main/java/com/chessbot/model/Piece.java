package com.chessbot.model;

import java.util.List;
import com.chessbot.model.Color;

public abstract class Piece {
    public String position;
    public Color color;
    
    public Piece(Color color, String position) {
        this.color = color;
        this.position = position;
    }
}