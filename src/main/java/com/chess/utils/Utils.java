package com.chess.utils;

public class Utils {
    public static boolean isValidMove(String move) {
        // Implement logic to validate the move format
        return move.matches("[a-h][1-8]-[a-h][1-8]");
    }

    public static String formatMove(String move) {
        // Implement logic to format the move for display
        return move.toUpperCase();
    }

    public static void printBoardState(String[][] board) {
        // Implement logic to print the current state of the board
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}