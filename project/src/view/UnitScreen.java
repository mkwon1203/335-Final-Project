package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
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
	private JPanel panel;
	private String selected;
	
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
		//createTopPanel();
		createPanel();
		//createBottomPanel();
		//setBackground(Color.BLACK);
		//createList();
		setLayoutAndAddComponentsToFrame();
	}


	private void createPanel() {
		panel = new JPanel();
		panel.setSize(800, 600);
		panel.setLayout(null);

		
		// adding the characters to default model list to insert in  listUnits list
		model.addElement("Archer");
		model.addElement("Knight");
		model.addElement("Mage");
		model.addElement("Priest");
		model.addElement("Spearman");
	
		
		JLabel units = new JLabel("Units");
	
		//unitPreview.setPreferredSize(new Dimension(100,100));
		
		listUnits = new JList(model);
		addToTeam = new JButton("Add To Team");
		
		listUnits.setVisibleRowCount(5);
		listUnits.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		if (listUnits.getSelectedValue() != null){
			selected = listUnits.getSelectedValue().toString();
			System.out.println(selected);
		}
		
		JLabel description = new JLabel("Description");
		playerDescription = getPlayerDescription();
		JLabel descriptionText = new JLabel(playerDescription);
		
		
		
		// creating a unitPreview and scaling
		loadPreviewImage();
		previewImage = ScaleImage();
		
		//adding preview image to the JLabel
		unitPreview = new JLabel(new ImageIcon(previewImage));

		
		
		//TODO: do junit test for non trivial methods (attack/ movement /gui) and finish this gui
		
		JLabel team = new JLabel("Team");
		listTeam = new JList(teamModel);
		listTeam.setFont(new Font("Arial", Font.PLAIN, 20));
		backButton = new JButton("Back");
		removeFromTeam = new JButton("Remove from Team");
		playButton = new JButton("Play");
		JLabel empty = new JLabel();
		teamList = new JScrollPane(listTeam);
		
		// setting fonts for jlists and labels
		units.setFont(new Font("Ariel", Font.BOLD,  25)); 
		listUnits.setFont(new Font("Arial", Font.PLAIN, 20));
		team.setFont(new Font("Ariel", Font.BOLD,  25)); 
		//addToTeam.setFont(new Font("", Font.PLAIN, 20));
		//		teamList.setFont(new Font("Arial", Font.PLAIN, 20));
		
		listTeam.setVisibleRowCount(5);
		listTeam.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//add to the null layout
		panel.add(units);
		panel.add(listUnits);
		panel.add(team);
		panel.add(teamList);
		panel.add(addToTeam);
		panel.add(removeFromTeam);
		panel.add(unitPreview);
		panel.add(descriptionText);
		panel.add(backButton);
		panel.add(playButton);
		
		// setting location of components
		listUnits.setBounds(50, 70, 100, 135);
		units.setBounds(50, 0, 100, 100);
		team.setBounds(600, 0, 100, 100);
		teamList.setBounds(600, 70, 100, 135);
		addToTeam.setBounds(275, 70, 150, 30);
		removeFromTeam.setBounds(275, 100, 150, 30);
		unitPreview.setBounds(275, 130, 200, 300);
		descriptionText.setBounds(275, 375, 100, 100);
		playButton.setBounds(600, 500, 150, 30);
		backButton.setBounds(50, 500, 150, 30);
		
	}


	private void setLayoutAndAddComponentsToFrame() {
		this.setLayout(null);
		this.add(panel);
		this.setResizable(false);
	}

//		
//		
//		//changing characterState depending on what is selected in listUnits
//		if (listUnits.getSelectedValue() == "Archer"){
//			characterState = 1;
//		}
//		else if (listUnits.getSelectedValue() == "Knight"){
//			characterState = 2;		
//		}
//		else if (listUnits.getSelectedValue() == "Mage"){
//			characterState = 3;
//		}
//		else if (listUnits.getSelectedValue() == "Priest"){
//			characterState = 4;
//		}
//		else if (listUnits.getSelectedValue() == "Spearman"){
//			characterState = 5;
//		}

	
	public BufferedImage ScaleImage(){
		BufferedImage scaledImage = new BufferedImage(previewImage.getWidth(), previewImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
	    AffineTransform at = new AffineTransform();
	    at.scale(0.9, 0.9);
	    AffineTransformOp scale = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
	    scaledImage = scale.filter(previewImage, scaledImage);
	    return scaledImage;
	}
	
	
	// will get the unit preview Image
	private void loadPreviewImage(){
		try{
			previewImage = ImageIO.read(new File("res/unitScreen/Archer.png"));
		}catch(IOException e){System.out.println("Couldn't find title picture.");}
	}

	// will get the player description depending on the character that is selected
	private String getPlayerDescription() {
		getChatacterState();
		String result = "test";
		if (characterState == 0){
			result = "Archer";
		}
		if (characterState == 1){
			result = "Knight";
		}
		if (characterState == 2){
			result = "Mage";
		}
		if (characterState == 3){
			result = "Priest";
		}
		if (characterState == 4){
			result = "Spearman";
		}
			//playerDescription = model.Archer.getDescription();
		return result;
	}

	
	private void getChatacterState() {
		//changing characterState depending on what is selected in listUnits
		if (listUnits.getSelectedIndex() == 0){
			characterState = 1;
			System.out.println("characterState is: " + characterState);
		}
		else if (listUnits.getSelectedIndex() == 1){
			characterState = 2;		
		}
		else if (listUnits.getSelectedIndex() == 2){
			characterState = 3;
		}
		else if (listUnits.getSelectedIndex() == 3){
			characterState = 4;
		}
		else if (listUnits.getSelectedIndex() == 4){
			characterState = 5;
		}
		
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
				dispose();
			}
			else if (e.getSource() == playButton){ // start the game!
				Client.GAMESTATE = 1;
				dispose();
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
