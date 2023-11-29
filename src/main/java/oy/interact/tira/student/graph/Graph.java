package oy.interact.tira.student.graph;

import java.util.*;

import oy.interact.tira.student.ArrayQueue;
import oy.interact.tira.student.HashTableContainer;
import oy.interact.tira.student.StackImplementation;
import oy.interact.tira.student.graph.Edge.EdgeType;

import java.io.IOException;

/**
 * Implementation of the graph data structure and associated algorithms.
 * <p>
 * This abstract class implements the graph data structure and various
 * algorithms like breadth-first search, depth-first search and the Dijkstra
 * path finding algorithm.
 * <p>
 * The class needs your attention, dear student. Implement the methods
 * marked TODO in the comments, based on what you have learned about
 * graphs.
 * <p>
 * The Graph as a generic (template) class can use any data types conforming to
 * the Comparable interface.
 * <p>
 * This implementation uses edge lists to store the graph vertices
 * and edges.
 *
 * @author Antti Juustila
 * @version 1.0
 */
public class Graph<T> {

    /**
     * The edge list of the graph. Select and instantiate
     * a suitable type of Map, depending on application needs.
     */
    private Map<Vertex<T>, List<Edge<T>>> edgeList = null;
    private HashTableContainer<Integer, Vertex<T>> vertices = new HashTableContainer<>();

    /**
     * Constructor instantiates a suitable Map data structure
     * depending on the application requirements.
     */
    public Graph() {
        this.edgeList = new Hashtable<>();
    }

    /**
     * Creates a vertex holding the dataItem (node in a vertex) in the graph.
     * Use this method always to add vertices to the graph.
     * <p>
     * Create the vertex object with the data item, then create an empty
     * list of edges, and put the vertex and the empty list to the Map.
     * <p>
     * The newly created vertex must have an empty list of edges.
     *
     * @param element The data item to put in the vertex of the graph.
     * @return Returns the created vertex, placed in the graph's edge list.
     */
    public Vertex<T> createVertexFor(T element) {
        // Create the vertex object with the data item
        Vertex<T> vertex = new Vertex<>(element);

        // Create an empty list of edges and put the vertex and the empty list to the Map.
        List<Edge<T>> edges = new ArrayList<>();
        edgeList.put(vertex, edges);
        vertices.add(element.hashCode(), vertex);

        return vertex;
    }

    /**
     * Get all the vertices of the graph in a Set.
     *
     * @return A Set with all the vertices of the graph.
     */
    public Set<Vertex<T>> getVertices() {
        return edgeList.keySet();
    }

    /**
     * Adds an edge to the graph. Note that the vertices MUST have been created
     * earlier by calling {@code createVertexFor(T)} and are already in the graph.
     *
     * @param type        The type of the edge, either directed or undirected.
     * @param source      The source vertex of the edge.
     * @param destination The destination vertex of the edge.
     * @param weight      The weight of the edge.
     */
    public void addEdge(Edge.EdgeType type, Vertex<T> source, Vertex<T> destination, double weight) {
        switch (type) {
            case DIRECTED:
                // Add a directed edge to one direction
                addDirectedEdge(source, destination, weight);
                break;
            case UNDIRECTED:
                // Add a directed edge to both directions
                addDirectedEdge(source, destination, weight);
                addDirectedEdge(destination, source, weight);
                break;
        }
    }

    /**
     * Adds a directed edge to the graph. Note that the vertices must have been created
     * earlier by calling  {@code createVertexFor(T)}.
     *
     * @param source      The source vertex of the edge.
     * @param destination The destination vertex of the edge.
     * @param weight      The weight of the edge.
     */
    public void addDirectedEdge(Vertex<T> source, Vertex<T> destination, double weight) {
        Edge<T> edge = new Edge<>(source, destination, weight);
        edgeList.get(source).add(edge);
    }

    /**
     * Gets the edges of the specified vertex. The vertex must be
     * already in the graph.
     *
     * @param source The vertex edges of which we wish to get.
     * @return Returns the edges of the vertex or null if no edges from the source.
     */
    public List<Edge<T>> getEdges(Vertex<T> source) {
        return edgeList.get(source);
    }

