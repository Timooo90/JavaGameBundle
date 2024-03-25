package timooo90.javagamebundle.Snake;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Snake {
    private int[][] grid;
    private SnakePart head;
    private Direction direction;
    private ArrayList<SnakePart> bodyParts;


    public Snake() {
        this.grid = new int[20][20];
        this.head = new SnakePart(5, 5);
        this.direction = Direction.RIGHT;
    }

    public void startGame() {
        Timer timer = new Timer(true);
        timer.schedule(new renderTimer(), 0, 500);
    }

    private void renderFrame() {
        System.out.println("Yayyy");
    }

    private void moveSnakeParts() {
        int prevX = head.getXPosition();
        int prevY = head.getYPosition();

        switch (direction) {
            case UP: movePart(head, prevX, prevY - 1);
            case DOWN: movePart(head, prevX, prevY + 1);
            case LEFT:  movePart(head, prevX - 1, prevY);
            case RIGHT: movePart(head, prevX + 1, prevY);
        }
    }

    private void movePart(SnakePart part, int targetX, int targetY) {

    }

    private class renderTimer extends TimerTask {
        public void run() {
            renderFrame();
        }
    }

    public int[][] getGrid() {
        return grid;
    }
}
