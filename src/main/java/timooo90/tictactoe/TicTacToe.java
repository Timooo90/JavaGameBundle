package timooo90.tictactoe;

import timooo90.tictactoe.utilities.Utility;


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



    public void handleMouseClick(String coordinates) {
        System.out.println(coordinates);
        if (coordinates.length() != 2) { return; }

        int x = Character.getNumericValue(coordinates.charAt(0));
        int y = Character.getNumericValue(coordinates.charAt(1));


        if (gameBoard[x][y] == 0) {
            gameBoard[x][y] = 1;
        }

    }

}
