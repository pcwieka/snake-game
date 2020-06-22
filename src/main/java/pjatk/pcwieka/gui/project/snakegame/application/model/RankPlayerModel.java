package pjatk.pcwieka.gui.project.snakegame.application.model;

public class RankPlayerModel implements Model {

    private int finalScore;

    public RankPlayerModel(int finalScore) {
        this.finalScore = finalScore;
    }

    public int getFinalScore() {
        return finalScore;
    }
}
