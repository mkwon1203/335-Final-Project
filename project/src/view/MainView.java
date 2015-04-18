
package view;

import controller.Client;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.*;

public class MainView extends JFrame {
	
	private TitleScreen title;
	private Image screen;
	
	public MainView(){
		this(800,600);
	}
	
	public MainView(int x, int y){
		
		title = TitleScreen.getTitleScreen();
		
		createFrame(x, y);
		
	}
	
	private void createFrame(int x, int y){
		
		setSize(x, y);
		setTitle("335 TRPG");
		
		add(title);
		pack();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		screen = createVolatileImage(x,y);
	}
	
	public void draw(){
		Graphics g = screen.getGraphics();
		
		if(Client.GAMESTATE == 0){
			title.paintOffScreen(g);
		}
		
		g = title.getGraphics();
		
		g.drawImage(screen, 0, 0, null);
		g.dispose();
	}
	
}
