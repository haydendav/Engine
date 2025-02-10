package board;

import pieces.*;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private Map<String, Piece> board;

    public Board() {
        board = new HashMap<>();
    }

    public Piece getPieceAt(String position) {
        return board.get(position);
    }

    public void setPieceAt(String position, Piece piece) {
        board.put(position, piece);
    }

    public boolean isOccupied(String position) {
        return board.containsKey(position) && board.get(position) != null;
    }

    public void setup() {
        // Initialize white pieces
        board.put("a1", new Rook("a1", true, this));
        board.put("b1", new Knight("b1", true, this));
        board.put("c1", new Bishop("c1", true, this));
        board.put("d1", new Queen("d1", true, this));
        board.put("e1", new King("e1", true, this));
        board.put("f1", new Bishop("f1", true, this));
        board.put("g1", new Knight("g1", true, this));
        board.put("h1", new Rook("h1", true, this));
        for (char file = 'a'; file <= 'h'; file++) {
            board.put(file + "2", new Pawn(file + "2", true, this));
        }

        // Initialize black pieces
        board.put("a8", new Rook("a8", false, this));
        board.put("b8", new Knight("b8", false, this));
        board.put("c8", new Bishop("c8", false, this));
        board.put("d8", new Queen("d8", false, this));
        board.put("e8", new King("e8", false, this));
        board.put("f8", new Bishop("f8", false, this));
        board.put("g8", new Knight("g8", false, this));
        board.put("h8", new Rook("h8", false, this));
        for (char file = 'a'; file <= 'h'; file++) {
            board.put(file + "7", new Pawn(file + "7", false, this));
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int rank = 8; rank >= 1; rank--) {
            for (char file = 'a'; file <= 'h'; file++) {
                String position = "" + file + rank;
                Piece piece = getPieceAt(position);
                if (piece == null) {
                    sb.append("_|");
                } else {
                    char pieceChar = piece.getClass().getSimpleName().charAt(0);
                    if (pieceChar == 'K') pieceChar = 'N'; // Knight
                    if (!piece.isWhite()) {
                        pieceChar = Character.toLowerCase(pieceChar);
                    }
                    sb.append(pieceChar).append("|");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}