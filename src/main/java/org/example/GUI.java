package org.example;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class GUI extends Application {

    public void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Scene scene = new Scene(createContents());

        primaryStage.setScene(scene);

        primaryStage.show();

    }

    private Label createContents() {
        return new Label("Hello world");
    }
}
