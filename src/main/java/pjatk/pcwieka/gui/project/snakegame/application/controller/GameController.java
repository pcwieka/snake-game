package pjatk.pcwieka.gui.project.snakegame.application.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pjatk.pcwieka.gui.project.snakegame.application.model.GameModel;
import pjatk.pcwieka.gui.project.snakegame.domain.game.*;
import pjatk.pcwieka.gui.project.snakegame.domain.enums.Direction;
import pjatk.pcwieka.gui.project.snakegame.infrastructure.controller.StageInitializer;
import pjatk.pcwieka.gui.project.snakegame.infrastructure.time.GameTimeProvider;
import java.net.URL;
import java.util.ResourceBundle;

@Component
@FxmlView("/views/game.fxml")
public class GameController implements Controller<GameModel> {

    @FXML
    Pane gamePane;

    private StageInitializer stageInitializer;
    private GameModel gameModel;
    private GameTimeProvider gameTimeProvider;

    @Autowired
    public GameController(StageInitializer stageInitializer, GameTimeProvider gameTimeProvider) {
        this.stageInitializer = stageInitializer;
        this.gameTimeProvider = gameTimeProvider;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Canvas canvas = new Canvas(gameModel.getBoardWidth() * gameModel.getCornerSize(), gameModel.getBoardHeight() * gameModel.getCornerSize());
        canvas.setFocusTraversable(true);

        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

        gamePane.getChildren().add(canvas);

        DirectionProvider directionProvider = new DirectionProvider();

        gamePane.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
            if (key.getCode() == KeyCode.UP) {
                directionProvider.setDirection(Direction.UP);
            }
            if (key.getCode() == KeyCode.LEFT) {
                directionProvider.setDirection(Direction.LEFT);
            }
            if (key.getCode() == KeyCode.DOWN) {
                directionProvider.setDirection(Direction.DOWN);
            }
            if (key.getCode() == KeyCode.RIGHT) {
                directionProvider.setDirection(Direction.RIGHT);
            }

        });

        QuitGameEventProvider quitGameEventProvider = new QuitGameEventProvider();

        gamePane.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<>() {

            final KeyCombination keyCodeCombination = new KeyCodeCombination(
                KeyCode.Q,
                KeyCombination.SHIFT_ANY,
                KeyCombination.CONTROL_ANY
            );

            public void handle(KeyEvent keyEvent) {
                if (keyCodeCombination.match(keyEvent)) {
                    quitGameEventProvider.setQuitGame();
                    keyEvent.consume();
                }
            }
        });

        FoodService foodService = new FoodService(graphicsContext, gameModel);

        new GameTimeService(
            gameTimeProvider,
            quitGameEventProvider,
            gameModel
        ).start();

        new SnakeGameRenderer(
            graphicsContext,
            gameModel,
            foodService,
            directionProvider,
            quitGameEventProvider,
            gameTimeProvider
        ).start();

        new GameFinishService(
            stageInitializer,
            quitGameEventProvider,
            gameModel,
            gamePane
        ).start();
    }

    @Override
    public void setModel(GameModel model) {
        this.gameModel = model;
    }
}
