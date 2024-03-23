package timooo90.tictactoe.utilities;

public class GameResult {
    private int winner;
    private BoardHandler.Board board;
    private int depth = 0;

    public GameResult(int winner, BoardHandler.Board board) {
        this.winner = winner;
        this.board = board;
    }

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
