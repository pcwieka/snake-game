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

        int snakeLenght = (gameModel.getSnake().getBody().size() - 1);

        return 1000 * snakeLenght * snakeLenght
            * gameModel.getInitialSpeed()
            / gameTimeProvider.getGameTimeInSeconds()
            / (gameModel.getBoardWidth() * gameModel.getBoardHeight());
    }
}
