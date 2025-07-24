package model;

public class Parser {

    public String toParse(Subtask task){
        return task.getName() + "," + task.getDescribe() + "," + task.getId() + "," + task.getStatus() + "," + task.getEpicId();
    }
    public String toParse(Task task){
        return task.getName() + "," + task.getDescribe() + "," + task.getId() + "," + task.getStatus();
    }
}
