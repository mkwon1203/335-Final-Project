package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

public class UnitScreen extends JFrame{
	private DefaultListModel<String> model = new DefaultListModel<String>();
	private static UnitScreen screen = null;
	private JPanel topPanel = new JPanel();
	private JPanel bottomPanel = new JPanel();
	private JButton addToTeam, removeFromTeam, backButton, nextButton;
	
	public UnitScreen(){
		
		makeGUI();
		registerListeners();
		this.pack();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}


	public void makeGUI(){
		createTopPanel();
		createBottomPanel();
		setLayoutAndAddComponentsToFrame();
	}
	
	
	private void setLayoutAndAddComponentsToFrame() {
		this.setLayout(new BorderLayout());
		this.add(topPanel, BorderLayout.NORTH);
		this.add(bottomPanel, BorderLayout.SOUTH);
	}
	private void createTopPanel(){
		topPanel.setLayout(new GridLayout(0, 2));
		topPanel.setSize(400,600);
		
		model.addElement("Archer");
		model.addElement("Knight");
		model.addElement("Mage");
		model.addElement("Priest");
		model.addElement("Spearman");
	
		
		JLabel units = new JLabel("Units");
		JLabel unitPreview = new JLabel("Unit Preview");
		JList listUnits = new JList(model);
		addToTeam = new JButton("Add To Team");

		JLabel description = new JLabel("Description");
		JLabel descriptionText = new JLabel("Player Description will go here");
		
		// adding to the topPanel
		topPanel.add(units);
		topPanel.add(unitPreview);
		topPanel.add(listUnits);
		topPanel.add(description);
		topPanel.add(addToTeam);
		topPanel.add(descriptionText);
		
		
		listUnits.setVisibleRowCount(5);
		listUnits.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
	}
	
	private void createBottomPanel() {
		bottomPanel.setLayout(new GridLayout(0, 2));
		bottomPanel.setSize(400,600);
		
		JLabel team = new JLabel("Team");
		JList listTeam = new JList();
		backButton = new JButton("Back");
		removeFromTeam = new JButton("Remove from Team");
		nextButton = new JButton("Next");
		JLabel empty = new JLabel();
		
		bottomPanel.add(team);
		bottomPanel.add(removeFromTeam);
		bottomPanel.add(listTeam);
		bottomPanel.add(empty);
		bottomPanel.add(backButton);
		bottomPanel.add(nextButton);
		
	}
	
	private void registerListeners() {
		ActionListener listen = new ButtonListener();
		addToTeam.addActionListener(listen);
		removeFromTeam.addActionListener(listen);
		backButton.addActionListener(listen);
		nextButton.addActionListener(listen);
	}
	
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == addToTeam){
				
			}
			else if (e.getSource() == removeFromTeam){
				
			}
			else if (e.getSource() == backButton){
				
			}
			else if (e.getSource() == nextButton){
				
			}
		}
		
	}
// next-> play -> Client.gamestate = 1;
	
	public static void main(String[] arg){
		new UnitScreen();
	}
	
	//This method can be called to get the title screen object
	public static UnitScreen getUnitScreen(){
		if(screen == null)
			screen = new UnitScreen();
		return screen;
	}
}
