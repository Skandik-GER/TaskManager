package manager;

import modules.Epic;
import modules.Subtask;
import modules.Task;

import java.util.ArrayList;
import java.util.HashMap;

// RED
// Правила чекстайла не соблюдены
// Не хватает пробелов, лишние отступы
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

    // RED
    // Лишний пустой метод, который ничего не делает
    public void getSubtask(){

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

    public ArrayList<Subtask> getSubtasksByEpic(long epicId){
        // RED
        // Переменные принято называть с маленькой буквы
        ArrayList<Subtask> SubtasksById = new ArrayList<>();
        // RED
        // Не очень понятно как здесь возвращаются сабтаски эпика
        // Конкретно в этой реализации находятся сабтаски, у которых айди совпадает с айди эпика
        // Необходимо было сравнивать epicId сабтаска с айди эпика
        // Либо же есть другой более оптимальный вариант:
        // получить эпик по айди и вернуть все его сабтаски, которые хранятся внутри него
        for(Subtask subtasks : subtaskmap.values()){
            if(subtasks.getId() == epicId ){
                SubtasksById.add(subtasks);
            }
        }
        return SubtasksById;
    }

    public void removeTaskId(long id){
        if(taskmap.containsKey(id)){
            taskmap.remove(id);
        }else{
            System.out.println("Такой Id не найден");
        }
    }

    public void removeAllEpic(){
        epicmap.clear();
        subtaskmap.clear();
    }

    // RED
    // Эпик не удаляется
    // Так же не удаляются все его сабтаски
    public void removeEpicId(long id){
        if(epicmap.containsKey(id)){

        }
    }

    public void removeAllSubtask(){
        for(Epic epic : epicmap.values()){
            epic.removeSubtasksAll();
            // RED
            // Излишне чистить мапу в цикле много раз
            subtaskmap.clear();
        }
    }

    // RED
    // Неэффективно искать в цикле и перебирать все эпики
    // Мы можем получить нужный эпик по полю epicId в сабтаске
    // + излишне в цикле много раз удалять сабтаску и общего хранилища
    public void removeSubtaskId(long id){
        for(Epic epic : epicmap.values()){
            epic.removeSubtaskById(id);
            subtaskmap.remove(id);
        }
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
        taskmap.put(task.getId(),task);
    }

    // RED
    // Необходимо сабтаски старого эпика оставлять
    // Здесь получается, что старые сабтаски теряются и ни к какому эпику не относятся после обновления
    // Потому что при обновлении приходит эпик с пустым списком

    // Замечание все еще актуально, старые сабтаски не сохраняются в новый эпик
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
