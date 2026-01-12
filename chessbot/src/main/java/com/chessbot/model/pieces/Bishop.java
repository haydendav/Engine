package com.chessbot.model.pieces;

import java.util.ArrayList;
import java.util.List;

import com.chessbot.model.Board;
import com.chessbot.model.Color;
import com.chessbot.model.Piece;
import com.chessbot.model.Position;

public class Bishop extends Piece {

    private static final int[][] MOVES = {
        {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
    };

    public Bishop(Color color) {
        super(color);
    }

    @Override
    public List<Position> getPseudoLegalMoves(Board board, Position from) {
        List<Position> legalMoves = new ArrayList<>();

        for (int[] move : MOVES) {
            int rank = from.getRank();
            int file = from.getFile();

            while (true) {
                rank += move[0];
                file += move[1];
                
                Position newPos = new Position(rank, file);

                if (!board.isValidPosition(newPos.rank, newPos.file)) {
                    break;
                }

                Piece pieceAtNewPos = board.getPieceAt(newPos);
                if (pieceAtNewPos == null) {
                    legalMoves.add(newPos);
                } else {
                    if (pieceAtNewPos.getColor() != this.color) {
                        legalMoves.add(newPos);
                    }
                    break;
                }
            }
        }

        return legalMoves;
    }

    @Override
    public String getType() {
        return "B";
    }

    @Override
    protected int[][] getMoveOffsets() {
        return MOVES;
    }
}