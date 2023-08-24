package oy.interact.tira.view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import oy.interact.tira.NotYetImplementedException;
import oy.interact.tira.TIRACodersApp;

public class SearchPanel extends JPanel implements ActionListener {

	private boolean doSearchByKeyOnly = false;
	private JTextField searchField; 

	public SearchPanel() {
		super();
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		JLabel label = new JLabel("Search: ");
		searchField = new JTextField();
		searchField.addActionListener(this);		
		searchField.setPreferredSize(new Dimension(200, 20));
		searchField.setToolTipText("Write the search string and hit enter.");
		final JButton clearButton = new JButton("Clear");
		clearButton.addActionListener(event -> searchField.setText(""));
		clearButton.setEnabled(false);
		add(label);
		add(searchField);
		JCheckBox exactSearch = new JCheckBox("Exact (last first)", doSearchByKeyOnly);
		exactSearch.setToolTipText("Searches by Lastname First Name");
		exactSearch.setMnemonic(KeyEvent.VK_F);
		exactSearch.setSelected(doSearchByKeyOnly);
		exactSearch.addItemListener( event -> doSearchByKeyOnly = !doSearchByKeyOnly);
		add(exactSearch);
		add(clearButton);
		DocumentListener dl = new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				updateFieldState();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				updateFieldState();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				updateFieldState();
			}
			protected void updateFieldState() {
				clearButton.setEnabled(searchField.getText().length() > 0);
			}
		};
		searchField.getDocument().addDocumentListener(dl);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			String searchText = searchField.getText().trim();
			if (!doSearchByKeyOnly) {
				searchText = searchText.toLowerCase();
			}
			if (searchText.length() > 0) {
				TIRACodersApp.getModel().indexOfItem(searchText, doSearchByKeyOnly);
			}
		} catch (NotYetImplementedException ex) {
			JOptionPane.showMessageDialog(this, "Not yet implemented: " + ex.getMessage(),
					"Do not try this yet",
					JOptionPane.ERROR_MESSAGE);
		}
	}


}
