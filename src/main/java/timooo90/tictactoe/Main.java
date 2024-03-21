package timooo90.tictactoe;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        TicTacToe game = new TicTacToe();

        GUI gui = new GUI();

        for (int i = 0; i < game.getGameBoard().length; i++) {
            System.out.println(Arrays.toString(game.getGameBoard()[i]));
        }

        gui.main();





        //game.printGameBoard();
    }
}