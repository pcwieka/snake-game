package pjatk.pcwieka.gui.project.snakegame.application.model.highScores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pjatk.pcwieka.gui.project.snakegame.application.model.Model;
import pjatk.pcwieka.gui.project.snakegame.domain.entity.Score;
import pjatk.pcwieka.gui.project.snakegame.domain.repository.ScoreRepository;
import java.util.Comparator;

public class HighScoresModel implements Model {

    private TableView scoreTableView;
    private ScoreRepository scoreRepository;

    public HighScoresModel(TableView scoreTableView, ScoreRepository scoreRepository) {
        this.scoreTableView = scoreTableView;
        this.scoreRepository = scoreRepository;
    }

    public void createScoreList() {

        scoreTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<String, Score> playerNameColumn = new TableColumn<>("Player");
        playerNameColumn.setCellValueFactory(new PropertyValueFactory<>("playerName"));

        TableColumn<String, Score> scoreColumn = new TableColumn<>("Score");
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));

        scoreTableView.getColumns().add(playerNameColumn);
        scoreTableView.getColumns().add(scoreColumn);

        ObservableList<Score> scoresData = FXCollections.observableArrayList();

        scoresData.addAll(
            scoreRepository.getAll()
        );

        scoresData.sort(
            new ScoreComparator()
        );

        scoreTableView.setItems(scoresData);
    }

    private static class ScoreComparator implements Comparator<Score> {

        @Override
        public int compare(Score o1, Score o2) {
            return o1.getScore() < o2.getScore() ? 1 : 0;
        }
    }
}
