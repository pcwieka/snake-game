package pjatk.pcwieka.gui.project.snakegame.application.model.game;

import pjatk.pcwieka.gui.project.snakegame.domain.enums.Direction;

public class DirectionProvider {

    private Direction direction = Direction.LEFT;

    public synchronized Direction getDirection() {
        return direction;
    }

    public synchronized void setDirection(Direction direction) {
        this.direction = direction;
    }
}
