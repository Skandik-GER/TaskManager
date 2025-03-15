package manager;

import model.Epic;
import model.Subtask;
import model.Task;

import java.util.ArrayList;
import java.util.List;

public class Managers {
    public static Manager getDefault(){
        return new InMemoryTaskManager();

    }
    public static HistoryManager getDefaultHistory(){
        return new InMemoryHistoryManager();
    }
}
