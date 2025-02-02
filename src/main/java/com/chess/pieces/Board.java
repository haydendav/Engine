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
        // Initialize pieces on the board
        // Example: board[0][0] = new Rook("black");
        // Add other pieces similarly
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