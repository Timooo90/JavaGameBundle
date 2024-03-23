package timooo90.tictactoe.utilities;

public class GameResult {
    private BoardHandler.Board board;
    private int humanWinDepth = 0;
    private boolean humanWinReached = false;
    private int AIWinDepth = 0;
    private boolean AIWinReached = false;

    private int drawDepth = 0;
    private boolean drawReached = false;



    public BoardHandler.Board getBoard() {
        return board;
    }

    public void setBoard(BoardHandler.Board board) {
        this.board = board;
    }

    public int getHumanWinDepth() {
        return humanWinDepth;
    }

    public void setHumanWinDepth(int humanWinDepth) {
        this.humanWinDepth = humanWinDepth;
    }

    public int getAIWinDepth() {
        return AIWinDepth;
    }

    public void setAIWinDepth(int AIWinDepth) {
        this.AIWinDepth = AIWinDepth;
    }

    public boolean isHumanWinReached() {
        return humanWinReached;
    }

    public void setHumanWinReached(boolean humanWinReached) {
        this.humanWinReached = humanWinReached;
    }

    public boolean isAIWinReached() {
        return AIWinReached;
    }

    public void setAIWinReached(boolean AIWinReached) {
        this.AIWinReached = AIWinReached;
    }

    public int getDrawDepth() {
        return drawDepth;
    }

    public void setDrawDepth(int drawDepth) {
        this.drawDepth = drawDepth;
    }

    public boolean isDrawReached() {
        return drawReached;
    }

    public void setDrawReached(boolean drawReached) {
        this.drawReached = drawReached;
    }

    public void increaseDepth() {
        if (!humanWinReached) {
            setHumanWinDepth(getHumanWinDepth() + 1);
        }
        if (!AIWinReached) {
            setAIWinDepth(getHumanWinDepth() + 1);
        }
        if (!drawReached) {
            setDrawDepth(getHumanWinDepth() + 1);
        }
    }
}
