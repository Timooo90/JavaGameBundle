package timooo90.tictactoe.utilities;
import java.lang.Math;
import java.util.Arrays;


public final class Utility {
    private static int defaultSideLength = 3;

    public static int[][] getEmptyBoard() {
        return new int[defaultSideLength][defaultSideLength];
    }

    public static int[][] getEmptyBoard(int sideLength) {
        return new int[sideLength][sideLength];
    }

    public static String boardToDictionaryKey(int[][] gameBoard) {
        String[] boardPieces = new String[gameBoard.length * gameBoard.length];

        int piecesIndex = 0;
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                boardPieces[piecesIndex] = String.valueOf(gameBoard[i][j]);
                piecesIndex++;
            }
        }
        return String.join(";", boardPieces);
    }

    public static int[][] keyToGameBoard(String key) {
        int sideLength = (int)Math.sqrt(key.length());
        int[][] newBoard = new int[sideLength][sideLength];

        String[] keyPieces = key.split(";");

        int pieceIndex = 0;
        for (int i = 0; i < sideLength; i++) {
            for (int j = 0; j < sideLength; j++) {
                newBoard[i][j] = Integer.parseInt(keyPieces[pieceIndex]);
                pieceIndex++;
            }
        }
        return newBoard;
    }

    public static String getNextCoordinatesFromBoardDifference(int[][] current, int[][] next) {
        String coordinates = "";
        for (int i = 0; i < current.length; i++) {
            for (int j = 0; j < current.length; j++) {
                if (current[i][j] != next[i][j]) {
                    coordinates = String.valueOf(i) + String.valueOf(j);
                }
            }
        }

        return coordinates;
    }

    public static void printGameBoard(int[][] gameBoard) {
        System.out.println("================================");
        for (int i = 0; i < gameBoard.length; i++) {
            System.out.println(Arrays.toString(gameBoard[i]));
        }
        System.out.println("================================");
    }

}
