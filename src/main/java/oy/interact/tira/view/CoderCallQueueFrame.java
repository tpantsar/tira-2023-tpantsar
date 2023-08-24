package oy.interact.tira.view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import oy.interact.tira.model.Coder;
import oy.interact.tira.util.QueueInterface;

public class CoderCallQueueFrame extends JFrame implements ActionListener {
	
	private transient QueueInterface<Coder> queue;
	private String theLanguage;
	private transient Coder currentCoder;

	private JLabel infoLabel;
	private JLabel nextCoderToCall;
	private JLabel currentCoderToCall;
	private JLabel currentCoderPhone;
	private JButton nextCall;
	
	public CoderCallQueueFrame(QueueInterface<Coder> queue, String language) {
		super("TIRA Coders Call Queue");
		this.queue = queue;
		this.theLanguage = language;
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		Container container = getContentPane();
		JPanel mainPanel = new JPanel(new GridLayout(0, 1));
		this.setMinimumSize(new Dimension(450,300));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
		infoLabel = new JLabel("About call queue");
		infoLabel.setFont(new Font("Serif", Font.BOLD, 16));
		mainPanel.add(infoLabel);
		currentCoderToCall = new JLabel("Current coder name");
		currentCoderToCall.setFont(new Font("Serif", Font.PLAIN, 16));
		mainPanel.add(currentCoderToCall);
		currentCoderPhone = new JLabel("Current coder phone");
		mainPanel.add(currentCoderPhone);
		nextCoderToCall = new JLabel("Next coder name");
		nextCoderToCall.setFont(new Font("Serif", Font.ITALIC, 16));
		mainPanel.add(nextCoderToCall);
		nextCall = new JButton("Next coder (dequeue)");
		mainPanel.add(nextCall);
		nextCall.addActionListener(this);
		container.add(mainPanel);
		pack();
		setVisible(true);
		updateView();
	}

	private void updateView() {
		infoLabel.setText(String.format("Call queue for language %s has %d coders left", theLanguage, queue.size()));
		if (null != currentCoder) {
			String text = String.format("Currently on call: %s alias %s", currentCoder.getFullName(), currentCoder.getCoderName());
			currentCoderToCall.setText(text);
			currentCoderPhone.setText(currentCoder.getPhoneNumber());
		} else {
			currentCoderToCall.setText("No coder in call currently");
			currentCoderPhone.setText("");
		}
		if (!queue.isEmpty()) {
			Coder nextCoder = queue.element();
			String text = String.format("Next on queue: %s alias %s", nextCoder.getFullName(), nextCoder.getCoderName());
			nextCoderToCall.setText(text);
		} else {
			nextCoderToCall.setText("No more coders in queue");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (queue.size() > 0) {
			currentCoder = queue.dequeue();
		} else {
			currentCoder = null;
		}
		if (queue.isEmpty()) {
			nextCall.setEnabled(false);
		}
		updateView();
	}

}
