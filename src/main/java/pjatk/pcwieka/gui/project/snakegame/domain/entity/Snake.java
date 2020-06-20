package pjatk.pcwieka.gui.project.snakegame.domain.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Snake {

    private List<Corner> body = Collections.synchronizedList(new ArrayList<>());

    public void addCorner(Corner corner) {
        body.add(corner);
    }

    public List<Corner> getBody() {
        return body;
    }
}
