package org.example.utilities;


public final class Utility {
    private static int defaultSideLength = 3;

    public static int[][] getEmptyBoard() {
        return new int[3][3];
    }

    public static int[][] getEmptyBoard(int sideLength) {
        return new int[sideLength][sideLength];
    }


    public static String boardToDictionaryKey(int[][] gameBoard) {
        return "TODO";
    }

    public static int[][] keyToGameBoard(String key) {
        // Placeholder!
        return new int[3][3];
    }

}
