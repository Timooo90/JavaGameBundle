package timooo90.tictactoe.utilities;

import java.util.ArrayList;

public class Board {
    public static int numberOfBoards = 0;
    private int[][] gameBoard;
    private boolean humanTurn = true;
    private String key;
    private int score = 0;

    ArrayList<Board> children;

    public Board(int[][] gameBoard, boolean humanTurn) {
        this.gameBoard = gameBoard;
        this.humanTurn = humanTurn;
        this.key = Utility.boardToDictionaryKey(this.gameBoard);
        this.children = new ArrayList<>();

        calculateScore();
        generateChildren();
    }

    private void calculateScore() {
        numberOfBoards++;
        System.out.println(this.key);
    }

    private int[][] getDeepCopyOfGameBoard() {
        int[][] newGameBoard = new int[gameBoard.length][gameBoard.length];

        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                newGameBoard[i][j] = gameBoard[i][j];
            }
        }

        return newGameBoard;
    }

    private void generateChildren() {
        int player = humanTurn ? 1 : -1;

        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                if (gameBoard[i][j] == 0) {
                    int[][] newGameBoard = getDeepCopyOfGameBoard();
                    newGameBoard[i][j] = player;

                    Board newBoard = new Board(newGameBoard, !humanTurn);

                }
            }
        }
    }
}
