package com.chessbot.model;

import java.util.Random;

import com.chessbot.model.pieces.Bishop;
import com.chessbot.model.pieces.King;
import com.chessbot.model.pieces.Knight;
import com.chessbot.model.pieces.Pawn;
import com.chessbot.model.pieces.Queen;
import com.chessbot.model.pieces.Rook;

public class Board {
    private Piece[][] board;

    public Board() {
        board = new Piece[8][8];
        setupInitalPosition();
    }

    private void setupInitalPosition() {
        //Place pawns
        for(int i=0; i<8; i++) {
            board[1][i] = new Pawn(Color.WHITE);
            board[6][i] = new Pawn(Color.BLACK);
        }

        //Place the other pieces
        board[0][0] = new Rook(Color.WHITE);
        board[0][1] = new Knight(Color.WHITE);
        board[0][2] = new Bishop(Color.WHITE);
        board[0][3] = new Queen(Color.WHITE);
        board[0][4] = new King(Color.WHITE);
        board[0][5] = new Bishop(Color.WHITE);
        board[0][6] = new Knight(Color.WHITE);
        board[0][7] = new Rook(Color.WHITE);

        board[7][0] = new Rook(Color.WHITE);
        board[7][1] = new Knight(Color.WHITE);
        board[7][2] = new Bishop(Color.WHITE);
        board[7][3] = new Queen(Color.WHITE);
        board[7][4] = new King(Color.WHITE);
        board[7][5] = new Bishop(Color.WHITE);
        board[7][6] = new Knight(Color.WHITE);
        board[7][7] = new Rook(Color.WHITE);
    }

    public Piece getPiece(int rank, int file) {
        return board[rank][file];
    }

    public void movePiece(int fromRank, int fromFile, int toRank, int toFile) {
        board[toRank][toFile] = board[fromRank][fromFile];
        board[fromRank][fromFile] = null;
    }
}