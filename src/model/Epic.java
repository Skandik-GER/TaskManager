package model;

import java.util.HashMap;

public class Epic extends Task {
    private HashMap<Long, Subtask> subTasks = new HashMap<>();

    public Epic(String name, String describe) {
        super(name, describe, Status.NEW);

    }

    public void setSubTasks(HashMap<Long, Subtask> subTasks) {
        this.subTasks = subTasks;
        updateStatus();
    }

    public HashMap<Long, Subtask> getSubTask() {
        return subTasks;
    }


    public void removeSubtasksAll() {
        subTasks.clear();
        status = Status.NEW;
    }

    public void removeSubtaskById(long id) {
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

    private void updateStatus() {
        boolean hasNew = false;
        boolean hasInProcess = false;
        boolean hasDone = false;
        // Yellow
        // Лучше добавлять блок default всегда
        for (Subtask subTask : subTasks.values()) {
            switch (subTask.getStatus()) {
                case Status.NEW:
                    hasNew = true;
                    break;
                case Status.IN_PROCESS:
                    hasInProcess = true;
                    break;
                case Status.DONE:
                    hasDone = true;
                    break;
            }
        }

        if (hasInProcess || (hasNew && hasDone)) {
            status = Status.IN_PROCESS;
        } else if (hasNew) {
            status = Status.NEW;
        } else if (hasDone) {
            status = Status.DONE;
        } else {
            status = Status.NEW;
        }
    }
}
