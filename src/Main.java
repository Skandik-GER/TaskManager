
import manager.InMemoryTaskManager;
import manager.Manager;
import model.Status;
import model.Subtask;
import model.Task;
import model.Epic;

public class Main {
    public static void main(String[] args) {

        Task task1 = new Task("Sleep", "Fall asleep ", Status.NEW);
        Task task2 = new Task("sSleep", "Fall asleep ", Status.NEW);
        Task updtask3 = new Task(2, "NotSleep", "Not sleep", Status.IN_PROCESS);

        Epic epic1 = new Epic("Backflip", "Jump with flip");
        Epic epic2 = new Epic("Backflip", "Jump with flip");

        Subtask subtask1 = new Subtask("Sit", "sit", 3, Status.DONE);
        Subtask subtask2 = new Subtask("JUdMP", "JUMP", 3, Status.NEW);
        Subtask subtask3 = new Subtask("211JUMP", "JUMP", 3, Status.DONE);
        Subtask subtask4 = new Subtask("Sgdfgd", "sit", 4, Status.DONE);
        Subtask subtask5 = new Subtask("JgfdgdfMP", "JUMP", 4, Status.NEW);
        Subtask subtask6 = new Subtask("211JgdfgdfgdfP", "JUMP", 4, Status.DONE);

        Subtask updSubtask4 = new Subtask(6, "jMP", "jhP", 3, Status.DONE);
        Manager manager = new InMemoryTaskManager();
        Epic epic = new Epic("Sit", "sit");
        manager.createTask(task1);
        manager.createTask(task2);
        manager.updateTask(updtask3);


        manager.createEpic(epic);
        manager.createEpic(epic1);
        manager.createEpic(epic2);
        manager.createSubtask(subtask1);
        manager.createSubtask(subtask2);
        manager.createSubtask(subtask3);
        manager.createSubtask(subtask4);
        manager.createSubtask(subtask5);
        manager.createSubtask(subtask6);

        manager.getEpicById(epic1.getId());
        manager.getEpicById(epic2.getId());
        manager.getTaskById(1);
        manager.getTaskById(2);
        manager.getSubtaskById(subtask1.getId());
        manager.getSubtaskById(subtask2.getId());
        manager.getSubtaskById(subtask3.getId());
        manager.getSubtaskById(subtask4.getId());
        System.out.println(manager.getHistory());

    }
}