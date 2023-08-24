package oy.interact.tira.view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import oy.interact.tira.model.Coder;
import oy.interact.tira.student.graph.Graph;
import oy.interact.tira.student.graph.Vertex;
import oy.interact.tira.student.graph.Edge.EdgeType;

public class CoderGraphFrame extends JFrame implements ActionListener {

	private transient Graph<Coder> graph;
	private DefaultComboBoxModel<Coder> comboModelFirst;
	private DefaultComboBoxModel<Coder> comboModelSecond;
	private JComboBox<Coder> firstCoderCombo;
	private JComboBox<Coder> secondCoderCombo;
	private Button searchButton;
	private Button graphPropertiesButton;
	private Button toDotFileButton;
	private transient Coder firstCoder;
	private transient Coder secondCoder;
	private JTextArea resultArea;

	public CoderGraphFrame(Graph<Coder> graph, Coder [] coders) {
		super("Find Shortest Path Between Coders");
		this.graph = graph;
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		Container contentPanel = getContentPane();
		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		comboModelFirst = new DefaultComboBoxModel<>(coders);
		comboModelSecond = new DefaultComboBoxModel<>(coders);
		mainPanel.add(new JLabel("First Coder:"));
		firstCoderCombo = new JComboBox<>(comboModelFirst);
		firstCoderCombo.setActionCommand("first-coder");
		firstCoderCombo.setSelectedIndex(0);
		firstCoderCombo.addActionListener(this);
		firstCoder = coders[0];
		mainPanel.add(firstCoderCombo);

		mainPanel.add(new JLabel("Second Coder:"));
		secondCoderCombo = new JComboBox<>(comboModelSecond);
		secondCoderCombo.setActionCommand("second-coder");
		secondCoderCombo.setSelectedIndex(0);
		secondCoderCombo.addActionListener(this);
		secondCoder = coders[0];
		mainPanel.add(secondCoderCombo);

		searchButton = new Button("Search Path");
		searchButton.addActionListener(this);
		searchButton.setActionCommand("search");
		searchButton.setEnabled(false); // since first items always selected first
		mainPanel.add(searchButton);

		graphPropertiesButton = new Button("Graph Properties");
		graphPropertiesButton.addActionListener(this);
		graphPropertiesButton.setActionCommand("graph-properties");
		mainPanel.add(graphPropertiesButton);

		toDotFileButton = new Button("Export to dot file");
		toDotFileButton.addActionListener(this);
		toDotFileButton.setActionCommand("to-dot-file");
		mainPanel.add(toDotFileButton);

		resultArea = new JTextArea(30, 50);
		resultArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(resultArea);
		mainPanel.add(scrollPane, BorderLayout.SOUTH);

		contentPanel.add(mainPanel);
		pack();
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("first-coder")) {
			firstCoder = (Coder)firstCoderCombo.getSelectedItem();
		} else if (e.getActionCommand().equals("second-coder")) {
			secondCoder = (Coder)secondCoderCombo.getSelectedItem();
		} else if (e.getActionCommand().equals("search")) {
			long start = System.currentTimeMillis();
			Vertex<Coder> from = graph.getVertexFor(firstCoder);
			Vertex<Coder> to = graph.getVertexFor(secondCoder);
			Graph<Coder>.DijkstraResult<Coder> result = graph.shortestPathDijkstra(from, to);
			System.out.format("[measurement] Finding shortest path with Dijkstra took %d ms%n", (System.currentTimeMillis() - start));
			StringBuilder resultBuilder = new StringBuilder();
			resultBuilder.append("\nSearched for a shortest path from ");
			resultBuilder.append(firstCoder.getFullName());
			resultBuilder.append(" to ");
			resultBuilder.append(secondCoder.getFullName());
			resultBuilder.append("\n");
			resultBuilder.append("The path was ");
			if (result.pathFound) {
				resultBuilder.append("found!\n");
				resultBuilder.append(String.format("Path has %d steps. ", result.steps));
				resultBuilder.append("Path is the following:\n\n");
				int counter = 0;
				for (Coder coder : result.path) {
					if (counter > 0) {
						resultBuilder.append(" -> ");
					}
					resultBuilder.append(String.format("%s (%s) %n", coder.getFullName(), coder.getId()));
					counter++;
				}
			} else {
				resultBuilder.append("NOT found!\n");
			}
			resultArea.append(resultBuilder.toString());
		} else if (e.getActionCommand().equals("graph-properties")) {
			Vertex<Coder> from = graph.getVertexFor(firstCoder);
			boolean hasCycles = graph.hasCycles(EdgeType.DIRECTED, from);
			// if notVisited has things, then this is disconnected graph
			List<Coder> notVisited = graph.disconnectedVertices(from);
			StringBuilder builder = new StringBuilder("\nCoder graph");
			builder.append(" is");
			if (!notVisited.isEmpty()) {
				builder.append(" not connected, when starting from ");
				builder.append(from.toString());
				builder.append(", but contains separate components.\n");
				int count = Math.min(10, notVisited.size());
				builder.append("Not connected vertices (first 10 or less):\n");
				for (int index = 0; index < count; index++) {
					builder.append(String.format(" %3d. %s%n", index + 1, notVisited.get(index)));
				}
				builder.append("\nTry searching a path from first coder in the left list, to ones listed above: a path is not found then.\n");
			} else {
				builder.append(" connected, when starting from node ");
				builder.append(from.toString());
				builder.append("\n");
			}
			if (hasCycles) {
				builder.append("Coder graph has cycles.\n\n");
			} else {
				builder.append("Coder graph does not have cycles (not reliable if graph is disconnected)\n\n");
			}
			resultArea.append(builder.toString());
		} else if (e.getActionCommand().equals("to-dot-file")) {
			Vertex<Coder> from = graph.getVertexFor(firstCoder);
			try {
				graph.toDotBFS(from, "coder-graph.dot.txt");
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(this, "Something wrong with writing the dot file: " + e1.getMessage(),
				"Could not write to file",
				JOptionPane.ERROR_MESSAGE);
			}
		}
		if (firstCoder.equals(secondCoder)) {
			searchButton.setEnabled(false);
		} else {
			searchButton.setEnabled(true);
		}
	}

}
