package manager;

import model.Epic;
import model.Parser;
import model.Subtask;
import model.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class FileBackedTasksManager extends InMemoryTaskManager {
    private final Parser parser = new Parser();
    private String path = "resources/data.csv";

    public FileBackedTasksManager() {
        try(FileWriter fileWriter = new FileWriter("resources/data.csv", StandardCharsets.UTF_8)){
            fileWriter.write("name,describe,id,status\n");
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла");
        }
    }

    public void save(String filename){
        try (FileWriter fileWriter = new FileWriter(filename,true)){
            for(Epic epic : epicmap.values()){
                fileWriter.write(parser.toParse(epic) + "\n");
            }
            for(Task task: taskmap.values()){
                fileWriter.write(parser.toParse(task) + "\n");
            }
            for(Subtask subtask: subtaskmap.values()){
                fileWriter.write(parser.toParse(subtask) + "\n");
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла");
        }
    }

    @Override
    public void createSubtask(Subtask subtask){
        super.createSubtask(subtask);
        save(path);
    }
    @Override
    public void createTask(Task task) {
        super.createTask(task);
        save(path);
    }
    @Override
    public void createEpic(Epic epic) {
        super.createEpic(epic);
        save(path);
    }

    @Override
    public void updateTask(Task task){
        super.updateTask(task);
        save(path);
    }

    @Override
    public void updateEpic(Epic newEpic) {
        super.updateEpic(newEpic);
        save(path);
    }

    @Override
    public void updateSubtask(Subtask subtask) {
        super.updateSubtask(subtask);
        save(path);
    }

    String historyToString(){
        StringBuilder sb = new StringBuilder();
        for(Task task : historyManager.getHistory()){
            Long id = task.getId();
            sb.append(id + ",");
        }
        return sb.toString();
    }

    List<Long> historyFromString(String value) {
        List<Long> history = new ArrayList<>();
        String[] values = value.split(",");
        for(String number : values){
            long num = Long.parseLong(number);
            history.add(num);
        }
        return history;
    }
}
