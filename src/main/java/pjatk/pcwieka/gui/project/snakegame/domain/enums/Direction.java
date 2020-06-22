package pjatk.pcwieka.gui.project.snakegame.domain.enums;

public enum Direction {

    UP,
    LEFT,
    DOWN,
    RIGHT;

    public Direction opposite() {

        switch(this) {
            case UP: return Direction.DOWN;
            case DOWN: return Direction.UP;
            case LEFT: return Direction.RIGHT;
            case RIGHT: return Direction.LEFT;
            default: throw new IllegalStateException(this + " has no opposite.");
        }
    }
}
