package timooo90.javagamebundle.Snake;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Snake {
    private int gridSize = 20;
    private SnakeController controller;
    private int[][] grid;
    private SnakePart head;
    private Direction direction;
    private ArrayList<SnakePart> bodyParts;


    public Snake(SnakeController controller) {
        this.controller = controller;
        this.grid = new int[gridSize][gridSize];
        this.head = new SnakePart(5, 5);
        this.direction = Direction.RIGHT;

        grid[head.getXPosition()][head.getYPosition()] = 1;
    }

    public void startGame() {
        /*
        Timer timer = new Timer(true);
        timer.schedule(new renderTimer(), 0, 100);

         */
    }

    public void renderFrame() {
        moveSnakeParts();

        updateGrid();
    }

    private void moveSnakeParts() {
        int prevX = head.getXPosition();
        int prevY = head.getYPosition();

        System.out.println(head.getXPosition());

        switch (direction) {
            case UP: movePart(head, prevX, prevY - 1);
            case DOWN: movePart(head, prevX, prevY + 1);
            case LEFT:  movePart(head, prevX - 1, prevY);
            case RIGHT: movePart(head, prevX + 1, prevY);
        }

        System.out.println(head.getXPosition());
    }

    private void movePart(SnakePart part, int targetX, int targetY) {
        part.setXPosition(targetX);
        part.setYPosition(targetY);
    }

    private void updateGrid() {
        grid = new int[gridSize][gridSize];

        grid[head.getXPosition()][head.getYPosition()] = 1;
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
