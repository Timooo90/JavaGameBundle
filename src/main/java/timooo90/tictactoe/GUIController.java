package timooo90.tictactoe;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class GUIController {

    @FXML
    private void squareClicked(MouseEvent event) {
        Object clicked = event.getSource();

        if (clicked.getClass().equals(Rectangle.class)) {
            Rectangle rectangle = (Rectangle)clicked;

            System.out.println(rectangle.getId());
        }
    }
}
