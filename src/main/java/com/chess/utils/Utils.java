package com.chess.utils;

public class Utils {
    public static String toChessNotation(int x, int y) {
        char file = (char) ('a' + y);
        int rank = 8 - x;
        return "" + file + rank;
    }
}