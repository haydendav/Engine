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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Position position = (Position) obj;
        return rank == position.rank && file == position.file;
    }

    @Override
    public int hashCode() {
        return 31 * rank + file;
    }

    @Override
    public String toString() {
        return String.valueOf((char)('a' + file)) + (rank + 1);
    }
}