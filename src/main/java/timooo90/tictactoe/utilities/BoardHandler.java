package timooo90.tictactoe.utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class BoardHandler {
    private static int boardCount;
    private HashMap<String, Board> boards = new HashMap<>();

    public HashMap<String, Board> getBoards() {
        return boards;
    }

    public static void main(String[] args) {
        BoardHandler boardHandler = new BoardHandler();
        boardHandler.initializeBoards();
    }

    public void initializeBoards() {
        int[][] gameBoard = Utility.getEmptyBoard();
        Board board = new Board(gameBoard, true);
        addNewBoard(board.key, board);
    }

    public boolean boardExistsAlready(String key) {
        return boards.containsKey(key);
    }

    public void addNewBoard(String key, Board board) {
        boards.put(key, board);
        boardCount++;

        if (!board.isGameEndPosition()) {
            board.generateChildren(board);
        }
    }

    public String findBestMoveCoordinates(int[][] gameBoard, int player) {
        String key = Utility.boardToDictionaryKey(gameBoard);

        Board board = getBoards().get(key);

        ArrayList<GameResult> results = new ArrayList<>();

        Board bestBoard;

        if (board.getChildren().size() > 0) {
            bestBoard = board.getChildren().get(0);
        }

        for (Board childBoard: board.getChildren()) {
            if (childBoard.winner == player) {
                bestBoard = childBoard;
                break;
            }
            GameResult result = new GameResult();
            results.add(result);

            result.setBoard(childBoard);
            result.setDepth(result.getDepth() + 1);

            findBestBoard(childBoard, player, result);
        }

        GameResult optimalResult = results.get(0);

        for (GameResult result : results) {
            if (result.getBoard().getWinner() == player && result.getDepth() < optimalResult.getDepth()) {
                optimalResult = result;
            }
        }


        return Utility.getNextCoordinatesFromBoardDifference(board.gameBoard, optimalResult.getBoard().gameBoard);
    }

    public void findBestBoard(Board board, int player, GameResult result) {

        for (Board childBoard: board.getChildren()) {
            if (childBoard.winner == player) {
                result.setDepth(result.getDepth() + 1);
                break;
            }

            result.setDepth(result.getDepth() + 1);

            findBestBoard(childBoard, player, result);
        }
    }


    public class Board {
        private int[][] gameBoard;
        private boolean humanTurn;
        private String key;
        private int winner = 0;
        private int score = 0;
        private boolean gameEndPosition = false;

        ArrayList<Board> children;

        public Board(int[][] gameBoard, boolean humanTurn) {
            this.gameBoard = gameBoard;
            this.humanTurn = humanTurn;
            this.key = Utility.boardToDictionaryKey(this.gameBoard);
            this.children = new ArrayList<>();

            checkForEndPosition();
        }

        public ArrayList<Board> getChildren() {
            return children;
        }

        public boolean isGameEndPosition() {
            return gameEndPosition;
        }

        public int getWinner() {
            return winner;
        }

        public void setWinner(int winner) {
            this.winner = winner;
        }

        private void checkForEndPosition() {
            int antiDiagonalIndex = gameBoard.length - 1;
            int antiDiagonalSum = 0;

            for (int i = 0; i < gameBoard.length; i++) {
                // Check anti diagonal.
                antiDiagonalSum += gameBoard[antiDiagonalIndex][i];
                antiDiagonalIndex--;
                // Check rows.
                int rowSum = Arrays.stream(gameBoard[i]).sum();

                if (rowSum == 3 || rowSum == -3) {
                    setWinner(rowSum);
                    gameEndPosition = true;
                    return;
                }

                // Check columns and diagonal.
                int columnSum = 0;
                int diagonalSum = 0;
                for (int j = 0; j < gameBoard.length; j++) {
                    columnSum += gameBoard[j][i];
                    if (i == j) {
                        diagonalSum += gameBoard[i][j];
                    }
                }

                if (columnSum == 3 || columnSum == -3) {
                    setWinner(columnSum);
                    gameEndPosition = true;
                    return;
                }

                if (diagonalSum == 3 || diagonalSum == -3) {
                    setWinner(diagonalSum);
                    gameEndPosition = true;
                    return;
                }

                if (antiDiagonalSum == 3 || antiDiagonalSum == -3) {
                    setWinner(antiDiagonalSum);
                    gameEndPosition = true;
                    return;
                }
            }
        }


        private int[][] getDeepCopyOfGameBoard() {
            int[][] newGameBoard = new int[gameBoard.length][gameBoard.length];

            for (int i = 0; i < gameBoard.length; i++) {
                for (int j = 0; j < gameBoard.length; j++) {
                    newGameBoard[i][j] = gameBoard[i][j];
                }
            }

            return newGameBoard;
        }

        public void generateChildren(Board parentBoard) {
            int player = humanTurn ? 1 : -1;

            for (int i = 0; i < gameBoard.length; i++) {
                for (int j = 0; j < gameBoard.length; j++) {
                    if (gameBoard[i][j] == 0) {
                        int[][] newGameBoard = getDeepCopyOfGameBoard();
                        newGameBoard[i][j] = player;

                        Board newBoard = new Board(newGameBoard, !humanTurn);

                        parentBoard.getChildren().add(newBoard);

                        if (!boardExistsAlready(newBoard.key)) {
                            addNewBoard(key, newBoard);
                        }
                    }
                }
            }
        }
    }
}