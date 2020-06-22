package pjatk.pcwieka.gui.project.snakegame.application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pjatk.pcwieka.gui.project.snakegame.application.model.GameModel;
import pjatk.pcwieka.gui.project.snakegame.application.model.Model;
import pjatk.pcwieka.gui.project.snakegame.infrastructure.controller.StageInitializer;
import java.net.URL;
import java.util.ResourceBundle;

@Component
@FxmlView("/views/new_game.fxml")
public class NewGameController implements Controller {

    @FXML
    private Spinner<Integer> selectWidthSpinner;

    @FXML
    private Spinner<Integer> selectHeightSpinner;

    private StageInitializer stageInitializer;

    @Autowired
    public NewGameController(StageInitializer stageInitializer) {
        this.stageInitializer = stageInitializer;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        SpinnerValueFactory<Integer> widthSpinnerValueFactory =
            new SpinnerValueFactory.IntegerSpinnerValueFactory(10, 50, 20, 5);

        selectWidthSpinner.setValueFactory(widthSpinnerValueFactory);

        SpinnerValueFactory<Integer> heightSpinnerValueFactory =
            new SpinnerValueFactory.IntegerSpinnerValueFactory(10, 50, 20, 5);

        selectHeightSpinner.setValueFactory(heightSpinnerValueFactory);
    }

    @FXML
    private void handlePlayButtonAction() {

        GameModel gameModel = new GameModel(
            selectWidthSpinner.getValue(),
            selectHeightSpinner.getValue()
        );

        stageInitializer.initialize(
            GameController.class,
            gameModel,
            gameModel.getBoardWidth() * gameModel.getCornerSize(),
            gameModel.getBoardHeight() * gameModel.getCornerSize()
        );
    }

    @Override
    public void setModel(Model model) {
        //noop
    }
}
