package timooo90.tictactoe.AIOpponent;

import timooo90.tictactoe.utilities.TicTacToeHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class AdvancedAI {
    private HashMap<String, Board> boards = new HashMap<>();


    public static void main(String[] args) {
        AdvancedAI advancedAI = new AdvancedAI();
        advancedAI.initializeBoards();

        Board board = advancedAI.getBoards().get("0;0;0;0;0;0;0;1;0");

        advancedAI.findBestMoveCoordinates(board.gameBoard, -1);

        System.out.println(TicTacToeHelper.boardToDictionaryKey(board.gameBoard));
    }

    public HashMap<String, Board> getBoards() {
        return boards;
    }

    public void initializeBoards() {
        int[][] gameBoard = TicTacToeHelper.getEmptyBoard();
        Board board = new Board(gameBoard, true);
        addNewBoard(board.key, board);
    }

    public boolean boardExistsAlready(String key) {
        return boards.containsKey(key);
    }

    public void addNewBoard(String key, Board board) {
        boards.put(key, board);

        if (!board.isGameEndPosition()) {
            board.generateChildren(board);
        }
    }

    public String findBestMoveCoordinates(int[][] gameBoard, int player) {
        String key = TicTacToeHelper.boardToDictionaryKey(gameBoard);

        Board board = getBoards().get(key);

        ArrayList<GameResult> results = new ArrayList<>();


        for (Board childBoard: board.getChildren()) {
            if (childBoard.winner == player) {
                return TicTacToeHelper.getNextCoordinatesFromBoardDifference(board.gameBoard, childBoard.gameBoard);
            }

            GameResult result = new GameResult();
            results.add(result);

            result.setRootBoard(childBoard);

            findBestBoard(childBoard, result);
        }

        GameResult bestResult = getBestResult(results);

        return TicTacToeHelper.getNextCoordinatesFromBoardDifference(gameBoard, bestResult.getRootBoard().gameBoard);
    }

    public void findBestBoard(Board board, GameResult result) {

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

            findBestBoard(childBoard, result);

        }
    }

    public GameResult getBestResult(ArrayList<GameResult> results) {
        GameResult optimalResult = results.get(0);

        System.out.println("=================================================================================");
        for (GameResult result : results) {
            System.out.println("AI win depth: " + result.getAIWinDepth() + " | Human win depth: " + result.getHumanWinDepth() + " | Draw depth: " + result.getDrawDepth());
            //System.out.println(Utility.boardToDictionaryKey(result.getBoard().gameBoard));

            TicTacToeHelper.printGameBoard(result.getRootBoard().gameBoard);


            if (result.getHumanWinDepth() > optimalResult.getHumanWinDepth()) {
                optimalResult = result;
            }
            /*
            //if (result.getHumanWinDepth() > optimalResult.getHumanWinDepth()) {
            if (result.isAIWinReached() && result.getAIWinDepth() < result.getHumanWinDepth() && result.getAIWinDepth() < optimalResult.getAIWinDepth()) {
                optimalResult = result;
            }
            else if (result.isDrawReached() && result.getDrawDepth() <= result.getHumanWinDepth()) {
                optimalResult = result;
                System.out.println("Optimal result: " + Utility.boardToDictionaryKey(optimalResult.getBoard().gameBoard));
            }
             */
        }

        return optimalResult;
    }

    public class Board {
        private int[][] gameBoard;
        private boolean humanTurn;
        private String key;
        private int winner = -99;
        private boolean gameEndPosition = false;
        ArrayList<Board> children;

        public Board(int[][] gameBoard, boolean humanTurn) {
            this.gameBoard = gameBoard;
            this.humanTurn = humanTurn;
            this.key = TicTacToeHelper.boardToDictionaryKey(this.gameBoard);
            this.children = new ArrayList<>();

            checkForEndPosition();
        }

        public ArrayList<Board> getChildren() {
            return children;
        }

        public boolean isGameEndPosition() {
            return gameEndPosition;
        }

        private void checkForEndPosition() {
            if (TicTacToeHelper.isGameBoardFull(gameBoard)) {
                gameEndPosition = true;
                winner = 0;
                return;
            }

            int boardWinner = TicTacToeHelper.getWinner(gameBoard, 3);

            if (boardWinner != 0) {
                gameEndPosition = true;
                winner = boardWinner;
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

                        if (!boardExistsAlready(newBoard.key)) {
                            addNewBoard(newBoard.key, newBoard);
                        }
                        else {
                            newBoard = boards.get(newBoard.key);
                        }

                        parentBoard.getChildren().add(newBoard);
                    }
                }
            }
        }
    }
}