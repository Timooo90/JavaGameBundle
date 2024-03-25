package timooo90.javagamebundle.TicTacToe.utilities;

import java.util.ArrayList;
import java.util.Random;

public class TestHelper {
    private Random random = new Random();
    private ArrayList<int[][]> humanRowWinPositions;
    private ArrayList<int[][]> humanColumnWinPositions;
    private ArrayList<int[][]> AIRowWinPositions;
    private ArrayList<int[][]> AIColumnWinPositions;
    private int[][] humanDiagonalWinPosition;
    private int[][] humanAntiDiagonalWinPosition;
    private int[][] AIDiagonalWinPosition;
    private int[][] AIAntiDiagonalWinPosition;
    private ArrayList<int[][]> noWinnerPositions;
    private ArrayList<int[][]> fullBoards;
    private ArrayList<int[][]> nonFullBoards;



    public TestHelper() {
        initializeWinPositions();
        initializeNonWinPositions();
        initializeFullBoards();
        initializeNonFullBoards();
    }

    private void initializeFullBoards() {
        ArrayList<int[][]> boards = new ArrayList<>();

        int[][] pos1 = {
                {1, -1, 1},
                {1, -1, -1},
                {1, 1, -1} };

        int[][] pos2 = {
                {1, -1, 1},
                {1, 1, -1},
                {-1, 1, -1} };

        int[][] pos3 = {
                {-1, -1, 1},
                {1, -1, 1},
                {1, 1, 1} };

        int[][] pos4 = {
                {-1, 1, 1},
                {1, -1, -1},
                {1, -1, 1} };

        fullBoards = boards;
    }

    private void initializeNonFullBoards() {
        ArrayList<int[][]> boards = new ArrayList<>();

        int[][] pos1 = {
                {1, -1, 1},
                {1, 0, -1},
                {1, 1, -1} };

        int[][] pos2 = {
                {0, -1, 1},
                {1, 1, -1},
                {-1, 1, -1} };

        int[][] pos3 = {
                {-1, -1, 1},
                {1, -1, 1},
                {1, 1, 0} };

        int[][] pos4 = {
                {0, 1, 0},
                {1, 0, -1},
                {1, -1, 0} };

        nonFullBoards = boards;
    }

    private void initializeNonWinPositions() {
        ArrayList<int[][]> nonWinPositions = new ArrayList<>();

        int[][] pos1 = {
                {0, -1, 1},
                {1, 0, -1},
                {0, 0, 0} };

        int[][] pos2 = {
                {1, -1, 1},
                {1, 1, -1},
                {-1, 0, 0} };

        int[][] pos3 = {
                {-1, -1, 1},
                {1, -1, 1},
                {1, 1, 0} };

        int[][] pos4 = {
                {0, 0, 0},
                {1, -1, -1},
                {1, 0, 1} };

        nonWinPositions.add(pos1);
        nonWinPositions.add(pos2);
        nonWinPositions.add(pos3);
        nonWinPositions.add(pos4);

        noWinnerPositions = nonWinPositions;
    }

    private void initializeWinPositions() {
        createPlayerWinPositions(1);
        createPlayerWinPositions(-1);
    }

    private void createPlayerWinPositions(int playerNumber) {
        ArrayList<int[][]> rowWinPositions = new ArrayList<>();
        ArrayList<int[][]> columnWinPositions = new ArrayList<>();

        int[] emptyRow = new int[3];
        int[] winningRow = {playerNumber, playerNumber, playerNumber};

        int[][] pos1 = new int[][] { winningRow, emptyRow, emptyRow };
        int[][] pos2 = new int[][] { emptyRow, winningRow, emptyRow };
        int[][] pos3 = new int[][] { emptyRow, emptyRow, winningRow };
        rowWinPositions.add(pos1);
        rowWinPositions.add(pos2);
        rowWinPositions.add(pos3);

        int[][] pos4 = new int[][] { {playerNumber, 0, 0}, {playerNumber, 0, 0}, {playerNumber, 0, 0} };
        int[][] pos5 = new int[][] { {0, playerNumber, 0}, {0, playerNumber, 0}, {0, playerNumber, 0} };
        int[][] pos6 = new int[][] { {0, 0, playerNumber}, {0, 0, playerNumber}, {0, 0, playerNumber} };
        columnWinPositions.add(pos4);
        columnWinPositions.add(pos5);
        columnWinPositions.add(pos6);

        int[][] pos7 = new int[][] { {playerNumber, 0, 0}, {0, playerNumber, 0}, {0, 0, playerNumber} };
        int[][] pos8 = new int[][] { {0, 0, playerNumber}, {0, playerNumber, 0}, {playerNumber, 0, 0} };


        if (playerNumber == 1) {
            humanRowWinPositions = rowWinPositions;
            humanColumnWinPositions = columnWinPositions;
            humanDiagonalWinPosition = pos7;
            humanAntiDiagonalWinPosition = pos8;
        } else {
            AIRowWinPositions = rowWinPositions;
            AIColumnWinPositions = columnWinPositions;
            AIDiagonalWinPosition = pos7;
            AIAntiDiagonalWinPosition = pos8;
        }
    }



    // GETTERS
    public ArrayList<int[][]> getHumanRowWinPositions() {
        return humanRowWinPositions;
    }

    public ArrayList<int[][]> getHumanColumnWinPositions() {
        return humanColumnWinPositions;
    }

    public ArrayList<int[][]> getAIRowWinPositions() {
        return AIRowWinPositions;
    }

    public ArrayList<int[][]> getAIColumnWinPositions() {
        return AIColumnWinPositions;
    }

    public int[][] getHumanDiagonalWinPosition() {
        return humanDiagonalWinPosition;
    }

    public int[][] getHumanAntiDiagonalWinPosition() {
        return humanAntiDiagonalWinPosition;
    }

    public int[][] getAIDiagonalWinPosition() {
        return AIDiagonalWinPosition;
    }

    public int[][] getAIAntiDiagonalWinPosition() {
        return AIAntiDiagonalWinPosition;
    }

    public ArrayList<int[][]> getNoWinnerPositions() {
        return noWinnerPositions;
    }

    public ArrayList<int[][]> getFullBoards() {
        return fullBoards;
    }

    public ArrayList<int[][]> getNonFullBoards() {
        return nonFullBoards;
    }
}
