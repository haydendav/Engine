package com.chessbot.model.pieces;

import com.chessbot.model.Color;
import com.chessbot.model.Piece;

public class King extends Piece {
    public King(Color color) {
        super(color);
    }

    @Override
    public String getType() {
        return "K";
    }
}