package timooo90.javagamebundle.Snake;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import timooo90.javagamebundle.GUI.GUI;

public class SnakeController {
    private GameLoopTimer timer;
    private GUI graphicalUI;
    private Snake game;
    @FXML
    private VBox playArea;

    public void setGraphicalUI(GUI graphicalUI) {
        this.graphicalUI = graphicalUI;
    }

    public void startNewGame() {
        game = new Snake(this);
        this.timer = new GameLoopTimer(this);
        timer.start();

        game.startGame();

    }

    public void render() {
        game.renderFrame();
        drawGrid();
    }

    private void drawGrid() {
        playArea.getChildren().clear();

        int[][] grid = game.getGrid();

        boolean even = false;
        for (int i = 0; i < grid.length; i++) {
            HBox row = new HBox();
            even = !even;
            for (int j = 0; j < grid[0].length; j++) {
                Rectangle cell = new Rectangle();

                cell.setWidth(35);
                cell.setHeight(35);

                if (even) {
                    cell.getStyleClass().clear();
                    cell.getStyleClass().add("snakeGridCell1");
                } else {
                    cell.getStyleClass().clear();
                    cell.getStyleClass().add("snakeGridCell2");
                }

                if (grid[i][j] == 1) {
                    cell.getStyleClass().clear();
                    cell.getStyleClass().add("snakePartCell");
                }

                even = !even;

                row.getChildren().add(cell);
            }
            playArea.getChildren().add(row);
        }
    }
}
