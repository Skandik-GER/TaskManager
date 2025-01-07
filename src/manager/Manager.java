package manager;

import model.Epic;
import model.Subtask;
import model.Task;

import java.util.ArrayList;
import java.util.HashMap;

public class Manager {
    private HashMap<Long, Task> taskmap = new HashMap<>();
    private HashMap<Long, Subtask> subtaskmap = new HashMap<>();
    private HashMap<Long, Epic> epicmap = new HashMap<>();
    private long nextId = 1;

    public void createTask(Task task) {
        task.setId(nextId);
        taskmap.put(nextId, task);
        nextId++;
    }

    public void createSubtask(Subtask subtask) {

        long epicID = subtask.getEpicId();
        if (!epicmap.containsKey(epicID)) {
            System.out.println("Тысяча чертей!Не найден ID ");
            return;
        }
        Epic epic = epicmap.get(epicID);

        subtask.setId(nextId);
        subtaskmap.put(nextId, subtask);
        epic.addSubTask(subtask);
        nextId++;

    }

    public void createEpic(Epic epic) {
        epic.setId(nextId);
        nextId++;
        epicmap.put(epic.getId(), epic);
    }

    public ArrayList<Task> getTasks() {
        return new ArrayList<>(taskmap.values());
    }

    public ArrayList<Epic> getEpics() {
        return new ArrayList<>(epicmap.values());
    }

    public ArrayList<Subtask> getSubtasks() {
        return new ArrayList<>(subtaskmap.values());
    }

    public void removeTaskId(long id) {
        if (taskmap.containsKey(id)) {
            taskmap.remove(id);
        } else {
            System.out.println("Такой Id не найден");
        }
    }

    public void removeAllTasks() {
        taskmap.clear();
    }

    public void removeAllEpic() {
        epicmap.clear();
        subtaskmap.clear();
    }

    // RED+
    // Не удаляются все сабтаски эпика
    public void removeEpicId(long id) {
        Epic epic = epicmap.get(id);
        epic.removeSubtasksAll();
        subtaskmap.remove(id);

    }

    public void removeAllSubtask() {
        for (Epic epic : epicmap.values()) {
            epic.removeSubtasksAll();
        }
        subtaskmap.clear();
    }

    // RED+
    // getEpicID
    public void removeSubtaskId(long id) {
        Subtask subtask = subtaskmap.get(id);
        Epic epic = epicmap.get(subtask.getEpicId());
        epic.removeSubtaskById(id);
        subtaskmap.remove(id);

    }

    public ArrayList<Subtask> getSubtasksByEpic(long epicId) {
        Epic epic = epicmap.get(epicId);
        return new ArrayList<>(epic.getSubTask().values());
    }

    public Task getTaskById(long id) {
        return taskmap.get(id);
    }

    public Subtask getSubtaskById(long id) {
        return subtaskmap.get(id);
    }

    public Epic getEpicById(long id) {
        return epicmap.get(id);
    }

    public void updateTask(Task task) {
        taskmap.put(task.getId(), task);
    }

    public void updateEpic(Epic newEpic) {
        Epic oldEpic = epicmap.get(newEpic.getId());
        HashMap<Long, Subtask> subtasks = oldEpic.getSubTask();
        newEpic.setSubTasks(subtasks);
        epicmap.put(newEpic.getId(), newEpic);
    }

    // RED+
    // getEpicID
    public void updateSubtask(Subtask subtask) {
        Epic epic = epicmap.get(subtask.getEpicId());
        epic.removeSubtaskById(subtask.getId());
        epic.addSubTask(subtask);
        subtaskmap.put(subtask.getId(), subtask);
    }

}
