package timooo90.tictactoe.utilities;
import java.lang.Math;
import java.util.Arrays;


public final class TicTacToeHelper {
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

    public static int getWinner(int[][] gameBoard, int winThreshold) {
        int rowWinner = getRowWinner(gameBoard, winThreshold);
        if (rowWinner != 0) { return rowWinner; }

        int columnWinner = getColumnWinner(gameBoard, winThreshold);
        if (columnWinner != 0) { return columnWinner; }

        int diagonalWinner = getDiagonalWinner(gameBoard, winThreshold);
        if (diagonalWinner != 0) { return diagonalWinner; }

        int antiDiagonalWinner = getAntiDiagonalWinner(gameBoard, winThreshold);
        if (antiDiagonalWinner != 0) { return antiDiagonalWinner; }

        return 0;
    }

    public static int getRowWinner(int[][] gameBoard, int winThreshold) {
        for (int i = 0; i < gameBoard.length; i++) {
            int rowSum = gameBoard[i][0];

            for (int j = 0; j < gameBoard.length - 1; j++) {
                if (gameBoard[i][j] != gameBoard[i][j+1]) {
                    break;
                }
                rowSum += gameBoard[i][j+1];
            }

            if (rowSum >= winThreshold || rowSum <= -winThreshold) {
                return rowSum < 0 ? -1 : 1;
            }
        }
        return 0;
    }

    public static int getColumnWinner(int[][] gameBoard, int winThreshold) {
        for (int i = 0; i < gameBoard.length; i++) {
            int columnSum = gameBoard[0][i];

            for (int j = 0; j < gameBoard.length - 1; j++) {
                if (gameBoard[j][i] != gameBoard[j+1][i]) {
                    break;
                }
                columnSum += gameBoard[j+1][i];

                if (columnSum >= winThreshold || columnSum <= -winThreshold) {
                    return columnSum < 0 ? -1 : 1;
                }
            }
        }
        return 0;
    }

    public static int getDiagonalWinner(int[][] gameBoard, int winThreshold) {
        int diagonalSum = gameBoard[0][0];

        for (int i = 0; i < gameBoard.length - 1; i++) {
            if (gameBoard[i][i] != gameBoard[i+1][i+1]) {
                break;
            }
            diagonalSum += gameBoard[i+1][i+1];
        }
        if (diagonalSum >= winThreshold || diagonalSum <= -winThreshold) {
            return diagonalSum < 0 ? -1 : 1;
        }
        return 0;
    }

    public static int getAntiDiagonalWinner(int[][] gameBoard, int winThreshold) {
        int row = 0;
        int column = gameBoard.length - 1;

        int antiDiagonalSum = gameBoard[row][column];

        while (column >= 1) {
            if (gameBoard[row][column] != gameBoard[row+1][column-1]) {
                break;
            }

            antiDiagonalSum += gameBoard[row+1][column-1];
            row += 1;
            column -= 1;
        }

        if (antiDiagonalSum >= winThreshold || antiDiagonalSum <= -winThreshold) {
            return antiDiagonalSum < 0 ? -1 : 1;
        }

        return 0;
    }

    public static boolean isGameBoardFull(int[][] gameBoard) {
        for (int i = 0; i < gameBoard.length; i++){
            for (int j = 0; j < gameBoard.length; j++) {
                if (gameBoard[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

}
