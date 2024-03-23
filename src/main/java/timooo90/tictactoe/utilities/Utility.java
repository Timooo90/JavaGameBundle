package timooo90.tictactoe.utilities;
import java.lang.Math;


public final class Utility {
    private static int defaultSideLength = 3;

    public static void main(String[] args) {
        int[][] gameBoard = getEmptyBoard();

    }

    public static int[][] getEmptyBoard() {
        return new int[defaultSideLength][defaultSideLength];
    }

    public static int[][] getEmptyBoard(int sideLength) {
        return new int[sideLength][sideLength];
    }


    public static String boardToDictionaryKey(int[][] gameBoard) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                stringBuilder.append(gameBoard[i][j]);
            }
        }

        return stringBuilder.toString();
    }

    public static int[][] keyToGameBoard(String key) {
        int sideLength = (int)Math.sqrt(key.length());
        int[][] newBoard = new int[sideLength][sideLength];

        int stringIndex = 0;
        for (int i = 0; i < sideLength; i++) {
            for (int j = 0; j < sideLength; j++) {
                newBoard[i][j] = key.charAt(stringIndex);
                stringIndex++;
            }
        }
        return newBoard;
    }

}
