package oy.interact.tira.task_09_graphs;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import oy.interact.tira.student.graph.Graph;
import oy.interact.tira.student.graph.Vertex;
import oy.interact.tira.student.graph.Edge.EdgeType;

/**
 * Basic unit tests for the Graph class.
 * Tests if the graph has the vertices and edges it should have.
 */
class CyclesTests {

    @Test 
    // @Timeout(value = 60, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    void testConnectedUndirectedGraph()
    {
        IntegerTestGraph testGraph = IntegerTestGraph.createSimpleUndirectedGraph();
        assertNotNull(testGraph, () -> "Test graph not created");
        assertTrue(testGraph.hasCycles(EdgeType.UNDIRECTED, null), () -> "This graph should have cycles");
    }

    @Test 
    // @Timeout(value = 60, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    void testConnectedDirectedGraph()
    {
        IntegerTestGraph testGraph = IntegerTestGraph.createSimpleDirectedGraph();
        assertNotNull(testGraph, () -> "Test graph not created");
        // We know the graph structure and know that from vertex 1 you can get to all places in this directed graph.
        Vertex<Integer> toStartFrom = testGraph.getVertexFor(1);
        assertTrue(testGraph.hasCycles(EdgeType.DIRECTED, toStartFrom), () -> "This graph should have cycles");
    }

    @Test 
    // @Timeout(value = 60, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    void testDisconnectedUndirectedGraph()
    {
        IntegerTestGraph testGraph = IntegerTestGraph.createSimpleUndirectedDisconnectedGraph();
        assertNotNull(testGraph, () -> "Test graph not created");
        assertFalse(testGraph.hasCycles(EdgeType.UNDIRECTED, null), () -> "This graph should have cycles");
    }

    @Test
    // @Timeout(value = 60, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    void testDirectedGraphWithCycles() {
        IntegerTestGraph testGraph = IntegerTestGraph.createGraphForDijkstraSearch();
        assertNotNull(testGraph, () -> "Test graph not created");
        assertTrue(testGraph.hasCycles(EdgeType.DIRECTED, null), "This graph has cycles");
    }

    @Test
    // @Timeout(value = 60, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    void testFinnishTrainStationsGraph() {
        Graph<String> testGraph = StringTestGraph.createFinlandTrainNetworks();
        assertNotNull(testGraph, "Test graph not created");
        assertTrue(testGraph.hasCycles(EdgeType.UNDIRECTED, null), "This graph has cycles and should report that");
    }


}
