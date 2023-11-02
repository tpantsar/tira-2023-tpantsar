package oy.interact.tira.student;

import oy.interact.tira.util.Pair;
import oy.interact.tira.util.QueueInterface;

public class LinkedListQueue<E> implements QueueInterface<E> {

    private static class Node<T> {
        T data; // The element (object) inside node
        Node<T> next; // Next element in node

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private static class HashTableNode<K extends Comparable<K>, V> {
        Pair<K, V> data;
        HashTableNode<K, V> next;

        public HashTableNode(Pair<K, V> data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<E> head; // End of the queue
    private Node<E> tail; // Start of the queue
    private int size;

    // Default constructor for linked list
    public LinkedListQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public int capacity() {
        return Integer.MAX_VALUE;
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

        E removed = head.data;
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
        return head.data;
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
            builder.append(current.data.toString());
            current = current.next;
            if (null != current) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }
}
