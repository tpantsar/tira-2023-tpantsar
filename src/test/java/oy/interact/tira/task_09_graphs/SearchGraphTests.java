package oy.interact.tira.task_09_graphs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import oy.interact.tira.student.graph.Graph;
import oy.interact.tira.student.graph.Vertex;


/**
 * Basic unit tests for the Graph class.
 * Tests if the graph has the vertices and edges it should have.
 */
public class SearchGraphTests {

    @Test 
    public void testBreadthFirstSearchUndirected()
    {
        IntegerTestGraph testGraph = IntegerTestGraph.createSimpleUndirectedGraph();
        assertNotNull(testGraph, () -> "Test graph not created");
        
        List<Vertex<Integer>> bfsResult = testGraph.doBreadthFirstSearch(testGraph.getVertexFor(4));
        assertEquals(5, bfsResult.size(), () -> "BFS should find all vertices from the test graph");

        Set<Vertex<Integer>> allVertices = testGraph.getVertices();
        assertTrue(new HashSet<>(bfsResult).equals(new HashSet<>(allVertices)), () -> "BFS result set and all vertices should be equal with test graph");
    }

    @Test 
    public void testDepthFirstSearchUndirected()
    {
        IntegerTestGraph testGraph = IntegerTestGraph.createSimpleUndirectedGraph();
        assertNotNull(testGraph, () -> "Test graph not created");
        
        List<Vertex<Integer>> dfsResult = testGraph.doDepthFirstSearch(testGraph.getVertexFor(1));
        assertEquals(5, dfsResult.size(), () -> "DFS should find all vertices from the test graph");

        Set<Vertex<Integer>> allVertices = testGraph.getVertices();
        assertTrue(new HashSet<>(dfsResult).equals(new HashSet<>(allVertices)), () -> "DFS result set and all vertices should be equal with test graph");
    }

    @Test 
    public void testBreadthFirstSearchDirected()
    {
        IntegerTestGraph testGraph = IntegerTestGraph.createSimpleDirectedGraph();
        assertNotNull(testGraph, () -> "Test graph not created");
        
        List<Vertex<Integer>> bfsResult = testGraph.doBreadthFirstSearch(testGraph.getVertexFor(1));
        assertEquals(5, bfsResult.size(), () -> "BFS should find all vertices from the test graph");

        Set<Vertex<Integer>> allVertices = testGraph.getVertices();
        assertTrue(new HashSet<>(bfsResult).equals(new HashSet<>(allVertices)), () -> "BFS result set and all vertices should be equal with test graph");
    }

    @Test 
    public void testDepthFirstSearchDirected()
    {
        IntegerTestGraph testGraph = IntegerTestGraph.createSimpleDirectedGraph();
        assertNotNull(testGraph, () -> "Test graph not created");
        
        List<Vertex<Integer>> dfsResult = testGraph.doDepthFirstSearch(testGraph.getVertexFor(1));
        assertEquals(5, dfsResult.size(), () -> "DFS should find all vertices from the test graph");

        Set<Vertex<Integer>> allVertices = testGraph.getVertices();
        assertTrue(new HashSet<>(dfsResult).equals(new HashSet<>(allVertices)), () -> "DFS result set and all vertices should be equal with test graph");
    }

    @Test
    void testTrainStationsBFS() {
        Graph<String> testGraph = StringTestGraph.createFinlandTrainNetworks();
        assertNotNull(testGraph, "Test graph not created");
        List<Vertex<String>> dfsResult = testGraph.breadthFirstSearch(testGraph.getVertexFor("Oulu"), null);
        assertEquals(10, dfsResult.size(), () -> "DFS should find all vertices from the test graph");
        System.out.println("Order of stations as visited by BFS:");
        System.out.println(Arrays.toString(dfsResult.toArray()));

        Set<Vertex<String>> allVertices = testGraph.getVertices();
        assertTrue(new HashSet<>(dfsResult).equals(new HashSet<>(allVertices)), () -> "DFS result set and all vertices should be equal with test graph");
    }

    @Test
    void testTrainStationsDFS() {
        Graph<String> testGraph = StringTestGraph.createFinlandTrainNetworks();
        assertNotNull(testGraph, "Test graph not created");
        List<Vertex<String>> dfsResult = testGraph.depthFirstSearch(testGraph.getVertexFor("Oulu"), null);
        assertEquals(10, dfsResult.size(), () -> "DFS should find all vertices from the test graph");
        System.out.println("Order of stations as visited by DFS:");
        System.out.println(Arrays.toString(dfsResult.toArray()));

        Set<Vertex<String>> allVertices = testGraph.getVertices();
        assertTrue(new HashSet<>(dfsResult).equals(new HashSet<>(allVertices)), () -> "DFS result set and all vertices should be equal with test graph");
    }

}
