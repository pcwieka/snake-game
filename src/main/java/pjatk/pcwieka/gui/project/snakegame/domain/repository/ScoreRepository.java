package pjatk.pcwieka.gui.project.snakegame.domain.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pjatk.pcwieka.gui.project.snakegame.domain.entity.Score;
import pjatk.pcwieka.gui.project.snakegame.infrastructure.repository.LocalFileRepository;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScoreRepository {

    private static final String SCORE_REPOSITORY_NAME = "scoreRepository";

    private LocalFileRepository<List<Score>> localFileScoreRepository;

    @Autowired
    public void setLocalFileScoreRepository(
        LocalFileRepositoryFactory<List<Score>> listLocalFileRepositoryFactory
    ) {
        localFileScoreRepository = listLocalFileRepositoryFactory.create();
    }

    public void save(Score score) {

        List<Score> scoreList = getAll();
        scoreList.add(score);

        localFileScoreRepository.save(scoreList, SCORE_REPOSITORY_NAME);
    }

    public List<Score> getAll() {

        List<Score> scoreList = localFileScoreRepository.get(SCORE_REPOSITORY_NAME);

        if (scoreList == null) {
            scoreList = new ArrayList<>();
        }

        return scoreList;
    }
}
