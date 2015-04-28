

package view;

import model.CharacterInterface;
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

public class Level extends JPanel
{
	
	private JPanel gamePanel;
	private Game game;
	private LevelStatPanel statPanel;
	
	private List<Character> playerUnits;
	private List<Enemy> aiUnits;
	
	private Point clickLocation;
	
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
		
		
		if(game != null){
			
			Block[][] level = game.getMap().getLevel();
			
			//Draws the map itself
			for(int y = 0; y < game.getMap().getLevelRow(); y++){
				for(int x = 0; x < game.getMap().getLevelCol(); x++){
					g.drawImage(level[y][x].getTexture(), x * Client.BLOCKSIZE, y * Client.BLOCKSIZE, null);
				}
			}
			
			//Method call to draw the units on the map
			drawUnits(g);
			
			//This block draws the health bars above all the units on the map.
			List<Character> playerCharacters = game.getPlayer().getCharacters();
			for(Character c : playerCharacters){
				g.setColor(Color.RED);
				g.fillRect(c.getLocation().y * Client.BLOCKSIZE + 1, c.getLocation().x * Client.BLOCKSIZE - 10, 30, 5);
				g.setColor(Color.GREEN);
				g.fillRect(c.getLocation().y * Client.BLOCKSIZE + 1, c.getLocation().x * Client.BLOCKSIZE - 10, (int)(c.getPercentHealth() * 30), 5);
			}
			
			List<Enemy> aiCharacters = game.getAI().getEnemies();
			for(Enemy c : aiCharacters){
				g.setColor(Color.RED);
				g.fillRect(c.getLocation().y * Client.BLOCKSIZE + 1, c.getLocation().x * Client.BLOCKSIZE - 10, 30, 5);
				g.setColor(Color.GREEN);
				g.fillRect(c.getLocation().y * Client.BLOCKSIZE + 1, c.getLocation().x * Client.BLOCKSIZE - 10, (int)(c.getPercentHealth() * 30), 5);
			}
			
			
			//This highlights the movable locations for the selected character
			if(game.isCharacterSelected()){
				if(game.getSelectedCharacter().getMoveAvailable()){
					List<Point> movableTiles = game.movablePositionList(game.getSelectedCharacter());
					for(Point p : movableTiles){
						if(!game.getSelectedCharacter().getLocation().equals(p)){
							g.setColor(Color.GREEN);
							g.drawRect(p.y * Client.BLOCKSIZE, p.x * Client.BLOCKSIZE, Client.BLOCKSIZE, Client.BLOCKSIZE);
						}
					}
					
					List<CharacterInterface> attackableCharacters = game.attackableCharacterList(game.getSelectedCharacter());
					for(CharacterInterface c : attackableCharacters){
						g.setColor(Color.RED);
						g.drawRect(c.getLocation().y * Client.BLOCKSIZE, c.getLocation().x * Client.BLOCKSIZE, Client.BLOCKSIZE, Client.BLOCKSIZE);
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
			g.drawImage(c.getTexture(), c.getLocation().y * Client.BLOCKSIZE, c.getLocation().x * Client.BLOCKSIZE, null);
		}
		
		for(Enemy e : aiUnits){
			g.drawImage(e.getTexture(), e.getLocation().y * Client.BLOCKSIZE, e.getLocation().x * Client.BLOCKSIZE, null);
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
					
					clickLocation = new Point((int)(e.getY() / Client.BLOCKSIZE), (int)(e.getX() / Client.BLOCKSIZE));
					Block b = game.getMap().getBlock(clickLocation);
					
					if(game.isPlayersTurn()){
						
						if(game.isCharacterSelected())
							if(!b.isOccupied())
								game.move(game.getSelectedCharacter(), clickLocation);
						
						if(b.isOccupied())
							game.setSelectedCharacter(game.getCharacterAt(clickLocation.x, clickLocation.y));
						
					}
				}
				break;
				
			case 2:
				
				break;
					
			}//End of switch
		}//End of mouseClicked()
	} //End of inner class
	
	
}//End of Level class.
