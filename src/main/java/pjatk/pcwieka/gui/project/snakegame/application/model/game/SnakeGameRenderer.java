package pjatk.pcwieka.gui.project.snakegame.application.model.game;

import javafx.animation.AnimationTimer;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import pjatk.pcwieka.gui.project.snakegame.domain.entity.Corner;
import pjatk.pcwieka.gui.project.snakegame.domain.entity.Snake;
import pjatk.pcwieka.gui.project.snakegame.infrastructure.time.GameTimeProvider;
import java.util.List;

public class SnakeGameRenderer extends AnimationTimer {

    private long lastAnimationUpdate = 0;
    private boolean isFirstFoodProvided = false;

    private final GraphicsContext graphicsContext;
    private final GameModel gameModel;
    private final FoodService foodService;
    private final DirectionProvider directionProvider;
    private final GameTimeProvider gameTimeProvider;

    public SnakeGameRenderer(GraphicsContext graphicsContext, GameModel gameModel, FoodService foodService, DirectionProvider directionProvider, GameTimeProvider gameTimeProvider) {
        this.graphicsContext = graphicsContext;
        this.gameModel = gameModel;
        this.foodService = foodService;
        this.directionProvider = directionProvider;
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

        if (now - lastAnimationUpdate >= 1_000_000_000 / gameModel.getInitialSpeed()) {

            lastAnimationUpdate = now ;
            renderNextMove();
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

        switch (directionProvider.getDirection()) {

            case UP:

                int y = snakeHead.getY();
                snakeHead.setY(--y);

                if (snakeHead.getY() < 0) {
                    gameModel.setGameOver(true);
                }
                break;

            case DOWN:

                y = snakeHead.getY();
                snakeHead.setY(++y);

                if (snakeHead.getY() > gameModel.getBoardHeight()) {
                    gameModel.setGameOver(true);
                }
                break;

            case LEFT:

                int x = snakeHead.getX();
                snakeHead.setX(--x);

                if (snakeHead.getX() < 0) {
                    gameModel.setGameOver(true);
                }
                break;

            case RIGHT:

                x = snakeHead.getX();
                snakeHead.setX(++x);

                if (snakeHead.getX() > gameModel.getBoardWidth()) {
                    gameModel.setGameOver(true);
                }
                break;
        }

        foodService.eatFood();

        for (int i = 1; i < snakeBody.size(); i++) {
            if (snakeHead.getX() == snakeBody.get(i).getX() && snakeHead.getY() == snakeBody.get(i).getY()) {
                gameModel.setGameOver(true);
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
        graphicsContext.setFont(new Font("", 15));
        graphicsContext.fillText("Food: " + (gameModel.getSnake().getBody().size() - 1), 10, 30);

        // time
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.setFont(new Font("", 15));

        graphicsContext.fillText(
            gameTimeProvider.getGameTimeDurationAsString(),
            gameModel.getBoardWidth() * gameModel.getCornerSize() - 50,
            30
        );

        foodService.renderFood();

        // snake
        for (Corner corner : snakeBody) {

            graphicsContext.setFill(Color.LIGHTGREEN);

            graphicsContext.fillRect(
                corner.getX() * gameModel.getCornerSize(),
                corner.getY() * gameModel.getCornerSize(),
                gameModel.getCornerSize() - 1,
                gameModel.getCornerSize() - 1
            );

            graphicsContext.setFill(Color.GREEN);

            graphicsContext.fillRect(
                corner.getX() * gameModel.getCornerSize(),
                corner.getY() * gameModel.getCornerSize(),
                gameModel.getCornerSize() - 2,
                gameModel.getCornerSize() - 2
            );
        }
    }
}
