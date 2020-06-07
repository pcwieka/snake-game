package pjatk.pcwieka.gui.project.snakegame.application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import pjatk.pcwieka.gui.project.snakegame.SnakeGameApp;
import pjatk.pcwieka.gui.project.snakegame.application.controller.MainController;
import pjatk.pcwieka.gui.project.snakegame.infrastructure.controller.MainStageHolder;
import pjatk.pcwieka.gui.project.snakegame.infrastructure.controller.StageInitializer;

public class SnakeGameJavaFxApp extends Application {

    private ConfigurableApplicationContext applicationContext;

    @Override
    public void init() {

        String[] args = getParameters().getRaw().toArray(new String[0]);

        this.applicationContext = new SpringApplicationBuilder()
            .sources(SnakeGameApp.class)
            .run(args);
    }

    @Override
    public void start(Stage stage) {

        MainStageHolder mainStageHolder = applicationContext.getBean(MainStageHolder.class);
        mainStageHolder.setStage(stage);

        StageInitializer stageInitializer = applicationContext.getBean(StageInitializer.class);
        stageInitializer.initialize(MainController.class);
    }

    @Override
    public void stop() {

        this.applicationContext.close();
        Platform.exit();
    }
}
