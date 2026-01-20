package com.chessbot.model;

import java.util.List;

import com.chessbot.model.pieces.Bishop;
import com.chessbot.model.pieces.King;
import com.chessbot.model.pieces.Knight;
import com.chessbot.model.pieces.Pawn;
import com.chessbot.model.pieces.Queen;
import com.chessbot.model.pieces.Rook;

public class Board {
    private Piece[][] board;

    public Board() {
        board = new Piece[8][8];
        setupInitalPosition();
    }

    public void setupInitalPosition() {
        //Place pawns
        for(int i=0; i<8; i++) {
            board[1][i] = new Pawn(Color.WHITE);
            board[6][i] = new Pawn(Color.BLACK);
        }

        //Place the other pieces
        board[0][0] = new Rook(Color.WHITE);
        board[0][1] = new Knight(Color.WHITE);
        board[0][2] = new Bishop(Color.WHITE);
        board[0][3] = new Queen(Color.WHITE);
        board[0][4] = new King(Color.WHITE);
        board[0][5] = new Bishop(Color.WHITE);
        board[0][6] = new Knight(Color.WHITE);
        board[0][7] = new Rook(Color.WHITE);

        board[7][0] = new Rook(Color.BLACK);
        board[7][1] = new Knight(Color.BLACK);
        board[7][2] = new Bishop(Color.BLACK);
        board[7][3] = new Queen(Color.BLACK);
        board[7][4] = new King(Color.BLACK);
        board[7][5] = new Bishop(Color.BLACK);
        board[7][6] = new Knight(Color.BLACK);
        board[7][7] = new Rook(Color.BLACK);
    }

    public void movePiece(int fromRank, int fromFile, int toRank, int toFile) {
        board[toRank][toFile] = board[fromRank][fromFile];
        board[fromRank][fromFile] = null;
    }

    public Piece getPieceAt(Position pos) {
        return isValidPosition(pos.getRank(), pos.getFile())
            ? board[pos.getRank()][pos.getFile()]
            : null;
    }   
    
    public void setPieceAt(Position pos, Piece piece) {
        if (!isValidPosition(pos.getRank(), pos.getFile())) {
            throw new IllegalArgumentException(
                "Invalid position: " + pos.getRank() + "," + pos.getFile()
            );
        }
        board[pos.getRank()][pos.getFile()] = piece;
    }    

    public boolean isValidPosition(int rank, int file) {
        return rank >= 0 && rank < 8 && file >= 0 && file < 8;
    }    

    public boolean isEmpty(Position position) {
        return board[position.getRank()][position.getFile()] == null;
    }
    
    public boolean isEnemy(Position position, Color color) {
        Piece piece = board[position.getRank()][position.getFile()];
        return piece != null && piece.getColor() != color;
    }    

    public Board copy() {
        Board newBoard = new Board();
        // Clear the board that was set up by constructor
        newBoard.board = new Piece[8][8];
        // Copy the actual board state
        for(int rank=0; rank<8; rank++) {
            System.arraycopy(this.board[rank], 0, newBoard.board[rank], 0, 8);
        }
        return newBoard;
    }

    public boolean isKingInCheck(Color color) {
        Position kingPos = findKing(color);

        Color enemy = color.opposite();

        for (int rank = 0; rank < 8; rank++) {
            for (int file = 0; file < 8; file++) {
                Piece piece = board[rank][file];

                if (piece != null && piece.getColor() == enemy) {
                    Position from = new Position(rank, file);
                    List<Position> attacks =
                            piece.getPseudoLegalMoves(this, from);

                    if (attacks.contains(kingPos)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Checks if the given color is in checkmate.
     * Checkmate occurs when the king is in check and there are no legal moves.
     */
    public boolean isCheckmate(Color color) {
        if (!isKingInCheck(color)) {
            return false;
        }
        
        // Check if there are any legal moves
        List<Move> legalMoves = com.chessbot.engine.MoveGenerator.generateLegalMoves(this, color);
        return legalMoves.isEmpty();
    }

    /**
     * Checks if the given color is in stalemate.
     * Stalemate occurs when the king is not in check but there are no legal moves.
     */
    public boolean isStalemate(Color color) {
        if (isKingInCheck(color)) {
            return false; // If in check, it's checkmate, not stalemate
        }
        
        // Check if there are any legal moves
        List<Move> legalMoves = com.chessbot.engine.MoveGenerator.generateLegalMoves(this, color);
        return legalMoves.isEmpty();
    }

    private Position findKing(Color color) {
        for (int rank = 0; rank < 8; rank++) {
            for (int file = 0; file < 8; file++) {
                Piece piece = board[rank][file];
                if (piece != null &&
                    piece.getColor() == color &&
                    piece.getType().equals("K")) {
                    return new Position(rank, file);
                }
            }
        }
        throw new IllegalStateException("King not found for " + color);
    }

    public void applyMove(Move move) {
        Position from = move.from;
        Position to = move.to;

        Piece movingPiece = getPieceAt(from);

        // Check for castling
        if (movingPiece instanceof King && Math.abs(to.getFile() - from.getFile()) == 2) {
            // Kingside castling (O-O): king moves from e to g, rook moves from h to f
            if (to.getFile() == 6) {
                // Move king
                setPieceAt(to, movingPiece);
                setPieceAt(from, null);
                // Move rook from h-file (7) to f-file (5)
                Position rookFrom = new Position(from.getRank(), 7);
                Position rookTo = new Position(from.getRank(), 5);
                Piece rook = getPieceAt(rookFrom);
                if (rook != null) {
                    setPieceAt(rookTo, rook);
                    setPieceAt(rookFrom, null);
                }
                return;
            }
            // Queenside castling (O-O-O): king moves from e to c, rook moves from a to d
            else if (to.getFile() == 2) {
                // Move king
                setPieceAt(to, movingPiece);
                setPieceAt(from, null);
                // Move rook from a-file (0) to d-file (3)
                Position rookFrom = new Position(from.getRank(), 0);
                Position rookTo = new Position(from.getRank(), 3);
                Piece rook = getPieceAt(rookFrom);
                if (rook != null) {
                    setPieceAt(rookTo, rook);
                    setPieceAt(rookFrom, null);
                }
                return;
            }
        }

        // Regular move
        setPieceAt(to, movingPiece);
        setPieceAt(from, null);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int rank = 7; rank >= 0; rank--) {
            for (int file = 0; file < 8; file++) {
                Piece p = board[rank][file];
                sb.append(p == null ? "." : p.getType());
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}