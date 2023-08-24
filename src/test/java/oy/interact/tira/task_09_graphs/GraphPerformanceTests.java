package oy.interact.tira.task_09_graphs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONTokener;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import oy.interact.tira.model.Coder;
import oy.interact.tira.student.graph.Edge;
import oy.interact.tira.student.graph.Graph;
import oy.interact.tira.student.graph.Vertex;
import oy.interact.tira.util.JSONConverter;

public class GraphPerformanceTests {

	private static final String outputFileName = "compare-graph.csv";
	private static final String separator = ",";
	private static int currentIndex = 0;
	private static BufferedWriter writer = null;
	Integer[] population = { 10,
			100,
			1000,
			5000,
			10000,
			50000,
			100000
	};

	private static final String[] testFiles = {
			"10-village-coders.json",
			"100-city-coders.json",
			"1000-area-coders.json",
			"5000-town-coders.json",
			"10000-large-city-coders.json",
			"50000-country-coders.json",
			"100000-europe-coders.json"
	};

	private Graph<Coder> graph;

	@BeforeAll
	static void openOutputFile() {
		try {
			writer = new BufferedWriter(new FileWriter(outputFileName));
			writer.append("** Analysing add(key, value) and to array + sorting speed, times in ms **");
			writer.newLine();
			writer.append("Vertice count,Edge count,Fill time (ms),Fill time/V+E (ms),BFS time (ms),DFS time (ms),Dijkstra time (ms),Testfile");
			writer.newLine();
		} catch (IOException e) {
			e.printStackTrace();
			fail("Could not open test output file for writing");
		}
	}

