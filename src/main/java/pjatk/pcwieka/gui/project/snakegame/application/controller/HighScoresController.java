package pjatk.pcwieka.gui.project.snakegame.application.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pjatk.pcwieka.gui.project.snakegame.application.model.highScores.HighScoresModel;
import pjatk.pcwieka.gui.project.snakegame.domain.repository.ScoreRepository;
import pjatk.pcwieka.gui.project.snakegame.infrastructure.controller.StageInitializer;
import java.net.URL;
import java.util.ResourceBundle;

@Component
@FxmlView("/views/high_score.fxml")
public class HighScoresController implements Controller<HighScoresModel> {

    @FXML
    private ScrollPane scrollPane;

    private StageInitializer stageInitializer;
    private HighScoresModel highScoresModel;
    private ScoreRepository scoreRepository;

    @Autowired
    public HighScoresController(
        StageInitializer stageInitializer,
        ScoreRepository scoreRepository
    ) {
        this.stageInitializer = stageInitializer;
        this.scoreRepository = scoreRepository;
    }

    @Override
    public void setModel(HighScoresModel model) {
        //noop
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        highScoresModel = new HighScoresModel(
            scrollPane,
            scoreRepository
        );

        Platform.runLater(() ->
            highScoresModel.createScoreList()
        );
    }

    @FXML
    private void handleOkayButtonAction() {

        Stage stage = (Stage) scrollPane.getScene().getWindow();
        stage.close();

        stageInitializer.initialize(MainController.class);
    }
}
