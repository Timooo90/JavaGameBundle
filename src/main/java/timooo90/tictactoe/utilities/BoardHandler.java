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

        for (Board childBoard: board.getChildren()) {
            if (childBoard.winner == player) {
                bestBoard = childBoard;
                return Utility.getNextCoordinatesFromBoardDifference(board.gameBoard, bestBoard.gameBoard);
            }
            else if (childBoard.winner == -player) {
                continue;
            }
            GameResult result = new GameResult();
            results.add(result);

            result.setBoard(childBoard);

            findBestBoard(childBoard, player, result);
        }

        if (results.size() < 1) {
            return Utility.getNextCoordinatesFromBoardDifference(board.gameBoard, gameBoard);
        }

        GameResult optimalResult = results.get(0);

        for (GameResult result : results) {
            if (result.isAIWinReached() && result.getAIWinDepth() < result.getHumanWinDepth()) {
                optimalResult = result;
            }
            else if (result.isDrawReached() && result.getDrawDepth() < result.getHumanWinDepth() && !optimalResult.isAIWinReached()) {
                optimalResult = result;
            }
        }

        bestBoard = optimalResult.getBoard();


        return Utility.getNextCoordinatesFromBoardDifference(board.gameBoard, bestBoard.gameBoard);
    }

    public void findBestBoard(Board board, int player, GameResult result) {

        for (Board childBoard: board.getChildren()) {

            switch (childBoard.winner) {
                case -1: {
                    result.setAIWinReached(true);
                }
                case 1: {
                    result.setHumanWinReached(true);
                }
                case 0: {
                    result.setDrawReached(true);
                }
            }

            result.increaseDepth();

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
                    setWinner(rowSum/3);
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
                    setWinner(columnSum/3);
                    gameEndPosition = true;
                    return;
                }

                if (diagonalSum == 3 || diagonalSum == -3) {
                    setWinner(diagonalSum/3);
                    gameEndPosition = true;
                    return;
                }

                if (antiDiagonalSum == 3 || antiDiagonalSum == -3) {
                    setWinner(antiDiagonalSum/3);
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
                            addNewBoard(newBoard.key, newBoard);
                        }
                    }
                }
            }
        }
    }
}