package oy.interact.tira.task_09_graphs;

import oy.interact.tira.student.graph.Edge;
import oy.interact.tira.student.graph.Graph;
import oy.interact.tira.student.graph.Vertex;

public class StringTestGraph extends Graph<String> {
	
	// Test data from C++ demo.
    public static Graph<String> createFinlandTrainNetworks() {
        Graph<String> network = new Graph<>();

        Vertex<String> oulu = network.createVertexFor("Oulu");
        Vertex<String> kuopio = network.createVertexFor("Kuopio");
        Vertex<String> jyvaskyla = network.createVertexFor("Jyväskylä");
        Vertex<String> joensuu = network.createVertexFor("Joensuu");
        Vertex<String> tampere = network.createVertexFor("Tampere");
        Vertex<String> turku = network.createVertexFor("Turku");
        Vertex<String> lahti = network.createVertexFor("Lahti");
        Vertex<String> kotka = network.createVertexFor("Kotka");
        Vertex<String> vainikkala = network.createVertexFor("Vainikkala");
        Vertex<String> helsinki = network.createVertexFor("Helsinki");

        network.addEdge(Edge.EdgeType.UNDIRECTED, oulu, kuopio, 359);
        network.addEdge(Edge.EdgeType.UNDIRECTED, oulu, jyvaskyla, 389);
        network.addEdge(Edge.EdgeType.UNDIRECTED, oulu, tampere, 495);

        network.addEdge(Edge.EdgeType.UNDIRECTED, kuopio, joensuu, 169);
        network.addEdge(Edge.EdgeType.UNDIRECTED, kuopio, jyvaskyla, 164);
        network.addEdge(Edge.EdgeType.UNDIRECTED, kuopio, lahti, 330);
        network.addEdge(Edge.EdgeType.UNDIRECTED, kuopio, kotka, 322);
        network.addEdge(Edge.EdgeType.UNDIRECTED, kuopio, vainikkala, 360);

        network.addEdge(Edge.EdgeType.UNDIRECTED, jyvaskyla, tampere, 154);
        network.addEdge(Edge.EdgeType.UNDIRECTED, jyvaskyla, joensuu, 262);
        network.addEdge(Edge.EdgeType.UNDIRECTED, jyvaskyla, kotka, 318);
        network.addEdge(Edge.EdgeType.UNDIRECTED, jyvaskyla, lahti, 325);
        network.addEdge(Edge.EdgeType.UNDIRECTED, jyvaskyla, vainikkala, 355);

        network.addEdge(Edge.EdgeType.UNDIRECTED, joensuu, lahti, 378);
        network.addEdge(Edge.EdgeType.UNDIRECTED, joensuu, vainikkala, 289);
        network.addEdge(Edge.EdgeType.UNDIRECTED, joensuu, kotka, 369);

        network.addEdge(Edge.EdgeType.UNDIRECTED, tampere, turku, 170);
        network.addEdge(Edge.EdgeType.UNDIRECTED, tampere, helsinki, 187);
        network.addEdge(Edge.EdgeType.UNDIRECTED, tampere, lahti, 175);

        network.addEdge(Edge.EdgeType.UNDIRECTED, turku, helsinki, 193);
        network.addEdge(Edge.EdgeType.UNDIRECTED, turku, lahti, 265);

        network.addEdge(Edge.EdgeType.UNDIRECTED, lahti, vainikkala, 153);
        network.addEdge(Edge.EdgeType.UNDIRECTED, lahti, helsinki, 104);
        network.addEdge(Edge.EdgeType.UNDIRECTED, lahti, kotka, 115);

        network.addEdge(Edge.EdgeType.UNDIRECTED, kotka, vainikkala, 145);
		  System.out.println(network);
        return network;
    }
}
