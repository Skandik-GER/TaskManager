package manager;

import model.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileBackedTasksManager extends InMemoryTaskManager {
    private final Parser parser = new Parser();
    // RED+
    // поля можно сделать final
    private final String path = "resources/data.csv";
    private final String hist = "resources/history.csv";


    public FileBackedTasksManager() {
        if (!Files.exists(Path.of(path))) {
            try (FileWriter fileWriter = new FileWriter(path, StandardCharsets.UTF_8)) {
                fileWriter.write("type,name,describe,id,status\n");
            } catch (IOException e) {
                System.out.println("Ошибка чтения файла");
            }
        }
    }

    // RED+
    // В файлик сохраняется одна и та же задача много раз
    // данные дублируются

    // RED+
    // Метод сохранения не должен вызываться извне этого класса
    // Им руководит только сам объект manager

    // RED+?
    // Метод сохранения не сохраняет информацию о типе задачи
    // Это повлечет за проблемы при работе с методом load
    // Невозможно будет понять, какой тип задачи
    private void clearFileHist(String filePath) {
        try (FileWriter fw = new FileWriter(filePath, false)) {
        } catch (IOException e) {
            throw new RuntimeException("Ошибка очистки " + filePath, e);
        }
    }

    public void save(String fileName) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            StringBuilder stringBuilder = new StringBuilder("type,name,description,id,status\n");
            for (Epic epic : epicmap.values()) {
                stringBuilder.append("EPIC,").append(parser.toParse(epic)).append("\n");
            }
            for (Task task : taskmap.values()) {
                stringBuilder.append("TASK,").append(parser.toParse(task)).append("\n");
            }
            for (Subtask subtask : subtaskmap.values()) {
                stringBuilder.append("SUBTASK,").append(parser.toParse(subtask)).append("\n");
            }

            stringBuilder.append("\n");
            fileWriter.write(stringBuilder.toString().trim());

        } catch (IOException e) {
            System.out.println("Ошибка во время чтения файла.");
        }

        try (FileWriter fileWriter = new FileWriter(hist,true)) {
            fileWriter.write(historyToString());
        } catch (IOException e) {
            System.out.println("Ошибка чтения");
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


    private String historyToString() {
        final StringBuilder sb = new StringBuilder();
        for (Task task : historyManager.getHistory()) {
            Long id = task.getId();
            sb.append(id).append(",");
        }
        if (!sb.isEmpty()) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    private static List<Long> historyFromString(String value) {
        final List<Long> history = new ArrayList<>();
        String[] values = value.split(",");
        for (String number : values) {
            long num = Long.parseLong(number);
            history.add(num);
        }
        return history;

    }

    // YELLOW+
    // Метод лучше сделать статическим
    private static Task fromString(String value) {
        String[] parts = value.split(",");
        String name = parts[1];
        String describe = parts[2];
        long id = Long.parseLong(parts[3]);
        Status status = Status.valueOf(parts[4]);
        return new Task(id, name, describe, status);
    }

    private static Epic fromStringEpic(String value) {
        String[] parts = value.split(",");
        String name = parts[1];
        String describe = parts[2];
        return new Epic(name, describe);
    }

    private static Subtask fromStringSubtask(String value) {
        String[] parts = value.split(",");
        String name = parts[1];
        String describe = parts[2];
        long id = Long.parseLong(parts[3]);
        Status status = Status.valueOf(parts[4]);
        return new Subtask(name, describe, id, status);
    }

    // RED
    // Нигде не протестировал работу метода
    public static FileBackedTasksManager loadFromFile(String file) {
        FileBackedTasksManager manager = new FileBackedTasksManager();
        try (BufferedReader fileReader = new BufferedReader(new FileReader(file))) {
            fileReader.readLine();
            String line;
            while ((line = fileReader.readLine()) != null) {
                line = line.trim();
                String[] data = line.split(",");
                TaskType taskType = TaskType.valueOf(data[0].trim().toUpperCase());
                switch (taskType) {
                    case TASK:
                        Task task = fromString(line);
                        manager.createTask(task);
                        break;
                    case EPIC:
                        Epic epic = fromStringEpic(line);
                        manager.createEpic(epic);
                        break;
                    case SUBTASK:
                        Subtask subtask = fromStringSubtask(line);
                        manager.createSubtask(subtask);
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла");
        } catch (IllegalArgumentException e) {
            System.err.println("ОШИБКА: неизвестный тип задачи");
        }


        return manager;
    }
}
