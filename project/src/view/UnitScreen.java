package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import model.Archer;
import model.CharacterInterface;
import model.Knight;
import controller.Client;

public class UnitScreen extends JFrame{
	private DefaultListModel<String> model = new DefaultListModel<String>();
	private DefaultListModel<String> teamModel = new DefaultListModel<String>();
	private static UnitScreen screen = null;
	private JPanel topPanel = new JPanel();
	private JPanel bottomPanel = new JPanel();
	private JButton addToTeam, removeFromTeam, backButton, playButton;
	private JList listUnits, listTeam;
	private JLabel unitPreview;
	private JScrollPane teamList;
	private int characterState;
	private BufferedImage previewImage;
	private String playerDescription;
	
	public UnitScreen(){
		if (Client.GAMESTATE == 4){
			makeGUI();
			registerListeners();
			this.pack();
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			this.setSize(800,600);
			this.setBackground(Color.BLACK);
			this.setVisible(true);
		}
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
		//setBackground(Color.BLACK);
		topPanel.setLayout(new GridLayout(0, 2));
		topPanel.setSize(800,600);
		
		// adding the characters to default model list to insert in  listUnits list
		model.addElement("Archer");
		model.addElement("Knight");
		model.addElement("Mage");
		model.addElement("Priest");
		model.addElement("Spearman");
	
		
		JLabel units = new JLabel("Units");
		// creating a unitPreview
		unitPreview = new JLabel(new ImageIcon(System.getProperty("user.unitScreen") + 
				"/Archer.png"));
		//unitPreview.setIcon((Icon) loadPreviewImage());
		
		listUnits = new JList(model);
		addToTeam = new JButton("Add To Team");

		JLabel description = new JLabel("Description");
		playerDescription = getPlayerDescription();
		JLabel descriptionText = new JLabel(playerDescription);
		
		// adding to the topPanel
		topPanel.add(units);
		topPanel.add(unitPreview);
		topPanel.add(listUnits);
		topPanel.add(description);
		topPanel.add(addToTeam);
		topPanel.add(descriptionText);
		
		
		listUnits.setVisibleRowCount(5);
		listUnits.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//changing characterState depending on what is selected in listUnits
		if (listUnits.getSelectedValue() == "Archer"){
			characterState = 1;
		}
		else if (listUnits.getSelectedValue() == "Knight"){
			characterState = 2;		
		}
		else if (listUnits.getSelectedValue() == "Mage"){
			characterState = 3;
		}
		else if (listUnits.getSelectedValue() == "Priest"){
			characterState = 4;
		}
		else if (listUnits.getSelectedValue() == "Spearman"){
			characterState = 5;
		}
		
		
		
	}
	
	// will get the unit preview Image
//	private void loadPreviewImage(){
//		try{
//			previewImage = ImageIO.read(new File("res/unitScreen/Archer.png"));
//		}catch(IOException e){System.out.println("Couldn't find title picture.");}
//	}

	// will get the player description depending on the character that is selected
	private String getPlayerDescription() {
		String result = "test";
		if (listUnits.getSelectedValue() != null){
			if (listUnits.getSelectedValue().equals("Archer")){
				return "Archer";
				//this.repaint();
			}
			//playerDescription = model.Archer.getDescription();
		}
		return result;
	}


	private BufferedImage loadPreviewImage() {
			try{
				previewImage =  ImageIO.read(new File("unitScreen/Archer.png"));
			}catch(IOException e){System.out.println("Couldn't find title picture.");}
		return previewImage;
	}


	private void createBottomPanel() {
		

		bottomPanel.setLayout(new GridLayout(0, 2));
		bottomPanel.setSize(800,600);
		
		JLabel team = new JLabel("Team");
		listTeam = new JList(teamModel);
		backButton = new JButton("Back");
		removeFromTeam = new JButton("Remove from Team");
		playButton = new JButton("Play");
		JLabel empty = new JLabel();
		teamList = new JScrollPane(listTeam);
		
		bottomPanel.add(team);
		bottomPanel.add(removeFromTeam);
		bottomPanel.add(teamList);
		bottomPanel.add(empty);
		bottomPanel.add(backButton);
		bottomPanel.add(playButton);
		
		listTeam.setVisibleRowCount(5);
		listTeam.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
	private void registerListeners() {
		ActionListener listen = new ButtonListener();
		addToTeam.addActionListener(listen);
		removeFromTeam.addActionListener(listen);
		backButton.addActionListener(listen);
		playButton.addActionListener(listen);
	}
	
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == addToTeam){
				if (teamModel.size() < 5)
					teamModel.addElement((String) listUnits.getSelectedValue());
			}
			else if (e.getSource() == removeFromTeam){
				teamModel.removeElement(listTeam.getSelectedValue());
			}
			else if (e.getSource() == backButton){ // go back to previous screen
				Client.GAMESTATE-= 1;
			}
			else if (e.getSource() == playButton){ // start the game!
				Client.GAMESTATE = 1;
			}
		}
		
	}
	
	public static void main(String[] arg){
		Client.GAMESTATE = 4;
		new UnitScreen();
	}
	
	//This method can be called to get the unit screen object
	public static UnitScreen getUnitScreen(){
			if(screen == null)
				screen = new UnitScreen();
		return screen;
	}
}
