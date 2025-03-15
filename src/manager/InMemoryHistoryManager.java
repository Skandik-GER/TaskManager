package manager;

import model.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager{
    // RED
    // Уже изучили полиморфизм, поэтому следует объявлять переменные типом интерфейса или абстрактного класса
    // Здесь подойдет просто List

    // YELLOW
    // В данном случае больше подойдет LinkedList,
    // так как часто происходит удаление и добавление начальных и конечных элементов
    // List<Task> history = new LinkedList<>(10);

    // Green
    // Здорово, что мы сразу объявляем вместимость списка историй,
    // но аргумент initialCapacity - это лишь изначальная вместимость
    // Список все равно сможет расширяться

    // RED
    // По условию задачи необходимо сохранять историю лишь последних 10-ти задач

    // YELLOW
    // так же поле можно сделать финализированным
    ArrayList<Task> history = new ArrayList<>(10);

    @Override
    public void add(Task task) {
        history.add(task);
    }

    @Override
    public List<Task> getHistory() {
        return history ;
    }
}
