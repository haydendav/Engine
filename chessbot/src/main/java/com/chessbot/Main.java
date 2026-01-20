package com.chessbot;

import java.util.List;
import java.util.Scanner;

import com.chessbot.engine.MoveGenerator;
import com.chessbot.model.Board;
import com.chessbot.model.Color;
import com.chessbot.model.Move;
import com.chessbot.model.MoveParser;
import com.chessbot.model.Piece;
import com.chessbot.model.Position;

public class Main {

    public static void main(String[] args) {
        Board board = new Board(); 
        board.setupInitalPosition();

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Welcome to ChessBot!");
            Color humanColor = promptForColor(scanner);
            Color engineColor = humanColor == Color.WHITE ? Color.BLACK : Color.WHITE;
            
            System.out.println("You are playing as " + humanColor);
            System.out.println("Enter moves in algebraic notation (e.g. e4, Nf3, O-O)");
            System.out.println();
            
            Color sideToMove = Color.WHITE;
            int moveCount = 1;
            String moveList = "";
            
            while (true) {
                // Print board (optional)
                printBoard(board);
                
                if (sideToMove == humanColor) {
                    System.out.print("Your move: ");
                    String input = scanner.nextLine().trim();
                    
                    if(input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("quit")) {
                        System.out.println("Exiting game.");
                        break;
                    }
                    
                    // Parse human move
                    Move move = MoveParser.parseAlgebraicMove(input, board, humanColor);
                    if (move != null) {
                        board.applyMove(move);
                    } else {
                        System.out.println("Try again!");
                        continue; // ask again
                    }
                    
                } else {
                    // Engine turn: pick a random legal move
                    List<Move> engineMoves = MoveGenerator.generateLegalMoves(board, engineColor);
                    if (engineMoves.isEmpty()) {
                        System.out.println("No legal moves for engine. Game over.");
                        break;
                    }
                    
                    Move engineMove = engineMoves.get((int)(Math.random() * engineMoves.size()));
                    board.applyMove(engineMove);
                    
                    // Print move in standard format
                    moveList += moveCount + ". " + engineMove + "\n";
                    System.out.println("Engine plays: " + engineMove);
                    moveCount++;
                }
                
                // Switch turn
                sideToMove = sideToMove == Color.WHITE ? Color.BLACK : Color.WHITE;
                
                // Check for check
                if (board.isKingInCheck(sideToMove)) {
                    System.out.println(sideToMove + " is in check!");
                }
                
                // TODO: add checkmate/stalemate detection
            }
        }
    }

    /** Simple color prompt */
    private static Color promptForColor(Scanner scanner) {
        while (true) {
            System.out.print("Choose your color (white/black): ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("white") || input.equals("w")) return Color.WHITE;
            if (input.equals("black") || input.equals("b")) return Color.BLACK;

            System.out.println("Invalid input. Enter 'white' or 'black'.");
        }
    }

    
    private static void printBoard(Board board) {
        for (int rank = 0; rank < 8; rank++) {
            for (int file = 0; file < 8; file++) {
                Position pos = new Position(rank, file);
                Piece piece = board.getPieceAt(pos);
                if (piece == null) {
                    System.out.print(". ");
                } else {
                    System.out.print(piece.getType() + " ");
                }
            }
            System.out.println();
        }
    }

}