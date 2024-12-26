package modules;

import java.util.ArrayList;

public class Epic extends Task {
    private ArrayList<Subtask> subTasks = new ArrayList<>();


    public Epic(String name, String describe) {
        super(name, describe, "NEW");

    }

    public void removeSubtasksAll(){
        subTasks.clear();
        status = "NEW";
    }

    public void removeSubtaskById( long id){
        for (int i = 0;i < subTasks.size();i++){
            if(subTasks.get(i).getId() == id){
                subTasks.remove(i);
            }
            UpdateStatus();
        }

    }


    public void addSubTask(Subtask subTask) {
        subTasks.add(subTask);
        UpdateStatus();
    }

    public void UpdateStatus(){
        for (Subtask subtask : subTasks) {

            if (subtask.getStatus().equals("NEW") && status.equals("DONE")) {
                status = "IN PROCESS";
            }

            if (subtask.getStatus().equals("NEW") && status.equals("IN PROCESS")) {
                status = "IN PROCESS";
            }

            if (subtask.getStatus().equals("NEW") && status.equals("NEW")) {
                status = "NEW";
            }

            if (subtask.getStatus().equals("IN PROCESS") && status.equals("NEW")) {
                status = "IN PROCESS";
            }

            if (subtask.getStatus().equals("IN PROCESS") && status.equals("DONE")) {
                status = "IN PROCESS";
            }

            if (subtask.getStatus().equals("IN PROCESS") && status.equals("IN PROCESS")) {
                status = "IN PROCESS";
            }


            if (subtask.getStatus().equals("DONE") && !status.equals("IN PROCESS") && !status.equals("NEW")) {
                status = "DONE";
            }

            if (subtask.getStatus().equals("DONE") && status.equals("IN PROCESS")) {
                status = "IN PROCESS";
            }

            if (subtask.getStatus().equals("DONE") && !status.equals("IN PROCESS") && !status.equals("NEW")) {
                status = "DONE";
            }

            if (subtask.getStatus().equals("DONE") && status.equals("NEW")) {
                status = "IN PROCESS";
                if (subtask.getStatus().equals("DONE")){
                    status = "DONE";
                }
            }

        }
    }


    @Override
    public String toString() {
        return "Epic{" +
                "subTasksId=" + subTasks +
                ", status='" + status + '\'' +
                ", id=" + id +
                ", describe='" + describe + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
