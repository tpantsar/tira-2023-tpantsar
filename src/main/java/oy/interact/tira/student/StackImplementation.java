package oy.interact.tira.student;

import oy.interact.tira.util.StackInterface;

public class StackImplementation<E> implements StackInterface<E> {

    private static final int DEFAULT_STACK_SIZE = 10;
    private static final int MINIMUM_STACK_SIZE = 2;
    private Object [] itemArray;
    private int capacity;
    private int currentIndex;

    // Create stack with the default size
    public StackImplementation() throws OutOfMemoryError {
        this(DEFAULT_STACK_SIZE);
    }

    // Create stack with the size of parameter capacity
    public StackImplementation(int capacity) throws OutOfMemoryError, IllegalStateException {
        if (capacity < MINIMUM_STACK_SIZE) {
            throw new IllegalStateException("Stack size is less than " + MINIMUM_STACK_SIZE);
        }
        try {
            this.itemArray = new Object[capacity];
            this.capacity = capacity;
            this.currentIndex = -1;
        } catch (Exception e) {
            throw new OutOfMemoryError("Error allocating the array: " + e.getMessage());
        }
    }

    private void reallocateArray() {
        try {
            int newCapacity = capacity * 2;
            Object[] newArray = new Object[newCapacity];
            for (int i = 0; i <= currentIndex; i++) {
                newArray[i] = itemArray[i];
            }
            this.itemArray = newArray;
            this.capacity = newCapacity;
        } catch (Exception e) {
            throw new OutOfMemoryError("No room for larger array: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (int i = 0; i <= currentIndex; i++) {
            builder.append(itemArray[i]);
            if (i < currentIndex) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }

    @Override
    public int capacity() {
        return this.capacity;
    }

    @Override
    public void push(E element) throws OutOfMemoryError, NullPointerException {
        if (element == null) {
            throw new NullPointerException("Element cannot be null");
        }
        if (currentIndex >= capacity - 1) {
            reallocateArray();
        }
        currentIndex++;
        itemArray[currentIndex] = element;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E pop() throws IllegalStateException {
        if (currentIndex < 0) {
            throw new IllegalStateException("Cannot pop from empty stack.");
        }
        E element = (E) itemArray[currentIndex];
        itemArray[currentIndex] = null;
        currentIndex--;
        return element;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E peek() throws IllegalStateException {
        if (currentIndex < 0) {
            throw new IllegalStateException("Cannot pop from empty stack.");
        }
        return (E) itemArray[currentIndex];
    }

    @Override
    public int size() {
        return currentIndex + 1;
    }

    @Override
    public boolean isEmpty() {
        return currentIndex < 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < itemArray.length; i++) {
            itemArray[i] = null;
        }
        currentIndex = -1;
    }
}
