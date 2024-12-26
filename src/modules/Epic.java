package modules;

import java.util.ArrayList;

// Yellow
// Было бы здорово переопределить equals и hashcode у этого класса
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
        // RED
        // Запрещено проходиться по коллекции и удалять в ней элементы одновременно
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

    // RED
    // Методы принято называть с маленькой буквы
    public void UpdateStatus(){
        for (Subtask subtask : subTasks) {
            // RED
            // Обновление эпика необходимо сделать более рационально
            // без большого количества условных конструкций
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
