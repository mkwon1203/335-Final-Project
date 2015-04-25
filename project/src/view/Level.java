

package view;

import model.Game;
import model.Block;
import model.Character;
import model.Enemy;

import javax.swing.*;

import java.util.List;

import controller.Client;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Level extends JPanel
{
	
	private JPanel gamePanel, statsPanel;
	private Game game;
	
	Image screen;
	
	//Level Constructor
	public Level(){
		define(800, 600);
	}
	
	public Level(int x, int y){
		define(x,y);
	}
	
	public Level(Game game, int x, int y){
		this.game = game;
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
	
	public void drawMap(){
		
		screen = createVolatileImage(gamePanel.getWidth(), gamePanel.getHeight());
		Graphics2D g = (Graphics2D)screen.getGraphics();
		
		
		if(game != null){
			
			Block[][] level = game.getMap().getLevel();
			
			for(int y = 0; y < game.getMap().getLevelY(); y++){
				for(int x = 0; x < game.getMap().getLevelX(); x++){
					g.drawImage(level[x][y].getTexture(), y * Client.BLOCKSIZE, x * Client.BLOCKSIZE, null);
				}
			}
			
			drawUnits(g);
			
			g = (Graphics2D)gamePanel.getGraphics();
			g.drawImage(screen, 0, 0, null);
			g.dispose();
		}
		
	}
	
	public void drawUnits(Graphics2D g){
		List<Character> playerUnits = game.getPlayer().getCharacters();
		List<Enemy> aiUnits = game.getAI().getEnemies();
		
		for(Character c : playerUnits){
			g.drawImage(c.getTexture(), c.getLocation().x, c.getLocation().y, null);
		}
		
		for(Enemy e : aiUnits){
			g.drawImage(e.getTexture(), e.getLocation().x, e.getLocation().y, null);
		}
		
	}
	
}
