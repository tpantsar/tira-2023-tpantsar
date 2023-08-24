package oy.interact.tira.view;

import java.awt.BorderLayout;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;

import oy.interact.tira.TIRACodersApp;
import oy.interact.tira.model.PhoneBookModelObserver;

public class ContentPanel extends JPanel implements PhoneBookModelObserver {
	
	private JTable programmerTable;
	
	private transient ListSelectionModel listSelectionModel;

	public ContentPanel(ListSelectionListener listener) {
		TIRACodersApp.getModel().addObserver(this);
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		ContentPanelModel dataModel = new ContentPanelModel();
		programmerTable = new JTable(dataModel);
		listSelectionModel = programmerTable.getSelectionModel();
		listSelectionModel.addListSelectionListener(listener);
		programmerTable.setSelectionModel(listSelectionModel);
		programmerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		dataModel.addTableModelListener(programmerTable);
		add(new JScrollPane(programmerTable));
	}

	@Override
	public void modelChanged() {
		// nada
	}

	@Override
	public void selectionChanged(int item) {
		if (item >= 0 && item < TIRACodersApp.getModel().size()) {
			programmerTable.setRowSelectionInterval(item, item);
			programmerTable.scrollRectToVisible(new Rectangle(programmerTable.getCellRect(item, 0, true)));
		} else {
			programmerTable.clearSelection();
		}
	}

}
