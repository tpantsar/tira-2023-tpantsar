package oy.interact.tira.task_09_graphs;

import java.util.ArrayList;
import java.util.List;

import oy.interact.tira.student.graph.Edge;
import oy.interact.tira.student.graph.Graph;
import oy.interact.tira.student.graph.Vertex;
import oy.interact.tira.student.graph.Edge.EdgeType;

/**
 * Class to test the Graph implementation.
 */
public class IntegerTestGraph extends Graph<Integer> {

   private List<Vertex<Integer>> currentPath = new ArrayList<>();

   List<Vertex<Integer>> getCurrentPath() {
      return currentPath;
   }

   List<Vertex<Integer>> doBreadthFirstSearch(Vertex<Integer> from) {
      return breadthFirstSearch(from, null);
   }

   List<Vertex<Integer>> doDepthFirstSearch(Vertex<Integer> from) {
      return depthFirstSearch(from, null);
   }

   DijkstraResult<Integer> doDijkstra(Vertex<Integer> from, Vertex<Integer> to) {
      return shortestPathDijkstra(from, to);
   }

   static IntegerTestGraph createSimpleUndirectedGraph() {
      IntegerTestGraph testGraph = new IntegerTestGraph();
      // Undirected graph from lecture Graphs 1, slide "What is a graph?".
      for (int id = 1; id <= 5; id++) {
         testGraph.createVertexFor(id);
      }
      testGraph.addEdge(Edge.EdgeType.UNDIRECTED, testGraph.getVertexFor(1), testGraph.getVertexFor(2), 1.0);
      testGraph.addEdge(Edge.EdgeType.UNDIRECTED, testGraph.getVertexFor(2), testGraph.getVertexFor(3), 1.0);
      testGraph.addEdge(Edge.EdgeType.UNDIRECTED, testGraph.getVertexFor(2), testGraph.getVertexFor(4), 1.0);
      testGraph.addEdge(Edge.EdgeType.UNDIRECTED, testGraph.getVertexFor(2), testGraph.getVertexFor(5), 1.0);
      testGraph.addEdge(Edge.EdgeType.UNDIRECTED, testGraph.getVertexFor(3), testGraph.getVertexFor(4), 1.0);
      testGraph.addEdge(Edge.EdgeType.UNDIRECTED, testGraph.getVertexFor(3), testGraph.getVertexFor(5), 1.0);
      testGraph.addEdge(Edge.EdgeType.UNDIRECTED, testGraph.getVertexFor(4), testGraph.getVertexFor(5), 1.0);
      System.out.println("Created simple undirected graph:");
      System.out.println(testGraph.toString());
      return testGraph;
   }

   static IntegerTestGraph createSimpleDirectedGraph() {
      IntegerTestGraph testGraph = new IntegerTestGraph();
      // Directed graph from lecture Graphs 1, slide "What is a graph?".
      for (int id = 1; id <= 5; id++) {
         testGraph.createVertexFor(id);
      }
      testGraph.addEdge(Edge.EdgeType.DIRECTED, testGraph.getVertexFor(1), testGraph.getVertexFor(2), 1.0);
      testGraph.addEdge(Edge.EdgeType.DIRECTED, testGraph.getVertexFor(2), testGraph.getVertexFor(4), 0.5);
      testGraph.addEdge(Edge.EdgeType.DIRECTED, testGraph.getVertexFor(2), testGraph.getVertexFor(5), 2.5);
      testGraph.addEdge(Edge.EdgeType.DIRECTED, testGraph.getVertexFor(3), testGraph.getVertexFor(2), 4.0);
      testGraph.addEdge(Edge.EdgeType.DIRECTED, testGraph.getVertexFor(3), testGraph.getVertexFor(5), 1.5);
      testGraph.addEdge(Edge.EdgeType.DIRECTED, testGraph.getVertexFor(4), testGraph.getVertexFor(3), 11.5);
      testGraph.addEdge(Edge.EdgeType.DIRECTED, testGraph.getVertexFor(5), testGraph.getVertexFor(4), 3.5);
      System.out.println("Created simple directed graph:");
      System.out.println(testGraph.toString());
      return testGraph;
   }

   static IntegerTestGraph createSimpleUndirectedDisconnectedGraph() {
      IntegerTestGraph testGraph = new IntegerTestGraph();
      // Undirected graph from lecture Graphs 1, slide "Disconnected or not?".
      for (int id = 1; id <= 5; id++) {
         testGraph.createVertexFor(id);
      }
      testGraph.addEdge(Edge.EdgeType.UNDIRECTED, testGraph.getVertexFor(1), testGraph.getVertexFor(2), 1.0);
      testGraph.addEdge(Edge.EdgeType.UNDIRECTED, testGraph.getVertexFor(2), testGraph.getVertexFor(3), 1.0);
      testGraph.addEdge(Edge.EdgeType.UNDIRECTED, testGraph.getVertexFor(4), testGraph.getVertexFor(5), 1.0);
      System.out.println("Created simple undirected graph:");
      System.out.println(testGraph.toString());
      return testGraph;
   }

   static IntegerTestGraph createGraphForDijkstraSearch() {
      IntegerTestGraph testGraph = new IntegerTestGraph();

      testGraph.createVertexFor(1);
      testGraph.createVertexFor(2);
      testGraph.createVertexFor(3);
      testGraph.createVertexFor(4);
      testGraph.createVertexFor(5);
      testGraph.createVertexFor(6);
      testGraph.createVertexFor(7);
      
      testGraph.addEdge(EdgeType.UNDIRECTED, testGraph.getVertexFor(1), testGraph.getVertexFor(2), 4.0);
      testGraph.addEdge(EdgeType.UNDIRECTED, testGraph.getVertexFor(1), testGraph.getVertexFor(5), 1.0);
      testGraph.addEdge(EdgeType.UNDIRECTED, testGraph.getVertexFor(2), testGraph.getVertexFor(3), 11.0);
      testGraph.addEdge(EdgeType.UNDIRECTED, testGraph.getVertexFor(2), testGraph.getVertexFor(5), 2.0);
      testGraph.addEdge(EdgeType.UNDIRECTED, testGraph.getVertexFor(2), testGraph.getVertexFor(6), 5.0);
      testGraph.addEdge(EdgeType.UNDIRECTED, testGraph.getVertexFor(3), testGraph.getVertexFor(6), 4.0);
      testGraph.addEdge(EdgeType.UNDIRECTED, testGraph.getVertexFor(3), testGraph.getVertexFor(4), 9.0);
      testGraph.addEdge(EdgeType.UNDIRECTED, testGraph.getVertexFor(3), testGraph.getVertexFor(7), 9.0);
      testGraph.addEdge(EdgeType.UNDIRECTED, testGraph.getVertexFor(4), testGraph.getVertexFor(7), 4.0);
      testGraph.addEdge(EdgeType.UNDIRECTED, testGraph.getVertexFor(5), testGraph.getVertexFor(6), 8.0);
      testGraph.addEdge(EdgeType.UNDIRECTED, testGraph.getVertexFor(6), testGraph.getVertexFor(7), 7.0);
      System.out.println("Created graph for dijkstra testing:");
      System.out.println(testGraph.toString());
      return testGraph;
   }

}
