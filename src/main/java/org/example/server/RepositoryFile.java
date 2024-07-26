package org.example.server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class RepositoryFile implements Repository{

    private final String LOG_PATH = "/Users/chernykh/IdeaProjects/Swing_sem_1/src/main/java/org/example/server/repository";

    @Override
    public void save(String text) {
        try (FileWriter writer = new FileWriter(LOG_PATH, true)){
            writer.write(text);
            writer.write("\n");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public StringBuilder read() {
        String line;
        StringBuilder result = new StringBuilder();
        try (FileReader reader = new FileReader(LOG_PATH)){
            BufferedReader bf = new BufferedReader(reader);
            while ((line = bf.readLine()) != null){
                result.append(line);
                result.append("\n");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
