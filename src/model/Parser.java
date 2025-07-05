package model;

public class Parser {
    public String toParse(Task task){
        return task.getName() + "," + task.getDescribe() + "," + task.getId() + "," + task.getStatus();
    }
}
