package timooo90.javagamebundle;

import javafx.fxml.FXML;
import timooo90.javagamebundle.GUI.GUI;

public class MainMenuController {
    private GUI graphicalUI;

    public void setGraphicalUI(GUI graphicalUI) {
        this.graphicalUI = graphicalUI;
    }

    @FXML
    private void startTicTacToe() {
        try {
            graphicalUI.startTicTacToe();
        } catch (Exception e) {
            System.out.println("Failed to start Tic Tac Toe.");;
        }
    }

    @FXML
    private void startSnake() {
        try {
            graphicalUI.startSnake();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to start Snake.");;
        }
    }

}
