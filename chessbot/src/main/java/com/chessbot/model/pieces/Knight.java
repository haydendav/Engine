package com.chessbot.model.pieces;

import com.chessbot.model.Color;
import com.chessbot.model.Piece;

public class Knight extends Piece {

    private static final int[][] MOVES = {
        {2, 1}, {1, 2}, {-1, 2}, {-2, 1},
        {-2, -1}, {-1, -2}, {1, -2}, {2, -1}
    };

    public Knight(Color color) {
        super(color);
    }

    @Override
    public String getType() {
        return "N";
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