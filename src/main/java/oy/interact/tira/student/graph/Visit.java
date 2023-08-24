package oy.interact.tira.student.graph;

/**
 * Holds a step in traversing the graph via edges.
 * <p>
 * Used by Dijkstra to track the path traversed. Members
 * are public for simpler and faster access from code, so 
 * this is more like a record (C/C++ struct) than an object oriented class.
 * 
 * @author Antti Juustila
 * @version 1.0
 */
public class Visit<T> {

    /** Type of the visit. See Type. */
    public Type type;
    /** The edge of the visit. */
    public Edge<T> edge = null;

    /**
     * Constructs a visit of type START with a null edge.
     */
    Visit() {
        this.type = Type.START;
        this.edge = null;
    }

    /**
     * The type of the visit.
     */
    public enum Type {
        /** Starting visit is the beginning of the path. */
        START,
        /** After the starting visit, rest of the visit steps are edge visits. */
        EDGE,
    }

    /**
     * Constructs a visit with a type and an edge.
     * @param type Type of the visit.
     * @param edge The edge of the visit.
     */
    Visit(Type type, Edge<T> edge) {
        this.type = type;
        this.edge = edge;
    }

}
