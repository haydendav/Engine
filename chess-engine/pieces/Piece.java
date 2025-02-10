package pieces;

import java.util.List;

public abstract class Piece {
    protected String position;
    protected boolean isWhite;

    public Piece(String position, boolean isWhite) {
        this.position = position;
        this.isWhite = isWhite;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        
    }

    public abstract boolean isValidMove(String position);

    public abstract List<int[]> generateAllPossibleMoves();
}
