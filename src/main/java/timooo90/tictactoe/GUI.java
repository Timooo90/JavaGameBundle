package timooo90.tictactoe;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class GUI extends Application {
    private GUIController controller;
    private TicTacToe game;
    private Stage primaryStage;
    private int[][] gameBoard;


    public void main() {
        launch();
    }

    public int[][] getGameBoard() {
        return gameBoard;
    }

    public GUIController getController() {
        return controller;
    }

    public void setGameBoard(int[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void setController(GUIController controller) {
        this.controller = controller;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.game = new TicTacToe();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI.fxml"));

        Parent root = loader.load();

        setController(loader.getController());

        controller.setGraphicalUI(this);
        controller.setGame(this.game);

        System.out.println(controller.getGame());

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();


    }


    private VBox createPlayArea(int boardSideLength) {
        VBox results = new VBox();
        List<Rectangle> playAreaSquares = new ArrayList<Rectangle>();

        for (int i = 0; i < boardSideLength; i++) {
            HBox row = new HBox();
            for (int j = 0; j < boardSideLength; j++) {
                Rectangle rectangle = new Rectangle(10, 10, 200, 200);
                rectangle.setStroke(Color.CRIMSON);
                rectangle.setStrokeWidth(5);

                rectangle.getStyleClass().add("playAreaSquare");

                rectangle.setId(i + "," + j);

                rectangle.setOnMouseClicked(mouseEvent -> {
                    if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                        System.out.println("Clicked on " + rectangle.getId());
                    }
                });

                row.getChildren().add(rectangle);
            }
            results.getChildren().add(row);
        }
        return results;
    }
}
