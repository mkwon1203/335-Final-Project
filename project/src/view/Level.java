

package view;

import javax.swing.*;
import java.awt.*;

public class Level extends JPanel
{
	
	private JPanel gamePanel, statsPanel;
	
	//Level Constructor
	public Level(){
		define(800, 600);
	}
	
	public Level(int x, int y){
		define(x,y);
	}
	
	//This method creates the panels needed during the game,
	//	and adds them to the main panel.
	public void define(int x, int y){
		
		setPreferredSize(new Dimension(x,y));
		setLayout(new BorderLayout());
		
		gamePanel = new JPanel();
		gamePanel.setPreferredSize(new Dimension(x,y - 100));
		gamePanel.setBackground(Color.GREEN);
		
		statsPanel = new JPanel();
		statsPanel.setPreferredSize(new Dimension(x,100));
		statsPanel.setBackground(Color.BLACK);
		
		add(gamePanel, BorderLayout.NORTH);
		add(statsPanel, BorderLayout.SOUTH);
		
	}
	
	public void draw(){
		
	}
	
}
