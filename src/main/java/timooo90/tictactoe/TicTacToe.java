package timooo90.tictactoe;

import timooo90.tictactoe.utilities.Utility;

import java.util.Arrays;

public class TicTacToe {
    private int[][] gameBoard;



    public TicTacToe() {
        initializeBoard();
    }

    private void initializeBoard() {
        gameBoard = Utility.getEmptyBoard();
    }

    public void startGame() {
        boolean playerTurn = true;

        while (true) {
            
        }
    }

    public void printGameBoard() {

        for (int row = 0; row < gameBoard.length; row++) {
            System.out.println(Arrays.toString(gameBoard[row]));
            /*
            for (int column = 0; column < gameBoard[0].length; column++) {
                System.out.println(gameBoard[row][column]);
            }

             */
        }
    }

    public int[][] getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(int[][] gameBoard) {
        this.gameBoard = gameBoard;
    }
}