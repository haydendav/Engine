package com.chessbot.model;

public class Position {
    public int rank;
    public int file;

    public Position(int rank, int file) {
        this.rank = rank;
        this.file = file;
    }

    public int getRank() {
        return rank;
    }

    public int getFile() {
        return file;
    }

    public int setRank(int rank) {
        this.rank = rank;
        return rank;
    }

    public int setFile(int file) {
        this.file = file;
        return file;
    }
}