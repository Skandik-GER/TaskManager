package manager;

import model.Epic;
import model.Task;

import java.util.NoSuchElementException;

public class CustomLinkedList<T> {
    class Node<E> {
        public E data;
        public Node<E> next;
        public Node<E> prev;

        public Node(Node<E> prev, E data, Node<E> next) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }


    /**
     * Указатель на первый элемент списка. Он же first
     */
    private Node<T> head;

    /**
     * Указатель на последний элемент списка. Он же last
     */
    private Node<T> tail;

    private int size = 0;

    public T getFirst() {
        final Node<T> curHead = head;
        if (curHead == null)
            throw new NoSuchElementException();
        return head.data;
    }

    public void linkLast(Task task) {
        // Реализуйте метод
        final Node newnode = new Node(task,tail,null);

    }

    public T getTasks(T element){
        return element;
    }

    public int size() {
        return this.size;
    }

    public void remove(T element){

    }
    public void removeNode(Node){

    }

}