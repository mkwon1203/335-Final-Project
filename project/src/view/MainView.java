
package view;

import controller.Client;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.*;

public class MainView extends JFrame {
	
	private TitleScreen title;
	private Level level;
	private Image screen;
	
	public MainView(){
		this(800,600);
	}
	
	public MainView(int x, int y){
		
		title = TitleScreen.getTitleScreen();
		level = new Level(800,600);
		
		createFrame(x, y);
		
	}
	
	private void createFrame(int x, int y){
		
		setSize(x, y);
		setTitle("335 TRPG");
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		screen = createVolatileImage(x,y);
	}
	
	//This method should create the level panel, and add it 
	//	to the frame.
	public void addLevelToFrame(){
		//TODO: Create and add the level panel to the frame.
	}
	
	public void draw(){
		
		if(Client.GAMESTATE == 0){
			title.draw();
			add(title);
			pack();
		}else if(Client.GAMESTATE == 1){
			if(title.isDisplayable()){
				remove(title);
				System.out.println("I removed the title panel");
			}
			
			level.draw();
			add(level);
			pack();
			
		}
		
	}
	
}
