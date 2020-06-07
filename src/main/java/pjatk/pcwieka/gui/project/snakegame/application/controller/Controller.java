package pjatk.pcwieka.gui.project.snakegame.application.controller;

import javafx.fxml.Initializable;
import pjatk.pcwieka.gui.project.snakegame.application.model.Model;

public interface Controller<T extends Model> extends Initializable {

    void setModel(T model);
}
