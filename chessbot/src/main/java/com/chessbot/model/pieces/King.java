package com.chessbot.model.pieces;

import com.chessbot.model.Color;
import com.chessbot.model.Piece;

public class King extends Piece {

    private static final int[][] MOVES = {
        {0, 1}, {1, 0}, {0, -1}, {-1, 0},
        {-1, 1}, {1, -1}, {-1, -1}, {1, 1}
    };

    public King(Color color) {
        super(color);
    }

    @Override
    public String getType() {
        return "K";
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    protected int[][] getMoveOffsets() {
        return MOVES;
    }
}