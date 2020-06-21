package pjatk.pcwieka.gui.project.snakegame.domain.game;

import javafx.application.Platform;
import javafx.scene.Parent;
import pjatk.pcwieka.gui.project.snakegame.application.controller.RankPlayerController;
import pjatk.pcwieka.gui.project.snakegame.application.model.game.GameModel;
import pjatk.pcwieka.gui.project.snakegame.application.model.rankPlayer.RankPlayerModel;
import pjatk.pcwieka.gui.project.snakegame.infrastructure.controller.StageInitializer;
import pjatk.pcwieka.gui.project.snakegame.infrastructure.time.GameTimeProvider;

public class GameFinishService extends Thread {

    private StageInitializer stageInitializer;
    private GameTimeProvider gameTimeProvider;
    private GameModel gameModel;
    private Parent ownerWindow;

    public GameFinishService(
        StageInitializer stageInitializer,
        GameTimeProvider gameTimeProvider,
        GameModel gameModel,
        Parent ownerWindow
    ) {
        this.stageInitializer = stageInitializer;
        this.gameTimeProvider = gameTimeProvider;
        this.gameModel = gameModel;
        this.ownerWindow = ownerWindow;
    }

    @Override
    public void run() {

        try {

            Thread.sleep(1000);

            while(true) {

                if (gameModel.isGameOver()) {

                    ScoreEvaluationProvider scoreEvaluationProvider = new ScoreEvaluationProvider(
                        gameModel,
                        gameTimeProvider
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
