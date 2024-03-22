package timooo90.tictactoe;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        TicTacToe game = new TicTacToe();

        GUI gui = new GUI();

        GUIController controller = new GUIController();

        controller.setGame(game);
        controller.setGraphicalUI(gui);

        gui.main();


        //gui.main();


        //game.printGameBoard();
    }
}