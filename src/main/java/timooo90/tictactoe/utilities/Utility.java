package timooo90.tictactoe.utilities;
import java.lang.Math;
import java.util.HashMap;


public final class Utility {
    private static int defaultSideLength = 3;

    /*
    public static void main(String[] args) {
        int[][] gameBoard = getEmptyBoard();
        Board board = new Board(gameBoard, true);

        System.out.println(Board.numberOfBoards);
    }
     */

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

}
