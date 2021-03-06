package pjatk.pcwieka.gui.project.snakegame.infrastructure.controller;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import pjatk.pcwieka.gui.project.snakegame.application.controller.Controller;
import pjatk.pcwieka.gui.project.snakegame.application.model.Model;

@Component
public class StageInitializer {

    private FxWeaver fxWeaver;
    private MainStageProvider mainStageProvider;
    private ApplicationContext applicationContext;

    @Autowired
    public StageInitializer(FxWeaver fxWeaver, MainStageProvider mainStageProvider, ApplicationContext applicationContext) {
        this.fxWeaver = fxWeaver;
        this.mainStageProvider = mainStageProvider;
        this.applicationContext = applicationContext;
    }

    public <T extends Controller> void initialize(Class<T> tClass) {

        this.initialize(tClass,null);
    }

    public <T extends Controller> void initialize(Class<T> tClass, Model model) {

        T controller = applicationContext.getBean(tClass);
        controller.setModel(model);

        Parent root = fxWeaver.loadView(tClass);

        Scene scene = new Scene(root);

        Stage stage = mainStageProvider.getStage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public <T extends Controller> void initialize(Class<T> tClass, Model model, int width, int height) {

        T controller = applicationContext.getBean(tClass);
        controller.setModel(model);

        Parent root = fxWeaver.loadView(tClass);

        Scene scene = new Scene(root, width, height);

        Stage stage = mainStageProvider.getStage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public <T extends Controller> void initializeNewModalStage(Class<T> tClass, Model model, Window ownerWindow) {

        T controller = applicationContext.getBean(tClass);
        controller.setModel(model);

        Parent root = fxWeaver.loadView(tClass);
        Scene scene = new Scene(root);

        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(ownerWindow);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
