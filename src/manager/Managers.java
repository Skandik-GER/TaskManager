package manager;

// RED
// Неиспользуемые импорты необходимо чистить

import model.Epic;
import model.Subtask;
import model.Task;

import java.util.ArrayList;
import java.util.List;

public class Managers {

    // YELLOW
    // Класс утилитарный, объектов на основе него мы не планируем создавать
    // поэтому можно вообще запретить это делать, объявив приватный конструктор
    public static Manager getDefault(){
        return new InMemoryTaskManager();

    }
    public static HistoryManager getDefaultHistory(){
        return new InMemoryHistoryManager();
    }
}
