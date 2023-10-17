package oy.interact.tira.student;

import oy.interact.tira.util.Pair;
import oy.interact.tira.util.TIRAKeyedOrderedContainer;
import oy.interact.tira.util.Visitor;

import java.util.Comparator;
import java.util.function.Predicate;

public class BinarySearchTreeContainer<K extends Comparable<K>, V> implements TIRAKeyedOrderedContainer<K, V> {

    //TreeNode<K, V> root; // Root node of the tree, your private little helper class.
    private Node root = null;
    private int size; // Number of elements currently in the tree.
    private Comparator<K> comparator;  // The comparator used to determine if new node will go to left or right subtree.

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

        private Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.leftChild = null;
            this.rightChild = null;
        }

        // Utilize Comparable.compareTo method for comparing objects
        private void add(Node node) {
            if (node.key.compareTo(this.key) < 0) {
                if (this.leftChild == null) {
                    this.leftChild = node;
                } else {
                    this.leftChild.add(node);
                }
            } else if (node.key.compareTo(this.key) > 0) {
                if (this.rightChild == null) {
                    this.rightChild = node;
                } else {
                    this.rightChild.add(node);
                }
            }
        }

        private Node bstSearch(Node node, K key) {
            if (node == null || node.key.compareTo(key) == 0) {
                return node;
            }
            if (key.compareTo(node.key) < 0) {
                return bstSearch(node.leftChild, key);
            }
            return bstSearch(node.rightChild, key);
        }
    }

    @Override
    public void add(K key, V value) throws OutOfMemoryError, IllegalArgumentException {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Key or value cannot be null");
        }
        Node node = new Node(key, value);

        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
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
            return null;
        }
    }

    @Override
    public V remove(K key) throws IllegalArgumentException {
        return null;
    }

    @Override
    // Haetaan predikaatin mukaisen hakukriteerin täyttävä arvo puusta.
    public V find(Predicate<V> searcher) {
        return findInOrder(root, searcher);
    }

    private V findInOrder(Node node, Predicate<V> searcher) {
        if (node == null) {
            return null; // Value not found
        }

        // Traverse the left subtree
        V leftResult = findInOrder(node.leftChild, searcher);

        // Check if the current node's value satisfies the predicate
        if (searcher.test(node.value)) {
            return node.value; // Value found
        }

        // If not found in the current node, return the result from the left subtree (if any)
        if (leftResult != null) {
            return leftResult;
        }

        // Traverse the right subtree
        return findInOrder(node.rightChild, searcher);
    }

    @Override
    public int size() {
        size = calculateTreeSize(root);
        return size;
    }

    private int calculateTreeSize(Node node) {
        if (node == null) {
            return 0; // An empty tree has size 0
        }

        return calculateTreeSize(node.leftChild) + calculateTreeSize(node.rightChild) + 1;
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
        inOrderTraversal(root, resultArray, index);
        return resultArray;
    }

    // Helper method for in-order traversal and array population
    private void inOrderTraversal(Node node, Pair<K, V>[] array, int[] index) {
        if (node == null) {
            return;
        }
        inOrderTraversal(node.leftChild, array, index); // Left subtree
        array[index[0]++] = new Pair<>(node.key, node.value);
        inOrderTraversal(node.rightChild, array, index); // Right subtree
    }

    @Override
    public int indexOf(K itemKey) {
        int[] index = {0}; // Use an array to store the index, as it needs to be mutable
        indexOfInOrder(root, itemKey, index);
        return index[0];
    }

    // Helper method for in-order traversal
    private void indexOfInOrder(Node node, K itemKey, int[] index) {
        if (node == null) {
            return;
        }

        // Traverse the left subtree
        indexOfInOrder(node.leftChild, itemKey, index);

        // Compare the current node's key with the target key
        if (node.key.compareTo(itemKey) == 0) {
            // Found a node with the same key
            index[0]++;
            return; // Stop the traversal
        } else if (node.key.compareTo(itemKey) < 0) {
            // The current node's key is smaller, so increment the index
            index[0]++;
        }

        // Traverse the right subtree
        indexOfInOrder(node.rightChild, itemKey, index);
    }

    @Override
    // Haetaan tietyssä indeksissä oleva avain-arvo -pari
    public Pair<K, V> getIndex(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }

        //Pair<K, V>[] result = new Pair[1];

        //inOrderTraversalForIndex(root, index, 0, result);
        Pair<K, V> result = inOrderTraversalForIndex(root, index, 0);

        return result;

        //return result[0];
        //return result[0];
    }

    // Helper method for in-order traversal
    private Pair<K, V> inOrderTraversalForIndex(Node node, int targetIndex, int currentIndex) {
        if (node == null) {
            return null; // Value not found
        }

        // Traverse the left subtree
        Pair<K, V> leftResult = inOrderTraversalForIndex(node.leftChild, targetIndex, currentIndex + 1);

        // Check if the current node's index matches the target index
        if (currentIndex == targetIndex) {
            return new Pair<>(node.key, node.value); // Return the value and stop the traversal
        }

        currentIndex++;

        if (leftResult != null) {
            return leftResult; // Value found in the left subtree, return it
        }

        // Traverse the right subtree
        return inOrderTraversalForIndex(node.rightChild, targetIndex, currentIndex + 1);
    }

    @Override
    // Haetaan predikaatin mukaisen hakukriteerin täyttävä arvon indeksi eli järjestysnumero puusta.
    public int findIndex(Predicate<V> searcher) {
        Pair<K, V>[] array = toArray();

        for (int i = 0; i < array.length; i++) {
            if (searcher.test(array[i].getValue())) {
                return i;
            }
        }
        return -1; // Index not found
    }

    // Helper method for in-order traversal (Predicate)
    private void findIndexInOrder(Node node, Predicate<V> searcher, int[] index) {
        if (node == null) {
            return;
        }

        // Traverse the left subtree
        findIndexInOrder(node.leftChild, searcher, index);

        // Test searcher with the node value
        if (searcher.test(node.value)) {
            if (index[0] == -1) {
                index[0] = 0;
            } else {
                index[0]++; // Increment the index
            }
            return; // Stop the traversal
        }

        index[0]++;

        // Traverse the right subtree
        findIndexInOrder(node.rightChild, searcher, index);
    }

    @Override
    public void accept(Visitor<K, V> visitor) throws Exception {

    }
}

