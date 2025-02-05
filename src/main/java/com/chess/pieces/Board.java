package com.chess.pieces;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private Piece[][] board;
    private Map<Character, Integer> fileMap;

    public Board() {
        board = new Piece[8][8];
        fileMap = new HashMap<>();
        for (int i = 0; i < 8; i++) {
            fileMap.put((char) ('a' + i), i);
        }
        initializeBoard();
    }

    private void initializeBoard() {
        // Initialize pawns
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn("white", 1, i);
            board[6][i] = new Pawn("black", 6, i);
        }

        // Initialize rooks
        board[0][0] = new Rook("white", 0, 0);
        board[0][7] = new Rook("white", 0, 7);
        board[7][0] = new Rook("black", 7, 0);
        board[7][7] = new Rook("black", 7, 7);

        // Initialize knights
        board[0][1] = new Knight("white", 0, 1);
        board[0][6] = new Knight("white", 0, 6);
        board[7][1] = new Knight("black", 7, 1);
        board[7][6] = new Knight("black", 7, 6);

        // Initialize bishops
        board[0][2] = new Bishop("white", 0, 2);
        board[0][5] = new Bishop("white", 0, 5);
        board[7][2] = new Bishop("black", 7, 2);
        board[7][5] = new Bishop("black", 7, 5);

        // Initialize queens
        board[0][3] = new Queen("white", 0, 3);
        board[7][3] = new Queen("black", 7, 3);

        // Initialize kings
        board[0][4] = new King("white", 0, 4);
        board[7][4] = new King("black", 7, 4);
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

    public void makeMove(String move) {
        int startCol = fileMap.get(move.charAt(0));
        int startRow = 8 - Character.getNumericValue(move.charAt(1));
        int endCol = fileMap.get(move.charAt(2));
        int endRow = 8 - Character.getNumericValue(move.charAt(3));

        if (isValidMove(startRow, startCol, endRow, endCol)) {
            board[endRow][endCol] = board[startRow][startCol];
            board[startRow][startCol] = null;
        } else {
            System.out.println("Invalid move");
        }
    }

    public static void main(String[] args) {
        Board board = new Board();
        board.displayBoard();
        board.makeMove("e2e4");
        board.displayBoard();
    }
}