package modules;

public class Subtask extends Task {
    private long epicId;

    public Subtask(String name, String describe,long epicId, String status) {
        super(name, describe,status);
        this.epicId = epicId;
    }

    public Subtask(long id,String name, String describe,long epicId, String status) {
        super(id,name, describe,status);
        this.epicId = epicId;
    }

    public long getEpicId() {
        return epicId;
    }

    public void setEpicId(long epicId) {
        this.epicId = epicId;
    }

    @Override
    public String toString() {
        return "Subtask{" +
                "epicId=" + epicId +
                ", name='" + name + '\'' +
                ", describe='" + describe + '\'' +
                ", id=" + id +
                ", status='" + status + '\'' +
                '}';
    }
}
