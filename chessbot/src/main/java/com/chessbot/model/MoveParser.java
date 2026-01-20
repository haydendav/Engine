package com.chessbot.model;

import java.util.ArrayList;
import java.util.List;

import com.chessbot.model.pieces.Bishop;
import com.chessbot.model.pieces.King;
import com.chessbot.model.pieces.Knight;
import com.chessbot.model.pieces.Pawn;
import com.chessbot.model.pieces.Queen;
import com.chessbot.model.pieces.Rook;

public class MoveParser {

    /**
     * Parses algebraic notation input and returns a legal Move.
     * Supports:
     * - Pawn moves (e4, d5)
     * - Piece moves (Nf3, Bc4)
     * - Captures (exd5, Nxd5)
     * - Castling (O-O, O-O-O)
     */
    public static Move parseAlgebraicMove(String input, Board board, Color color) {
        input = input.trim();

        // Handle castling
        if (input.equals("O-O") || input.equals("0-0")) {
            Position from = findKingPosition(board, color);
            Position to = new Position(from.getRank(), 6); // kingside -> g-file
            return new Move(from, to);
        } else if (input.equals("O-O-O") || input.equals("0-0-0")) {
            Position from = findKingPosition(board, color);
            Position to = new Position(from.getRank(), 2); // queenside -> c-file
            return new Move(from, to);
        }

        // Determine piece type
        char firstChar = input.charAt(0);
        PieceType pieceType;
        int startIndex = 0;

        if (Character.isUpperCase(firstChar) && firstChar != 'O') {
            // Piece specified
            pieceType = switch (firstChar) {
                case 'N' -> PieceType.KNIGHT;
                case 'B' -> PieceType.BISHOP;
                case 'R' -> PieceType.ROOK;
                case 'Q' -> PieceType.QUEEN;
                case 'K' -> PieceType.KING;
                default -> PieceType.PAWN;
            };
            startIndex = 1;
        } else {
            pieceType = PieceType.PAWN; // default
        }

        // Remove capture marker for now (x)
        input = input.replace("x", "");

        // Destination square = last two characters
        if (input.length() < startIndex + 2) {
            System.out.println("Invalid move format: " + input);
            return null;
        }

        String dest = input.substring(input.length() - 2);
        int toFile = dest.charAt(0) - 'a';
        int toRank = dest.charAt(1) - '1'; // <-- FIXED for board[0][0]=a1

        Position to = new Position(toRank, toFile);

        // Find all pieces of this type for this color that can move to 'to'
        List<Move> candidates = new ArrayList<>();
        for (int rank = 0; rank < 8; rank++) {
            for (int file = 0; file < 8; file++) {
                Position from = new Position(rank, file);
                Piece piece = board.getPieceAt(from);
                if (piece != null &&
                    piece.getColor() == color &&
                    pieceMatchesType(piece, pieceType)) {

                    List<Position> pseudoMoves = piece.getPseudoLegalMoves(board, from);
                    if (pseudoMoves.contains(to)) {
                        candidates.add(new Move(from, to));
                    }
                }
            }
        }

        if (candidates.isEmpty()) {
            System.out.println("Illegal move or no matching piece for " + input);
            return null;
        }

        // Filter candidates by legality (king not in check)
        for (Move move : candidates) {
            Board copy = board.copy();
            copy.applyMove(move);
            if (!copy.isKingInCheck(color)) {
                return move;
            }
        }

        System.out.println("Move would leave king in check: " + input);
        return null;
    }

    /** Helper to find the king's position */
    private static Position findKingPosition(Board board, Color color) {
        for (int rank = 0; rank < 8; rank++) {
            for (int file = 0; file < 8; file++) {
                Piece piece = board.getPieceAt(new Position(rank, file));
                if (piece != null && piece.getColor() == color && piece.getType().equals("K")) {
                    return new Position(rank, file);
                }
            }
        }
        throw new IllegalStateException("King not found for " + color);
    }

    /** Helper to match PieceType enum with actual piece */
    private static boolean pieceMatchesType(Piece piece, PieceType type) {
        return switch (type) {
            case PAWN -> piece instanceof Pawn;
            case KNIGHT -> piece instanceof Knight;
            case BISHOP -> piece instanceof Bishop;
            case ROOK -> piece instanceof Rook;
            case QUEEN -> piece instanceof Queen;
            case KING -> piece instanceof King;
            default -> false;
        };
    }
}