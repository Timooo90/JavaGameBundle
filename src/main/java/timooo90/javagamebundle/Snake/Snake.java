package timooo90.javagamebundle.Snake;

import java.util.ArrayList;
import java.util.TimerTask;

public class Snake {
    private int gridSize = 20;
    private SnakeController controller;
    private int[][] grid;
    private SnakePart head;
    private ArrayList<SnakePart> bodyParts = new ArrayList<>();
    private Direction direction;
    private boolean movedSinceLastInput = true;
    private int numberOfBodyParts = 0;


    public Snake(SnakeController controller) {
        this.controller = controller;
        this.grid = new int[gridSize][gridSize];
        this.head = new SnakePart(5, 5);
        this.direction = Direction.RIGHT;

        grid[head.getXPosition()][head.getYPosition()] = 1;
        bodyParts.add(new SnakePart(head.getXPosition() - 1, head.getYPosition()));
        bodyParts.add(new SnakePart(head.getXPosition() - 2, head.getYPosition()));
        numberOfBodyParts += 2;
    }

    public void startGame() {

    }

    public void renderFrame() {
        moveSnakeParts();
        movedSinceLastInput = true;

        updateGrid();
    }

    private void moveSnakeParts() {
        int prevX = head.getXPosition();
        int prevY = head.getYPosition();

        switch (direction) {
            case UP: {
                movePart(head, prevX, prevY - 1);
                break;
            }
            case DOWN: {
                movePart(head, prevX, prevY + 1);
                break;
            }
            case LEFT: {
                movePart(head, prevX - 1, prevY);
                break;
            }
            case RIGHT:{
                movePart(head, prevX + 1, prevY);
                break;
            }
        }
        moveBody(prevX, prevY);
    }

    private void movePart(SnakePart part, int targetX, int targetY) {
        part.setXPosition(targetX);
        part.setYPosition(targetY);
    }

    private void moveBody(int previousX, int previousY) {
        for (SnakePart part : bodyParts) {
            int tempPreviousX = part.getXPosition();
            int tempPreviousY = part.getYPosition();

            movePart(part, previousX, previousY);

            previousX = tempPreviousX;
            previousY = tempPreviousY;
        }
    }

    private void updateGrid() {
        grid = new int[gridSize][gridSize];

        grid[head.getYPosition()][head.getXPosition()] = 1;

        for (SnakePart part : bodyParts) {
            grid[part.getYPosition()][part.getXPosition()] = 1;
        }
    }

    private class renderTimer extends TimerTask {
        public void run() {
            renderFrame();
        }
    }

    public int[][] getGrid() {
        return grid;
    }

    public void changeDirection(Direction targetDirection) {
        if (!movedSinceLastInput) {
            return; }

        if ((direction == Direction.UP || direction == Direction.DOWN) && (targetDirection == Direction.UP || targetDirection == Direction.DOWN)) {
            return; }

        if ((direction == Direction.RIGHT || direction == Direction.LEFT) && (targetDirection == Direction.RIGHT || targetDirection == Direction.LEFT)) {
            return; }

        direction = targetDirection;

        movedSinceLastInput = false;
    }
}
