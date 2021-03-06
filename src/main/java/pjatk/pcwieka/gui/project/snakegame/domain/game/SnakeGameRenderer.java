package pjatk.pcwieka.gui.project.snakegame.domain.game;

import javafx.animation.AnimationTimer;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import pjatk.pcwieka.gui.project.snakegame.application.model.GameModel;
import pjatk.pcwieka.gui.project.snakegame.domain.entity.Corner;
import pjatk.pcwieka.gui.project.snakegame.domain.entity.Snake;
import pjatk.pcwieka.gui.project.snakegame.domain.enums.Direction;
import pjatk.pcwieka.gui.project.snakegame.infrastructure.time.GameTimeProvider;
import java.util.List;
import java.util.Optional;

public class SnakeGameRenderer extends AnimationTimer {

    private long lastAnimationUpdate = 0;
    private boolean isFirstFoodProvided = false;
    private Optional<Direction> currentDirection = Optional.empty();

    private final GraphicsContext graphicsContext;
    private final GameModel gameModel;
    private final FoodService foodService;
    private final DirectionProvider directionProvider;
    private final QuitGameEventProvider quitGameEventProvider;
    private final GameTimeProvider gameTimeProvider;

    public SnakeGameRenderer(
        GraphicsContext graphicsContext,
        GameModel gameModel,
        FoodService foodService,
        DirectionProvider directionProvider,
        QuitGameEventProvider quitGameEventProvider,
        GameTimeProvider gameTimeProvider
    ) {
        this.graphicsContext = graphicsContext;
        this.gameModel = gameModel;
        this.foodService = foodService;
        this.directionProvider = directionProvider;
        this.quitGameEventProvider = quitGameEventProvider;
        this.gameTimeProvider = gameTimeProvider;
    }

    @Override
    public void handle(long now) {

        if (!isFirstFoodProvided) {

            gameModel.setCurrentFood(
                foodService.provideNewFood()
            );

            isFirstFoodProvided = true;
        }

        if (now - lastAnimationUpdate >= 1_700_000_000 / gameModel.getSpeed()) {

            lastAnimationUpdate = now ;
            renderNextMove();
        }

        if (quitGameEventProvider.isQuitGame()) {

            this.stop();
        }
    }

    private void renderNextMove() {

        Snake snake = gameModel.getSnake();
        List<Corner> snakeBody = snake.getBody();

        for (int i = snakeBody.size() - 1; i >= 1; i--) {

            snakeBody.get(i).setX(
                snakeBody.get(i - 1).getX()
            );

            snakeBody.get(i).setY(
                snakeBody.get(i - 1).getY()
            );
        }
        
        Corner snakeHead = snakeBody.get(0);

        Direction newDirection = directionProvider.getDirection();

        if (currentDirection.isPresent()) {

            Direction currentDir = currentDirection.get();

            if (currentDir == newDirection.opposite()) {

                newDirection = currentDir;
            }
        }

        switch (newDirection) {

            case UP:

                int y = snakeHead.getY();
                snakeHead.setY(--y);

                break;

            case DOWN:

                y = snakeHead.getY();
                snakeHead.setY(++y);

                break;

            case LEFT:

                int x = snakeHead.getX();
                snakeHead.setX(--x);

                break;

            case RIGHT:

                x = snakeHead.getX();
                snakeHead.setX(++x);

                break;
        }

        currentDirection = Optional.of(newDirection);

        foodService.eatFood();

        // game over
        if (snakeHead.getY() < 0 || snakeHead.getY() >= gameModel.getBoardHeight() ||
            snakeHead.getX() < 0 || snakeHead.getX() >= gameModel.getBoardWidth()) {

            gameModel.setGameOver();
        }

        for (int i = 1; i < snakeBody.size(); i++) {
            if (snakeHead.getX() == snakeBody.get(i).getX() && snakeHead.getY() == snakeBody.get(i).getY()) {
                gameModel.setGameOver();
            }
        }

        if (gameModel.isGameOver()) {

            graphicsContext.setFill(Color.RED);
            graphicsContext.setFont(new Font("", 30));
            graphicsContext.setTextAlign(TextAlignment.CENTER);
            graphicsContext.setTextBaseline(VPos.CENTER);

            graphicsContext.fillText(
                "GAME OVER",
                gameModel.getBoardWidth() * gameModel.getCornerSize() / 2,
                gameModel.getBoardHeight() * gameModel.getCornerSize() / 2
            );

            return;
        }

        // background
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRect(
            0,
            0,
            gameModel.getBoardWidth() * gameModel.getCornerSize(),
            gameModel.getBoardHeight() * gameModel.getCornerSize()
        );

        // food scored
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.setFont(new Font("", 18));
        graphicsContext.fillText("Food: " + gameModel.getFoodEaten(), 10, 30);

        // time
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.setFont(new Font("", 18));

        graphicsContext.fillText(
            gameTimeProvider.getGameTimeDurationAsString(),
            gameModel.getBoardWidth() * gameModel.getCornerSize() - 55,
            30
        );

        foodService.renderFood();

        // snake
        for(int i = 0; i < snakeBody.size(); i++) {

            Corner corner = snakeBody.get(i);

            graphicsContext.setFill(Color.LIGHTGREEN);

            graphicsContext.fillRect(
                corner.getX() * gameModel.getCornerSize(),
                corner.getY() * gameModel.getCornerSize(),
                gameModel.getCornerSize() - 1,
                gameModel.getCornerSize() - 1
            );

            if (i == 0) {

                graphicsContext.setFill(Color.ORANGE);

            } else {

                graphicsContext.setFill(Color.GREEN);
            }

            graphicsContext.fillRect(
                corner.getX() * gameModel.getCornerSize(),
                corner.getY() * gameModel.getCornerSize(),
                gameModel.getCornerSize() - 2,
                gameModel.getCornerSize() - 2
            );
        }
    }
}
