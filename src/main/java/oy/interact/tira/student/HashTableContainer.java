package oy.interact.tira.student;

import oy.interact.tira.util.Pair;
import oy.interact.tira.util.TIRAKeyedContainer;

import java.util.function.Predicate;

public class HashTableContainer<K extends Comparable<K>,V> implements TIRAKeyedContainer<K,V> {

    private Object [] itemArray;
    private int size;

    // Constructor for hash table
    public HashTableContainer(int size) {
        this.size = size;
        this.itemArray = new Object[size];
    }

    private int hashFunction(Pair<K, V> element, int index) {

        // Hash the string one character (as integer) at a time
        // Chars have numerical code in different char sets
        // NB: that number below is not yet usable as an index to table

        /*
        hash(string)
            hash = 31;
            for char in string
                hash = (hash * 31 + charAsInt(char))
            return hash
        */

        // hash(2761) = 2761 % 1009 = 743

        // probeFunc(k,i) = (hashFunc(k) + i) mod m)
        // hash(2761,0) = (2761 + 0) % 10000 = 2761
        // let’s play: collisions happen and i got very large for k
        // hash(2761, 9876) = (2761 + 9876) % 10000 = 2637

        int modulus = size / 10; // Convert to prime number

        K elementKey = element.getKey();

        for (int i = 0; i < elementKey.toString().length(); i++) {
            char character = elementKey.toString().charAt(i);

        }

        //int hashIndex = element.getKey()

        return 0;
    }

    @Override
    public void add(K key, V value) throws OutOfMemoryError, IllegalArgumentException {
        /*
        i = 0
        while i < A.length - 1
            hashIndex = hashFunc(e, i)
            if A[hashIndex] = null
                A[hashIndex] = e
                return hashIndex
            i += 1
        return OVERFLOW
        */

        // Check if one or both of the parameters are null
        if (key == null || value == null) {
            throw new IllegalArgumentException("Key or value cannot be null");
        }

        try {
            int i = 0;
            while (i < itemArray.length - 1) {
                Pair<K, V> element = new Pair<>(key, value);
                int hashIndex = hashFunction(element, i);
            }
        } catch (OutOfMemoryError e) {
            System.out.println("\n*** ERROR: Run out of memory while adding key-value pairs to container\n");
            e.printStackTrace();
        }
    }

    @Override
    public V get(K key) throws IllegalArgumentException {
        return null;
    }

    @Override
    public V remove(K key) throws IllegalArgumentException {
        return null;
    }

    @Override
    public V find(Predicate<V> searcher) {
        /*
        i = 0
        hashIndex = hashFunc(key, i)
        while i < A.length - 1 & A[hashIndex] = null
            if A[hashIndex] = key
                return A[hashIndex]
            i += 1
            if i < A.length - 1
                hashIndex = hashFunc(key, i)
        return null
        */



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
    public void clear() {
        size = 0;
        itemArray = new Object[size];
    }

    @Override
    public Pair<K, V>[] toArray() throws Exception {
        return new Pair[0];
    }
}