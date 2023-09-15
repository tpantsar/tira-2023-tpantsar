package oy.interact.tira.student;

import oy.interact.tira.util.QueueInterface;

public class LinkedListQueue<E> implements QueueInterface<E> {

    private class Node<T> {
        T element; // The element inside node
        Node<T> next; // Next element in node

        public Node(T data) {
            this.element = data;
        }
    }

    private Node<E> head; // End of the queue
    private Node<E> tail; // Start of the queue
    private int size;
    private int capacity;

    // Default constructor for linked list
    public LinkedListQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // Linked list does not care about capacity parameter
    public LinkedListQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalStateException("Capacity must be greater than 0.");
        }

        this.head = null;
        this.tail = null;
        this.size = 0;
        this.capacity = capacity;
    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public void enqueue(E element) throws OutOfMemoryError, NullPointerException {
        if (element == null) {
            throw new NullPointerException("Element added cannot be null.");
        }
        try {
            Node<E> current = new Node<>(element);

            if (isEmpty()) {
                head = current;
            } else {
                tail.next = current;
            }
            tail = current;
            size++;
        } catch (Exception e) {
            throw new OutOfMemoryError("Failed to allocate a new list element.");
        }
    }

    @Override
    public E dequeue() throws IllegalStateException {
        // LinkedList: Returns the element contained in "head" node and removes it from queue
        if (isEmpty()) {
            throw new IllegalStateException("Cannot remove element from empty queue.");
        }

        E removed = head.element;
        head = head.next; // Next node in head is the new head
        size--;

        if (isEmpty()) {
            tail = null;
        }

        return removed;
    }

    @Override
    public E element() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty.");
        }
        return head.element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        Node<E> current = head;
        while (null != current) {
            builder.append(current.element.toString());
            current = current.next;
            if (null != current) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }
}
