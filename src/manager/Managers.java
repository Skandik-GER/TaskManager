package manager;

public class Managers {
    private Managers() {
    }

    public static Manager getDefault(){
        return new InMemoryTaskManager();

    }
    public static HistoryManager getDefaultHistory(){
        return new InMemoryHistoryManager();
    }


}
