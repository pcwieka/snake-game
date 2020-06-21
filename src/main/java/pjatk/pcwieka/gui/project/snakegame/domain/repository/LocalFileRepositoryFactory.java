package pjatk.pcwieka.gui.project.snakegame.domain.repository;

import org.springframework.stereotype.Component;
import pjatk.pcwieka.gui.project.snakegame.infrastructure.repository.LocalFileRepository;

@Component
public class LocalFileRepositoryFactory<T> {

    public LocalFileRepository<T> create() {
        return new LocalFileRepository<>();
    }
}
