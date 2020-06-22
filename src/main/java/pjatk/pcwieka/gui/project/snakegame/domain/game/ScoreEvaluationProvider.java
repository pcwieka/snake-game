package pjatk.pcwieka.gui.project.snakegame.domain.game;

import pjatk.pcwieka.gui.project.snakegame.application.model.GameModel;

public class ScoreEvaluationProvider {

    private GameModel gameModel;

    public ScoreEvaluationProvider(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    public int evaluateFinalScore() {

        return gameModel.getFoodEaten()
            * (gameModel.getSpeed() - 4)
            * 2500 / (gameModel.getBoardWidth() * gameModel.getBoardHeight());
    }
}
