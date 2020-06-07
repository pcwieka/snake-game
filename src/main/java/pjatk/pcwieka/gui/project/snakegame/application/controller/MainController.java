package pjatk.pcwieka.gui.project.snakegame.application.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pjatk.pcwieka.gui.project.snakegame.infrastructure.controller.StageInitializer;
import pjatk.pcwieka.gui.project.snakegame.infrastructure.shutdown.ApplicationShutdowner;
import java.net.URL;
import java.util.ResourceBundle;

@Component
@FxmlView("/views/main.fxml")
public class MainController implements Initializable {

    private ApplicationShutdowner applicationShutdowner;
    private StageInitializer stageInitializer;

    @Autowired
    public MainController(ApplicationShutdowner applicationShutdowner, StageInitializer stageInitializer) {
        this.applicationShutdowner = applicationShutdowner;
        this.stageInitializer = stageInitializer;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void handleNewGameButtonAction() {

        stageInitializer.initialize(NewGameController.class);
    }

    @FXML
    public void handleHighScoreButtonAction() {

        stageInitializer.initialize(HighScoreController.class);
    }

    @FXML
    public void handleExitButtonAction() {

        applicationShutdowner.shutdown();
    }
}