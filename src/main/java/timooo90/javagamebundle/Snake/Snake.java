package timooo90.javagamebundle.Snake;

import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;

public class Snake {
    Random random = new Random();
    private int gridSize = 20;
    private SnakeController controller;
    private int[][] grid;
    private SnakePart head;
    private ArrayList<SnakePart> bodyParts = new ArrayList<>();
    private Direction direction;
    private boolean movedSinceLastInput = true;
    private int numberOfBodyParts = 0;
    private Food food = new Food();


    public Snake(SnakeController controller) {
        this.controller = controller;
        this.grid = new int[gridSize][gridSize];
        this.head = new SnakePart(5, 5);
        this.direction = Direction.RIGHT;

        grid[head.getXPosition()][head.getYPosition()] = 1;
        bodyParts.add(new SnakePart(head.getXPosition() - 1, head.getYPosition()));
        bodyParts.add(new SnakePart(head.getXPosition() - 2, head.getYPosition()));
        numberOfBodyParts += 2;

        spawnFood();
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

        int targetX = prevX;
        int targetY = prevY;

        switch (direction) {
            case UP: {
                targetY -= 1;
                break;
            }
            case DOWN: {
                targetY += 1;
                break;
            }
            case LEFT: {
                targetX -= 1;
                break;
            }
            case RIGHT:{
                targetX += 1;
                break;
            }
        }

        if (targetX > gridSize - 1) {
            targetX = 0;
        }
        else if (targetX < 0) {
            targetX = gridSize - 1;
        }
        if (targetY > gridSize - 1) {
            targetY = 0;
        }
        else if (targetY < 0) {
            targetY = gridSize - 1;
        }

        movePart(head, targetX, targetY);
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

        grid[food.getPositionY()][food.getPositionX()] = 5;
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

    public void spawnFood() {
        while (true) {
            int randomX = random.nextInt(gridSize);
            int randomY = random.nextInt(gridSize);

            if (grid[randomY][randomX] != 0) {
                continue;
            }

            food.setPositionX(randomX);
            food.setPositionY(randomY);
            break;
        }
    }
}
