import manager.Manager;
import modules.Subtask;
import modules.Task;
import modules.Epic;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Task task1 = new Task("Sleep", "Fall asleep ", "New");
        Task task2 = new Task("sSleep", "Fall asleep ", "New");
        Task updtask3 = new Task(2, "NotSleep", "Not sleep", "in process");

        Epic epic1 = new Epic("Backflip", "Jump with flip");
        Epic epic2 = new Epic("Backflip", "Jump with flip");

        Subtask subtask1 = new Subtask("Sit", "sit", 3, "DONE");
        Subtask subtask2 = new Subtask("JUdMP", "JUMP", 3, "DONE");
        Subtask subtask3 = new Subtask("211JUMP", "JUMP", 3, "DONE");
        Subtask UPDsubtask4 = new Subtask(6, "jMP", "jhP", 3, "DONE");
        Manager manager = new Manager();
        Epic epic = new Epic("Sit", "sit");
        manager.createTask(task1);
        manager.createTask(task2);
        manager.updateTask(updtask3);
        System.out.println(manager.getTaskById(2));


        manager.createEpic(epic);
        manager.createEpic(epic1);
        manager.createSubtask(subtask1);
        manager.createSubtask(subtask2);
        manager.createSubtask(subtask3);



        System.out.println(epic.getStatus());
        System.out.println(manager.getSubtasks());
        manager.updateSubtask(UPDsubtask4);
        System.out.println(epic.getStatus());
        System.out.println(manager.getSubtasks());
        System.out.println(epic.getStatus());


        manager.getSubtaskById(subtask1.getId());
        System.out.println(manager.getSubtasks());
        manager.getTaskById(task1.getId());
        System.out.println(manager.getTasks());
        manager.getEpicById(epic1.getId());
        System.out.println(manager.getEpics());



        manager.removeAllSubtask();
        manager.removeAllTasks();
        manager.removeAllEpic();
        System.out.println(manager.getTasks());
        System.out.println(manager.getSubtasks());
        System.out.println(manager.getEpics());
        manager.removeSubtaskId(subtask1.getId());
        System.out.println(manager.getSubtasks());
        manager.removeTaskId(task1.getId());
        System.out.println(manager.getTasks());
        manager.removeEpicId(epic1.getId());
        System.out.println(manager.getEpics());


    }
}