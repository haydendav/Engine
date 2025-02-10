package pieces;

import java.util.List;

import board.Board;

public class Bishop extends Piece {
    private Board board;
    
    public Bishop(String position, boolean isWhite, Board board) {
        super(position, isWhite);
        this.board = board;
    }
    
    @Override
    public boolean isValidMove(String position) {
        if (board.isOccupied(position)) {
            return false;
        }
        return true;
    }

    @Override
    public List<int[]> generateAllPossibleMoves() {        
        return null;
    }
}
