public abstract class Piece {
    public String position;
    public Color color;
    
    public Piece(Color color, String position) {
        this.color = color;
        this.position = position;
    }

    public abstract List<Move> getLegalMoves(Board board);
}