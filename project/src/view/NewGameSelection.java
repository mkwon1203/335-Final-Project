package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

public class NewGameSelection extends JPanel {
	private JPanel upPanel;
	private JPanel lowPanel;
	private JLabel preview;
	private JList<String> scenarioList;
	private JList<String> levelList;
	private JButton back;
	private JButton next;

	public NewGameSelection() {

		setLayout(new BorderLayout());

		upPanel = new JPanel();
		lowPanel = new JPanel();

		GridLayout upGrid = new GridLayout(2, 2);// use the grid layout for up
													// panel
		upPanel.setLayout(upGrid);// set the layout for up panel

		back = new JButton("back");
		next = new JButton("next");

		lowPanel.add(back);
		lowPanel.add(next);

	}
}
