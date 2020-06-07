package pjatk.pcwieka.gui.project.snakegame.infrastructure.controller;

import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class MainStageHolder {

    private Stage stage;

    public synchronized Stage getStage() {
        return stage;
    }

    public synchronized void setStage(Stage stage) {
        this.stage = stage;
    }
}
