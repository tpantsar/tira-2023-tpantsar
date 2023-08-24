package oy.interact.tira.view;

import java.awt.Container;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import oy.interact.tira.TIRACodersApp;
import oy.interact.tira.model.DurationMeasurement;
import oy.interact.tira.model.MeasurementsObserver;

public class LogFrame extends JFrame implements MeasurementsObserver {

	private JTextArea log;
	private static final String NEWLINE = "\n";

	public LogFrame() {
		super("TIRA Coders Log View");
		Container contentPanel = getContentPane();
		JPanel mainPanel = new JPanel(); 
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		final JButton clearButton = new JButton("Clear");
		clearButton.addActionListener( event -> log.setText("") );
		mainPanel.add(clearButton);
		log = new JTextArea(50, 50);
		log.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(log);
		mainPanel.add(scrollPane);
		contentPanel.add(mainPanel);
		TIRACodersApp.getModel().setMeasurementsObserver(this);
		pack();
	}

	@Override
	public void newMeasurement(DurationMeasurement measurement) {
		StringBuilder builder = new StringBuilder();
		if (null != measurement) {
			builder.append(measurement.what);
			builder.append(" took ");
			builder.append(measurement.duration);
			builder.append(" ms");
			builder.append(NEWLINE);
			log.append(builder.toString());
		}
	}
}
