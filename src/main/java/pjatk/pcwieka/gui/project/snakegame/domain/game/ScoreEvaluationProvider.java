package pjatk.pcwieka.gui.project.snakegame.domain.game;

import pjatk.pcwieka.gui.project.snakegame.application.model.game.GameModel;
import pjatk.pcwieka.gui.project.snakegame.infrastructure.time.GameTimeProvider;

public class ScoreEvaluationProvider {

    private GameModel gameModel;
    private GameTimeProvider gameTimeProvider;

    public ScoreEvaluationProvider(GameModel gameModel, GameTimeProvider gameTimeProvider) {
        this.gameModel = gameModel;
        this.gameTimeProvider = gameTimeProvider;
    }

    public int evaluateFinalScore() {

        return 100 * gameModel.getFoodEaten() * gameModel.getFoodEaten()
            * gameModel.getSpeed()
            * (int)(0.3 * gameTimeProvider.getGameTimeInSeconds())
            / (gameModel.getBoardWidth() * gameModel.getBoardHeight());
    }
}
