package pjatk.pcwieka.gui.project.snakegame.domain.game;

import pjatk.pcwieka.gui.project.snakegame.application.model.game.GameModel;
import pjatk.pcwieka.gui.project.snakegame.infrastructure.time.GameTimeProvider;

public class GameTimeService extends Thread {

    private GameTimeProvider gameTimeProvider;
    private GameModel gameModel;

    public GameTimeService(GameTimeProvider gameTimeProvider, GameModel gameModel) {
        this.gameTimeProvider = gameTimeProvider;
        this.gameModel = gameModel;
    }

    @Override
    public void run() {

        long startTime = System.currentTimeMillis();

        while(true) {

            try {

                gameTimeProvider.setGameTimeInSeconds(
                    (int)(System.currentTimeMillis() - startTime) / 1000
                );

                if (gameModel.isGameOver()) {
                    return;
                }

                Thread.sleep(500);

            } catch (InterruptedException e) {
               throw new RuntimeException(e);
            }
        }
    }
}