    /**
     * Gets a vertex for the specified node (contents) in a vertex, if found.
     * If the vertex with the node value is not found, returns null.
     * Use `equals` to search for the element from the vertices.
     *
     * @param element The value of T that is in some Vertex in the graph.
     * @return The vertex containing the node, or null if no vertex contains the element.
     */
    public Vertex<T> getVertexFor(T element) {
        return vertices.get(element.hashCode()); // Returns null if no vertex contains the element
    }

    /**
     * If target is null, search is done for the whole graph. Otherwise,
     * search MUST be stopped when the target vertex is found.
     *
     * @param from   The vertex where the search is started from.
     * @param target An optional ending vertex, null if not given.
     * @return Returns all the visited vertices traversed while doing BFS, in order they were found, or an empty list.
     */
    public List<Vertex<T>> breadthFirstSearch(Vertex<T> from, Vertex<T> target) {
        ArrayQueue<Vertex<T>> queue = new ArrayQueue<>(); // Vertices to visit next
        Set<Vertex<T>> enqueued = new HashSet<>(); // Vertices already found and marked to be visited
        List<Vertex<T>> visited = new ArrayList<>(); // All visited vertices

        queue.enqueue(from);
        enqueued.add(from);

        while (!queue.isEmpty()) {
            Vertex<T> currentVertex = queue.dequeue(); // Take the next vertex and remove it from queue
            visited.add(currentVertex); // Add the vertex to the visited

            // Find the edges of this vertex; where can we go from here.
            List<Edge<T>> neighborEdges = getEdges(currentVertex);

            for (Edge<T> edge : neighborEdges) {
                Vertex<T> neighborVertex = edge.getDestination();
                if (null != target) {
                    // search MUST be stopped when the target vertex is found.
                    if (neighborVertex.equals(target)) {
                        return visited;
                    }
                }
                if (!enqueued.contains(neighborVertex)) {
                    queue.enqueue(neighborVertex);
                    enqueued.add(neighborVertex);
                }
            }
        }
        return visited; // All the vertices in the order they were found, breadth first.
    }

    /**
     * Does depth first search (DFS) of the graph starting from a vertex.
     * <p>
     * If target is null, search is done for the whole graph. Otherwise,
     * search MUST be stopped when the target vertex is found.
     * <p>
     *
     * @param from   The vertex where the search is started from.
     * @param target An optional ending vertex, null if not given.
     * @return Returns all the visited vertices traversed while doing DFS.
     */
    public List<Vertex<T>> depthFirstSearch(Vertex<T> from, Vertex<T> target) {
        StackImplementation<Vertex<T>> stack = new StackImplementation<>(); // Vertices to depth search.
        Set<Vertex<T>> pushed = new HashSet<>(); // Not yet handled in depth search
        List<Vertex<T>> visited = new ArrayList<>(); // Vertices visited in depth search

        stack.push(from); // Where to start from
        pushed.add(from); // This is now (being) handled
        visited.add(from); // And also visited

        while (!stack.isEmpty()) {
            // Find the edges of this vertex; where can we go from here.
            List<Edge<T>> neighborEdges = getEdges(stack.peek());
            if (neighborEdges.isEmpty()) {
                stack.pop();
                continue;
            } else { // Vertex has edges to go
                boolean continueOuter = false;
                for (Edge<T> edge : neighborEdges) {
                    Vertex<T> neighborVertex = edge.getDestination();
                    if (null != target) {
                        // search MUST be stopped when the target vertex is found.
                        if (neighborVertex.equals(target)) {
                            return visited;
                        }
                    }
                    if (!pushed.contains(neighborVertex)) {
                        stack.push(neighborVertex);
                        pushed.add(neighborVertex);
                        visited.add(neighborVertex);
                        continueOuter = true;
                        break;
                    }
                }
                if (continueOuter) {
                    continue;
                }
            }
            stack.pop();
        }
        return visited; // All the vertices in the order they were found, depth first.
    }

