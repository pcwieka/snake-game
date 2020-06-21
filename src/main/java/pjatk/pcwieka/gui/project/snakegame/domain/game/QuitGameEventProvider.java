package pjatk.pcwieka.gui.project.snakegame.domain.game;

public class QuitGameEventProvider {

    private boolean quitGame = false;

    public synchronized boolean isQuitGame() {
        return quitGame;
    }

    public synchronized void setQuitGame() {
        this.quitGame = true;
    }
}
