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

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Welcome to ChessBot!");
            Color humanColor = promptForColor(scanner);
            Color engineColor = humanColor == Color.WHITE ? Color.BLACK : Color.WHITE;
            
            boolean showBoard = promptForBoardVisibility(scanner);
            
            System.out.println("You are playing as " + humanColor);
            System.out.println("Enter moves in algebraic notation (e.g. e4, Nf3, O-O)");
            System.out.println();
            
            Color sideToMove = Color.WHITE;
            int moveNumber = 1;
            String whiteMove = "";
            String blackMove = "";
            StringBuilder moveList = new StringBuilder();
            
            while (true) {
                // Print board from player's perspective if enabled
                if (showBoard) {
                    printBoard(board, humanColor);
                } else {
                    // Show move list instead
                    printMoveList(moveList.toString());
                }
                
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
                        String moveNotation = move.toAlgebraicNotation(board);
                        board.applyMove(move);
                        
                        // Track move in move list (before switching turns)
                        if (sideToMove == Color.WHITE) {
                            whiteMove = moveNotation;
                        } else {
                            blackMove = moveNotation;
                            // Complete the move pair
                            moveList.append(moveNumber).append(". ").append(whiteMove).append(" ").append(blackMove).append("\n");
                            moveNumber++;
                            whiteMove = "";
                            blackMove = "";
                        }
                        
                        System.out.println(); // Add spacing after move
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
                    
                    // Get move notation before applying the move (piece is still at 'from' position)
                    String moveNotation = engineMove.toAlgebraicNotation(board);
                    board.applyMove(engineMove);
                    
                    // Track move in move list (before switching turns)
                    if (sideToMove == Color.WHITE) {
                        whiteMove = moveNotation;
                    } else {
                        blackMove = moveNotation;
                        // Complete the move pair
                        moveList.append(moveNumber).append(". ").append(whiteMove).append(" ").append(blackMove).append("\n");
                        moveNumber++;
                        whiteMove = "";
                        blackMove = "";
                    }
                    
                    // Print move in algebraic notation
                    System.out.println("Engine plays: " + moveNotation);
                    System.out.println(); // Add spacing after move
                }
                
                // Switch turn
                sideToMove = sideToMove == Color.WHITE ? Color.BLACK : Color.WHITE;
                
                // Check for checkmate
                if (board.isCheckmate(sideToMove)) {
                    // If game ends mid-move, add the incomplete move
                    if (!whiteMove.isEmpty() && blackMove.isEmpty()) {
                        moveList.append(moveNumber).append(". ").append(whiteMove).append("\n");
                    }
                    System.out.println(sideToMove + " is in checkmate!");
                    System.out.println("Game Over! " + (sideToMove == Color.WHITE ? Color.BLACK : Color.WHITE) + " wins!");
                    if (!showBoard) {
                        System.out.println();
                        printMoveList(moveList.toString());
                    }
                    break;
                }
                
                // Check for stalemate
                if (board.isStalemate(sideToMove)) {
                    // If game ends mid-move, add the incomplete move
                    if (!whiteMove.isEmpty() && blackMove.isEmpty()) {
                        moveList.append(moveNumber).append(". ").append(whiteMove).append("\n");
                    }
                    System.out.println(sideToMove + " is in stalemate!");
                    System.out.println("Game Over! The game is a draw.");
                    if (!showBoard) {
                        System.out.println();
                        printMoveList(moveList.toString());
                    }
                    break;
                }
                
                // Check for check (but not checkmate)
                if (board.isKingInCheck(sideToMove)) {
                    System.out.println(sideToMove + " is in check!");
                    System.out.println(); // Add spacing after check message
                }
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

    /** Prompt for board visibility */
    private static boolean promptForBoardVisibility(Scanner scanner) {
        while (true) {
            System.out.print("Show board? (y/n): ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("y") || input.equals("yes")) return true;
            if (input.equals("n") || input.equals("no")) return false;

            System.out.println("Invalid input. Enter 'y' or 'n'.");
        }
    }

    /** Print the move list in standard chess notation */
    private static void printMoveList(String moveList) {
        System.out.println();
        System.out.println("Move List:");
        System.out.println("----------");
        if (moveList.isEmpty()) {
            System.out.println("(No moves yet)");
        } else {
            System.out.print(moveList);
        }
        System.out.println();
    }

    private static void printBoard(Board board, Color perspective) {
        // Print board from the player's perspective with larger spacing
        // White perspective: rank 7 (8) at top, rank 0 (1) at bottom
        // Black perspective: rank 0 (1) at top, rank 7 (8) at bottom, files reversed
        
        System.out.println(); // Extra spacing before board
        
        int startRank = perspective == Color.WHITE ? 7 : 0;
        int endRank = perspective == Color.WHITE ? -1 : 8;
        int rankStep = perspective == Color.WHITE ? -1 : 1;
        
        for (int rank = startRank; rank != endRank; rank += rankStep) {
            int startFile = perspective == Color.WHITE ? 0 : 7;
            int endFile = perspective == Color.WHITE ? 8 : -1;
            int fileStep = perspective == Color.WHITE ? 1 : -1;
            
            // Add spacing before each row
            System.out.print("    ");
            
            for (int file = startFile; file != endFile; file += fileStep) {
                Position pos = new Position(rank, file);
                Piece piece = board.getPieceAt(pos);
                if (piece == null) {
                    System.out.print(" .  ");
                } else {
                    System.out.print(" " + piece.getType() + "  ");
                }
            }
            System.out.println();
            System.out.println(); // Extra blank line between rows
        }
        System.out.println(); // Extra spacing after board
    }

}