package pjatk.pcwieka.gui.project.snakegame.infrastructure.controller;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StageInitializer {

    private FxWeaver fxWeaver;
    private MainStageHolder mainStageHolder;

    @Autowired
    public StageInitializer(FxWeaver fxWeaver, MainStageHolder mainStageHolder) {
        this.fxWeaver = fxWeaver;
        this.mainStageHolder = mainStageHolder;
    }

    public <T> void initialize(Class<T> tClass) {

        Parent root = fxWeaver.loadView(tClass);
        Scene scene = new Scene(root);
        Stage stage = mainStageHolder.getStage();
        stage.setScene(scene);
        stage.show();
    }
}
