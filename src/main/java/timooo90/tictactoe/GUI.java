package timooo90.tictactoe;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class GUI extends Application {
    private GUIController controller;
    private TicTacToe game;

    public void main() {
        launch();
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

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}
