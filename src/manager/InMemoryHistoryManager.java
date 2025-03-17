package manager;

import model.Task;


import java.util.LinkedList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager{
    public List<Task> history = new LinkedList<>();

    final static long SIZE = 10;

    @Override
    public void add(Task task) {
        if(history.size() >= SIZE){
            history.removeFirst();
        history.add(task);
    }

    @Override
    public List<Task> getHistory() {
        return history ;
    }
}
