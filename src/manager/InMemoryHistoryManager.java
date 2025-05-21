package manager;

import model.Task;


import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {
    public class CustomLinkedList<T> {

        private Node<T> head;

        private Node<T> tail;

        private int size = 0;


        public List<T> getTasks() {
            List<T> tasks = new ArrayList<>();
            Node<T> cur = head;
            while (cur != null) {
                tasks.add(cur.data);
                cur = cur.next;
            }
            return tasks;
        }

        public void linkLast(T task) {
            final Node<T> oldtail = tail;
            final Node<T> newNode = new Node<>(oldtail, null, task);
            tail = newNode;
            if (size == 0) {
                head = newNode;
            }
            size++;
        }

        public void removeNode(Node<T> node) {
            if (node == head) {
                node = node.next;
                if (head != null) {
                    head.prev = null;
                }
            }
            if (node == tail) {
                node = node.prev;
                if (tail != null) {
                    tail.next = null;
                }
            }

            Node<T> nodePrev = node.prev;
            nodePrev.next = node.next;

            Node<T> nodeNext = node.next;
            nodeNext.prev = node.prev;

            size--;
        }

        public int size() {
            return this.size;
        }
    }

    Map<Long, Node<Task>> tasksHistory = new HashMap<>();
    CustomLinkedList<Task> tasks = new CustomLinkedList<>();


    @Override
    public void add(Task task) {
        tasksHistory.remove(task.getId());
        tasks.linkLast(task);
    }

    @Override
    public void remove(int id) {
        Node<Task> node = tasksHistory.get(id);
        tasks.removeNode(node);
        tasksHistory.remove(id);
    }

    @Override
    public List<Task> getHistory() {
        return tasks.getTasks();
    }
}
