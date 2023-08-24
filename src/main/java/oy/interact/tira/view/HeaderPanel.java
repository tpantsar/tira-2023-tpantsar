package oy.interact.tira.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.net.URL;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import oy.interact.tira.TIRACodersApp;
import oy.interact.tira.model.PhoneBookModelObserver;

public class HeaderPanel extends JPanel implements PhoneBookModelObserver {
	
	private JLabel subTitle;

	public HeaderPanel() {
		super(new FlowLayout(FlowLayout.LEFT));
		TIRACodersApp.getModel().addObserver(this);
		setBackground(TIRACodersApp.CODER_APP_COLOR);
		JPanel textPanel = new JPanel();
		textPanel.setBackground(TIRACodersApp.CODER_APP_COLOR);
		textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
		JLabel titleLabel = new JLabel("TIRA Coders");
		titleLabel.setFont(new Font("Serif", Font.BOLD, 36));
		titleLabel.setForeground(Color.WHITE);
		textPanel.add(titleLabel);
		subTitle = new JLabel(String.format("All %,d Coders of the World, Unite!", TIRACodersApp.getModel().size()));
		subTitle.setFont(new Font("Serif", Font.BOLD, 26));
		subTitle.setForeground(Color.LIGHT_GRAY);
		textPanel.add(subTitle);
		URL url = ClassLoader.getSystemResource("images/coder-tools-001.png");
		ImageIcon logoIcon = new ImageIcon(url);
		add(new JLabel(logoIcon));
		add(textPanel);
	}

	@Override
	public void modelChanged() {
		subTitle.setText(String.format("All %,d Coders of the World, Unite! Phonebook: %s", TIRACodersApp.getModel().size(), TIRACodersApp.getModel().getCurrentFileName()));
	}

	@Override
	public void selectionChanged(int item) {
		// Nada
	}
}
