package timooo90.tictactoe.utilities;

import java.util.ArrayList;
import java.util.HashMap;

public class BoardHandler {
    private static int boardCount;
    private HashMap<String, Board> boards = new HashMap<>();

    public static void main(String[] args) {
        BoardHandler boardHandler = new BoardHandler();
        boardHandler.initializeBoards();
        System.out.println(boardCount);
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
        board.generateChildren();
    }


    public class Board {
        private int[][] gameBoard;
        private boolean humanTurn = true;
        private String key;
        private int score = 0;

        ArrayList<Board> children;

        public Board(int[][] gameBoard, boolean humanTurn) {
            this.gameBoard = gameBoard;
            this.humanTurn = humanTurn;
            this.key = Utility.boardToDictionaryKey(this.gameBoard);
            this.children = new ArrayList<>();

            calculateScore();
        }

        private void calculateScore() {
            System.out.println(this.key);
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

        public void generateChildren() {
            int player = humanTurn ? 1 : -1;

            for (int i = 0; i < gameBoard.length; i++) {
                for (int j = 0; j < gameBoard.length; j++) {
                    if (gameBoard[i][j] == 0) {
                        int[][] newGameBoard = getDeepCopyOfGameBoard();
                        newGameBoard[i][j] = player;

                        Board newBoard = new Board(newGameBoard, !humanTurn);

                        if (!boardExistsAlready(newBoard.key)) {
                            addNewBoard(key, newBoard);
                        }
                    }
                }
            }
        }
    }
}