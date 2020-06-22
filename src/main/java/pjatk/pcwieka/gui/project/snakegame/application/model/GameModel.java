package pjatk.pcwieka.gui.project.snakegame.application.model;

import pjatk.pcwieka.gui.project.snakegame.domain.entity.Corner;
import pjatk.pcwieka.gui.project.snakegame.domain.entity.Food;
import pjatk.pcwieka.gui.project.snakegame.domain.entity.Snake;

public class GameModel implements Model {

    private final Snake snake = new Snake();
    private final int cornerSize = 25;

    private int speed = 5;
    private boolean isGameOver = false;
    private int foodEaten = 0;

    private int boardWidth;
    private int boardHeight;
    private Food currentFood;

    public GameModel(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;

        snake.addCorner(new Corner(boardWidth/2, boardHeight/2));
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public int getCornerSize() {
        return cornerSize;
    }

    public synchronized Food getCurrentFood() {
        return currentFood;
    }

    public synchronized void setCurrentFood(Food currentFood) {
        this.currentFood = currentFood;
    }

    public synchronized int getFoodEaten() {
        return foodEaten;
    }

    public synchronized void addFoodEaten() {
        this.foodEaten++;
    }

    public synchronized int getSpeed() {
        return speed;
    }

    public synchronized void addSpeed() {
        this.speed++;
    }

    public synchronized void reduceSpeed() {

        if (this.speed > 5) {
            this.speed--;
        }
    }

    public Snake getSnake() {
        return snake;
    }

    public synchronized boolean isGameOver() {
        return isGameOver;
    }

    public synchronized void setGameOver() {
        isGameOver = true;
    }
}
