
package view;

import controller.Client;
import model.Character;
import model.Enemy;
import model.Knight;
import model.Goblin;
import model.Game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.*;

import javax.swing.*;

public class MainView extends JFrame {
	
	private TitleScreen title;
	private Level level;
	private Game game;
	
	//temporary variables
	private List<Character> playerCharacters;
	private List<Enemy> enemyCharacters;
	
	public MainView(){
		this(800,600);
	}
	
	public MainView(int x, int y){
		
		title = TitleScreen.getTitleScreen();
		
		createFrame(x, y);
		
	}
	
	public Game getGame(){
		return game;
	}
	
	private void createFrame(int x, int y){
		
		setSize(x, y);
		setTitle("335 TRPG");
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	
	//This method should create the level panel, and add it 
	//	to the frame.
	public void addLevelToFrame(){
		//TODO: Create and add the level panel to the frame.
		
		if(game == null){
			playerCharacters = new ArrayList<Character>();
			playerCharacters.add(new Knight(new Point(7,5)));
			
			enemyCharacters = new ArrayList<Enemy>();
			enemyCharacters.add(new Goblin(new Point(7,15)));
			
			
			game = new Game("Player1", playerCharacters, enemyCharacters, "milestone");
		}
		if(level == null){
			level = new Level(game, 800, 600);
		}
		
		add(level);
		pack();
	}
	
	public void update(){
		
		if(Client.GAMESTATE == 1){
			
			if(title.isDisplayable()){
				remove(title);
				System.out.println("I removed the title panel");
				addLevelToFrame();
			}
			
			if(level == null)
				addLevelToFrame();
			
		}
		
	}
	
	public void draw(){
		
		if(Client.GAMESTATE == 0){
			title.draw();
			add(title);
			pack();
		}else if(Client.GAMESTATE == 1){
			
			level.drawMap();
			level.drawStatsPanel();
			
		}
		
	}
	
}
