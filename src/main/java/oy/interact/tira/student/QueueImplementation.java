package oy.interact.tira.student;

import oy.interact.tira.util.QueueInterface;

public class QueueImplementation<E> implements QueueInterface<E> {

    private static final int DEFAULT_QUEUE_SIZE = 10;
    private Object[] itemArray;
    private int head;
    private int tail;
    private int count;
    private int capacity;

    // Create queue with the default size
    public QueueImplementation() {
        // Call the constructor with size parameter with default size
        this(DEFAULT_QUEUE_SIZE);
    }

    // Create queue with the size of parameter capacity
    public QueueImplementation(int capacity) {
        this.capacity = capacity;
        this.itemArray = new Object[capacity];
        this.head = 0;
        this.tail = 0;
        this.count = 0;
    }

    private void reallocateArray() {
        int newCapacity = capacity * 2;
        Object[] newArray;
        try {
            newArray = new Object[newCapacity];
        } catch (Exception e) {
            throw new OutOfMemoryError("Reallocation for queue array failed.");
        }
        int index = head;
        int counter = count;
        int newItemArrayIndex = 0;
        while (counter > 0) {
            newArray[newItemArrayIndex++] = itemArray[index++];
            counter--;
            if (index >= capacity) {
                index = 0;
            }
        }
        head = 0;
        tail = count;
        capacity = newCapacity;
        itemArray = newArray;
    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public void enqueue(E element) throws OutOfMemoryError, NullPointerException {
        if (element == null) {
            throw new NullPointerException("An element added must not be null.");
        }
        if (count >= capacity) {
            reallocateArray();
        }
        if (tail >= capacity) {
            tail = 0;
        }
        itemArray[tail++] = element;
        count++;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E dequeue() throws IllegalStateException {
        if (count == 0) {
            throw new IllegalStateException("Cannot dequeue from empty queue");
        }
        E element = (E) itemArray[head];
        itemArray[head] = null;
        count--;
        head++;
        if (head >= capacity) {
            head = 0;
        }
        return element;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E element() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty.");
        }
        return (E) itemArray[head];
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public void clear() {
        capacity = DEFAULT_QUEUE_SIZE;
        head = 0;
        tail = 0;
        count = 0;
        itemArray = new Object[capacity];
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        if (count > 0) {
            int index = head;
            int counter = count;
            while (counter > 0) {
                builder.append(itemArray[index]);
                counter--;
                if (counter > 0) {
                    builder.append(", ");
                }
                if (index >= capacity) {
                    index = 0;
                }
                index++;
            }
        }
        builder.append("]");
        return builder.toString();
    }
}
