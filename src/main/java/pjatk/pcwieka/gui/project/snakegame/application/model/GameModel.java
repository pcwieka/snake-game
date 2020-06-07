package pjatk.pcwieka.gui.project.snakegame.application.model;

public class GameModel implements Model {

    private Integer boardWidth;
    private Integer boardHeight;

    public GameModel(Integer boardWidth, Integer boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
    }

    public Integer getBoardHeight() {
        return boardHeight;
    }

    public Integer getBoardWidth() {
        return boardWidth;
    }

    @Override
    public String toString() {
        return "GameModel{" +
            "boardWidth=" + boardWidth +
            ", boardHeight=" + boardHeight +
            '}';
    }
}
