package modules;

import java.util.ArrayList;
import java.util.HashMap;

// Yellow
// Было бы здорово переопределить equals и hashcode у этого класса
public class Epic extends Task {
    private HashMap<Long, Subtask> subTasks = new HashMap<>();


    public Epic(String name, String describe) {
        super(name, describe, "NEW");

    }

    public HashMap<Long,Subtask> getSubTask(){
        return subTasks;
    }


    public void removeSubtasksAll() {
        subTasks.clear();
        status = "NEW";
    }

    public void removeSubtaskById(long id) {
        // RED +
        // Запрещено проходиться по коллекции и удалять в ней элементы одновременно
        subTasks.remove(id);
        updateStatus();

    }


    public void addSubTask(Subtask subTask) {
        subTasks.put(subTask.getId(), subTask);
        updateStatus();
    }

    @Override
    public String toString() {
        return "Epic{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", describe='" + describe + '\'' +
                ", name='" + name + '\'' +
                ", subTasks=" + subTasks +
                '}';
    }

    // RED+
    // Методы принято называть с маленькой буквы
    public void updateStatus() {
        boolean hasNew = false;
        boolean hasInProcess = false;
        boolean hasDone = false;

        for (Subtask subTask : subTasks.values()) {
            switch (subTask.getStatus()) {
                case "NEW":
                    hasNew = true;
                    break;
                case "IN PROCESS":
                    hasInProcess = true;
                    break;
                case "DONE":
                    hasDone = true;
                    break;
            }
        }

        if (hasInProcess || (hasNew && hasDone)) {
            status = "IN PROCESS";
        } else if (hasNew) {
            status = "NEW";
        } else if (hasDone) {
            status = "DONE";
        } else {
            status = "NEW";
        }
    }
}
