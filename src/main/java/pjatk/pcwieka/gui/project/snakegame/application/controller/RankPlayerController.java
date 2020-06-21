package pjatk.pcwieka.gui.project.snakegame.application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pjatk.pcwieka.gui.project.snakegame.application.model.rankPlayer.RankPlayerModel;
import pjatk.pcwieka.gui.project.snakegame.domain.entity.Score;
import pjatk.pcwieka.gui.project.snakegame.domain.repository.ScoreRepository;
import pjatk.pcwieka.gui.project.snakegame.infrastructure.controller.StageInitializer;
import java.net.URL;
import java.util.ResourceBundle;

@Component
@FxmlView("/views/rank_player.fxml")
public class RankPlayerController implements Controller<RankPlayerModel> {

    @FXML
    private TextField enterYourNameTextField;

    @FXML
    private Text scoreText;

    private RankPlayerModel rankPlayerModel;
    private StageInitializer stageInitializer;
    private ScoreRepository scoreRepository;

    @Autowired
    public RankPlayerController(
        StageInitializer stageInitializer,
        ScoreRepository scoreRepository
    ) {
        this.stageInitializer = stageInitializer;
        this.scoreRepository = scoreRepository;
    }

    @Override
    public void setModel(RankPlayerModel model) {
        this.rankPlayerModel = model;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        scoreText.setText(
            Integer.toString(
                rankPlayerModel.getFinalScore()
            )
        );

        if (rankPlayerModel.getFinalScore() == 0) {
            enterYourNameTextField.setDisable(true);
        }
    }

    @FXML
    private void handleOkayButtonAction() {

        if (rankPlayerModel.getFinalScore() > 0) {
            savePlayerScore();
        }

        stageInitializer.initialize(MainController.class);

        Stage stage = (Stage) scoreText.getScene().getWindow();
        stage.close();
    }

    private void savePlayerScore() {

        Score score = new Score(
            enterYourNameTextField.getText(),
            rankPlayerModel.getFinalScore()
        );

        scoreRepository.save(score);
    }
}
