package pjatk.pcwieka.gui.project.snakegame.infrastructure.shutdown;

import javafx.application.Platform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ApplicationShutdowner {

    private ApplicationContext applicationContext;

    @Autowired
    public ApplicationShutdowner(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void shutdown() {

        ((ConfigurableApplicationContext) this.applicationContext).close();
        Platform.exit();
    }
}
