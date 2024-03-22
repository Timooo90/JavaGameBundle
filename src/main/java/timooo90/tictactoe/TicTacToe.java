package timooo90.tictactoe;
import timooo90.tictactoe.utilities.Utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class TicTacToe {
    private GUIController controller;
    private Random randomNumberGenerator = new Random();
    private AIPlayStyle aiPlayStyle = AIPlayStyle.RANDOM;
    private int[][] gameBoard;
    private boolean playerTurn = true;

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


    public int[][] handleMouseClick(String coordinates) {
        if (coordinates.length() != 2 || !playerTurn) { return gameBoard; }

        int x = Character.getNumericValue(coordinates.charAt(0));
        int y = Character.getNumericValue(coordinates.charAt(1));

        if (gameBoard[x][y] == 0) {
            gameBoard[x][y] = 1;
            playerTurn = false;
            controller.setSquareLabelValue("L" + x + y, "X");
            checkWinConditions();
        }

        //printGameBoardToConsole();

        if (!playerTurn && !isGameBoardFull()) { handleAITurn(); }

        return gameBoard;
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

        checkWinConditions();

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

    private boolean isGameBoardFull() {
        for (int i = 0; i < gameBoard.length; i++){
            for (int j = 0; j < gameBoard.length; j++) {
                if (gameBoard[i][j] == 0) {
                    return false;
                }
            }
        }
        System.out.println("Board is full!");
        return true;
    }

    private void checkWinConditions() {
        if (rowCheck() || columnCheck() || diagonalCheck() || antiDiagonalCheck()) {
            System.out.println("Joku voitti!");
        }
    }

    private boolean rowCheck() {
        for (int i = 0; i < gameBoard.length; i++) {
            int rowSum = Arrays.stream(gameBoard[i]).sum();
            if (rowSum == gameBoard.length || rowSum == -gameBoard.length) {
                System.out.println("Row sum is " + rowSum);
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
                System.out.println("Column sum is " + columnSum);
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
            return true;
        }

        return false;
    }

}
