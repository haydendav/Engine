package com.chessbot.model.pieces;

import com.chessbot.model.Color;
import com.chessbot.model.Piece;

public class Bishop extends Piece {
    public Bishop(Color color) {
        super(color);
    }

    @Override
    public String getType() {
        return "B";
    }
}