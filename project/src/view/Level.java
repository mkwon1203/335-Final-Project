

package view;

import model.Game;
import model.Block;
import model.Character;
import model.Enemy;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import java.util.List;

import controller.Client;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Level extends JPanel
{
	
	private JPanel gamePanel, statsPanel;
	private Game game;
	
	private List<Character> playerUnits;
	private List<Enemy> aiUnits;
	
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
	
	public JPanel getGamePanel(){
		return gamePanel;
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
		
		gamePanel.addMouseListener(new InputListener());
		
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
		playerUnits = game.getPlayer().getCharacters();
		aiUnits = game.getAI().getEnemies();
		for(Character c : playerUnits){
			g.drawImage(c.getTexture(), c.getLocation().x, c.getLocation().y, null);
		}
		
		for(Enemy e : aiUnits){
			g.drawImage(e.getTexture(), e.getLocation().x, e.getLocation().y, null);
		}
		
	}
	
	
	public class InputListener extends MouseInputAdapter{

		public void mouseClicked(MouseEvent e){
			
			int button = e.getButton();
			
			switch(button){
				
			case 0:
				
				break;
				
			case 1:
				if(Client.GAMESTATE == 1){
					
					Block b = game.getMap().getValue(new Point((int)(e.getY() / Client.BLOCKSIZE), (int)(e.getX() / Client.BLOCKSIZE)));
					
					if(game.isCharacterSelected()){
						if(game.isPlayersTurn()){
							System.out.println("trying to move.");
							game.move(game.getSelectedCharacter(), new Point(((int)(e.getX() / Client.BLOCKSIZE) * Client.BLOCKSIZE),((int)(e.getY() / Client.BLOCKSIZE) * Client.BLOCKSIZE)));
							game.endTurn();
						}
					}
					
					for(Character c : playerUnits){
						if(((int)(c.getLocation().x / Client.BLOCKSIZE) == (int)(e.getX() / Client.BLOCKSIZE)) && 
								((int)(c.getLocation().y / Client.BLOCKSIZE) == (int)(e.getY() / Client.BLOCKSIZE))){
							game.updateCurrentCharacter(c);
							//game.move(c, new Point((int)(e.getX() / Client.BLOCKSIZE),(int)(e.getY() / Client.BLOCKSIZE)));
							System.out.println("selected a unit");
						}
					}
					
					
					System.out.println(b.isSolid());
					System.out.println("Mouse coordinates: " + e.getX() + ", " + e.getY());
					
				}
				break;
				
			case 2:
				
				break;
				
				
			}
			
			
		}
		
		
	}
	
}
