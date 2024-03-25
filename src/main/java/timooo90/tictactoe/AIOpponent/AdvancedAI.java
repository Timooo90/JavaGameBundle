package timooo90.tictactoe.AIOpponent;

import timooo90.tictactoe.utilities.TicTacToeHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class AdvancedAI {
    private HashMap<String, Board> boards = new HashMap<>();


    public HashMap<String, Board> getBoards() {
        return boards;
    }

    public static void main(String[] args) {
        AdvancedAI advancedAI = new AdvancedAI();
        advancedAI.initializeBoards();

        int[][] gameBoard = new int[][] { {1, 1, -1}, {0, 0, 1}, {1, -1, -1} };

        Board board = advancedAI.getBoards().get(TicTacToeHelper.boardToDictionaryKey(gameBoard));

        int[][] gameBoard2 = new int[][] { {1, 1, -1}, {1, -1, 1}, {1, -1, -1} };

        Board board2 = advancedAI.getBoards().get(TicTacToeHelper.boardToDictionaryKey(gameBoard2));

        System.out.println(board2.winner);

        /*
        for (Board child : board.getChildren()) {
            TicTacToeHelper.printGameBoard(child.gameBoard);

            for (Board grandChild : child.getChildren()) {
                TicTacToeHelper.printGameBoard(grandChild.gameBoard);
                System.out.println("Winner: " + grandChild.winner);
            }
        }
         */


        /*
        String coordinates = advancedAI.findBestMoveCoordinates(gameBoard, -1);
        System.out.println(coordinates);
         */

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
        // If center is free, place there.
        if (gameBoard[1][1] == 0) {
            return "11";
        }

        String key = TicTacToeHelper.boardToDictionaryKey(gameBoard);
        Board board = getBoards().get(key);

        Board optimalChild = findOptimalChildBoard(board);

        return TicTacToeHelper.getNextCoordinatesFromBoardDifference(gameBoard, optimalChild.gameBoard);
    }

    private Board findOptimalChildBoard(Board board) {
        ArrayList<GameResult> results = new ArrayList<>();

        for (Board childBoard : board.getChildren()) {
            LinkedList<Board> childrenToCheck = new LinkedList<>();
            childrenToCheck.add(childBoard);
            GameResult gameResult = new GameResult(childBoard);
            results.add(gameResult);

            findGameResults(gameResult, childrenToCheck);
        }

        GameResult bestResult = getBestResult(results);

        return bestResult.getRootBoard();
    }


    private void findGameResults(GameResult gameResult, LinkedList<Board> childrenToCheck) {

        while (childrenToCheck.size() > 0) {
            LinkedList<Board> nextDepthOfChildren = new LinkedList<>();
            for (Board child : childrenToCheck) {
                nextDepthOfChildren.addAll(child.getChildren());
            }

            Board nextChild = childrenToCheck.removeFirst();

            switch (nextChild.winner) {
                case -1: {
                    gameResult.setAIWinReached(true);
                }
                case 1: {
                    gameResult.setHumanWinReached(true);
                }
                case 0: {
                    gameResult.setDrawReached(true);
                }
            }

            if (childrenToCheck.size() == 0 && nextDepthOfChildren.size() != 0) {
                childrenToCheck.addAll(nextDepthOfChildren);
                nextDepthOfChildren.clear();
                gameResult.increaseDepth();
            }
        }
    }

    private GameResult getBestResult(ArrayList<GameResult> results) {
        GameResult optimalResult = results.get(0);

        System.out.println("=================================================================================");
        for (GameResult result : results) {
            if (!result.isHumanWinReached()) {
                result.setHumanWinDepth(99);
            }
            if (!result.isAIWinReached()) {
                result.setAIWinDepth(99);
            }
            if (!result.isDrawReached()) {
                result.setDrawDepth(99);
            }

            System.out.println("AI win depth: " + result.getAIWinDepth() + " | Human win depth: " + result.getHumanWinDepth() + " | Draw depth: " + result.getDrawDepth());

            TicTacToeHelper.printGameBoard(result.getRootBoard().gameBoard);
            System.out.println("AI win reached: " + result.isAIWinReached());
            System.out.println("Human win reached: " + result.isHumanWinReached());

            // If AI can win on next move, choose that.
            if (result.isAIWinReached() && result.getAIWinDepth() <= 1) {
                return result;
            }
            // Or if human can't win at all (AKA a draw at worst), choose that.
            else if (!result.isHumanWinReached()) {
                optimalResult = result;
            }
            else if (result.getHumanWinDepth() > optimalResult.getAIWinDepth()) {
                optimalResult = result;
            }
            else if (result.getHumanWinDepth() > optimalResult.getHumanWinDepth()) {
                optimalResult = result;
            }
            else if (result.getHumanWinDepth() > optimalResult.getDrawDepth()) {
                optimalResult = result;
            }

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
            int boardWinner = TicTacToeHelper.getWinner(gameBoard, 3);

            if (boardWinner != 0) {
                gameEndPosition = true;
                winner = boardWinner;
                return;
            }

            if (TicTacToeHelper.isGameBoardFull(gameBoard)) {
                gameEndPosition = true;
                winner = 0;
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