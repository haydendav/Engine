package com.chess;

import java.util.Scanner;

import com.chess.board.Board;

public class App {
    public static void main(String[] args) {
        // Initialize the chessboard and start the game loop
        Board board = new Board();
        board.initialize();
        board.display();

        // Game loop for user input and move handling
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                System.out.print("Enter your move: ");
                String move = scanner.nextLine();
                if (move.equalsIgnoreCase("exit")) {
                    break;
                }
                // Process the move (validation and updating the board)
                // This part will be implemented later
            }
        } finally {
            scanner.close();
        }
    }

    public Object handleUserInput(String input) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handleUserInput'");
    }
}