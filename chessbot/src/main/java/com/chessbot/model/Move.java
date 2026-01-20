package com.chessbot.model;

import com.chessbot.model.pieces.King;
import com.chessbot.model.pieces.Pawn;

public class Move {
    public Position from;
    public Position to;

    public Move(Position from, Position to) {
        this.from = from;
        this.to = to;
    }

    /**
     * Converts this move to algebraic notation (e.g., e4, Nf3, O-O)
     */
    public String toAlgebraicNotation(Board board) {
        Piece piece = board.getPieceAt(from);
        if (piece == null) {
            return to.toString();
        }

        // Check for castling
        if (piece instanceof King && Math.abs(to.getFile() - from.getFile()) == 2) {
            if (to.getFile() == 6) {
                return "O-O";
            } else if (to.getFile() == 2) {
                return "O-O-O";
            }
        }

        String pieceSymbol = piece.getType();
        boolean isPawn = piece instanceof Pawn;
        
        // For pawns, don't show piece symbol
        if (isPawn) {
            pieceSymbol = "";
        }

        // Check if it's a capture
        boolean isCapture = board.getPieceAt(to) != null;
        String captureMarker = isCapture ? "x" : "";

        // Build the notation
        String notation = pieceSymbol + captureMarker + to.toString();
        
        return notation;
    }

    @Override
    public String toString() {
        // Default toString - but prefer using toAlgebraicNotation(Board) when you have the board
        return from.toString() + "-" + to.toString();
    }
}