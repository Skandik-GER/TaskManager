package manager;

import modules.Epic;
import modules.Subtask;
import modules.Task;

import java.util.ArrayList;
import java.util.HashMap;

public class Manager {
    private HashMap<Long,Task> taskmap = new HashMap<>();
    private HashMap<Long, Subtask> subtaskmap= new HashMap<>();
    private HashMap<Long, Epic> epicmap= new HashMap<>();
    private long nextId = 1;

    public void createTask(Task task){
        task.setId(nextId);
        taskmap.put(nextId,task);
        nextId++;
    }

    public void createSubtask(Subtask subtask){

        long epicID = subtask.getEpicId();
        if(!epicmap.containsKey(epicID)){
            System.out.println("Тысяча чертей!Не найден ID ");
            return;
        }
        Epic epic = epicmap.get(epicID);

        subtask.setId(nextId);
        subtaskmap.put(nextId,subtask);
        epic.addSubTask(subtask);
        nextId++;

    }
    public void createEpic(Epic epic){

        epic.setId(nextId);
        nextId++;
        epicmap.put(epic.getId(),epic);
    }

    public ArrayList<Task> getTasks() {
        return new ArrayList<>(taskmap.values());
    }

    public ArrayList<Epic> getEpics(){
        return new ArrayList<>(epicmap.values());
    }

    public ArrayList<Subtask> getSubtasks(){
        return new ArrayList<>(subtaskmap.values());
    }

    public void  removeAllTasks(){
        taskmap.clear();
    }

    public void removeTaskId(long id){
        taskmap.remove(id);
    }

    public void removeAllEpic(){
        epicmap.clear();
    }

    public void removeEpicId(long id){
        epicmap.remove(id);
    }

    public void removeAllSubtask(){
        subtaskmap.clear();
        for(Epic epic : epicmap.values()){
            epic.removeSubtasksAll();
        }
    }

    public void removeSubtaskId(long id){
        subtaskmap.remove(id);
    }

    public Task getTaskById(long id){
        return taskmap.get(id);
    }

    public Subtask getSubtaskById(long id){
        return subtaskmap.get(id);
    }

    public Epic getEpicById(long id){
        return epicmap.get(id);
    }

    public void updateTask(Task task){
        taskmap.remove(task.getId());
        taskmap.put(task.getId(),task);
    }

    public void updateEpic(Epic epic){
        epicmap.remove(epic.getId());
        epicmap.put(epic.getId(),epic);
    }

    public void updateSubtask(Subtask subtask){
        subtaskmap.put(subtask.getId(),subtask);
    }

}
