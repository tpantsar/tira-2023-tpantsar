package oy.interact.tira.student.graph;

/**
 * Vertex is the node of a graph. This generic class
 * holds the actual vertex data object. For example, in a
 * graph having the train network, vertices would be stations
 * and edges would be the train tracks.
 * <p>
 * The data object T must implement the Comparable interface.
 *
 * @author Antti Juustila
 * @version 1.0
 */
public class Vertex<T> {

    /**
     * The generic data element of the vertex.
     */
    private T element = null;

    /**
     * Constructs a vertex with the data element.
     * <p>
     * NB: Though the constructor is public, _always_ create a new
     * vertex for the graph using Graph.createVertexFor(T dataItem).
     * This method creates the vertex, adds empty set of edges to it and
     * adds it to the dictionary of vertices and edges in the graph.
     * <p>
     * This method is public only to allow tests to create new vertices for
     * searching from the graph.
     *
     * @param element The data to put inside the vertex.
     */
    Vertex(T element) {
        assert (element != null);
        this.element = element;
    }

    /**
     * Gets the value object inside the element.
     *
     * @return The element inside the vertex.
     */
    public T getElement() {
        return element;
    }

    /**
     * Provides a string representation of the vertex.
     *
     * @return The vertex element as string.
     */
    @Override
    public String toString() {
        return element.toString();
    }

    /**
     * Calculates a hash for the vertex using the element.
     */
    @Override
    public int hashCode() {
        return element.hashCode();
    }

    /**
     * Checks whether two vertices are the same.
     *
     * @param another The another object to compare this to.
     * @return Returns true if the vertices are identical (or their elements match).
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object another) {
        if (another == null) {
            return false;
        }
        if (this == another) {
            return true;
        }
        if (another instanceof Vertex<?>) {
            return this.element.equals(((Vertex<T>) another).element);
        }
        return false;
    }

};
