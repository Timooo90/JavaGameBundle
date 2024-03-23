package timooo90.tictactoe;
import timooo90.tictactoe.utilities.BoardHandler;
import timooo90.tictactoe.utilities.Utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class TicTacToe {
    private static BoardHandler boardHandler;
    private GUIController controller;
    private Random randomNumberGenerator = new Random();
    private AIPlayStyle aiPlayStyle = AIPlayStyle.RANDOM;
    private int[][] gameBoard;
    private boolean playerTurn = true;
    private boolean gameOver = false;
    private int winner = 0;

    public TicTacToe(GUIController controller) {
        this.controller = controller;
        initializeEmptyBoard();
    }

    public void setGameBoard(int[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

    public int[][] getGameBoard() {
        return gameBoard;
    }


    private void initializeEmptyBoard() {
        setGameBoard(Utility.getEmptyBoard());
    }

    public void printGameBoardToConsole() {
        for (int i = 0; i < gameBoard.length; i++) {
            System.out.println(Arrays.toString(gameBoard[i]));
        }
    }


    public void handleMouseClick(String coordinates) {
        if (gameOver || coordinates.length() != 2 || !playerTurn) { return; }

        int x = Character.getNumericValue(coordinates.charAt(0));
        int y = Character.getNumericValue(coordinates.charAt(1));

        if (gameBoard[x][y] == 0) {
            gameBoard[x][y] = 1;
            playerTurn = false;
            controller.setSquareLabelValue("L" + x + y, "X");
            if (isGameOver()) {
                return ;
            }
        }

        if (!playerTurn && !isGameBoardFull()) { handleAITurn(); }
    }

    private void handleAIMove(String coordinates) {
        int x = Character.getNumericValue(coordinates.charAt(0));
        int y = Character.getNumericValue(coordinates.charAt(1));

        gameBoard[x][y] = -1;
        controller.setSquareLabelValue("L" + x + y, "O");
    }

    private void handleAITurn() {
        switch (aiPlayStyle) {
            case RANDOM: selectAIRandomMove();
            case OPTIMIZED: selectAIOptimizedMove();
        }

        if (isGameOver()) { return; }

        playerTurn = true;
    }

    private void selectAIRandomMove() {
        ArrayList<String> freeCoordinates = getFreeCoordinates();

        int randomNumber = randomNumberGenerator.nextInt(freeCoordinates.size());

        String AIChoice = freeCoordinates.get(randomNumber);

        handleAIMove(AIChoice);
    }

    private void selectAIOptimizedMove() {

    }


    private ArrayList<String> getFreeCoordinates() {
        ArrayList<String> freeCoordinates = new ArrayList<>();

        for (int i = 0; i < gameBoard.length; i++){
            for (int j = 0; j < gameBoard.length; j++) {
                if (gameBoard[i][j] == 0) {
                    freeCoordinates.add(String.valueOf(i) + String.valueOf(j));
                }
            }
        }
        return freeCoordinates;
    }


    private boolean isGameOver() {
        if (rowCheck() || columnCheck() || diagonalCheck() || antiDiagonalCheck() || isGameBoardFull()) {
            gameOver = true;
            controller.generateGameEndResult(winner);
            return true;
        }
        return false;
    }

    private boolean isGameBoardFull() {
        for (int i = 0; i < gameBoard.length; i++){
            for (int j = 0; j < gameBoard.length; j++) {
                if (gameBoard[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean rowCheck() {
        for (int i = 0; i < gameBoard.length; i++) {
            int rowSum = Arrays.stream(gameBoard[i]).sum();
            if (rowSum == gameBoard.length || rowSum == -gameBoard.length) {
                winner = rowSum;
                return true;
            }
        }
        return false;
    }

    private boolean columnCheck() {
        for (int i = 0; i < gameBoard.length; i++) {
            int columnSum = 0;
            for (int j = 0; j < gameBoard.length; j++) {
                columnSum += gameBoard[j][i];
            }

            if (columnSum == gameBoard.length || columnSum == -gameBoard.length) {
                winner = columnSum;
                return true;
            }
        }
        return false;
    }

    private boolean diagonalCheck() {
        int diagonalSum = 0;

        for (int i = 0; i < gameBoard.length; i++) {
            diagonalSum += gameBoard[i][i];
        }
        if (diagonalSum == gameBoard.length || diagonalSum == -gameBoard.length) {
            winner = diagonalSum;
            return true;
        }

        return false;
    }

    private boolean antiDiagonalCheck() {
        int row = 0;
        int column = gameBoard.length - 1;

        int antiDiagonalSum = 0;

        while (column >= 0) {
            antiDiagonalSum += gameBoard[row][column];
            row += 1;
            column -= 1;
        }

        if (antiDiagonalSum == gameBoard.length || antiDiagonalSum == -gameBoard.length) {
            winner = antiDiagonalSum;
            return true;
        }

        return false;
    }

}
