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

        int[][] gameBoard = new int[][] { {1, -1, 0}, {0, 0, 0}, {0, 1, 0} };

        Board board = advancedAI.getBoards().get(TicTacToeHelper.boardToDictionaryKey(gameBoard));

        String coordinates = advancedAI.findBestMoveCoordinates(gameBoard, -1);
        System.out.println(coordinates);

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
        gameResult.increaseDepth();


        while (childrenToCheck.size() > 0) {
            LinkedList<Board> nextDepthOfChildren = new LinkedList<>();
            for (Board child : childrenToCheck) {
                nextDepthOfChildren.addAll(child.getChildren());
            }

            Board nextChild = childrenToCheck.removeFirst();

            switch (nextChild.winner) {
                case -1: {
                    gameResult.setAIWinReached(true);
                    return;
                }
                case 1: {
                    gameResult.setHumanWinReached(true);
                }
                case 0: {
                    gameResult.setDrawReached(true);
                }
            }


            if (childrenToCheck.size() == 0 && nextDepthOfChildren.size() != 0) {
                System.out.println("asdasd");
                childrenToCheck.addAll(nextDepthOfChildren);
                nextDepthOfChildren.clear();
                gameResult.increaseDepth();
            }
            System.out.println("Children to check size " + childrenToCheck.size());
            System.out.println("Next size " + nextDepthOfChildren.size());
        }
    }

    private GameResult getBestResult(ArrayList<GameResult> results) {
        GameResult optimalResult = results.get(0);

        System.out.println("=================================================================================");
        for (GameResult result : results) {
            System.out.println("AI win depth: " + result.getAIWinDepth() + " | Human win depth: " + result.getHumanWinDepth() + " | Draw depth: " + result.getDrawDepth());

            TicTacToeHelper.printGameBoard(result.getRootBoard().gameBoard);

            if (result.getAIWinDepth() == 0) {
                return result;
            }

            if (result.getHumanWinDepth() <= 1) {
                continue;
            }

            if (result.getHumanWinDepth() > optimalResult.getAIWinDepth()) {
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