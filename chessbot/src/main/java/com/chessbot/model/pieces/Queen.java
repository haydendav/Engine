package com.chessbot.model.pieces;

import com.chessbot.model.Color;
import com.chessbot.model.Piece;

public class Queen extends Piece {
    public Queen(Color color) {
        super(color);
    }

    @Override
    public String getType() {
        return "Q";
    }
}