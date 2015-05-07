

package view;

import model.Animation;
import model.CharacterInterface;
import model.Game;
import model.Block;
import model.Character;
import model.Enemy;
import model.InvalidLocationException;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import java.util.List;

import controller.Camera;
import controller.Client;

import java.awt.*;
import java.awt.event.*;

public class Level extends JPanel
{
	
	private JPanel gamePanel;
	private Game game;
	private LevelStatPanel statPanel;
	
	private List<Character> playerUnits;
	private List<Enemy> aiUnits;
	
	private Point clickLocation;
	
	private Animation animation;
	
	Image screen;
	
	//Level Constructor
	public Level(){
		define(800, 612);
	}
	
	public Level(int x, int y){
		define(x,y);
	}
	
	public Level(Game game, int x, int y){
		this.game = game;
		define(x,y);
	}
	
	//Returns the game panel.
	public JPanel getGamePanel(){
		return gamePanel;
	}
	
	//Returns the stats panel.
	public LevelStatPanel getStatPanel(){
		return statPanel;
	}
	
	//This method creates the panels needed during the game,
	//	and adds them to the main panel.
	public void define(int x, int y){
		
		setPreferredSize(new Dimension(x,y));
		setLayout(new BorderLayout());
		
		gamePanel = new JPanel();
		gamePanel.setPreferredSize(new Dimension(x,y - 100));
		gamePanel.setBackground(Color.BLACK);
		
		statPanel = new LevelStatPanel(game, x, 100);
		
		gamePanel.addMouseListener(new InputListener());
		
		add(gamePanel, BorderLayout.NORTH);
		add(statPanel, BorderLayout.SOUTH);
		
	}
	
	//This method draws the gamePanel, which has the map and characters.
	public void drawMap(){
		
		screen = createVolatileImage(gamePanel.getWidth(), gamePanel.getHeight());
		Graphics2D g = (Graphics2D)screen.getGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, gamePanel.getWidth(), gamePanel.getHeight());
		
