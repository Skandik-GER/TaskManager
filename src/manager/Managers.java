package manager;

// RED ++++
// Неиспользуемые импорты необходимо чистить

public class Managers {
    private Managers() {
    }

    // YELLOW++++
    // Класс утилитарный, объектов на основе него мы не планируем создавать
    // поэтому можно вообще запретить это делать, объявив приватный конструктор
    public static Manager getDefault(){
        return new InMemoryTaskManager();

    }
    public static HistoryManager getDefaultHistory(){
        return new InMemoryHistoryManager();
    }


}
