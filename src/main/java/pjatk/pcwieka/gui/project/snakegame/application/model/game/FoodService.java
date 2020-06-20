package pjatk.pcwieka.gui.project.snakegame.application.model.game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import pjatk.pcwieka.gui.project.snakegame.domain.entity.Corner;
import pjatk.pcwieka.gui.project.snakegame.domain.entity.Food;
import pjatk.pcwieka.gui.project.snakegame.domain.entity.Snake;
import java.util.Random;

public class FoodService {

    private final Random random = new Random();

    private final GraphicsContext graphicsContext;
    private final GameModel gameModel;

    public FoodService(GraphicsContext graphicsContext, GameModel gameModel) {

        this.graphicsContext = graphicsContext;
        this.gameModel = gameModel;
    }

    void eatFood() {

        Snake snake = gameModel.getSnake();
        Food food = gameModel.getCurrentFood();

        Corner snakeHead = snake.getBody().get(0);

        if (food.getX() == snakeHead.getX() && food.getY() == snakeHead.getY()) {

            snake.addCorner(new Corner(-1, -1));

            food =  this.provideNewFood();

            gameModel.setCurrentFood(food);
        }
    }

    Food provideNewFood() {

        Snake snake = gameModel.getSnake();

        start: while (true) {

            int foodX = random.nextInt(gameModel.getBoardWidth());
            int foodY = random.nextInt(gameModel.getBoardHeight());

            for (Corner corner : snake.getBody()) {
                if (corner.getX() == foodX && corner.getY() == foodY) {
                    continue start;
                }
            }

            int speed = gameModel.getInitialSpeed();
            gameModel.setInitialSpeed(++speed);

            return new Food(foodX, foodY, this.getFoodColor());
        }
    }

    public void renderFood() {

        Food food = gameModel.getCurrentFood();

        graphicsContext.setFill(food.getColor());
        graphicsContext.fillOval(
            food.getX() * gameModel.getCornerSize(),
            food.getY() * gameModel.getCornerSize(),
            gameModel.getCornerSize(),
            gameModel.getCornerSize()
        );
    }

    private Color getFoodColor() {

        int foodColor = random.nextInt(5);

        Color color = Color.WHITE;

        switch (foodColor) {

            case 0:
                color = Color.PURPLE;
                break;
            case 1:
                color = Color.LIGHTBLUE;
                break;
            case 2:
                color = Color.YELLOW;
                break;
            case 3:
                color = Color.PINK;
                break;
            case 4:
                color = Color.ORANGE;
                break;
        }

        return color;
    }
}