    /**
     * Returns a non-empty list if the graph is disconnected. A disconnected graph is a
     * graph that has separate areas without any connecting edges between them.
     * <p>
     * If the graph is disconnected, the list contains all the elements _not_ visited,
     * doing a breadth first search from the vertex provided as the parameter.
     * If the parameter is null, starts from the first vertex of the graph.
     *
     * @param toStartFrom Vertex to start investigating from. If null, start from the first vertex.
     * @return Returns non-empty list if the graph is disconnected, otherwise list is empty.
     */
    public List<T> disconnectedVertices(Vertex<T> toStartFrom) {
        List<T> notInVisited = new ArrayList<>();

        if (isDisconnected(toStartFrom)) {
            Set<Vertex<T>> vertices = getVertices(); // Get all vertices

            // If toStartFrom is null, search starts from the first vertex
            if (toStartFrom == null) {
                toStartFrom = vertices.iterator().next();
            }

            // Do a breadth first search to get all vertices.
            List<Vertex<T>> visited = breadthFirstSearch(toStartFrom, null);
            for (Vertex<T> vertex : vertices) {
                if (!visited.contains(vertex)) {
                    notInVisited.add(vertex.getElement()); // Vertex was not visited, so add its element to the list.
                }
            }
        }
        return notInVisited;
    }

    /**
     * Returns true if the graph is disconnected. That means, the graph
     * has areas that can not be reached from the starting vertex.
     *
     * @param toStartFrom The vertex to start the analysis from. Can be null, then starts from first vertex.
     * @return True if the graph is disconnected.
     */
    public boolean isDisconnected(Vertex<T> toStartFrom) {
        Set<Vertex<T>> vertices = getVertices(); // Get all vertices

        // If there are no vertices, it is not a disconnected graph.
        if (vertices.isEmpty()) {
            return false;
        }

        // If toStartFrom is null, search starts from the first vertex
        if (toStartFrom == null) {
            toStartFrom = vertices.iterator().next();
        }

        // Do a breadth first search to get all vertices.
        List<Vertex<T>> visited = breadthFirstSearch(toStartFrom, null);
        for (Vertex<T> vertex : vertices) {
            if (!visited.contains(vertex)) {
                return true; // One of all vertices was not visited, so graph is disconnected.
            }
        }
        return false; // Graph is not disconnected
    }

    /**
     * Checks if the graph has cycles.
     * <p>
     * If the graph is directed, provide true as the parameter, false for
     * undirected graphs.
     *
     * <p>NB: For this course project it is enough that this method works for
     * connected graphs. It does not need to work on disconnected graphs when starting
     * from the given vertex.
     *
     * @param isDirected If true graph is directed.
     * @param fromVertex Start looking from this vertex. If null, starts from first vertex in adjacency list.
     * @return Returns true if the graph has cycles.
     */
    public boolean hasCycles(EdgeType edgeType, Vertex<T> fromVertex) {
        Set<Vertex<T>> vertices = getVertices(); // Get all vertices

        // If the graph is disconnected or is empty, return false
        if (isDisconnected(fromVertex) || vertices.isEmpty()) {
            return false;
        }

        // Iterator used to traverse the vertices set
        Iterator<Vertex<T>> vertexIterator = getVertices().iterator();

        // If fromVertex is not null, search starts from there.
        // If fromVertex is null, search starts from the first vertex in a graph.
        if (fromVertex != null) {
            // Traverse vertices until fromVertex is found
            while (vertexIterator.hasNext()) {
                if (vertexIterator.next().equals(fromVertex)) {
                    break;
                }
            }
        }
        return hasCyclesHelper(edgeType, vertexIterator, vertices);
    }

    // Helper method for finding cycles from a graph.
    public boolean hasCyclesHelper(EdgeType edgeType, Iterator<Vertex<T>> iterator, Set<Vertex<T>> vertices) {
        int edgeCount = 0;
        while (iterator.hasNext()) {
            Vertex<T> vertex = iterator.next();
            edgeCount += getEdges(vertex).size();
        }
        if (edgeType == EdgeType.UNDIRECTED) {
            edgeCount /= 2;
        }
        return vertices.size() - 1 != edgeCount;
    }

    // Dijkstra starts here.

