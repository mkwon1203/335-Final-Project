

package view;

import controller.Client;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.event.*;
import javax.swing.*;


public class TitleScreen extends JPanel{
	
	public static int TITLESTATE = 0;
	private static TitleScreen title = null;
	
	private Graphics bufferGraphics;
	private Image titlePic, startImage, exitImage;
	private JButton startButton, optionButton, exitButton;
	
	
	MenuListener listener;
	
	private TitleScreen(){
		this(800, 600);
	}
	
	//Main constructor for the title screen
	private TitleScreen(int x, int y){
		
		setPreferredSize(new Dimension(x,y));
		setLayout(null);
		setBackground(Color.BLUE);
		
		
		listener = new MenuListener();
		
		loadTitleImage();
		loadButtonImages();
		
	}
	
	//This method is just to load in the title screen's background image
	private void loadTitleImage(){
		try{
			titlePic = ImageIO.read(new File("res/titleScreen/titleBackground.png"));
			//backgroundLabel = new JLabel(new ImageIcon(titlePic));
		}catch(IOException e){System.out.println("Couldn't find title picture.");}
	}
	
	//This method is just to load in the images for the title screen's buttons
	private void loadButtonImages(){
		try{
			startImage = ImageIO.read(new File("res/titleScreen/startGame.png"));
			exitImage = ImageIO.read(new File("res/titleScreen/quit.png"));
			
			//Sets the images into the JLabels that can be drawn later.
			startButton = new JButton(new ImageIcon(startImage));
			exitButton = new JButton(new ImageIcon(exitImage));
			
			startButton.setOpaque(false);
			startButton.setContentAreaFilled(false);
			startButton.setBorderPainted(false);
			startButton.setRolloverIcon(new ImageIcon(ImageIO.read(new File("res/titleScreen/startGame2.png"))));
			
			exitButton.setOpaque(false);
			exitButton.setContentAreaFilled(false);
			exitButton.setBorderPainted(false);
			exitButton.setRolloverIcon(new ImageIcon(ImageIO.read(new File("res/titleScreen/quit2.png"))));
			
			startButton.setName("start");
			exitButton.setName("quit");
			
			startButton.addActionListener(listener);
			//optionLabel.addMouseListener(listener);
			exitButton.addActionListener(listener);
		}catch(IOException e){System.out.println("Couldn't find button image.");}
	}
	
	//This method can be called to get the title screen object
	public static TitleScreen getTitleScreen(){
		if(title == null)
			title = new TitleScreen(800,600);
		return title;
	}
	
	public void draw(){
		
		if(TITLESTATE == 0){
			
			//backgroundLabel.setLocation(0,0);
			//startButton.setLocation(500,100);
			//exitButton.setLocation(new Point(575,175));
			
			add(startButton);
			add(exitButton);
			
			startButton.setBounds(500, 100, startImage.getWidth(null), 
					startImage.getHeight(null));
			exitButton.setBounds(575, 175, exitImage.getWidth(null), 
					exitImage.getHeight(null));
			
		}
		
	}
	
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
			g.drawImage(titlePic, 0, 0, null);
		
	}
	
}
