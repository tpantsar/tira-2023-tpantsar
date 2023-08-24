package oy.interact.tira.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import oy.interact.tira.TIRACodersApp;
import oy.interact.tira.model.CoderSortOrder;

public class DataControlPanel extends JPanel implements ActionListener {
		
	private JComboBox<String> sortOrderCombo;

	public DataControlPanel() {
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		add(new SearchPanel());
		add(new JLabel("Sort order:"));
		sortOrderCombo = new JComboBox<>(CoderSortOrder.getNames());
		sortOrderCombo.setActionCommand("sort");
		sortOrderCombo.setSelectedIndex(0);
		sortOrderCombo.addActionListener(this);
		add(sortOrderCombo);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getActionCommand().equals("sort")) {
				if (sortOrderCombo == e.getSource()) {
					CoderSortOrder order = CoderSortOrder.getOrder((String)sortOrderCombo.getSelectedItem());
					TIRACodersApp.getModel().sort(order);
				}	
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(this, "Something wrong with the file: " + e1.getMessage(),
					"Could not open file",
					JOptionPane.ERROR_MESSAGE);
		}
	}

}
