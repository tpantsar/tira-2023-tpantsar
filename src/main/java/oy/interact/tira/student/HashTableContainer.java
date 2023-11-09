package oy.interact.tira.student;

import oy.interact.tira.model.Coder;
import oy.interact.tira.util.Pair;
import oy.interact.tira.util.TIRAKeyedContainer;

import java.util.function.Predicate;

public class HashTableContainer<K extends Comparable<K>, V> implements TIRAKeyedContainer<K, V> {

    private Node<K, V>[] itemArray; // An array of linked lists
    private static final int DEFAULT_ARRAY_SIZE = 2000000; // Default array size
    private int size = 0; // The amount of elements in hash table array
    public int collisions = 0;

    private static class Node<K extends Comparable<K>, V> {
        Pair<K, V> data;
        Node<K, V> next;

        public Node(Pair<K, V> data) {
            this.data = data;
            this.next = null;
        }
    }

    // Constructor for hash table
    @SuppressWarnings("unchecked")
    public HashTableContainer() {
        this.itemArray = new Node[DEFAULT_ARRAY_SIZE];
    }

    /* Helper method to calculate hash index in range of 0 <= hashIndex < array.length
     * Parameter key is used to access the Coder attributes, such as id
     * Uses Coder.hashCode() to return hash value to this method.
     */
    private int hashFunction(K key) {
        int hashCode = key.hashCode();
        return (hashCode & 0x7FFFFFFF) % itemArray.length;
    }

    @Override
    public void add(K key, V value) throws OutOfMemoryError, IllegalArgumentException {
        // Check if one or both of the parameters are null
        if (key == null || value == null) {
            throw new IllegalArgumentException("Key or value cannot be null");
        }

        try {
            // Calculates the final hash index limited to table size
            int hashIndex = hashFunction(key);

            if (itemArray[hashIndex] != null && itemArray[hashIndex].data.getKey().equals(key)) {
                // Duplicate keys found
                itemArray[hashIndex] = new Node<>(new Pair<>(key, value));
            } else if (itemArray[hashIndex] == null) {
                // Empty index found
                itemArray[hashIndex] = new Node<>(new Pair<>(key, value));
                size++; // Increase container size by one, after adding new element
            } else {
                // Collision, chain elements using linked lists
                Node<K, V> current = itemArray[hashIndex];
                while (current.next != null) {
                    current = current.next; // Iterate to the last link
                }
                current.next = new Node<>(new Pair<>(key, value));
                collisions++;
                size++; // Increase container size by one, after adding new element
            }
        } catch (OutOfMemoryError e) {
            System.out.println("\n*** ERROR: Run out of memory while adding key-value pairs to container\n");
            e.printStackTrace();
        }
    }

    @Override
    public V get(K key) throws IllegalArgumentException {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }

        // Calculates the final hash index limited to table size
        int hashIndex = hashFunction(key);
        Node<K, V> current = itemArray[hashIndex];

        while (current != null) {
            if (current.data.getKey().equals(key)) {
                return current.data.getValue();
            }
            current = current.next;
        }
        return null; // Key not found
    }

    @Override
    public V remove(K key) throws IllegalArgumentException {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }

        // Calculates the final hash index limited to table size
        int hashIndex = hashFunction(key);

        V value = null;
        Node<K, V> current = itemArray[hashIndex];
        Node<K, V> previous = null;

        while (current != null) {
            if (current.data.getKey().equals(key)) {
                value = current.data.getValue();
                if (previous == null) {
                    itemArray[hashIndex] = current.next;
                } else {
                    previous.next = current.next;
                }
                size--;
                break;
            }
            previous = current;
            current = current.next;
        }
        return value;
    }

    @Override
    public V find(Predicate<V> searcher) {
        for (Node<K, V> keyValuePair : itemArray) {
            Node<K, V> current = keyValuePair;
            while (current != null) {
                if (searcher.test(current.data.getValue())) {
                    return current.data.getValue();
                }
                current = current.next;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int capacity() {
        return itemArray.length;
    }

    @Override
    public void ensureCapacity(int capacity) throws OutOfMemoryError, IllegalArgumentException {

    }

    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        size = 0;
        itemArray = new Node[DEFAULT_ARRAY_SIZE];
    }

    @Override
    @SuppressWarnings("unchecked")
    public Pair<K, V>[] toArray() throws Exception {
        Pair<K, V>[] resultArray = (Pair<K, V>[]) new Pair[size];
        int resultArrayIndex = 0; // Index for temporary array

        for (Node<K, V> keyValueNode : itemArray) {
            Node<K, V> current = keyValueNode;
            while (current != null) {
                resultArray[resultArrayIndex++] = new Pair<>(current.data.getKey(), current.data.getValue());
                current = current.next;
            }
        }
        return resultArray;
    }
}