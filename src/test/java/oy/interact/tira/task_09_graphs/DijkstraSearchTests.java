package oy.interact.tira.task_09_graphs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import oy.interact.tira.student.graph.Graph;
import oy.interact.tira.student.graph.Vertex;
import oy.interact.tira.student.graph.Edge.EdgeType;

/**
 * Basic unit tests for the Graph class. Tests if the graph has the vertices and
 * edges it should have.
 */
public class DijkstraSearchTests {

    @Test
    @DisplayName("Tests Dijkstra with simple integers and small number of vertices")
    void testDijkstraSearchWithIntegers() {
        IntegerTestGraph testGraph = IntegerTestGraph.createGraphForDijkstraSearch();
        assertNotNull(testGraph, () -> "Test graph not created");

        Integer[] pathAsArray = { 1, 5, 2, 6, 7, 4 };
        Graph<Integer>.DijkstraResult<Integer> result = testGraph.doDijkstra(testGraph.getVertexFor(1),
                testGraph.getVertexFor(4));
        assertEquals(pathAsArray.length, result.path.size(), () -> "Length of the path not expected");
        int counterToExpectedPath = 0;
        for (Integer edge : result.path) {
            assertEquals(pathAsArray[counterToExpectedPath], edge, () -> "Not a correct path");
            counterToExpectedPath++;
        }
        assertEquals(pathAsArray[pathAsArray.length - 1], result.path.get(result.path.size() - 1),
                () -> "Destination was not the expected");
        assertEquals(19, result.totalWeight, () -> "Total weight of the shortest path was not the expected");

    }

    @Test
    @DisplayName("Tests Dijkstra with strings and Finnish train network Oulu->Turku")
    void testTrainStationSearchOuluTurku() {
        Graph<String> network = StringTestGraph.createFinlandTrainNetworks();
        System.out.println(network);
        assertNotNull(network, "Must have a graph of train stations");
        assertTrue(network.hasCycles(EdgeType.UNDIRECTED, null), "Train graph has cycles so this fails");
        assertFalse(network.isDisconnected(null), "Finnish train network is not disconnected");
        /*
         * Oulu --> 495 --> Tampere
         * Tampere --> 170 --> Turku
         * >> Totalling 665 km
         */

        Vertex<String> turku = network.getVertexFor("Turku");
        assertNotNull(turku, "Turku should be in the train network as a vertex");
        Vertex<String> oulu = network.getVertexFor("Oulu");
        assertNotNull(oulu, "Oulu should be in the train network as a vertex");
        Graph<String>.DijkstraResult<String> result = network.shortestPathDijkstra(oulu, turku);
        assertEquals(2, result.steps, "From Oulu to Turku shortest path is two steps: Oulu->Tampere->Turku");
        assertEquals(3, result.path.size(), "From Oulu to Turku shortest path is three vertices: Oulu->Tampere->Turku");
        assertEquals(665, result.totalWeight, "Kilometres from Oulu->Tampere->Turku must be 665");
        assertEquals("Oulu", result.path.get(0), "Starting vertex must be Oulu");
        assertEquals("Tampere", result.path.get(1), "Next vertex must be Tampere");
        assertEquals("Turku", result.path.get(2), "Next vertex must be Turku");
    }

    @Test
    @DisplayName("Tests Dijkstra with strings and Finnish train network Oulu->Vainikkala")
    void testTrainStationSearchOuluVainikkala() {
        Graph<String> network = StringTestGraph.createFinlandTrainNetworks();
        System.out.println(network);
        assertNotNull(network, "Must have a graph of train stations");
        assertTrue(network.hasCycles(EdgeType.UNDIRECTED, null), "Train graph has cycles so this fails");
        /*
            Oulu   -->  359 -->      Kuopio  
          Kuopio   -->  360 -->  Vainikkala  
                >> Totalling    719 km
        */
        Vertex<String> oulu = network.getVertexFor("Oulu");
        assertNotNull(oulu, "Oulu should be in the train network as a vertex");
        Vertex<String> vainikkala = network.getVertexFor("Vainikkala");
        assertNotNull(vainikkala, "Turku should be in the train network as a vertex");
        Graph<String>.DijkstraResult<String> result = network.shortestPathDijkstra(oulu, vainikkala);
        assertEquals(2, result.steps, "From Oulu to Turku shortest path is two steps: Oulu->Kuopio->Vainikkala");
        assertEquals(3, result.path.size(), "From Oulu to Turku shortest path is three vertices: Oulu->Kuopio->Vainikkala");
        assertEquals(719, result.totalWeight, "Kilometres from Oulu->Kuopio->Vainikkala must be Vainikkala");
        assertEquals("Oulu", result.path.get(0), "Starting vertex must be Oulu");
        assertEquals("Kuopio", result.path.get(1), "Next vertex must be Kuopio");
        assertEquals("Vainikkala", result.path.get(2), "Next vertex must be Vainikkala");
    }

}
