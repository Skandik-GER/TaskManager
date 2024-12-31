package manager;

import modules.Epic;
import modules.Subtask;
import modules.Task;

import java.util.ArrayList;
import java.util.HashMap;


// RED+
// Отсутствует метод по получению всех сабтасков определенного эпика
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
    public void getSubtask(){

    }

    // GREEN
    // Молодец! Метод добавления сабтаска реализован корректно с учетом сущестовавания эпика
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

    public ArrayList<Subtask> getSubtasksByEpic(long epicId){
        ArrayList<Subtask> SubtasksById = new ArrayList<>();
        for(Subtask subtasks : subtaskmap.values()){
            if(subtasks.getId() == epicId ){
                SubtasksById.add(subtasks);
            }
        }
        return SubtasksById;
    }

    // Yellow+
    // При удалении желательно проверять есть ли вообще такая задача в мапе
    public void removeTaskId(long id){
        if(taskmap.containsKey(id)){
            taskmap.remove(id);
        }else{
            System.out.println("Такой Id не найден");
        }
    }

    // RED+
    // Если мы удаляем эпики,
    // то мы обязаны удалить и все сабтаски
    public void removeAllEpic(){
        epicmap.clear();
        subtaskmap.clear();
    }

    // Yellow+
    // При удалении желательно проверять есть ли вообще такая задача в мапе

    // RED+
    // Если мы удаляем эпик,
    // то мы обязаны удалить и все сабтаски, которые к нему относятся
    public void removeEpicId(long id){
        if(epicmap.containsKey(id)){

        }
    }

    public void removeAllSubtask(){
        // RED+
        // Так же необходимо всем эпикам теперь присвоить NEW
        for(Epic epic : epicmap.values()){
            epic.removeSubtasksAll();
            subtaskmap.clear();
        }
    }

    // Yellow+
    // При удалении желательно проверять есть ли вообще такая задача в мапе

    // RED+
    // Кроме мапы ссылки на сабтаски хранятся в их эпиках
    // Оттуда их необходимо тоже удалить
    // и пересчитать статус эпика с учетом удаленного сабтаска
    public void removeSubtaskId(long id){
        for(Epic epic : epicmap.values()){
            epic.removeSubtaskById(id);
            subtaskmap.remove(id);
        }
    }

    public Task getTaskById(long id){
        return taskmap.get(id);
    }

    // Yellow NO!
    // Было бы здорово сгруппировать отдельно в классе методы по удалению, добавлению и обновлению.
    public Subtask getSubtaskById(long id){
        return subtaskmap.get(id);
    }

    public Epic getEpicById(long id){
        return epicmap.get(id);
    }

    public void updateTask(Task task){
        // Yellow+
        // Необязательно удалять старую пару,
        // тк она и так заменится засчет put
        taskmap.put(task.getId(),task);
    }


// Yellow+
// Необязательно удалять старую пару,
// тк она и так заменится засчет put    epic 2 na epic 3
// RED+
// Необходимо сабтаски старого эпика оставлять
// Здесь получается, что старые сабтаски теряются и ни к какому эпику не относятся после обновления
// Потому что при обновлении приходит эпик с пустым списком

    public void updateEpic(Epic newEpic){
        Epic oldEpic = epicmap.get(newEpic.getId());
        HashMap<Long, Subtask> subtasks = oldEpic.getSubTask();
        newEpic.getSubTask();
        epicmap.put(newEpic.getId(),newEpic);
    }

    // RED
    // Необходимо пересчитывать статус у эпика,
    // потому что у сабтаска после обновления он мог измениться
    public void updateSubtask(Subtask subtask){
        subtaskmap.put(subtask.getId(),subtask);
    }

}
