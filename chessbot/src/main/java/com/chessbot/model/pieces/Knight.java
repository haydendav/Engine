package com.chessbot.model.pieces;

import com.chessbot.model.Color;
import com.chessbot.model.Piece;

public class Knight extends Piece {
    public Knight(Color color) {
        super(color);
    }

    @Override
    public String getType() {
        return "N";
    }
}