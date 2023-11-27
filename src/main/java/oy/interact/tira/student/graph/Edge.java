package oy.interact.tira.student.graph;

/**
 * The edge of a graph defines a link from a Vertex to another along
 * with the weight of the edge.
 * <p>
 * Edges can be either directed or undirected. Undirected edges can be
 * traversed to both directions. Actually an undirected edge is implemented
 * just by adding two edges, one from source to destination, and another one
 * from destination to source, using the same weight.
 *
 * @author Antti Juustila
 * @version 1.0
 * @see oy.tol.tira.graph.Vertex
 */
public class Edge<T> {

    // The source of the edge.
    private Vertex<T> source = null;

    // The destination of the edge.
    private Vertex<T> destination = null;

    // The weight of the edge, 1.0 by default.
    private double weight = 1.0;

    /** The edge's type. */
    public enum EdgeType {
        /** Directed edge goes from source to destination but not back. */
        DIRECTED,
        /** Undirected edge goes from source to destination and back (as another edge). */
        UNDIRECTED;
    }

    /**
     * Constructs an Edge with a source and destination vertices and the
     * edge's weight.
     * @param source      The source Vertex.
     * @param destination The destination Vertex.
     * @param weight      The weight of the edge.
     */
    Edge(Vertex<T> source, Vertex<T> destination, double weight) {
        assert (source != null && destination != null);
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    /**
     * Gets the source vertex of the edge.
     * @return The source vertex.
     */
    public Vertex<T> getSource() {
        return source;
    }

    /**
     * Gets the destination vertex of the edge.
     * @return The destination vertex.
     */
    public Vertex<T> getDestination() {
        return destination;
    }

    /**
     * Gets the weight of the edge.
     * @return Edge's weight.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Creates a new Edge with source and destination reversed.
     * @return Returns a new edge with destination and source swapped.
     */
    public Edge<T> reversed() {
        return new Edge<>(this.destination, this.source, this.weight);
    }

    /**
     * Provides a string representation of the edge.
     * @return The edge represented as a string.
     */
    @Override
    public String toString() {
        return source.toString() + " -> " + destination.toString() + " (" + Double.toString(weight) + ")";
    }

    /**
     * Checks if the edge is identical to another.
     * Edges are equal if the source and destination vertices are the same.
     *
     * @return Returns true if the edges are identical.
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
        if (another instanceof Edge<?>) {
            Edge<T> edge = (Edge<T>) another;
            return this.source.equals(edge.source) && this.destination.equals(edge.destination);
        }
        return false;
    }

    /**
     * Calculates a hash for the edge.
     * <p>
     * Calculation combines the source and destination vertex hashes.
     */
    @Override
    public int hashCode() {
        return source.hashCode() + 31 * destination.hashCode();
    }

}
