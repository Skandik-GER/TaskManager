package manager;

import model.Epic;
import model.Subtask;
import model.Task;

import java.util.ArrayList;
import java.util.List;

// GREEN
// Замечательно, абстракция поддерживается!
public interface Manager {


    void createTask(Task task);

    void createSubtask(Subtask subtask);

    void createEpic(Epic epic);

    ArrayList<Task> getTasks();

    ArrayList<Epic> getEpics();

    ArrayList<Subtask> getSubtasks();

    void removeTaskId(long id);

    void removeAllTasks();

    void removeAllEpic();

    void removeEpicId(long id);

    void removeAllSubtask();

    void removeSubtaskId(long id);

    ArrayList<Subtask> getSubtasksByEpic(long epicId);

    Task getTaskById(long id);

    Subtask getSubtaskById(long id);

    Epic getEpicById(long id);

    void updateTask(Task task);

    void updateEpic(Epic newEpic);

    void updateSubtask(Subtask subtask);

    List<Task> getHistory();

}
