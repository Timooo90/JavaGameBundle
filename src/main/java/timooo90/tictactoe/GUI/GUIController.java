package timooo90.tictactoe.GUI;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import timooo90.tictactoe.TicTacToe;

import java.util.HashMap;

public class GUIController {
    @FXML
    private VBox contentBox;
    @FXML
    private Label gameEndLabel;
    HashMap<String, Label> squareLabels;
    private TicTacToe game;
    private GUI graphicalUI;


    public void startNewGame() {
        gameEndLabel.setText("");
        this.game = new TicTacToe(this);
        squareLabels = new HashMap<>();
        contentBox.getChildren().clear();

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
            getContentBox().getChildren().add(row);
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
                game.handleMouseClick(rectangle.getId());
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

    public void setSquareLabelValue(String labelID ,String value) {
        Label label = squareLabels.get(labelID);
        label.setText(value);
    }

    public void setGraphicalUI(GUI graphicalUI) {
        this.graphicalUI = graphicalUI;
    }

    public VBox getContentBox() {
        return contentBox;
    }

    public void generateGameEndResult(int winner) {
        String endText = "";

        if (winner == 0) {
            endText = "Draw!";
        }
        else if (winner < 0) {
            endText = "The computer won!";
        }
        else {
            endText = "You won!";
        }

        gameEndLabel.setText(endText);
    }

}
