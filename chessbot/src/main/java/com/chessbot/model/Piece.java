package com.chessbot.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {

    protected Color color;

    protected Piece(Color color) {
        this.color = color;
    }

    public abstract String getType();

    protected abstract int[][] getMoveOffsets();

    public List<Position> getPseudoLegalMoves(Board board, Position from) {
         List<Position> moves = new ArrayList<>();
        for (int[] move : getMoveOffsets()) {
            int newRank = from.rank + move[0];
            int newFile = from.file + move[1];
            if (board.isValidPosition(newRank, newFile)) {
                Position to = new Position(newRank, newFile);
                Piece targetPiece = board.getPieceAt(to);
                if (targetPiece == null || targetPiece.getColor() != this.getColor()) {
                    moves.add(to);
                }
            }
        }
        return moves;
    }

    public Color getColor() {
        return color;
    }
}