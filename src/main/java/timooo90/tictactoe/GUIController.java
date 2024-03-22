package timooo90.tictactoe;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class GUIController {
    private TicTacToe game;
    private GUI graphicalUI;

    public GUIController() {
        System.out.println("I've been created!");
    }

    public TicTacToe getGame() {
        return game;
    }

    public void setGame(TicTacToe game) {
        this.game = game;
    }

    public GUI getGraphicalUI() {
        return graphicalUI;
    }

    public void setGraphicalUI(GUI graphicalUI) {
        this.graphicalUI = graphicalUI;
    }

    @FXML
    private void squareClicked(MouseEvent event) {
        Object clicked = event.getSource();

        if (clicked.getClass().equals(Rectangle.class)) {
            Rectangle rectangle = (Rectangle)clicked;

            game.handleMouseClick(rectangle.getId());
        }
    }

}
