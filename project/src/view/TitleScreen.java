package view;
import controller.Client;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.event.*;
import javax.swing.*;


public class TitleScreen extends JPanel {
	
	private static TitleScreen title = null;
	
	private Graphics bufferGraphics;
	private BufferedImage titlePic, startImage, exitImage;
	
	private TitleScreen(){
		this(800, 600);
	}
	
	//Main constructor for the title screen
	private TitleScreen(int x, int y){
		
		setPreferredSize(new Dimension(x,y));
		
		loadTitleImage();
		loadButtonImages();
		
		setBackground(Color.BLACK);
		
	}
	
	//This method is just to load in the title screen's background image
	private void loadTitleImage(){
		try{
			titlePic = ImageIO.read(new File("res/titleScreen/titleBackground.png"));
		}catch(IOException e){System.out.println("Couldn't find title picture.");}
	}
	
	//This method is just to load in the images for the title screen's buttons
	private void loadButtonImages(){
		try{
			startImage = ImageIO.read(new File("res/titleScreen/startGame.png"));
			exitImage = ImageIO.read(new File("res/titleScreen/quit.png"));
		}catch(IOException e){System.out.println("Couldn't find button image.");}
	}
	
	//This method can be called to get the title screen object
	public static TitleScreen getTitleScreen(){
		if(title == null)
			title = new TitleScreen(800,600);
		return title;
	}
	
	//This method is meant to "pre-draw" the title screen (will not be seen on screen)
	public void paintOffScreen(Graphics g){
		bufferGraphics = titlePic.getGraphics();
		
		if(Client.GAMESTATE == 0){
			bufferGraphics.drawImage(titlePic, 0,0, null);
			bufferGraphics.drawImage(startImage,400,50, null);
			bufferGraphics.drawImage(exitImage, 400, 75, null);
		}
		
		draw(g);
	}
	
	//Used to draw the actual title screen
	public void draw(Graphics g){
		g.drawImage(titlePic,0,0,null);
	}
	
}
