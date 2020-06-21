package pjatk.pcwieka.gui.project.snakegame.application.model.game;

import pjatk.pcwieka.gui.project.snakegame.application.model.Model;
import pjatk.pcwieka.gui.project.snakegame.domain.entity.Corner;
import pjatk.pcwieka.gui.project.snakegame.domain.entity.Food;
import pjatk.pcwieka.gui.project.snakegame.domain.entity.Snake;

public class GameModel implements Model {

    private final Snake snake = new Snake();
    private final int cornerSize = 25;

    private int initialSpeed = 1;
    private boolean isGameOver = false;

    private int boardWidth;
    private int boardHeight;
    private Food currentFood;

    public GameModel(int initialSpeed, int boardWidth, int boardHeight) {

        this(boardWidth, boardHeight);

        this.initialSpeed = initialSpeed;
    }

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

    public synchronized int getInitialSpeed() {
        return initialSpeed;
    }

    public synchronized void setInitialSpeed(int initialSpeed) {
        this.initialSpeed = initialSpeed;
    }

    public Snake getSnake() {
        return snake;
    }

    public synchronized boolean isGameOver() {
        return isGameOver;
    }

    public synchronized void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }
}
