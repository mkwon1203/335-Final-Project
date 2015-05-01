package view;

import model.Archer;
import model.Character;
import model.Mage;
import model.Priest;
import model.Spearman;

import java.awt.Color;
import java.awt.Font;
import java.util.List;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Knight;
import model.Spearman;
import controller.Client;

public class UnitScreen extends JFrame{
	private DefaultListModel<String> model = new DefaultListModel<String>();
	private DefaultListModel<String> teamModel = new DefaultListModel<String>();
	private static UnitScreen screen = null;
	private JButton addToTeam, removeFromTeam, backButton, playButton;
	private JList listUnits, listTeam;
	private JLabel unitPreview, units, team, descriptionText;
	private JScrollPane teamList;
	private BufferedImage previewImage;
	private String playerDescription;
	private JPanel panel;
	private String selected;
	private static List<Character> characterList  = new ArrayList<Character>();
	
	
	public UnitScreen(){
		if (Client.GAMESTATE == 4){
			makeGUI();
			registerListeners();
			this.pack();
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			this.setSize(800,612);
			this.setVisible(true);
		}
	}


	public void makeGUI(){
		createPanel();
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
	
		
		units = new JLabel("Units");
	
		//unitPreview.setPreferredSize(new Dimension(100,100));
		
		listUnits = new JList(model);
		addToTeam = new JButton("Add To Team");
		
		listUnits.setVisibleRowCount(5);
		listUnits.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		if (listUnits.getSelectedValue() != null){
			selected = listUnits.getSelectedValue().toString();
			System.out.println(selected);
		}
		
		descriptionText = new JLabel(playerDescription);
		
		// creating a unitPreview and scaling
		loadPreviewImage();
		previewImage = scaleImage();
		
		//adding preview image to the JLabel
		unitPreview = new JLabel(new ImageIcon(previewImage));
		team = new JLabel("Team");
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
		addToPanel();
		setLocationInPanel();
		
		
	}


	private void setLocationInPanel() {
		// setting location of components
		listUnits.setBounds(50, 70, 100, 135);
		units.setBounds(50, 0, 100, 100);
		team.setBounds(600, 0, 100, 100);
		teamList.setBounds(600, 70, 100, 135);
		addToTeam.setBounds(275, 70, 150, 30);
		removeFromTeam.setBounds(275, 100, 150, 30);
		unitPreview.setBounds(275, 130, 200, 300);
		
		descriptionText.setBounds(275, 400, 300, 100);
		playButton.setBounds(600, 500, 150, 30);
		backButton.setBounds(50, 500, 150, 30);
		
	}


	private void addToPanel() {
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
		
	}


	private void setLayoutAndAddComponentsToFrame() {
		this.setLayout(null);
		panel.setBackground(Color.BLUE);
		this.add(panel);
		this.setResizable(false);
	}
	
	public BufferedImage scaleImage(){
		BufferedImage scaledImage = new BufferedImage(previewImage.getWidth(), previewImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
	    AffineTransform at = new AffineTransform();
	    at.scale(0.9, 0.9);
	    AffineTransformOp scale = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
	    scaledImage = scale.filter(previewImage, scaledImage);
	    return scaledImage;
	}
	
	
	// will get the unit preview Image
	private void loadPreviewImage(){
		//if (characterState == 1){
			try{
				previewImage = ImageIO.read(new File("res/unitScreen/Archer.png"));
			}catch(IOException e){System.out.println("Couldn't character image.");}
	}


	private void registerListeners() {
		// button listeners
		ActionListener listen = new ButtonListener();
		addToTeam.addActionListener(listen);
		removeFromTeam.addActionListener(listen);
		backButton.addActionListener(listen);
		playButton.addActionListener(listen);
		
		// adding a listener to list selection
		ListSelectionModel listSelectionModel = listUnits.getSelectionModel();
		   listSelectionModel.addListSelectionListener(new listListener());
	}
	
	
	private class listListener implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (listUnits.getSelectedIndex() == 0){ // Unit Selected is the Archer
				descriptionText.setText("Archer"); // set Archer description
				// get Archer Image
				previewImage = getImages(0);
				unitPreview.setIcon(new ImageIcon(previewImage));
			}
			else if (listUnits.getSelectedIndex() == 1){ // Unit Selected is the Knight
				Knight knight = new Knight(null);
				descriptionText.setText(knight.getDescription()); // set Knight description
				// get Knight Image
				previewImage = getImages(1);
				unitPreview.setIcon(new ImageIcon(previewImage));
			}
			else if(listUnits.getSelectedIndex() == 2){ // Unit Selected is the Mage
				descriptionText.setText("Mage"); // set Mage description
				// get Mage Image
				previewImage = getImages(2);
				unitPreview.setIcon(new ImageIcon(previewImage));
			}
			else if (listUnits.getSelectedIndex() == 3){ // Unit Selected is the Priest
				descriptionText.setText("Priest"); // set Priest description
				// get Priest Image
				previewImage = getImages(3);
				unitPreview.setIcon(new ImageIcon(previewImage));
			}
			else if (listUnits.getSelectedIndex() == 4){ // Unit Selected is the Spearman
				//Spearman spearman = new Spearman(null);
				//descriptionText.setText(spearman.getDescription()); // get Spearman description
				descriptionText.setText("Spearman");
				//String s = model.Spearman.getDescription();
				//String s = spearman.getDescription();
				// get Spearman Image
				previewImage = getImages(4);
				unitPreview.setIcon(new ImageIcon(previewImage));
			}
		}

		
	}
	
	private BufferedImage getImages(int i) {
		if (i == 0){ //Archer
			try{
				previewImage = ImageIO.read(new File("res/unitScreen/Archer.png"));
			}catch(IOException e){System.out.println("Couldn't character image.");}
		}
		else if (i == 1){ // Knight
			try{
				previewImage = ImageIO.read(new File("res/unitScreen/Knight.png"));
			}catch(IOException e){System.out.println("Couldn't character image.");}
		}
		else if (i == 2){ //Mage
			try{
				previewImage = ImageIO.read(new File("res/unitScreen/Mage.png"));
			}catch(IOException e){System.out.println("Couldn't character image.");}
		}
		else if (i == 3){ // Priest
			try{
				previewImage = ImageIO.read(new File("res/unitScreen/Priest.png"));
			}catch(IOException e){System.out.println("Couldn't character image.");}
		}
		
		else if (i == 4){ //Spearman
			try{
				previewImage = ImageIO.read(new File("res/unitScreen/Spearman.png"));
			}catch(IOException e){System.out.println("Couldn't character image.");}
		}
		return previewImage;
	}
	
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == addToTeam){ // adding to team list
				if (teamModel.size() < 5){
					teamModel.addElement((String) listUnits.getSelectedValue());
					//if (listUnits.getSelectedIndex() == 0)
						//characterList.add(new Archer(new Point(0,0)));
					 if (listUnits.getSelectedIndex() == 1)
						characterList.add(new Knight(new Point(0,0)));
					//else if (listUnits.getSelectedIndex() == 2)
					//	characterList.add(new Mage(new Point(0,0)));
					//else if (listUnits.getSelectedIndex() == 3)
						//characterList.add(new Priest(new Point(0,0)));
					//else if (listUnits.getSelectedIndex() == 4)
						//characterList.add(new Spearman(new Point(0,0)));
				}
			}
			else if (e.getSource() == removeFromTeam){ // removing from team list
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
