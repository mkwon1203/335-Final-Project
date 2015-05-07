
package view;

import controller.Camera;
import controller.Client;
import controller.SoundEffects;
import model.Character;
import model.Game;
import model.Player;

import java.awt.Point;
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
	public static MainView mainview;
	private Camera camera;
	
	//temporary variables
	private List<Character> playerCharacters;
	
	private MainView(){
		this(800,612);
	}
	
	private MainView(int x, int y){
		
		title = TitleScreen.getTitleScreen();
		game = new Game();
		
		createFrame(x, y);
		
	}
	
	public static MainView getMainView()
	{
		if (mainview == null)
			mainview = new MainView();
		return mainview;
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
	
	public Game getGame(){
		return game;
	}
	
	//This method should create the level panel, and add it 
	//	to the frame.
	public void addLevelToFrame(){
		//TODO: Create and add the level panel to the frame.
		
		game.addObserver(this);
		
		if(level == null){
			level = new Level(game, 800, 612);
		}
		
		add(level);
		pack();
	}
	
	public void update(){
		
		if(Client.GAMESTATE < 1){
			if(SoundEffects.getSoundClipArraySize() <= 0 || !SoundEffects.containsSoundClip("res/music/8bitDungeonTitle.wav"))
				SoundEffects.addSound("res/music/8bitDungeonTitle.wav");
		}else if(Client.GAMESTATE == 1){
			
			if(title.isDisplayable()){
				remove(title);
				System.out.println("I removed the title panel");
				addLevelToFrame();
				level.drawMap();
				level.getStatPanel().draw();
				level.addKeyListener(new CameraListener());
				level.setFocusable(true);
				level.requestFocusInWindow();
				
				if(camera == null)
					camera = Camera.getCamera();
				camera.setCameraPosition(new Point(0,0));
				
				if(SoundEffects.containsSoundClip("res/music/8bitDungeonTitle.wav"))
					SoundEffects.removeSoundClip("res/music/8bitDungeonTitle.wav");
				SoundEffects.addSound("res/music/8bitDungeon.wav");
			}
			
			if(level == null)
				addLevelToFrame();
			
//			System.out.println("Sound Clips: " + SoundEffects.getSoundClipArraySize());
			if(SoundEffects.getSoundClipArraySize() <= 0 || !SoundEffects.containsSoundClip("res/music/8bitDungeon.wav"))
				SoundEffects.addSound("res/music/8bitDungeon.wav");
			
		}
		
	}
	
	public void draw(){
		
		if(Client.GAMESTATE == 0){
			if (level != null && level.isDisplayable())
				remove(level);
			title.draw();
			title.repaint();
			add(title);
			pack();
		}else if(Client.GAMESTATE == 1){
			
			level.drawMap();
			level.requestFocusInWindow();
			//level.getStatPanel().draw();
			
		}
		
	}

	@Override
	public void update(Observable o, Object arg) {
		if(Client.GAMESTATE == 1)
			level.getStatPanel().draw();
	}
	
	
}
