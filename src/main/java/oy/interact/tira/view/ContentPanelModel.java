package oy.interact.tira.view;

import javax.swing.table.AbstractTableModel;

import oy.interact.tira.model.PhoneBookModelObserver;
import oy.interact.tira.TIRACodersApp;
import oy.interact.tira.model.Coder;

public class ContentPanelModel extends AbstractTableModel implements PhoneBookModelObserver {

	final String[] columns = { "First name", "Coder name", "Last name", "Phone#", "Languages" };

	ContentPanelModel() {
		TIRACodersApp.getModel().addObserver(this);
	}

	@Override
	public int getRowCount() {
		return TIRACodersApp.getModel().size();
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public String getColumnName(int col) {
		return columns[col];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		final Coder coder = TIRACodersApp.getModel().getCoder(rowIndex);
		if (null == coder)
			return null;
		switch (columnIndex) {
			case 0:
				return coder.getFirstName();
			case 1:
				return coder.getCoderName();
			case 2:
				return coder.getLastName();
			case 3:
				return coder.getPhoneNumber();
			case 4:
				if (coder.hasLanguages()) {
					return coder.getLanguagesString();
				}
				return "No languages";
			default:
				return null;
		}
	}

	@Override
	public Class<?> getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	@Override
	public void modelChanged() {
		fireTableDataChanged();
	}

	@Override
	public void selectionChanged(int item) {
		// nada
	}

}
