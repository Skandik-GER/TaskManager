package manager;

import model.Epic;
import model.Parser;
import model.Subtask;
import model.Task;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class FileBackedTasksManager extends InMemoryTaskManager {
    private final Parser parser = new Parser();
    private String path = "resources/data.csv";
    private String hist = "resources/history.csv";

    public FileBackedTasksManager() {
        try (FileWriter fileWriter = new FileWriter(path, StandardCharsets.UTF_8)) {
            fileWriter.write("name,describe,id,status\n");
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла");
        }
    }

    public void save(String filename) {
        try (FileWriter fileWriter = new FileWriter(filename, true)) {
            for (Epic epic : epicmap.values()) {
                fileWriter.write(parser.toParse(epic) + "\n");
            }
            for (Task task : taskmap.values()) {
                fileWriter.write(parser.toParse(task) + "\n");
            }
            for (Subtask subtask : subtaskmap.values()) {
                fileWriter.write(parser.toParse(subtask) + "\n");
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла");
        }
        try (FileWriter fileWriter = new FileWriter(hist, true)) {
            fileWriter.write(historyToString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createSubtask(Subtask subtask) {
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
    public void updateTask(Task task) {
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

    public String historyToString() {
        final StringBuilder sb = new StringBuilder();
        for (Task task : historyManager.getHistory()) {
            Long id = task.getId();
            sb.append(id).append(",");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public static List<Long> historyFromString(String value) {
        final List<Long> history = new ArrayList<>();
        String[] values = value.split(",");
        for (String number : values) {
            long num = Long.parseLong(number);
            history.add(num);
        }
        return history;

    }

    public Task fromString(String value){
        String[] parts = value.split(",");
        String name = parts[1];
        String describe = parts[2];
        long id = Long.parseLong(parts[0]);
        String status = parts[3];
        return  new Task(id,name,describe,status);
    }

    public static FileBackedTasksManager loadFromFile(File file) {
        FileBackedTasksManager manager = new FileBackedTasksManager();
        try (BufferedReader fileReader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean fl = true;
            List<Long> history = new ArrayList<>();
            while((line = fileReader.readLine()) != null){
                if (line.isEmpty()) {
                    fl = false;
                    continue;
                }
                if(fl){
                    Task task = manager.fromString(line);
                    manager.parser.toParse(task);
                }else{
                    history = historyFromString(line);
                }

                for(Long id : history){
                    Task task = manager.getTaskById(id);
                    if(task != null){
                        manager.historyManager.add(task);
                    }
                    Subtask subtask = manager.getSubtaskById(id);
                    if(subtask != null){
                        manager.historyManager.add(subtask);
                    }
                    Epic epic = manager.getEpicById(id);
                    if(epic != null){
                        manager.historyManager.add(epic);
                    }
                }

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return manager;
    }
}