    /**
     * The result of the Dijkstra's search.
     */
    public class DijkstraResult<E> {
        public boolean pathFound = false;
        public List<E> path;
        public int steps = 0;
        public double totalWeight = 0.0;

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append(String.format("Dijkstra result:\n- Path found: %s%n", (pathFound ? "yes" : "no")));
            if (pathFound) {
                builder.append(String.format("- steps: %d%n", steps));
                builder.append(String.format("- total edge weights: %.2f%n", totalWeight));
                if (null != path) {
                    builder.append(String.format("- path: %s", path.toString()));
                } else {
                    builder.append("Path not found\n");
                }
            }
            return builder.toString();
        }
    }

    // This class is used to compare vertices in the PriorityQueue,
    // where the highest priority vertices are the ones with the least weight.
    class DistanceComparator implements Comparator<Vertex<T>> {
        Graph<T> graph;
        Map<Vertex<T>, Visit<T>> paths;

        // Allocate the priority queue giving the graph and paths in the constructor
        public DistanceComparator(Graph<T> graph, Map<Vertex<T>, Visit<T>> paths) {
            this.graph = graph;
            this.paths = paths;
        }

        @Override
        public int compare(Vertex<T> left, Vertex<T> right) {
            // The order of the two vertices compared to each other depend
            // on their distance from the start node in the path
            return (int) (graph.distance(left, paths) - graph.distance(right, paths));
        }
    }

    /**
     * Finds the shortest path from start to end using Dijkstra's algorithm.
     * <p>
     * The return value contains information about the result.
     *
     * @param start The vertex to start from.
     * @param end   The vertex to search the shortest path to.
     * @return An object containing information about the result of the search.
     */
    public DijkstraResult<T> shortestPathDijkstra(Vertex<T> start, Vertex<T> end) {
        DijkstraResult<T> result = new DijkstraResult<>();
        result.pathFound = false;
        result.path = null;
        result.steps = 0;
        result.totalWeight = 0.0;

        // In Dijkstra, first call shortestPathsFrom() method, then call route()
        // with the paths collected using this method, to get the shortest path to the destination.
        Map<Vertex<T>, Visit<T>> viaPath = shortestPathsFrom(start);

        // "NB: route() starts from the destination and then traverses the visits backwards using the source node,
        // finishing to the start visit." -> 08-2 TIRA Graphs part 2 lectures
        List<Edge<T>> shortestPathToDestination = route(end, viaPath);

        // --> We should then reverse the edge list to get the shortest path from START vertex
        Collections.reverse(shortestPathToDestination);

        // Create a new path array and insert starting vertex to it
        List<T> path = new ArrayList<>();
        path.add(start.getElement());

        // Add rest of the destination vertex elements to the path array list
        for (Edge<T> edge : shortestPathToDestination) {
            path.add(edge.getDestination().getElement());
        }

        // Confirm that last destination vertex was the same as the ending parameter vertex
        Vertex<T> lastVertex = shortestPathToDestination.get(shortestPathToDestination.size() - 1).getDestination();
        result.pathFound = end.equals(lastVertex);
        result.path = path;
        result.steps = shortestPathToDestination.size();
        result.totalWeight = distance(end, viaPath);

        System.out.println(result.toString());
        return result;
    }


    /**
     * Finds a route to a destination using paths already constructed.
     * Before calling this method, call {@link shortestPathsFrom} to construct
     * the paths from the starting vertex of Dijkstra algorithm.
     * <p>
     * A helper method for implementing the Dijkstra algorithm.
     *
     * @param toDestination The destination vertex to find the route to.
     * @param paths         The paths to search the destination.
     * @return Returns the vertices forming the route to the destination.
     */
    private List<Edge<T>> route(Vertex<T> toDestination, Map<Vertex<T>, Visit<T>> paths) {
        // "NB: route() starts from the destination and then traverses the visits backwards using the source node,
        // finishing to the start visit." -> 08-2 TIRA Graphs part 2 lectures
        Vertex<T> vertex = toDestination;
        List<Edge<T>> path = new ArrayList<>();

        if (paths.isEmpty()) {
            return path;
        }
        Visit<T> visit = paths.get(vertex);
        while (visit.type != Visit.Type.START) {
            path.add(visit.edge);
            vertex = visit.edge.getSource();
            visit = paths.get(vertex);
        }
        return path;
    }

    // Calculates the distance to destination using given paths.
    private double distance(Vertex<T> toDestination, Map<Vertex<T>, Visit<T>> viaPath) {
        List<Edge<T>> path = route(toDestination, viaPath);
        double distance = 0.0;
        for (Edge<T> edge : path) {
            distance += edge.getWeight();
        }
        return distance;
    }

    /**
     * Finds the shortest paths in the graph from the starting vertex.
     * <p>
     * In doing Dijkstra, first call this method, then call {@link route}
     * with the paths collected using this method, to get the shortest path to the destination.
     *
     * @param start The starting vertex for the path searching.
     * @return Returns the visits from the starting vertex.
     * @see oy.tol.tira.graph.Graph#route(Vertex, Map)
     */
    private Map<Vertex<T>, Visit<T>> shortestPathsFrom(Vertex<T> start) {
        Visit<T> visit = new Visit<>();
        visit.type = Visit.Type.START;
        Map<Vertex<T>, Visit<T>> paths = new HashMap<>();

        // First insert the starting visiting edge to the path.
        paths.put(start, visit);

        // Create a priority queue sorting the vertices in the order of path distances from the vertex.
        PriorityQueue<Vertex<T>> priorityQueue = new PriorityQueue<>(new DistanceComparator(this, paths));
        priorityQueue.add(start);

        // Start from the starting vertex already in the queue.
        while (!priorityQueue.isEmpty()) {
            // Take the vertex having the shortest distance out and remove it from queue.
            Vertex<T> vertex = priorityQueue.poll();
            List<Edge<T>> edges = getEdges(vertex); // Get the edges of this vertex
            for (Edge<T> edge : edges) {
                double weight = edge.getWeight();
                Vertex<T> edgeDestination = edge.getDestination();
                if (!paths.containsKey(edgeDestination) ||
                        distance(vertex, paths) + weight < distance(edgeDestination, paths)) {
                    paths.put(edgeDestination, new Visit<>(Visit.Type.EDGE, edge));
                    priorityQueue.add(edgeDestination);
                }
            }
        }
        return paths;
    }

    // OPTIONAL task in the course!

    /**
     * Do breadth-first-search on the graph and export vertices and edges to a dot file
     * <p>
     * Note that if the graph is disconnected, you must check if some vertices
     * were not visited and continue the BFS until _all_ vertices have been visited.
     * Otherwise, part of the graph is missing from the output file.
     * <p>
     * The simplest way to do this is to first start from the given vertex, do
     * the BFS saving data to dot file. Then, after this loop, get the disconnected vertices starting from
     * the starting vertex by calling disconnectedVertices(from). If there are not visited vertices,
     * then pick one of the non visited vertices to be the new starting vertex (from).
     * Repeat this until all vertices have been visited. Basically you need an outer loop repeating
     * the BFS in the inner loop, and the outer loop stops when all vertices have been visited.
     *
     * @param from           Start the BFS from this vertex.
     * @param outputFileName Write the dot to this text file.
     * @throws IOException If something goes wrong with file operations.
     */
    public void toDotBFS(Vertex<T> from, String outputFileName) throws IOException {
        // TODO: Student, implement this if you want to (optional task).
    }

    // STUDENTS: TODO: Uncomment the code below and use it as a sample on how
    // to iterate over vertices and edges in one situation.
    // If you use some other name for your edge list than edgeList, then
    // rename that in the code below! Otherwise you will have compiler errors.

    /**
     * Provides a string representation of the graph, printing  out the vertices and edges.
     * <p>
     * Quite useful if you need to debug issues with algorithms. You can see is the graph
     * what it is supposed to be like.
     * <p>
     * Simple graph as a string would look like this:<br/>
     * <pre>
     * Created simple undirected graph where integers are vertex values:
     * [1] -> [ 2 ]
     * [2] -> [ 1, 3, 4, 5 ]
     * [3] -> [ 2, 4, 5 ]
     * [4] -> [ 2, 3, 5 ]
     * [5] -> [ 2, 3, 4 ]
     * </pre>
     *
     * @return The graph as a string.
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (Map.Entry<Vertex<T>, List<Edge<T>>> entry : edgeList.entrySet()) {
            output.append("[");
            output.append(entry.getKey().toString());
            output.append("] -> [ ");
            int counter = 0;
            int count = entry.getValue().size();
            for (Edge<T> edge : entry.getValue()) {
                output.append(edge.getDestination().toString());
                if (counter < count - 1) {
                    output.append(", ");
                }
                counter++;
            }
            output.append(" ]\n");
        }
        return output.toString();
    }
}
