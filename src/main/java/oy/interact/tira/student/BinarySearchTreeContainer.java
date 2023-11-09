package oy.interact.tira.student;

import oy.interact.tira.util.Pair;
import oy.interact.tira.util.TIRAKeyedOrderedContainer;
import oy.interact.tira.util.Visitor;

import java.util.Comparator;
import java.util.function.Predicate;

public class BinarySearchTreeContainer<K extends Comparable<K>, V> implements TIRAKeyedOrderedContainer<K, V> {

    //TreeNode root; // Root node of the tree, your private little helper class.
    private Node root;
    private int size; // Number of elements currently in the tree.
    private int maxDepth = 0; // Maximum depth (height) of the tree
    private int currentIndex = 0; // Index of the current node
    private Comparator<K> comparator; // The comparator used to determine if new node will go to left or right subtree.

    // Constructor
    public BinarySearchTreeContainer(Comparator<K> comparator) {
        this.root = null;
        this.comparator = comparator;
    }

    private class Node {
        private K key;
        private V value;
        private Node leftChild;
        private Node rightChild;
        private int childCount;

        private Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.leftChild = null;
            this.rightChild = null;
            this.childCount = 0;
        }

        // Utilizes comparator for comparing objects, returns depth of the added node in tree
        private int add(Node node, int depth) {
            if (this.value.equals(node.value)) {
                // If added value is identical in BST, update the node and remove duplicate values
                this.key = node.key;
                this.value = node.value;
                size--;
                return depth;
            } else if (comparator.compare(node.key, this.key) < 0) {
                if (this.leftChild == null) {
                    this.childCount++;
                    this.leftChild = node;
                    return depth + 1;
                } else {
                    this.childCount++;
                    return this.leftChild.add(node, depth + 1);
                }
            } else if (comparator.compare(node.key, this.key) > 0) {
                if (this.rightChild == null) {
                    this.childCount++;
                    this.rightChild = node;
                    return depth + 1;
                } else {
                    this.childCount++;
                    return this.rightChild.add(node, depth + 1);
                }
            }
            return depth;
        }

        private Node bstSearch(Node node, K key) {
            if (node == null || comparator.compare(node.key, key) == 0) {
                return node;
            }
            if (comparator.compare(key, node.key) < 0) {
                return bstSearch(node.leftChild, key);
            }
            return bstSearch(node.rightChild, key);
        }
    }

    @Override
    public void add(K key, V value) throws OutOfMemoryError, IllegalArgumentException {
        // Check if one or both of the parameters are null
        if (key == null || value == null) {
            throw new IllegalArgumentException("Key or value cannot be null");
        }
        Node node = new Node(key, value);

        if (root == null) {
            root = node;
        } else {
            int nodeDepth = root.add(node, 0);
            if (nodeDepth > this.maxDepth) {
                maxDepth = nodeDepth;
            }
        }
        this.size++;
    }

    @Override
    public V get(K key) throws IllegalArgumentException {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        Node result = root.bstSearch(root, key);

        if (result != null) {
            return result.value;
        } else {
            return null; // Key not found
        }
    }

    @Override
    public V remove(K key) throws IllegalArgumentException {
        return null;
    }

    @Override
    public V find(Predicate<V> searcher) {
        return findValueInOrder(root, searcher);
    }

    // Helper method for in-order traversal searching a value conforming to the predicate
    private V findValueInOrder(Node node, Predicate<V> searcher) {
        if (node == null) {
            return null; // Value not found
        }

        // Traverse the left subtree
        V leftResult = findValueInOrder(node.leftChild, searcher);

        // Check if the current node's value matches with predicate
        if (searcher.test(node.value)) {
            return node.value; // Value found
        }

        if (leftResult != null) {
            return leftResult;
        }

        // Traverse the right subtree
        return findValueInOrder(node.rightChild, searcher);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int capacity() {
        return 0;
    }

    @Override
    public void ensureCapacity(int capacity) throws OutOfMemoryError, IllegalArgumentException {

    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Pair<K, V>[] toArray() {
        Pair<K, V>[] resultArray = (Pair<K, V>[]) new Pair[size];
        int[] index = {0};
        toArrayInOrder(root, resultArray, index);
        return resultArray;
    }

    // Helper method for in-order traversal and filling the array
    private void toArrayInOrder(Node node, Pair<K, V>[] array, int[] index) {
        if (node == null) {
            return;
        }
        toArrayInOrder(node.leftChild, array, index); // Traverse the left subtree
        array[index[0]++] = new Pair<>(node.key, node.value);
        toArrayInOrder(node.rightChild, array, index); // Traverse the right subtree
    }

    @Override
    public int indexOf(K itemKey) {
        currentIndex = 0;
        return indexOfInOrder(root, itemKey);
    }

    // Helper method for finding the index of node's key
    private int indexOfInOrder(Node node, K itemKey) {
        if (node == null) {
            return -1; // Key not found
        }

        // Traverse the left subtree
        int leftIndex = indexOfInOrder(node.leftChild, itemKey);

        if (leftIndex != -1) {
            return leftIndex; // Key found in the left subtree
        }

        // If the current node's key matches the target key, return the currentIndex
        if (node.key.equals(itemKey)) {
            return currentIndex;
        }

        currentIndex++;

        // Traverse the right subtree
        return indexOfInOrder(node.rightChild, itemKey);
    }

    @Override
    public Pair<K, V> getIndex(int index) throws IndexOutOfBoundsException {
        if (index < 0) {
            throw new IllegalArgumentException("Index is out of bounds");
        }
        currentIndex = 0;
        return getKeyValueAtIndex(root, index);
    }

    // Helper method for finding key-value pair at the index
    private Pair<K, V> getKeyValueAtIndex(Node node, int index) {
        if (node == null) {
            return null;
        }

        // Traverse the left subtree
        Pair<K, V> keyValuePair = getKeyValueAtIndex(node.leftChild, index);

        if (keyValuePair != null) {
            return keyValuePair;
        }

        if (currentIndex == index) {
            return new Pair<>(node.key, node.value);
        }

        currentIndex++;

        // Traverse the right subtree
        return getKeyValueAtIndex(node.rightChild, index);
    }

    @Override
    public int findIndex(Predicate<V> searcher) {
        currentIndex = 0;
        return findIndexInOrder(root, searcher);
    }

    // Helper method for finding index
    private int findIndexInOrder(Node node, Predicate<V> searcher) {
        if (node == null) {
            return -1; // Index not found
        }

        // Traverse the left subtree
        int leftIndex = findIndexInOrder(node.leftChild, searcher);

        if (leftIndex != -1) {
            return leftIndex; // Key found in the left subtree
        }

        // Check if the current node's index matches with predicate
        if (searcher.test(node.value)) {
            return currentIndex; // Index for the value found
        }

        currentIndex++;

        // Traverse the right subtree
        return findIndexInOrder(node.rightChild, searcher);
    }

    @Override
    public void accept(Visitor<K, V> visitor) throws Exception {

    }
}
