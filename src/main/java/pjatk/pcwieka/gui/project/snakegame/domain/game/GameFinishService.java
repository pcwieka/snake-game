package pjatk.pcwieka.gui.project.snakegame.domain.game;

import javafx.application.Platform;
import javafx.scene.Parent;
import pjatk.pcwieka.gui.project.snakegame.application.controller.MainController;
import pjatk.pcwieka.gui.project.snakegame.application.controller.RankPlayerController;
import pjatk.pcwieka.gui.project.snakegame.application.model.GameModel;
import pjatk.pcwieka.gui.project.snakegame.application.model.RankPlayerModel;
import pjatk.pcwieka.gui.project.snakegame.infrastructure.controller.StageInitializer;

public class GameFinishService extends Thread {

    private StageInitializer stageInitializer;
    private QuitGameEventProvider quitGameEventProvider;
    private GameModel gameModel;
    private Parent ownerWindow;

    public GameFinishService(
        StageInitializer stageInitializer,
        QuitGameEventProvider quitGameEventProvider,
        GameModel gameModel,
        Parent ownerWindow
    ) {
        this.stageInitializer = stageInitializer;
        this.quitGameEventProvider = quitGameEventProvider;
        this.gameModel = gameModel;
        this.ownerWindow = ownerWindow;
    }

    @Override
    public void run() {

        try {

            Thread.sleep(1000);

            while(true) {

                if (quitGameEventProvider.isQuitGame()) {

                    Platform.runLater(() ->
                        stageInitializer.initialize(
                            MainController.class
                        )
                    );

                    return;
                }

                if (gameModel.isGameOver()) {

                    ScoreEvaluationProvider scoreEvaluationProvider = new ScoreEvaluationProvider(
                        gameModel
                    );

                    RankPlayerModel rankPlayerModel = new RankPlayerModel(
                        scoreEvaluationProvider.evaluateFinalScore()
                    );

                    Platform.runLater(() ->
                        stageInitializer.initializeNewModalStage(
                            RankPlayerController.class,
                            rankPlayerModel,
                            ownerWindow.getScene().getWindow()
                        )
                    );

                    return;
                }

                Thread.sleep(500);
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
