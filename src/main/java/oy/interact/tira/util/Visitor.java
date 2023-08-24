package oy.interact.tira.util;

/**
 * An interface for all visitors that visit Visitables.
 * 
 * It is the visitors' responsibility to control the navigation
 * in containers. That is, the containers do not "push" the visitor
 * around, but visitors need to decide which container elements to visit
 * and in which order, and call the accept method of the Visitable.
 * 
 * This gives flexibility to traversing the containers, since each
 * visitor can decide if they would like to visit the container
 * in different orders. For example, if the visitable container is
 * a binary search tree, each visitor can decide if they wish to
 * navigate the tree in:
 *
 * 1. inorder,
 * 2. preorder, or
 * 3. postorder.
 */
public interface Visitor<K extends Comparable<K>,V> {
	/**
	 * When a visitor wishes to visit a visitable, it calls the
	 * accept method of the node. Node then calls the visitor's
	 * visit method, passing itself as the parameter.
	 * 
	 * Visitor may then access the visitable object.
	 * 
	 * @param visitable The element to visit
	 * @throws Exception
	 */
	void visit(Visitable<K,V> visitable) throws Exception;

	/**
	 * For situations where the visitor needs to allocate resources
	 * for visiting. After visiting is done, calling close releases the
	 * resources visitor has reserved.
	 */
	void close();
}
