package timooo90.tictactoe;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GUIController {
    @FXML
    private VBox mainVBox;
    HashMap<String, Label> squareLabels;

    private TicTacToe game;
    private GUI graphicalUI;


    public TicTacToe getGame() {
        return game;
    }

    public void setGame(TicTacToe game) {
        this.game = game;
        squareLabels = new HashMap<>();
        generatePlayAreaSquares();
    }

    private void generatePlayAreaSquares() {
        int boardSideLength = game.getGameBoard().length;

        for (int i = 0; i < boardSideLength; i++) {
            HBox row = new HBox();
            for (int j = 0; j < boardSideLength; j++) {
                Rectangle rectangle = generateSingleSquare(i, j);
                Label label = createLabel(i, j);

                StackPane stackPane = new StackPane(rectangle, label);

                row.getChildren().addAll(stackPane);
            }
            getMainVBox().getChildren().add(row);
        }
    }

    private Rectangle generateSingleSquare(int i, int j) {
        Rectangle rectangle = new Rectangle(10, 10, 200, 200);
        rectangle.setStroke(Color.CRIMSON);
        rectangle.setStrokeWidth(5);

        rectangle.getStyleClass().add("playAreaSquare");

        rectangle.setId(String.valueOf(i) + String.valueOf(j));

        rectangle.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                System.out.println("Clicked on " + rectangle.getId());
                game.handleMouseClick(rectangle.getId());

                setSquareLabelValue("L" + rectangle.getId(), "X");
            }
        });

        return rectangle;
    }

    private Label createLabel(int i, int j) {
        Label label = new Label();
        label.setId("L" + i + j);
        label.getStyleClass().add("playerMoves");

        squareLabels.put(label.getId(), label);

        return label;
    }

    private void setSquareLabelValue(String labelID ,String value) {
        Label label = squareLabels.get(labelID);
        label.setText(value);
    }

    public GUI getGraphicalUI() {
        return graphicalUI;
    }

    public void setGraphicalUI(GUI graphicalUI) {
        this.graphicalUI = graphicalUI;
    }

    public VBox getMainVBox() {
        return mainVBox;
    }

    public void setMainVBox(VBox mainVBox) {
        this.mainVBox = mainVBox;
    }

    @FXML
    private void squareClicked(MouseEvent event) {
        Object clicked = event.getSource();

        if (clicked.getClass().equals(Rectangle.class)) {
            Rectangle rectangle = (Rectangle)clicked;

            int[][] newBoardState = game.handleMouseClick(rectangle.getId());

            graphicalUI.setGameBoard(newBoardState);
        }
    }
}
