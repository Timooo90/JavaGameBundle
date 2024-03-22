package timooo90.tictactoe;

import timooo90.tictactoe.utilities.Utility;

import java.util.Arrays;


public class TicTacToe {
    private int[][] gameBoard;
    private boolean playerTurn = true;

    public TicTacToe() {
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
        if (coordinates.length() != 2) { return gameBoard; }

        int x = Character.getNumericValue(coordinates.charAt(0));
        int y = Character.getNumericValue(coordinates.charAt(1));

        if (gameBoard[x][y] == 0) {
            gameBoard[x][y] = 1;
        }

        printGameBoardToConsole();

        return gameBoard;
    }

}
