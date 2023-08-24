package oy.interact.tira.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import oy.interact.tira.util.Pair;

public class TopWordsFrame extends JFrame {

	private JTable topWordsTable;
	private String [] columnNames = {"Position", "Word", "Word count"};

	public TopWordsFrame(File selectedFile, Pair<String, Integer>[] topWords) {
		super(String.format("Top words in %s", selectedFile.getName()));
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		Container contentPanel = getContentPane();
		JPanel mainPanel = new JPanel(new BorderLayout()); // GridLayout(2, 1)

		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		TableModel dataModel = new AbstractTableModel() {
			@Override
			public int getColumnCount() {
				return 3;
			}

			@Override
			public int getRowCount() {
				return topWords.length;
			}

			@Override
			public Object getValueAt(int row, int col) {
				switch (col) {
					case 0:
						return String.format("%4d.", row + 1);
					case 1:
						return topWords[row].getKey();
					case 2:
						return topWords[row].getValue();
					default:
						return null;
				}
			}

			@Override
			public String getColumnName(int col) {
				return columnNames[col];
			}
		};
		topWordsTable = new JTable(dataModel);
		mainPanel.add(new JScrollPane(topWordsTable));
		contentPanel.add(mainPanel);
		pack();
		setVisible(true);
	}

}
