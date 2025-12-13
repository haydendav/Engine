package com.chessbot;
import java.util.Scanner;
import com.chessbot.model.Color;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("This is chessbot!");

        // 1. Choose color
        Color humanColor = promptForColor(scanner);
        Color engineColor = humanColor == Color.WHITE ? Color.BLACK : Color.WHITE;

        System.out.println("You are playing as " + humanColor);
        System.out.println("Enter moves in algebraic notation (e.g. e4, Nf3)");
        System.out.println();

        Color sideToMove = Color.WHITE;

        int moveCount = 1;
        String moveList = "";

        while (true) {
            if (sideToMove == humanColor) {
                System.out.print("move: ");
                String input = scanner.nextLine().trim();

                if(input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("quit")) {
                    System.out.println("Exiting game.");
                    break;
                }

            } else {
                String move = moveCount + ". e4 e5";
                moveList += move + "\n";
                System.out.println(moveList);
                moveCount++;
            }

            // switch turn
            sideToMove = sideToMove == Color.WHITE ? Color.BLACK : Color.WHITE;
        }

        scanner.close();
    }

    private static Color promptForColor(Scanner scanner) {
        while (true) {
            System.out.print("Choose your color (white/black): ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("white") || input.equals("w")) {
                return Color.WHITE;
            }
            if (input.equals("black") || input.equals("b")) {
                return Color.BLACK;
            }

            System.out.println("Invalid input. Please enter 'white' or 'black'.");
        }
    }
}