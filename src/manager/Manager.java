package manager;

import modules.Epic;
import modules.Subtask;
import modules.Task;

import java.util.ArrayList;
import java.util.HashMap;


// RED
// Отсутствует метод по получению всех сабтасков определенного метода
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

    // Yellow
    // При удалении желательно проверять есть ли вообще такая задача в мапе
    public void removeTaskId(long id){
        taskmap.remove(id);
    }

    // RED
    // Если мы удаляем эпики,
    // то мы обязаны удалить и все сабтаски
    public void removeAllEpic(){
        epicmap.clear();
    }

    // Yellow
    // При удалении желательно проверять есть ли вообще такая задача в мапе

    // RED
    // Если мы удаляем эпик,
    // то мы обязаны удалить и все сабтаски, которые к нему относятся
    public void removeEpicId(long id){
        epicmap.remove(id);
    }

    public void removeAllSubtask(){
        subtaskmap.clear();
        // RED
        // Эпики без сабтасков могут существовать,
        // а сабтаски без эпиков не могут

        // RED
        // Так же необходимо всем эпикам теперь присвоить NEW
        for(Epic epic : epicmap.values()){
            epic.removeSubtasksAll();
        }
    }

    // Yellow
    // При удалении желательно проверять есть ли вообще такая задача в мапе

    // RED
    // Кроме мапы ссылки на сабтаски хранятся в их эпиках
    // Оттуда их необходимо тоже удалить
    // и пересчитать статус эпика с учетом удаленного сабтаска
    public void removeSubtaskId(long id){
        subtaskmap.remove(id);
    }

    public Task getTaskById(long id){
        return taskmap.get(id);
    }

    // Yellow
    // Было бы здорово сгруппировать отдельно в классе методы по удалению, добавлению и обновлению
    public Subtask getSubtaskById(long id){
        return subtaskmap.get(id);
    }

    public Epic getEpicById(long id){
        return epicmap.get(id);
    }

    public void updateTask(Task task){
        // Yellow
        // Необязательно удалять старую пару,
        // тк она и так заменится засчет put
        taskmap.remove(task.getId());
        taskmap.put(task.getId(),task);
    }

    public void updateEpic(Epic epic){
        // Yellow
        // Необязательно удалять старую пару,
        // тк она и так заменится засчет put
        epicmap.remove(epic.getId());
        // RED
        // Необходимо сабтаски старого эпика оставлять
        // Здесь получается, что старые сабтаски теряются и ни к какому эпику не относятся после обновления
        // Потому что при обновлении приходит эпик с пустым списком
        epicmap.put(epic.getId(),epic);
    }

    // RED
    // Необходимо пересчитывать статус у эпика,
    // потому что у сабтаска после обновления он мог измениться
    public void updateSubtask(Subtask subtask){
        subtaskmap.put(subtask.getId(),subtask);
    }

}
