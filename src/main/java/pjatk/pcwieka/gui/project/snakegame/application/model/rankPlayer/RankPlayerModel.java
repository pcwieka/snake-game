package pjatk.pcwieka.gui.project.snakegame.application.model.rankPlayer;

import pjatk.pcwieka.gui.project.snakegame.application.model.Model;

public class RankPlayerModel implements Model {

    private int finalScore;

    public RankPlayerModel(int finalScore) {
        this.finalScore = finalScore;
    }

    public int getFinalScore() {
        return finalScore;
    }
}
