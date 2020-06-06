package pjatk.pcwieka.gui.project.snakegame;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pjatk.pcwieka.gui.project.snakegame.application.SnakeGameJavaFxApp;

@SpringBootApplication
public class SnakeGameApp {

    public static void main(String[] args) {

        Application.launch(SnakeGameJavaFxApp.class, args);
    }
}
