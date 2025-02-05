package com.chess.pieces;

import com.chess.utils.Utils;
import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {
    private int x;
    private int y;
    
    public Bishop(String color, int x, int y) {
        super(color, x, y);
    }

    private int getX() {
        return x;
    }

    private int getY() {
        return y;
    }

    @Override
    public boolean isValidMove(int newX, int newY) {
        int deltaX = Math.abs(newX - this.getX());
        int deltaY = Math.abs(newY - this.getY());
        return deltaX == deltaY; // Bishops move diagonally
    }

    @Override
    public String getSymbol() {
        return color.equals("white") ? "B" : "b";
    }

    @Override
    public List<String> getLegalMoves(Piece[][] board) {
        ArrayList<String> legalMoves = new ArrayList<>();
        int[] directions = {-1, 1};

        for (int dx : directions) {
            for (int dy : directions) {
                int newX = getX() + dx;
                int newY = getY() + dy;

                while (newX >= 0 && newX < board.length && newY >= 0 && newY < board[0].length) {
                    if (board[newX][newY] == null) {
                        legalMoves.add(getSymbol() + Utils.toChessNotation(newX, newY));
                    } else if (!board[newX][newY].getColor().equals(this.getColor())) {
                        legalMoves.add(getSymbol() + "x" + Utils.toChessNotation(newX, newY));
                        break;
                    } else {
                        break;
                    }
                    newX += dx;
                    newY += dy;
                }
            }
        }
        return legalMoves;
    }
}