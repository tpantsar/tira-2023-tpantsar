package oy.interact.tira.student;

import oy.interact.tira.factories.QueueFactory;
import oy.interact.tira.model.Coder;
import oy.interact.tira.util.Pair;
import oy.interact.tira.util.QueueInterface;
import oy.interact.tira.util.TIRAKeyedContainer;

import java.util.function.Predicate;

public class HashTableContainer<K extends Comparable<K>, V> implements TIRAKeyedContainer<K, V> {

    private Pair<K, V>[] itemArray;
    private static final int DEFAULT_ARRAY_SIZE = 1000; // Default array size
    private int size = 0; // The amount of elements in hash table array

    private int collisions = 0;

    // Constructor for hash table
    public HashTableContainer() {
        this.itemArray = (Pair<K, V>[]) new Pair[DEFAULT_ARRAY_SIZE];
    }

    private int hashFunction(K key) {
        int hash = 31;
        int keyLength = key.toString().length();

        for (int i = 0; i < keyLength; i++) {
            int charAsInt = key.toString().charAt(i);
            hash = (hash * 31 + charAsInt);
        }

        return (hash & 0x7FFFFFFF) % itemArray.length;
    }

    @Override
    public void add(K key, V value) throws OutOfMemoryError, IllegalArgumentException {
        // Check if one or both of the parameters are null
        if (key == null || value == null) {
            throw new IllegalArgumentException("Key or value cannot be null");
        }

        try {
            int hashIndex = hashFunction(key);

            if (itemArray[hashIndex] != null && itemArray[hashIndex].getKey().equals(key)) { // Duplicate keys found
                itemArray[hashIndex] = new Pair<>(key, value);
            } else if (itemArray[hashIndex] == null) { // Empty index found
                itemArray[hashIndex] = new Pair<>(key, value);
                size++; // Increase container size by one, after adding new element
            } else { // Collision

                LinkedListQueue<Object> linkedList = new LinkedListQueue<>();
                QueueInterface<Coder> linkedListCoder = QueueFactory.createCoderQueue();
                //LinkedListQueue<Pair<K, V>>[] linkedListPair = new LinkedListQueue<>();


                linkedList.enqueue(itemArray[hashIndex]); // Current element in the index
                linkedList.enqueue(new Pair<>(key, value));
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
        int hashIndex = hashFunction(key);
        if (itemArray[hashIndex] != null && itemArray[hashIndex].getKey().equals(key)) {
            return itemArray[hashIndex].getValue();
        }
        return null; // Key not found
    }

    @Override
    public V remove(K key) throws IllegalArgumentException {
        V value = null;
        int hashIndex = hashFunction(key);

        if (itemArray[hashIndex].getKey().equals(key)) {
            value = itemArray[hashIndex].getValue();
            itemArray[hashIndex] = new Pair<>(null, null);
            size--; // Decrease container size by one, after removing the element
        }
        return value;
    }

    @Override
    public V find(Predicate<V> searcher) {
        for (Pair<K, V> keyValuePair : itemArray) {
            if (null != keyValuePair && searcher.test(keyValuePair.getValue())) {
                return keyValuePair.getValue();
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
        itemArray = (Pair<K, V>[]) new Pair[DEFAULT_ARRAY_SIZE];
    }

    @Override
    @SuppressWarnings("unchecked")
    public Pair<K, V>[] toArray() throws Exception {
        Pair<K, V>[] resultArray = (Pair<K, V>[]) new Pair[size];
        int resultArrayIndex = 0; // Index for temporary array

        for (int i = 0; i < itemArray.length; i++) {
            if (itemArray[i] != null) {
                resultArray[resultArrayIndex++] = new Pair<>(itemArray[i].getKey(), itemArray[i].getValue());
            }
        }
        return resultArray;
    }
}