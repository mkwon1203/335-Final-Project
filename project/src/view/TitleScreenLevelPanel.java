package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import controller.Client;

public class TitleScreenLevelPanel extends JFrame {
	private JPanel upPanel;
	private JPanel lowPanel;
	private JLabel preview;
	private JList<File> levelList;
	private JButton back;
	private JButton next;
	private static TitleScreenLevelPanel gameSelect;

	/**
	 * This the constructor for game selection class
	 * 
	 * @param
	 * 
	 */

	public TitleScreenLevelPanel() {

		setLayout(new BorderLayout());
		setSize(800, 600);

		createLowPanel();
		createUpPanel();
		add(createLowPanel(), BorderLayout.SOUTH);
		add(createUpPanel(), BorderLayout.NORTH);

		pack();
		setVisible(true);

	}

	/**
	 * create the lower level panel
	 * 
	 * @return lowPanel the whole unit of the lower level panel
	 */
	private JPanel createLowPanel() {

		lowPanel = new JPanel();

		back = new JButton("back");
		back.setPreferredSize(new Dimension(200, 50));

		next = new JButton("next");
		next.setPreferredSize(new Dimension(200, 50));

		lowPanel.add(back);
		lowPanel.add(next);

		return lowPanel;

	}

	/**
	 * Make a list to display the level
	 * 
	 * @return levelList The list of the level
	 * 
	 */

	private JList<File> createLevelList() {

		File folder = new File("res/levels");
		File[] fileList = folder.listFiles();

		levelList = new JList<File>(fileList);

		return levelList;
	}

	/**
	 * Create the up panel of the game selection
	 * 
	 * @return upPanel
	 */
	private JPanel createUpPanel() {

		upPanel = new JPanel();
		upPanel.setLayout(new GridLayout(2, 2));
		preview = new JLabel("res/selectionscreen/selectpreview");

		upPanel.add(preview);

		createLevelList();// create the jlist
		upPanel.add(levelList);// add the list to the up panel

		return upPanel;
	}

	/**
	 * Main class of the game select
	 * 
	 * @param arg
	 */

	public static void main(String[] arg) {
		Client.GAMESTATE = 3;
		new TitleScreenLevelPanel();

	}

	/**
	 * Access the game select object
	 * 
	 * @return
	 */

	public static TitleScreenLevelPanel getNewGameSelection() {

		gameSelect = new TitleScreenLevelPanel();

		return gameSelect;
	}

}
