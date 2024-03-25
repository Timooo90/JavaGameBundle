package timooo90.tictactoe;
import timooo90.tictactoe.AIOpponent.AIPlayStyle;
import timooo90.tictactoe.AIOpponent.AdvancedAI;
import timooo90.tictactoe.GUI.GUIController;
import timooo90.tictactoe.utilities.TicTacToeHelper;

import java.util.ArrayList;
import java.util.Random;


public class TicTacToe {
    private static AdvancedAI advancedAI = new AdvancedAI();
    private GUIController controller;
    private Random randomNumberGenerator = new Random();
    private AIPlayStyle aiPlayStyle = AIPlayStyle.OPTIMIZED;
    private int[][] gameBoard;
    private boolean playerTurn = true;
    private boolean gameOver = false;
    private int winner = 0;

    public TicTacToe(GUIController controller) {
        this.controller = controller;
        resetCurrentBoard();

        advancedAI.initializeBoards();
    }

    public int[][] getGameBoard() {
        return gameBoard;
    }

    private void resetCurrentBoard() {
        gameBoard = TicTacToeHelper.getEmptyBoard();
    }

    public void handleMouseClick(String coordinates) {
        if (gameOver || coordinates.length() != 2 || !playerTurn) {
            return;
        }

        int x = Character.getNumericValue(coordinates.charAt(0));
        int y = Character.getNumericValue(coordinates.charAt(1));

        if (gameBoard[x][y] == 0) {
            gameBoard[x][y] = 1;
            playerTurn = false;
            controller.setSquareLabelValue("L" + x + y, "X");

            checkGameEndingConditions();

            if (gameOver) { return; }
        }

        if (!playerTurn && !TicTacToeHelper.isGameBoardFull(gameBoard)) {
            handleAITurn();
        }
    }

    private void handleAIMove(String coordinates) {
        int x = Character.getNumericValue(coordinates.charAt(0));
        int y = Character.getNumericValue(coordinates.charAt(1));

        gameBoard[x][y] = -1;
        controller.setSquareLabelValue("L" + x + y, "O");
    }

    private void handleAITurn() {
        switch (aiPlayStyle) {
            case RANDOM:
                selectAIRandomMove();
            case OPTIMIZED:
                selectAIOptimizedMove();
        }

        checkGameEndingConditions();
        if (gameOver) { return; }

        playerTurn = true;
    }

    private void selectAIRandomMove() {
        ArrayList<String> freeCoordinates = getFreeCoordinates();

        int randomNumber = randomNumberGenerator.nextInt(freeCoordinates.size());

        String AIChoice = freeCoordinates.get(randomNumber);

        handleAIMove(AIChoice);
    }

    private void selectAIOptimizedMove() {
        handleAIMove(advancedAI.findBestMoveCoordinates(gameBoard));
    }


    private ArrayList<String> getFreeCoordinates() {
        ArrayList<String> freeCoordinates = new ArrayList<>();

        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                if (gameBoard[i][j] == 0) {
                    freeCoordinates.add(String.valueOf(i) + String.valueOf(j));
                }
            }
        }
        return freeCoordinates;
    }


    private boolean checkGameEndingConditions() {
        winner = TicTacToeHelper.getWinner(gameBoard, 3);
        if (winner != 0) {
            gameOver = true;
            controller.generateGameEndResult(winner);
        }

        else if (TicTacToeHelper.isGameBoardFull(gameBoard)) {
            gameOver = true;
            winner = 0;
            controller.generateGameEndResult(winner);
            return true;
        }

        return winner != 0;
    }

}


