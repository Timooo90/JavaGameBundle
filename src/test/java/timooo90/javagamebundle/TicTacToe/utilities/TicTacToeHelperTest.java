package timooo90.javagamebundle.TicTacToe.utilities;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TicTacToeHelperTest {
    private TestHelper testHelper = new TestHelper();


    @Test
    void getWinner() {
    }

    @Test
    void givenWinningBoards_whenGetRowWinner_thenReturnCorrectWinner() {
        ArrayList<int[][]> playerWinRows = testHelper.getHumanRowWinPositions();
        ArrayList<int[][]> AIWinRows = testHelper.getAIRowWinPositions();

        for (int[][] board : playerWinRows) {
            int winner = TicTacToeHelper.getRowWinner(board, 3);
            assertEquals(1, winner);
        }

        for (int[][] board : AIWinRows) {
            int winner = TicTacToeHelper.getRowWinner(board, 3);
            assertEquals(-1, winner);
        }
    }

    @Test
    void givenNonWinningBoards_whenGetRowWinner_thenReturnZero() {
        ArrayList<int[][]> noWinningBoards = testHelper.getNoWinnerPositions();

        for (int[][] board : noWinningBoards) {
            int winner = TicTacToeHelper.getRowWinner(board, 3);
            assertEquals(0, winner);
        }
    }

    @Test
    void givenWinningBoards_whenGetColumnWinner_thenReturnCorrectWinner() {
        ArrayList<int[][]> playerWinCols = testHelper.getHumanColumnWinPositions();
        ArrayList<int[][]> AIWinCols = testHelper.getAIColumnWinPositions();

        for (int[][] board : playerWinCols) {
            int winner = TicTacToeHelper.getColumnWinner(board, 3);
            assertEquals(1, winner);
        }

        for (int[][] board : AIWinCols) {
            int winner = TicTacToeHelper.getColumnWinner(board, 3);
            assertEquals(-1, winner);
        }
    }

    @Test
    void givenNonWinningBoards_whenGetColumnWinner_thenReturnZero() {
        ArrayList<int[][]> noWinningBoards = testHelper.getNoWinnerPositions();

        for (int[][] board : noWinningBoards) {
            int winner = TicTacToeHelper.getColumnWinner(board, 3);
            assertEquals(0, winner);
        }
    }

    @Test
    void givenWinningBoards_whenGetDiagonalWinner_thenReturnCorrectWinner() {
        int[][] playerWinDiag = testHelper.getHumanDiagonalWinPosition();
        int[][] AIWinDiag = testHelper.getAIDiagonalWinPosition();

        int winner = TicTacToeHelper.getDiagonalWinner(playerWinDiag, 3);
        assertEquals(1, winner);


        winner = TicTacToeHelper.getDiagonalWinner(AIWinDiag, 3);
        assertEquals(-1, winner);
    }

    @Test
    void givenNonWinningBoards_whenGetDiagonalWinner_thenReturnZero() {
        ArrayList<int[][]> noWinningBoards = testHelper.getNoWinnerPositions();

        for (int[][] board : noWinningBoards) {
            int winner = TicTacToeHelper.getDiagonalWinner(board, 3);
            assertEquals(0, winner);
        }
    }

    @Test
    void givenWinningBoards_whenGetAntiDiagonalWinner_thenReturnCorrectWinner() {
        int[][] playerWinAntiDiag = testHelper.getHumanAntiDiagonalWinPosition();
        int[][] AIWinAntiDiag = testHelper.getAIAntiDiagonalWinPosition();

        int winner = TicTacToeHelper.getAntiDiagonalWinner(playerWinAntiDiag, 3);
        assertEquals(1, winner);

        winner = TicTacToeHelper.getAntiDiagonalWinner(AIWinAntiDiag, 3);
        assertEquals(-1, winner);
    }

    @Test
    void givenNonWinningBoards_whenGetAntiDiagonalWinner_thenReturnZero() {
        ArrayList<int[][]> noWinningBoards = testHelper.getNoWinnerPositions();

        for (int[][] board : noWinningBoards) {
            int winner = TicTacToeHelper.getAntiDiagonalWinner(board, 3);
            assertEquals(0, winner);
        }
    }

    @Test
    void givenFullBoards_whenIsGameBoardFull_thenReturnTrue() {
        ArrayList<int[][]> fullBoards = testHelper.getFullBoards();

        for (int[][] board : fullBoards) {
            boolean isFull = TicTacToeHelper.isGameBoardFull(board);
            assertEquals(true, isFull);
        }
    }

    @Test
    void givenEmptyBoards_whenIsGameBoardFull_thenReturnFalse() {
        ArrayList<int[][]> nonFullBoards = testHelper.getNonFullBoards();

        for (int[][] board : nonFullBoards) {
            boolean isFull = TicTacToeHelper.isGameBoardFull(board);
            assertEquals(false, isFull);
        }
    }

}