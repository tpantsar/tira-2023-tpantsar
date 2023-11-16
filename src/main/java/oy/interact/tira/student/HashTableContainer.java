package oy.interact.tira.student;

import oy.interact.tira.util.Pair;
import oy.interact.tira.util.TIRAKeyedContainer;

import java.util.function.Predicate;

public class HashTableContainer<K extends Comparable<K>, V> implements TIRAKeyedContainer<K, V> {

    private Node<K, V>[] itemArray; // An array of linked lists
    private static int TABLE_SIZE = 40; // Default array size
    private int size; // Amount of elements in hash table array
    public int collisions; // Amount of chaining operations in hash table (linked lists)
    private final float fillRate = 0.65f; // Maximum portion of the table allowed to fill

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
        this.itemArray = new Node[TABLE_SIZE];
        this.size = 0;
        this.collisions = 0;
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
            // Check the fill rate of the table
            ensureCapacity(itemArray.length);

            // Calculates the final hash index limited to table size
            int hashIndex = hashFunction(key);

            if (itemArray[hashIndex] != null && itemArray[hashIndex].data.getKey().equals(key)) {
                // Duplicate keys found in first node, replace current key-value pair with new key-value pair
                itemArray[hashIndex].data = new Pair<>(key, value);
            } else if (itemArray[hashIndex] == null) {
                // Empty index found
                itemArray[hashIndex] = new Node<>(new Pair<>(key, value));
                size++; // Increase container size by one, after adding new node
            } else {
                // Collision, chain elements using linked lists or replace key-value pair if needed
                Node<K, V> current = itemArray[hashIndex];
                boolean keyFoundFromLinkedList = false;

                // Traverse the linked list and search for possible identical key
                while (current.next != null) {
                    // If the key is found from linked list, replace it with new key-value pair
                    // Hash table size does not increase in this case
                    if (current.next.data.getKey().equals(key)) {
                        keyFoundFromLinkedList = true;
                        current.next.data = new Pair<>(key, value);
                        break;
                    } else {
                        current = current.next;
                    }
                }

                // If the key with same hash index is not found from linked list, add new node to the end
                if (!keyFoundFromLinkedList) {
                    current.next = new Node<>(new Pair<>(key, value));
                    collisions++; // Increase collisions by one since new node is added
                    size++; // Increase container size by one, after adding new node
                }
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
        if (((float) size / capacity) > fillRate) {
            // Multiply hash table capacity according to fill rate
            reallocate((float) capacity * (1f + fillRate));
        }
    }

    private void reallocate(float newCapacity) {
        try {
            Pair<K, V>[] oldArray = toArray(); // Copy elements from original array
            TABLE_SIZE = (int) newCapacity;
            clear(); // Reset original hash table array

            // In reallocation, indexes need to be recalculated & elements put to new places
            for (int i = 0; i < oldArray.length; i++) {
                Pair<K, V> keyValuePair = oldArray[i];
                add(keyValuePair.getKey(), keyValuePair.getValue());
            }
        } catch (Exception e) {
            System.out.println("**ERROR** Could not reallocate hash table array.");
            e.printStackTrace();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        size = 0;
        collisions = 0;
        itemArray = new Node[TABLE_SIZE];
    }

    @Override
    @SuppressWarnings("unchecked")
    public Pair<K, V>[] toArray() throws Exception {
        Pair<K, V>[] resultArray = (Pair<K, V>[]) new Pair[size];
        int resultArrayIndex = 0; // Index for temporary array

        for (int i = 0; i < itemArray.length; i++) {
            Node<K, V> current = itemArray[i];
            while (current != null) {
                resultArray[resultArrayIndex++] = new Pair<>(current.data.getKey(), current.data.getValue());
                current = current.next;
            }
        }
        return resultArray;
    }
}