		if(game != null){
			
			Block[][] level = game.getMap().getLevel();
			
			//Draws the map itself
			for(int y = 0; y < game.getMap().getLevelRow(); y++){
				for(int x = 0; x < game.getMap().getLevelCol(); x++){
					g.drawImage(level[y][x].getTexture(), x * Client.BLOCKSIZE + Camera.CAMERAPOSITION.x, y * Client.BLOCKSIZE + Camera.CAMERAPOSITION.y, null);
				}
			}
			
			//Method call to draw the units on the map
			drawUnits(g);
			
			//This block draws the health bars above all the units on the map.
			List<Character> playerCharacters = game.getPlayer().getCharacters();
			for(Character c : playerCharacters){
				g.setColor(Color.RED);
				g.fillRect(c.getScreenCoordinate().x + 1 + Camera.CAMERAPOSITION.x, c.getScreenCoordinate().y - 10 + Camera.CAMERAPOSITION.y, 30, 5);
				g.setColor(Color.GREEN);
				g.fillRect(c.getScreenCoordinate().x + 1 + Camera.CAMERAPOSITION.x, c.getScreenCoordinate().y - 10 + Camera.CAMERAPOSITION.y, (int)(c.getPercentHealth() * 30), 5);
			}
			
			List<Enemy> aiCharacters = game.getAI().getEnemies();
			for(Enemy c : aiCharacters){
				g.setColor(Color.RED);
				g.fillRect(c.getScreenCoordinate().x + 1 + Camera.CAMERAPOSITION.x, c.getScreenCoordinate().y - 10 + Camera.CAMERAPOSITION.y, 30, 5);
				g.setColor(Color.GREEN);
				g.fillRect(c.getScreenCoordinate().x + 1 + Camera.CAMERAPOSITION.x, c.getScreenCoordinate().y - 10 + Camera.CAMERAPOSITION.y, (int)(c.getPercentHealth() * 30), 5);
			}
			
			
			//This highlights the movable locations for the selected character
			if(game.isCharacterSelected()){
				if(game.getSelectedCharacter().getMoveAvailable()){
					List<Point> movableTiles = game.movablePositionList(game.getSelectedCharacter());
					for(Point p : movableTiles){
						if(!game.getSelectedCharacter().getLocation().equals(p)){
							g.setColor(Color.GREEN);
							g.drawRect(p.y * Client.BLOCKSIZE + Camera.CAMERAPOSITION.x, p.x * Client.BLOCKSIZE + Camera.CAMERAPOSITION.y, Client.BLOCKSIZE, Client.BLOCKSIZE);
						}
					}
					
					List<CharacterInterface> attackableCharacters = game.attackableCharacterList(game.getSelectedCharacter());
					for(CharacterInterface c : attackableCharacters){
						g.setColor(Color.RED);
						g.drawRect(c.getLocation().y * Client.BLOCKSIZE + Camera.CAMERAPOSITION.x, c.getLocation().x * Client.BLOCKSIZE + Camera.CAMERAPOSITION.y, Client.BLOCKSIZE, Client.BLOCKSIZE);
					}
				}
			}
			
			g = (Graphics2D)gamePanel.getGraphics();
			g.drawImage(screen, 0, 0, null);
			g.dispose();
		}
		
	}
	
	//This method simply draws the units on the map.
	public void drawUnits(Graphics2D g){
		playerUnits = game.getPlayer().getCharacters();
		aiUnits = game.getAI().getEnemies();
		for(Character c : playerUnits){
			if(!c.isAnimated()){
				g.drawImage(c.getTexture(), c.getLocation().y * Client.BLOCKSIZE + Camera.CAMERAPOSITION.x, c.getLocation().x * Client.BLOCKSIZE + Camera.CAMERAPOSITION.y, null);
			}else if(c.isAnimated()){
				g.drawImage(c.getTexture(), c.getScreenCoordinate().x + Camera.CAMERAPOSITION.x, c.getScreenCoordinate().y + Camera.CAMERAPOSITION.y, null);
			}
		}
		
		for(Enemy e : aiUnits){
			if(!e.isAnimated()){
				g.drawImage(e.getTexture(), e.getLocation().y * Client.BLOCKSIZE + Camera.CAMERAPOSITION.x, e.getLocation().x * Client.BLOCKSIZE + Camera.CAMERAPOSITION.y, null);
			}else if(e.isAnimated()){
				g.drawImage(e.getTexture(), e.getScreenCoordinate().x + Camera.CAMERAPOSITION.x, e.getScreenCoordinate().y + Camera.CAMERAPOSITION.y, null);
			}
		}
		
	}
	
	//This inner-class is the mouse listener that handles user input during the game.
	public class InputListener extends MouseInputAdapter{

		public void mouseClicked(MouseEvent e){
			
			int button = e.getButton();
			
			switch(button){
				
			case 0:
				
				break;
				
			case 1:
				if(Client.GAMESTATE == 1){
					
					try{
					clickLocation = new Point((int)((e.getY() - Camera.CAMERAPOSITION.y) / Client.BLOCKSIZE), (int)((e.getX() - Camera.CAMERAPOSITION.x) / Client.BLOCKSIZE));
						System.out.println("Click location: " + clickLocation);
						Block b = game.getMap().getBlock(clickLocation);
						
						if(game.isPlayersTurn()){
							
							if(game.isCharacterSelected()){
								if(!b.isOccupied()){
									if(!game.getSelectedCharacter().isAnimated()){
										if(game.canMoveTo(game.getSelectedCharacter(), clickLocation)){
											game.getSelectedCharacter().setAnimated(true);
											animation = new Animation(game.findPath(game.getSelectedCharacter(), clickLocation), game.getSelectedCharacter());
											animation.execute();
											game.move(game.getSelectedCharacter(), clickLocation);
										}
									}
								}
							}
							
							if(b.isOccupied())
								if(!game.getCharacterAt(clickLocation.x, clickLocation.y).isAnimated())
									game.setSelectedCharacter(game.getCharacterAt(clickLocation.x, clickLocation.y));
							
						}
					}catch(InvalidLocationException ex){ }
				}
				break;
				
			case 2:
				
				break;
					
			}//End of switch
		}//End of mouseClicked()
	} //End of inner class
	
	
}//End of Level class.
