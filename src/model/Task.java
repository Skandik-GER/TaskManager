package model;

public class Task {
    protected String name;
    protected String describe;
    protected long id;
    protected Status status;

    public Task(String name, String describe, Status status) {
        this.name = name;
        this.describe = describe;
        this.status = status;
    }

    public Task(long id, String name, String describe, Status status) {
        this(name, describe, status);
        this.id = id;
    }




    public String getName() {
        return name;
    }

    public String getDescribe() {
        return describe;
    }

    public long getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", describe='" + describe + '\'' +
                ", id=" + id +
                ", status='" + status + '\'' +
                '}';
    }


}
