package oy.interact.tira.util;

import java.util.Comparator;
import java.util.function.Predicate;

/**
 * An interface for simple generic container class.
 * 
 * The container elements must be unique. There must not be
 * elements in the container that equal each other so that 
 * element.equals(another) returns true.
 * 
 * The elements must implement the Comparable interface.
 * Note that in this project, the equals and Comparable differ
 * from the recommended. That is, (x.compareTo(y)==0) == (x.equals(y))
 * does _not_ hold. See also the `Coder` class which is used as E in this project.
 */
public interface TIRAContainer<E extends Comparable<E>> {
	/**
	 * Adds an element to the container. Must check that parameter is not null.
	 * Also must check there is no equal element in the container already.
	 * 
	 * @param element To add
	 * @throws OutOfMemoryError If there is not enough memory to add the element.
	 * @throws IllegalArgumentException If the argument is null.
	 */
	void add(E element) throws OutOfMemoryError, IllegalArgumentException;

	/**
	 * Returns the element found in the container that equals the parameter.
	 * @param element To search, matching one in container using .equals method.
	 * @return Returns the element found or null.
	 * @throws IllegalArgumentException If the parameter is null.
	 */
	E get(E element) throws IllegalArgumentException;
	
	/**
	 * Returns the element at the specified index.
	 * @param index Index to access.
	 * @return The element found in the index.
	 * @throws IndexOutOfBoundsException If the index is out of bounds.
	 */
	E get(int index) throws IndexOutOfBoundsException;
	
	/**
	 * Removes the element from the container, if found.
	 * @param element To remove. Finds matching element from container using .equals.
	 * @return Returns the element removed from the container
	 * @throws IllegalArgumentException If parameter is null.
	 */
	E remove(E element) throws IllegalArgumentException;
	
	/**
	 * Searchers for an element using the predicate and returns the index of the
	 * element corresponding to the predicate, or -1 if not found.
	 * @param searcher The predicate having the thing to search.
	 * @return Returns the index of the first element fulfilling the predicate, or -1.
	 */
	 int findIndex(Predicate<E> searcher);

	/**
	 * Searchers for an element using the predicate and returns the
	 * element corresponding to the predicate, or null if not found.
	 * @param searcher The predicate having the thing to search.
	 * @return Returns the first element fulfilling the predicate, or null.
	 */
	E find(Predicate<E> searcher);

	/**
	 * Searches for the element from the container using the provided key and comparator.
	 * Returns the index of the element matching the comparator, or -1.
	 * 
	 * If the container is sorted, can do a faster search (e.g. binary search) using the
	 * comparator. Falls back to linear search if the container is not sorted. 
	 * 
	 * See also `sort(Comparator<E>)`.
	 * 
	 * @param key The key to search for.
	 * @param usingComparator The comparator to use in search.
	 * @return Returns the index of the element found or -1.
	 */
	int indexOf(E key, Comparator<E> usingComparator);

	/**
	 * Returns the size of the comparator. Size means the number of elements in the container.
	 * 
	 * @return The number of elements in the container.
	 */
	int size();

	/**
	 * Can be used to query the capacity of the container. Capacity means the number of
	 * elements that can be held in the container without reallocating a larger internal
	 * storage.
	 * 
	 * @return The number of elements the container could currently hold.
	 */
	int capacity();

	/**
	 * Returns an array of elements in the container. If the container is empty, must
	 * return an empty array of length 0.
	 * 
	 * If the container is implemented using an array, must _not_ return that array,
	 * but a create a new array, place the elements of the container in that array 
	 * and then return this new array.
	 * @return The array having the elements in the container, or an empty array.
	 */
	E [] toArray();


	/**
	 * Reverses the contents of the container in opposite order.
	 */
	void reverse();

	/**
	 * Tells if the container is sorted or not.
	 */
	boolean isSorted();

	/**
	 * Sorts the container to natural order.
	 * 
	 */
	void sort();

	/**
	 * Sorts the array using the provided comparator.
	 * 
	 * @param usingComparator The comparator to use.
	 */
	void sort(Comparator<E> usingComparator);

	/**
	 * Ensures that the container can hold at least the specified number of
	 * elements without reallocating the internal data structure.
	 * 
	 * If the container already has elements, they must be preserved.
	 * 
	 * @param capacity The new capacity of the container.
	 * @throws OutOfMemoryError If memory runs out.
	 * @throws IllegalArgumentException If the new capacity is smaller than equal of current container size.
	 */
	void ensureCapacity(int capacity) throws OutOfMemoryError, IllegalArgumentException;

	/**
	 * Resets the container so that it is empty and has a default
	 * capacity for the internal data structure.
	 */
	void clear();
}
