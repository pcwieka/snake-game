package pjatk.pcwieka.gui.project.snakegame.domain.game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import pjatk.pcwieka.gui.project.snakegame.application.model.GameModel;
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

            if (food.getColor() == Color.RED) {

                if(snake.getBody().size() > 1) {
                    snake.removeCorner();
                }

                gameModel.reduceSpeed();

            } else {

                snake.addCorner(new Corner(-1, -1));
                gameModel.addSpeed();
            }

            food =  this.provideNewFood();

            gameModel.setCurrentFood(food);
            gameModel.addFoodEaten();
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

        int foodColor = random.nextInt(10);

        Color color;

        switch (foodColor) {

            case 0:
                color = Color.RED;
                break;
            default:
                color = Color.YELLOW;
        }

        return color;
    }
}