	@Test
	@Order(1)
	// @Timeout(value = 600, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
	void testBFSUsingCodersJSONFiles() {
		if (null != writer) {
			try {
				currentIndex = 0;
				DecimalFormat df = new DecimalFormat("0.0000", DecimalFormatSymbols.getInstance(Locale.US));
				while (currentIndex < testFiles.length) {
					System.out.format(
							"\n%d/%d ==> Starting to analyse Graph BST with %s %s%n",
							currentIndex + 1,
							testFiles.length,
							testFiles[currentIndex],
							new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
					Coder[] coders = readCodersFromFile(testFiles[currentIndex]);					
					assertNotNull(coders, "Could not read coders from file.");
					assertEquals(population[currentIndex], coders.length,
							"Test array size and coder array size must be the same");
					int trueVertexCount = coders.length;
					int trueEdgeCount = 0;
					for (Coder coder : coders) {
						trueEdgeCount += coder.getFriendIDs().size();
					}
					System.out.println("...starting to fill the graph from the array...");
					long start = System.currentTimeMillis();
					graph = createGraph(coders);
					long end = System.currentTimeMillis();
					assertNotNull(graph, "Could not create the graph object, it is null");
					long duration = end - start;
					System.out.format(" Step 3/6: Filling the Graph from Coders array it took %d ms%n", duration);
					coders = null;

					// Also test if all coders and edges are in the graph
					Set<Vertex<Coder>> graphCoders = graph.getVertices();
					int graphVertexCount = graphCoders.size();
					assertEquals(trueVertexCount, graphVertexCount);
					int graphEdgeCount = 0;
					for (Vertex<Coder> vertex : graphCoders) {
						List<Edge<Coder>> edges = graph.getEdges(vertex);
						graphEdgeCount += edges.size();
					}
					assertEquals(trueEdgeCount, graphEdgeCount, "Edge count in raw data and in graph do not match");

					writer.append(Long.toString(graphVertexCount));
					writer.append(separator);
					writer.append(Long.toString(graphEdgeCount));
					writer.append(separator);

					writer.append(Long.toString(duration));
					writer.append(separator);
					String perItem = df.format((double) duration / (double) (trueVertexCount + trueEdgeCount));
					writer.append(perItem);
					writer.append(separator);

					// Do BFS from first vertex
					start = System.currentTimeMillis();
					Set<Vertex<Coder>> notVisited = new HashSet<Vertex<Coder>>();
					notVisited.addAll(graphCoders);
					int visitedCount = 0;
					while (!notVisited.isEmpty()) {
						Vertex<Coder> from = notVisited.iterator().next();
						List<Vertex<Coder>> visited = graph.breadthFirstSearch(from, null);
						visitedCount += visited.size();
						notVisited.removeAll(visited);
					}
					end = System.currentTimeMillis();
					duration = end - start;
					System.out.format(" Step 4/6: BFS visited %d vertices, it took %d ms%n", visitedCount, duration);

					writer.append(Long.toString(duration));
					writer.append(separator);

					// Do DFS from first vertex
					start = System.currentTimeMillis();
					notVisited.clear();
					notVisited.addAll(graphCoders);
					visitedCount = 0;
					while (!notVisited.isEmpty()) {
						Vertex<Coder> from = notVisited.iterator().next();
						List<Vertex<Coder>> visited = graph.depthFirstSearch(from, null);
						visitedCount += visited.size();
						notVisited.removeAll(visited);
					}
					end = System.currentTimeMillis();
					duration = end - start;
					System.out.format(" Step 5/6: DFS visited %d vertices, it took %d ms%n", visitedCount, duration);
					writer.append(Long.toString(duration));
					writer.append(separator);

					// Do Dijkstra from first vertex to last vertex
					@SuppressWarnings("unchecked")
					Vertex<Coder> [] vertexArray = new Vertex[graphVertexCount];
					graph.getVertices().toArray(vertexArray);
					Vertex<Coder> from = vertexArray[0];
					Vertex<Coder> to = vertexArray[vertexArray.length/2];
					start = System.currentTimeMillis();
					Graph<Coder>.DijkstraResult<Coder> result = graph.shortestPathDijkstra(from,  to);
					end = System.currentTimeMillis();
					duration = end - start;
					System.out.format(" Step 6/6: Dijkstra search on %d vertices, it took %d ms%n", visitedCount, duration);
					System.out.format(result.toString());
					System.out.println("\n");
					writer.append(Long.toString(duration));
					writer.append(separator);
					writer.append(testFiles[currentIndex]);
					writer.newLine();
					writer.flush();
					currentIndex++;
				}
				System.out.format("<== Test finished, see file %s for statistics!", outputFileName);
			} catch (OutOfMemoryError oom) {
				System.out.println("\n*** ERROR: Run out of memory while handling test json files\n");
				System.out.println("*** If testing from IDE; try to run the test from command line:");
				System.out.println("*** mvn -Dtest=oy.interact.tira.task_09_graphs.BFSPerformanceTests test");
				System.out.format("<== Test finished, see file %s for statistics!%n", outputFileName);
				fail("Not enough memory to conduct the test");
			} catch (IOException e) {
				e.printStackTrace();
				fail("Could not write test output file: " + e.getMessage());
			} catch (Exception ex1) {
				ex1.printStackTrace();
				fail("Could not execute test because: " + ex1.getMessage());
			}
		} else {
			fail("Cannot run tests since opening output file writer failed.");
		}
	}

	@AfterAll
	static void closeOutputFile() {
		try {
			writer.close();
		} catch (IOException e) {
			fail("Could not close test output file");
		}
	}

	public Graph<Coder> createGraph(Coder[] coders) throws Exception {
		Graph<Coder> graph = null;
		if (coders.length > 0) {
			graph = new Graph<>();
			for (Coder coder : coders) {
				graph.createVertexFor(coder);
			}
			for (Coder coder : coders) {
				Vertex<Coder> coderVertex = graph.getVertexFor(coder);
				Set<String> friendIDs = coder.getFriendIDs();
				if (null != friendIDs && !friendIDs.isEmpty()) {
					for (String friendID : friendIDs) {
						Vertex<Coder> friend = graph.getVertexFor(new Coder(friendID));
						graph.addDirectedEdge(coderVertex, friend, 1.0);
					}
				}
			}
		}
		return graph;
	}

	private Coder[] readCodersFromFile(String fileName)
			throws IOException {
		long start = System.currentTimeMillis();
		JSONTokener tokener = new JSONTokener(
				new BufferedReader(new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8)));
		JSONArray array = new JSONArray(tokener);
		System.out.format(" Step 1/6: JSON Parsing - from file %s to JSONArray it took %d ms%n", fileName,
				System.currentTimeMillis() - start);
		start = System.currentTimeMillis();
		Coder[] coders = JSONConverter.codersFromJSONArray(array);
		System.out.format(" Step 2/6: From JSONArray to Coders array it took %d ms%n",
				System.currentTimeMillis() - start);
		start = System.currentTimeMillis();
		return coders;
	}

}
