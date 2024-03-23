package timooo90.tictactoe.utilities;

public class GameResult {
    private BoardHandler.Board board;
    private int depth = 0;


    public BoardHandler.Board getBoard() {
        return board;
    }

    public void setBoard(BoardHandler.Board board) {
        this.board = board;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
}
