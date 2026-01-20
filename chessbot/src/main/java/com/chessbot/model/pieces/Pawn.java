package com.chessbot.model.pieces;

import java.util.ArrayList;
import java.util.List;

import com.chessbot.model.Board;
import com.chessbot.model.Color;
import com.chessbot.model.Piece;
import com.chessbot.model.Position;

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public String getType() {
        return "P";
    }

    @Override
    public List<Position> getPseudoLegalMoves(Board board, Position from) {
        List<Position> moves = new ArrayList<>();

        int dir = (color == Color.WHITE) ? 1 : -1;

        int startRank = (color == Color.WHITE) ? 1 : 6;

        // 1. Forward one
        Position oneForward = new Position(from.rank + dir, from.file);
        if (board.isValidPosition(oneForward.rank, oneForward.file) && board.isEmpty(oneForward)) {
            moves.add(oneForward);

            // 2. Forward two from starting rank
            Position twoForward = new Position(from.rank + 2 * dir, from.file);
            if (from.rank == startRank && board.isEmpty(twoForward)) {
                moves.add(twoForward);
            }
        }

        // 3. Captures
        int[] captureOffsets = {-1, 1}; // left and right
        for (int offset : captureOffsets) {
            Position diag = new Position(from.rank + dir, from.file + offset);
            if (board.isValidPosition(diag.rank, diag.file) && board.isEnemy(diag, color)) {
                moves.add(diag);
            }
        }

        return moves;
    }

    @Override
    protected int[][] getMoveOffsets() {
        // Pawn moves are handled in getPseudoLegalMoves() due to special rules
        // (forward movement, captures, en passant, promotion)
        return new int[0][];
    }
}