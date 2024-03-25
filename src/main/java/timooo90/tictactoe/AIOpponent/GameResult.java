package timooo90.tictactoe.AIOpponent;

public class GameResult {
    private AdvancedAI.Board rootBoard;
    private int humanWinDepth = 0;
    private int previousHumanWinDepth = 0;
    private boolean humanWinReached = false;
    private int AIWinDepth = 0;
    private int previousAIWinDepth = 0;
    private boolean AIWinReached = false;

    private int drawDepth = 0;
    private int previousDrawDepth = 0;

    private boolean drawReached = false;


    public GameResult(AdvancedAI.Board rootBoard) {
        this.rootBoard = rootBoard;
    }
    public AdvancedAI.Board getRootBoard() {
        return rootBoard;
    }

    public void setRootBoard(AdvancedAI.Board rootBoard) {
        this.rootBoard = rootBoard;
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

    public int getPreviousHumanWinDepth() {
        return previousHumanWinDepth;
    }

    public void setPreviousHumanWinDepth(int previousHumanWinDepth) {
        this.previousHumanWinDepth = previousHumanWinDepth;
    }

    public int getPreviousAIWinDepth() {
        return previousAIWinDepth;
    }

    public void setPreviousAIWinDepth(int previousAIWinDepth) {
        this.previousAIWinDepth = previousAIWinDepth;
    }

    public int getPreviousDrawDepth() {
        return previousDrawDepth;
    }

    public void setPreviousDrawDepth(int previousDrawDepth) {
        this.previousDrawDepth = previousDrawDepth;
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

    public boolean haveAllResultsBeenFound() {
        return isHumanWinReached() && isAIWinReached() && isDrawReached();
    }
}
