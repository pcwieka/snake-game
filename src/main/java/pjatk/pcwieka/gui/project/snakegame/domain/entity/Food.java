package pjatk.pcwieka.gui.project.snakegame.domain.entity;

import javafx.scene.paint.Color;

public class Food extends Corner {

    private Color color;

    public Food(int x, int y, Color color) {
        super(x, y);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
