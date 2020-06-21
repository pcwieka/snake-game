package pjatk.pcwieka.gui.project.snakegame.infrastructure.time;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GameTimeProvider {

    private int gameTimeInSeconds;

    public synchronized int getGameTimeInSeconds() {
        return gameTimeInSeconds;
    }

    public synchronized void setGameTimeInSeconds(int gameTimeInSeconds) {

        this.gameTimeInSeconds = gameTimeInSeconds;
    }

    public String getGameTimeDurationAsString() {

        int timeInSeconds = getGameTimeInSeconds();
        int secondsLeft = timeInSeconds % 3600 % 60;
        int minutes = (int) Math.floor(timeInSeconds % 3600 / 60);

        String mm = ((minutes < 10) ? "0" : "") + minutes;
        String ss = ((secondsLeft < 10) ? "0" : "") + secondsLeft;

        return mm + ":" + ss;

    }
}
