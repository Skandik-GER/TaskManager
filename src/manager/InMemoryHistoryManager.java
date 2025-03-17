package manager;

import model.Task;


import java.util.LinkedList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager{
    public List<Task> history = new LinkedList<>();
    // YELLOW+
    // Константы пишутся в стиле SCREAMING_SNAKE_CASE
    final static long SIZE = 10;
    // YELLOW++
    // Действие добавления задачи мы делаем в обоих случаях,
    // поэтому его можно вынести за условную конструкцию
    @Override
    public void add(Task task) {
        if(history.size() < SIZE){
           history.add(task);
        }else{
            history.removeFirst();
            history.add(task);
        }

    }

    @Override
    public List<Task> getHistory() {
        return history ;
    }
}
