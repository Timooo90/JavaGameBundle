package timooo90.javagamebundle.GUI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import timooo90.javagamebundle.TicTacToe.TicTacToeController;


public class GUI extends Application {
    private TicTacToeController controller;

    public void main() {
        launch();
    }
    public void setController(TicTacToeController controller) {
        this.controller = controller;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/TicTacToeGUI.fxml"));

        Parent root = loader.load();

        setController(loader.getController());

        controller.setGraphicalUI(this);
        controller.startNewGame();

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
