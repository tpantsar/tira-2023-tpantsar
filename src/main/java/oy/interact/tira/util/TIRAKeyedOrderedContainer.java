package oy.interact.tira.util;

import java.util.function.Predicate;

/**
 * An interface for an ordered generic container class having key-value (K,V) pairs.
 * The key elements K must implement the Comparable interface.
 * 
 * See also the documentation of the TIRAKeyedContainer interface this interface extends.
 * Any requirements there also apply to this interface.
 * 
 * The difference to TIRAKeyedContainer is that in this container, there is an order
 * in which the elemements are in the container, specified with an index.
 * 
 * The order of the elements is determined using e.g. the Comparable interface of K,
 * or by using a Comparator interface. The way this is done is implementation specific.
 * 
 * Note that in this project, the equals and Comparable differ
 * from the recommended. That is, (x.compareTo(y)==0) == (x.equals(y))
 * does _not_ hold. See also the `Coder` class which is used as E in this project.
 */

public interface TIRAKeyedOrderedContainer<K extends Comparable<K>, V> extends TIRAKeyedContainer<K,V>, Visitable<K,V> {
	/**
	 * Retrieves the index of an key in the container.
	 * 
	 * Note that in searching, comparison should be used; either Comparable interface
	 * or Comparator interface. When determining if the correct key has been found, 
	 * finally use equals.
	 * 
	 * @param itemKey The key to query the index of.
	 * @return The index of the key to search in the container, or -1 if not found or collection is empty.
	 */
	int indexOf(K itemKey);

	/**
	 * Retrieves the key-value pair at the index.
	 * 
	 * @param index Index to retrieve, must be 0..<size.
	 * @return The key-value pair.
	 * @throws IndexOutOfBoundsException If index < 0 or index >= size.
	 */
	Pair<K,V> getIndex(int index) throws IndexOutOfBoundsException;

	/**
	 * Finds an index for the value conforming to the predicate.
	 * 
	 * @param searcher The predicate specifying the search criteria.
	 * @return The index for the value or -1 if not found.
	 */
	int findIndex(Predicate<V> searcher);

	/**
	 * The container supports the Visitor design pattern. 
	 * https://en.wikipedia.org/wiki/Visitor_pattern
	 * 
	 * Visitors (Visitor is an inteface) can visit the elements in the container.
	 * What the visitors actually do is up to the various Visitor implementations.
	 * 
	 * How visitors navigate in containers can be done in two ways:
	 * 
	 * 1. It is the container who pushes the visitor around to visit the elements in
	 *    the container. Container then controls the visitation.
	 * 2. It is the visitors who control the visitation, using container methods
	 *    to access the elements in the container and deciding how the visit goes.
	 * 
	 * In this project, it is the _Visitors_ who control the visitations. In practice,
	 * this means that the container implementation of accept() just call the 
	 * visitor.visit(something). Something could be "this" or some member variable of
	 * the container, depending on the implementation. After this, the visitor calls 
	 * the container methods to access the elements in the container and then again 
	 * call (possibly) the container element's accept methods, e.g.: visitor.visit(this);
	 * 
	 * @param visitor The visitor that visits the container.
	 * @throws Exception If something goes wrong while visiting the container.
	 */
	@Override
	void accept(Visitor<K,V> visitor) throws Exception;
}
