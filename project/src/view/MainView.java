
package view;

import controller.Client;
import model.Character;
import model.Knight;
import model.Game;
import model.Player;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;

public class MainView extends JFrame implements Observer{
	
	private TitleScreen title;
	private Level level;
	private Game game;
	private Player player;
	
	//temporary variables
	private List<Character> playerCharacters;
	
	public MainView(){
		this(800,612);
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
		
		try{
			setIconImage(new ImageIcon(ImageIO.read(new File("res/titleScreen/trpgIcon.png"))).getImage());
		}catch(IOException e){
			System.out.println("Can't Find Icon Image.");
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	
	//This method should create the level panel, and add it 
	//	to the frame.
	public void addLevelToFrame(){
		//TODO: Create and add the level panel to the frame.
		
		if(game == null){
			playerCharacters = new ArrayList<Character>();
			playerCharacters.add(new Knight(null));
			
			
			game = new Game("Player1", playerCharacters, "milestone");
			game.addObserver(this);
		}
		if(level == null){
			level = new Level(game, 800, 612);
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
				level.drawMap();
				level.getStatPanel().draw();
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
			//level.getStatPanel().draw();
			
		}
		
	}

	@Override
	public void update(Observable o, Object arg) {
		if(Client.GAMESTATE == 1)
			level.getStatPanel().draw();
	}
	
}
