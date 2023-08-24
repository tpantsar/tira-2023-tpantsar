package oy.interact.tira.view;

import java.awt.Font;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import oy.interact.tira.NotYetImplementedException;
import oy.interact.tira.TIRACodersApp;
import oy.interact.tira.model.Coder;

public class CoderDetailPanel extends JPanel implements ListSelectionListener {

	private transient Coder coder;
	private JLabel firstName;
	private JLabel lastName;
	private JLabel coderName;
	private JLabel phoneNumber;
	private JList<String> languages;
	private JList<String> friends;
	private String[] languageListData = { "" };
	private String[] friendsListData = { "" };

	public CoderDetailPanel() {
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setAutoCreateGaps(true);
		groupLayout.setAutoCreateContainerGaps(true);
		setLayout(groupLayout);

		// Static labels:
		JLabel titleLabel1 = new JLabel("TIRA Coder");
		titleLabel1.setFont(new Font("Serif", Font.BOLD, 16));
		// Ugly way to keep the detail view the same width, at least with default font
		// and zoom level.
		JLabel titleLabel2 = new JLabel("Details                           ");
		titleLabel2.setFont(new Font("Serif", Font.BOLD, 16));
		JLabel fnLabel = new JLabel("First name: ");
		JLabel lnLabel = new JLabel("Last name:");
		JLabel cnLabel = new JLabel("Coder name:");
		JLabel phLabel = new JLabel("Phone:");
		JLabel lgLabel = new JLabel("Languages:");
		JLabel frLabel = new JLabel("Friends:");

		// Content labels:
		firstName = new JLabel("");
		lastName = new JLabel("");
		coderName = new JLabel("");
		phoneNumber = new JLabel("");
		languages = new JList<>(languageListData);
		languages.setVisibleRowCount(3);
		friends = new JList<>(friendsListData);
		friends.setVisibleRowCount(3);

		groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
						.addComponent(titleLabel1)
						.addComponent(fnLabel)
						.addComponent(cnLabel)
						.addComponent(lnLabel)
						.addComponent(phLabel)
						.addComponent(lgLabel)
						.addComponent(frLabel))
				.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(titleLabel2)
						.addComponent(firstName)
						.addComponent(coderName)
						.addComponent(lastName)
						.addComponent(phoneNumber)
						.addComponent(languages)
						.addComponent(friends)));

		groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(titleLabel1)
						.addComponent(titleLabel2))
				.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(fnLabel)
						.addComponent(firstName))
				.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(cnLabel)
						.addComponent(coderName))
				.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(lnLabel)
						.addComponent(lastName))
				.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(phLabel)
						.addComponent(phoneNumber))
				.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(lgLabel)
						.addComponent(languages))
				.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(frLabel)
						.addComponent(friends)));
	}

	void setCoder(Coder coder) {
		if (this.coder != coder) {
			this.coder = coder;
			updateView();
		}
	}

	private void updateView() {
		try {
			if (coder != null) {
				firstName.setText(coder.getFirstName());
				lastName.setText(coder.getLastName());
				coderName.setText(coder.getCoderName());
				phoneNumber.setText(coder.getPhoneNumber());
				Collection<String> langs = coder.getLanguages();
				int index = 0;
				if (null != langs) {
					languageListData = new String[langs.size()];
					index = 0;
					for (String lang : langs) {
						languageListData[index++] = lang;
					}
					languages.setListData(languageListData);
				} else {
					languages.setListData(new String[] { "" });
				}
				Collection<String> frnds = coder.getFriendIDs();
				if (null != frnds) {
					String[] ids = new String[frnds.size()];
					index = 0;
					for (String id : frnds) {
						ids[index++] = id;
					}
					try {
						friendsListData = TIRACodersApp.getModel().getFriendNames(ids);
						friends.setListData(friendsListData);
					} catch (NotYetImplementedException ex) {
						friends.setListData(new String[] { "Implement in 02-TASK" });
					}
				} else {
					friends.setListData(new String[] { "" });
				}
			} else {
				firstName.setText("");
				lastName.setText("");
				coderName.setText("");
				phoneNumber.setText("");
				languageListData = new String[1];
				languageListData[0] = "";
				languages.setListData(languageListData);
				friendsListData = new String[1];
				friendsListData[0] = "";
				friends.setListData(friendsListData);
			}
		} catch (NotYetImplementedException ex) {
			JOptionPane.showMessageDialog(this, "Not yet implemented: " + ex.getMessage(),
					"Do not try this yet",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		ListSelectionModel lsm = (ListSelectionModel) e.getSource();
		int selected = -1;
		if (!lsm.isSelectionEmpty()) {
			// Find out which indexes are selected.
			int minIndex = lsm.getMinSelectionIndex();
			int maxIndex = lsm.getMaxSelectionIndex();
			for (int i = minIndex; i <= maxIndex; i++) {
				if (lsm.isSelectedIndex(i)) {
					selected = i;
				}
			}
		}
		if (selected >= 0 && !e.getValueIsAdjusting()) {
			setCoder(TIRACodersApp.getModel().getCoder(selected, true));
		}
	}

}
