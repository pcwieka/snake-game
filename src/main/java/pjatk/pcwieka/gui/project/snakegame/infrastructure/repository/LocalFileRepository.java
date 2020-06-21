package pjatk.pcwieka.gui.project.snakegame.infrastructure.repository;

import java.io.*;

public class LocalFileRepository<T> {

    public T get(String repositoryName) {

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(getDataFilePath(repositoryName)))) {

            return (T) ois.readObject();

        } catch (FileNotFoundException e) {
        } catch (IOException | ClassNotFoundException e) {
        }
        return null;
    }

    public void save(T object, String repositoryName) {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(getDataFilePath(repositoryName)))) {

            oos.writeObject(object);

        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }

    private String getDataFilePath(String repositoryName) {

        String directory = System.getProperty("user.home") +
            File.separator +
            "SnakeGame" +
            File.separator +
            "data" +
            File.separator;

        createRepositoryDirectory(directory);

        String repositoryPath = directory + repositoryName + ".dat";

        createRepositoryFileIfNotExists(repositoryPath);

        return repositoryPath;
    }

    private void createRepositoryDirectory(String directoryPath) {

        File directory = new File(directoryPath);
        if (!directory.exists()) {
            System.out.println("@@ Creating data directory: " + directoryPath);
            directory.mkdirs();
        }
    }

    private void createRepositoryFileIfNotExists(String repositoryPath) {

        File repositoryFile = new File(repositoryPath);

        try {
            repositoryFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
