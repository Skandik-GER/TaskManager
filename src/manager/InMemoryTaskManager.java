package manager;

import model.Epic;
import model.Subtask;
import model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class InMemoryTaskManager implements Manager{
    HistoryManager historyManager = new InMemoryHistoryManager();
    final HashMap<Long, Task> taskmap = new HashMap<>();
    final HashMap<Long, Subtask> subtaskmap = new HashMap<>();
    final HashMap<Long, Epic> epicmap = new HashMap<>();
    long nextId = 1;

    @Override
    public void createTask(Task task) {
        task.setId(nextId);
        taskmap.put(nextId, task);
        nextId++;
    }
    @Override
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
    @Override
    public void createEpic(Epic epic) {
        epic.setId(nextId);
        nextId++;
        epicmap.put(epic.getId(), epic);
    }
    @Override
    public ArrayList<Task> getTasks() {
        return new ArrayList<>(taskmap.values());
    }
    @Override
    public ArrayList<Epic> getEpics() {
        return new ArrayList<>(epicmap.values());
    }
    @Override
    public ArrayList<Subtask> getSubtasks() {
        return new ArrayList<>(subtaskmap.values());
    }
    @Override
    public void removeTaskId(long id) {
        if (taskmap.containsKey(id)) {
            taskmap.remove(id);
        } else {
            System.out.println("Такой Id не найден");
        }
    }
    @Override
    public void removeAllTasks() {
        taskmap.clear();
    }

    public void removeAllEpic() {
        epicmap.clear();
        subtaskmap.clear();
    }
    @Override
    public void removeEpicId(long id) {
        Epic epic = epicmap.get(id);
        HashMap<Long,Subtask> subtasks = epic.getSubTask();
        for(Subtask subtask : subtasks.values()){
            subtaskmap.remove(subtask.getId());
        }
        epicmap.remove(id);
    }
    @Override
    public void removeAllSubtask() {
        for (Epic epic : epicmap.values()) {
            epic.removeSubtasksAll();
        }
        subtaskmap.clear();
    }
    @Override
    public void removeSubtaskId(long id) {
        Subtask subtask = subtaskmap.get(id);
        Epic epic = epicmap.get(subtask.getEpicId());
        epic.removeSubtaskById(id);
        subtaskmap.remove(id);

    }
    @Override
    public ArrayList<Subtask> getSubtasksByEpic(long epicId) {
        Epic epic = epicmap.get(epicId);
        return new ArrayList<>(epic.getSubTask().values());
    }
    @Override
    public Task getTaskById(long id) {
        historyManager.add(taskmap.get(id));
        return taskmap.get(id);
    }
    @Override
    public Subtask getSubtaskById(long id) {
        historyManager.add(subtaskmap.get(id));
        return subtaskmap.get(id);
    }
    @Override
    public Epic getEpicById(long id) {
        historyManager.add(epicmap.get(id));
        return epicmap.get(id);
    }
    @Override
    public void updateTask(Task task) {
        taskmap.put(task.getId(), task);
    }
    @Override
    public void updateEpic(Epic newEpic) {
        Epic oldEpic = epicmap.get(newEpic.getId());
        HashMap<Long, Subtask> subtasks = oldEpic.getSubTask();
        newEpic.setSubTasks(subtasks);
        epicmap.put(newEpic.getId(), newEpic);
    }
    @Override
    public void updateSubtask(Subtask subtask) {
        Epic epic = epicmap.get(subtask.getEpicId());
        epic.removeSubtaskById(subtask.getId());
        epic.addSubTask(subtask);
        subtaskmap.put(subtask.getId(), subtask);
    }
    @Override
    public List<Task> getHistory(){
        return historyManager.getHistory();
    }

    @Override
    public String toString() {
        return "InMemoryTaskManager{" +
                "taskmap=" + taskmap +
                ", subtaskmap=" + subtaskmap +
                ", epicmap=" + epicmap +
                ", history=" + historyManager.getHistory() +
                ", nextId=" + nextId +
                '}';
    }

}
