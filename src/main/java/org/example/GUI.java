package org.example;
import javafx.application.Application;
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
    private Stage primaryStage;
    private int[][] gameBoard;




    public void main() {
        launch();
    }


    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(createPlayArea(3));

        scene.getStylesheets().add("gui.css");


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

    public void setGameBoard(int[][] gameBoard) {
        this.gameBoard = gameBoard;
    }
}
