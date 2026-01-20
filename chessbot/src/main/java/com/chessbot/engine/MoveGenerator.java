package com.chessbot.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.chessbot.model.Board;
import com.chessbot.model.Color;
import com.chessbot.model.Move;
import com.chessbot.model.Position;

public class MoveGenerator {

    private static final Random random = new Random();

    public static List<Move> generatePseudoLegalMoves(Board board, Color color) {
        List<Move> moves = new ArrayList<>();

        for(int rank=0; rank <8; rank++) {
            for(int file=0; file<8; file++) {
                Position from = new Position(rank, file);
                if(board.getPieceAt(from) != null && board.getPieceAt(from).getColor() == color) {
                    List<Position> pseudoLegalMoves = board.getPieceAt(from).getPseudoLegalMoves(board, from);
                    for(Position to : pseudoLegalMoves) {
                        moves.add(new Move(from, to));
                    }
                }
            }
        }
        
        return moves;
    }

    public static List<Move> generateLegalMoves(Board board, Color color) {
        List<Move> legalMoves = new ArrayList<>();
    
        List<Move> pseudoMoves = generatePseudoLegalMoves(board, color);
    
        for (Move move : pseudoMoves) {
            Board copy = board.copy();
            copy.applyMove(move);
    
            if (!copy.isKingInCheck(color)) {
                legalMoves.add(move);
            }
        }
    
        return legalMoves;
    }
    

    public static Move generateRandomMove(Board board, Color color) {
        List<Move> legalMoves = generateLegalMoves(board, color);
        if (legalMoves.isEmpty()) return null;
        return legalMoves.get(random.nextInt(legalMoves.size()));
    }
}