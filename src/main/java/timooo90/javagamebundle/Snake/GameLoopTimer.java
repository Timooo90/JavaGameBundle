package timooo90.javagamebundle.Snake;

import javafx.animation.AnimationTimer;

public class GameLoopTimer extends AnimationTimer {
    private SnakeController controller;
    private long lastUpdate = 0;

    public GameLoopTimer(SnakeController controller) {
        this.controller = controller;
    }

    @Override
    public void handle(long now) {
        if (now - lastUpdate >= 500_000_000) {
            System.out.println("Yay!");

            lastUpdate = now;

            controller.render();
        }

    }
}
