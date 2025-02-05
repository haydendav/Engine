package com.chess.board;

import com.chess.pieces.Bishop;
import com.chess.pieces.King;
import com.chess.pieces.Knight;
import com.chess.pieces.Pawn;
import com.chess.pieces.Piece;
import com.chess.pieces.Queen;
import com.chess.pieces.Rook;

public class Board {
    private Piece[][] board;

    public Board() {
        board = new Piece[7][7];
        initializeBoard();
    }

    private void initializeBoard() {
        board[0][0] = new Rook("white", 0, 0);
        board[0][7] = new Rook("white", 0, 7);
        board[7][0] = new Rook("black", 7, 0);
        board[7][7] = new Rook("black", 7, 7);

        board[0][1] = new Knight("white", 0, 1);
        board[0][6] = new Knight("white", 0, 6);
        board[7][1] = new Knight("black", 7, 1);
        board[7][6] = new Knight("black", 7, 6);

        board[0][2] = new Bishop("white", 0, 2);
        board[0][5] = new Bishop("white", 0, 5);
        board[7][2] = new Bishop("black", 7, 2);
        board[7][5] = new Bishop("black", 7, 5);

        board[0][3] = new Queen("white", 0, 3);
        board[7][3] = new Queen("black", 7, 3);

        board[0][4] = new King("white", 0, 4);
        board[7][4] = new King("black", 7, 4);

        board[1][0] = new Pawn("white", 1, 0);
        board[1][1] = new Pawn("white", 1, 1);
        board[1][2] = new Pawn("white", 1, 2);
        board[1][3] = new Pawn("white", 1, 3);
        board[1][4] = new Pawn("white", 1, 4);
        board[1][5] = new Pawn("white", 1, 5);
        board[1][6] = new Pawn("white", 1, 6);
        board[1][7] = new Pawn("white", 1, 7);

        board[6][0] = new Pawn("black", 6, 0);
        board[6][1] = new Pawn("black", 6, 1);
        board[6][2] = new Pawn("black", 6, 2);
        board[6][3] = new Pawn("black", 6, 3);
        board[6][4] = new Pawn("black", 6, 4);
        board[6][5] = new Pawn("black", 6, 5);
        board[6][6] = new Pawn("black", 6, 6);
        board[6][7] = new Pawn("black", 6, 7);
    }

    public void displayBoard() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (board[row][col] == null) {
                    System.out.print(". ");
                } else {
                    System.out.print(board[row][col].getSymbol() + " ");
                }
            }
            System.out.println();
        }
    }

    public boolean isValidMove(int startRow, int startCol, int endRow, int endCol) {
        // Implement move validation logic
        return true; // Placeholder return value
    }

    public void initialize() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'initialize'");
    }

    public void display() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'display'");
    }
}