import manager.FileBackedTasksManager;
import manager.InMemoryTaskManager;
import manager.Manager;
import model.Status;
import model.Subtask;
import model.Task;
import model.Epic;
import model.Parser;

public class Main {
    public static void main(String[] args) {
        Manager manager = new FileBackedTasksManager();
        Task task1 = new Task("Karowka", "Fall asleep ", Status.NEW);
        Task task2 = new Task("sSleep", "Fall asleep ", Status.NEW);
//        Task updtask3 = new Task(1, "NotSleep", "Not sleep", Status.IN_PROCESS);

        Epic epic1 = new Epic("Backflip2", "Jump with flip");
//        Epic epic2 = new Epic("Backflip2", "Jump with flip");

//
        Subtask subtask1 = new Subtask("Sit", "sit", 1, Status.DONE);
//        Subtask subtask2 = new Subtask("JUdMP", "JUMP", 1, Status.NEW);
//        Subtask subtask3 = new Subtask("211JUMP", "JUMP", 3, Status.DONE);
//        Subtask subtask4 = new Subtask("Sgdfgd", "sit", 4, Status.DONE);
//        Subtask subtask5 = new Subtask("JgfdgdfMP", "JUMP", 4, Status.NEW);
//        Subtask subtask6 = new Subtask("211JgdfgdfgdfP", "JUMP", 4, Status.DONE);

        Subtask updSubtask = new Subtask(2, "jMP", "jhP", 1, Status.DONE);
        manager.createEpic(epic1);
        manager.createSubtask(subtask1);
        manager.getSubtaskById(2);
        manager.getEpicById(1);
        manager.updateSubtask(updSubtask);
        System.out.println(manager);


        // Epic epic = new Epic("Sit", "sit");

        // RED
        // Сценарий, при котором случается баг
        // (проблема в методе обновления, задачу необходимо обновлять так же в истории, а то произойдет утечка)
//        manager.createTask(task1);
//        manager.getTaskById(1);
//        System.out.println(manager.getHistory());
//        manager.updateTask(updtask3);
//        System.out.println(manager.getHistory());
//        manager.removeAllTasks();
//        System.out.println(manager.getHistory());

//        manager.createSubtask(subtask1);
//        manager.createEpic(epic2);
//        manager.updateEpic(epic2);
//        System.out.println(manager.getHistory());


//        manager.createTask(task1);
//        manager.createTask(task2);
//        manager.updateTask(updtask3);
//
//
//        manager.createEpic(epic);
//        manager.createEpic(epic1);
//        manager.createEpic(epic2);
//
//        manager.createSubtask(subtask1);
//        manager.createSubtask(subtask2);
//        manager.createSubtask(subtask3);
//        manager.createSubtask(subtask4);
//        manager.createSubtask(subtask5);
//        manager.createSubtask(subtask6);
//        manager.updateSubtask(subtask3);
//        System.out.println(manager.getHistory());
//
//        manager.getEpicById(epic1.getId());
//        manager.getEpicById(epic2.getId());
//
//        manager.getTaskById(1);
//        manager.getTaskById(2);
//
//        manager.getSubtaskById(subtask1.getId());
//        manager.getSubtaskById(subtask2.getId());
//        manager.getSubtaskById(subtask3.getId());
//        manager.getSubtaskById(subtask4.getId());
//        System.out.println("----------------");
//        manager.createEpic(epic1);
//        manager.createTask(task1);
//        manager.createSubtask(subtask1);
//        manager.getSubtaskById(subtask1.getId());
//        System.out.println(manager.getHistory());
//        manager.createTask(task2);
//        manager.getTaskById(2);
//        System.out.println(manager.getHistory());
//        manager.removeTaskId(2);
//        manager.removeSubtaskId(subtask1.getId());
//        manager.getEpicById(epic1.getId());
//        manager.createSubtask(subtask1);
//        manager.createSubtask(subtask2);
//        System.out.println(manager.getHistory());
//        manager.removeEpicId(epic1.getId());
//        System.out.println("---");
//        manager.removeEpicId(epic1.getId());
//        System.out.println(manager.getHistory());
//        manager.createEpic(epic2);
//        manager.createTask(task1);
//        manager.createTask(task2);
//        manager.createSubtask(subtask1);
//        manager.createSubtask(subtask2);
//        System.out.println("____Tasks____");
//        manager.getTaskById(task1.getId());
//        System.out.println(manager.getHistory());
//        manager.getTaskById(task2.getId());
//        System.out.println(manager.getHistory());
//        manager.getTaskById(task1.getId());
//        System.out.println(manager.getHistory());
//        manager.removeTaskId(task1.getId());
//        System.out.println(manager.getHistory());
//        manager.removeTaskId(task2.getId());
//        System.out.println(manager.getHistory());
//        System.out.println("____SUBTASKS____");
//        manager.getSubtaskById(subtask1.getId());
//        System.out.println(manager.getHistory());
//        manager.getSubtaskById(subtask2.getId());
//        System.out.println(manager.getHistory());
//        System.out.println("____Epics____");
//        manager.getEpicById(epic2.getId());
//        System.out.println(manager.getHistory());
//        manager.removeEpicId(epic2.getId());
//        System.out.println(manager.getHistory());


    }
}