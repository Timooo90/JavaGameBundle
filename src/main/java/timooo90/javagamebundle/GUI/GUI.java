package timooo90.javagamebundle.GUI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import timooo90.javagamebundle.MainMenuController;
import timooo90.javagamebundle.TicTacToe.TicTacToeController;


public class GUI extends Application {
    private MainMenuController mainMenuController;
    private TicTacToeController ticTacToeController;
    private Stage primaryStage;

    public void main() {
        launch();
    }
    public void setTicTacToeController(TicTacToeController ticTacToeController) {
        this.ticTacToeController = ticTacToeController;
    }

    public void setMainMenuController(MainMenuController mainMenuController) {
        this.mainMenuController = mainMenuController;
    }

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;

        startMainMenu();
    }

    public void startMainMenu() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainMenu.fxml"));

        Parent root = loader.load();

        setMainMenuController(loader.getController());

        mainMenuController.setGraphicalUI(this);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void startTicTacToe() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/TicTacToe.fxml"));
        Parent root = loader.load();

        setTicTacToeController(loader.getController());

        ticTacToeController.setGraphicalUI(this);
        ticTacToeController.startNewGame();

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
