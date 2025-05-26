package manager;

import model.Epic;
import model.Subtask;
import model.Task;

import java.util.*;


public class InMemoryTaskManager implements Manager{

    private final HistoryManager historyManager = Managers.getDefaultHistory();

    private final Map<Long, Task> taskmap = new HashMap<>();
    private final Map<Long, Subtask> subtaskmap = new HashMap<>();
    private final Map<Long, Epic> epicmap = new HashMap<>();
    private long nextId = 1;

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

    // RED
    // Задачи необходимо добавить в историю
    @Override
    public List<Task> getTasks() {
        return new ArrayList<>(taskmap.values());
    }

    // RED
    // Задачи необходимо добавить в историю
    @Override
    public List<Epic> getEpics() {
        return new ArrayList<>(epicmap.values());
    }

    // RED
    // Задачи необходимо добавить в историю
    @Override
    public List<Subtask> getSubtasks() {
        return new ArrayList<>(subtaskmap.values());
    }
    @Override
    public void removeTaskId(long id) {
        if (taskmap.containsKey(id)) {
            taskmap.remove(id);
            historyManager.remove(id);
        } else {
            System.out.println("Такой Id не найден");
        }
    }
    // RED
    // При удалении задач, так же необходимо почистить историю
    @Override
    public void removeAllTasks() {
        taskmap.clear();
    }

    // RED
    // 1. Нет аннотации переопределения
    // 2. При удалении эпиков, так же необходимо почистить соответствующие задачи из истории
    public void removeAllEpic() {
        epicmap.clear();
        subtaskmap.clear();
    }
    @Override
    public void removeEpicId(long id) {
        Epic epic = epicmap.get(id);
        Map<Long,Subtask> subtasks = epic.getSubTask();
        for(Long subtaskId : subtasks.keySet()){
            historyManager.remove(subtaskId);
            subtaskmap.remove(subtaskId);
        }
        epicmap.remove(id);
        historyManager.remove(id);
    }
    // RED
    // При удалении подзадач, так же необходимо почистить историю
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
        historyManager.remove(id);

    }
    // RED
    // Необходимо добавить в историю все сабтаски эпика
    @Override
    public List<Subtask> getSubtasksByEpic(long epicId) {
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
    // RED
    // В main показал тестовый сценарий, при котором произойдет баг
    // из-за этого метода
    @Override
    public void updateTask(Task task) {
        taskmap.put(task.getId(), task);
    }

    // RED
    // В main показал тестовый сценарий, при котором произойдет баг
    // из-за этого метода
    @Override
    public void updateEpic(Epic newEpic) {
        Epic oldEpic = epicmap.get(newEpic.getId());
        Map<Long, Subtask> subtasks = oldEpic.getSubTask();
        newEpic.setSubTasks(subtasks);
        epicmap.put(newEpic.getId(), newEpic);
    }
    // RED
    // В main показал тестовый сценарий, при котором произойдет баг
    // из-за этого метода
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

    // YELLOW
    // Лучше выводить просто размеры мап, чтобы информация в выводе не казалась перегруженной
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
