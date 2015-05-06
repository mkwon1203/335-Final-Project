

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
	
	public static boolean UNITMOVING = false;
	
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
				g.fillRect(c.getScreenCoordinate().x + 1, c.getScreenCoordinate().y - 10, 30, 5);
				g.setColor(Color.GREEN);
				g.fillRect(c.getScreenCoordinate().x + 1, c.getScreenCoordinate().y - 10, (int)(c.getPercentHealth() * 30), 5);
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
			if(!c.isAnimated()){
				g.drawImage(c.getTexture(), c.getLocation().y * Client.BLOCKSIZE, c.getLocation().x * Client.BLOCKSIZE, null);
			}else if(c.isAnimated()){
				g.drawImage(c.getTexture(), c.getScreenCoordinate().x, c.getScreenCoordinate().y, null);
			}
		}
		
		for(Enemy e : aiUnits){
			g.drawImage(e.getTexture(), e.getLocation().y * Client.BLOCKSIZE, e.getLocation().x * Client.BLOCKSIZE, null);
		}
		
	}
	
	
	private class Animation extends SwingWorker<Boolean, Point>{
		
		private int framesX;
		private int speed;
		private int index;
		private Point currentFrame, coordinates;
		private List<Point> path;
		private int frameDirection;
		private int currentIndex;
		
		private CharacterInterface unit;
		
		private int currentDirection;
		private final int SOUTH = 0;
		private final int WEST = 1;
		private final int EAST = 2;
		private final int NORTH = 3;
		
		private boolean textureChanged;
		
		private Image[][] images;
		
		public Animation(List<Point> path, CharacterInterface unit){
			
			images = model.LoadSprites.loadSpriteSheet(unit.getTextureFilePath(), 4, 3, Client.BLOCKSIZE);
			textureChanged = false;
			this.unit = unit;
			
			for(int x = 0; x < path.size(); x++)
				path.set(x, new Point(path.get(x).y * Client.BLOCKSIZE, path.get(x).x * Client.BLOCKSIZE));
			
			this.path = path;
			
			this.coordinates = unit.getScreenCoordinate();
			System.out.println("Starting position: " + coordinates);
			currentFrame = new Point(0,1);
			framesX = images[0].length;
			frameDirection = 1;
			currentDirection = 0;
			currentIndex = 0;
			speed = 3;
			index = 0;
		}
		
		private void nextFrame(int direction){
			currentFrame.y = direction;
			textureChanged = true;
			
			currentFrame.x += frameDirection;
			if(currentFrame.x >= framesX - 1)
				frameDirection = -1;
			else if(currentFrame.x <= 0)
				frameDirection = 1;
			
			System.out.println("texture changed. " + currentFrame);
			
		}
		
		@Override
		protected Boolean doInBackground() throws Exception {
			
			while(currentIndex != path.size()){
				index++;
				
				if(path.get(currentIndex).x < coordinates.x){
					currentDirection = WEST;
					coordinates.x--;
				}else if(path.get(currentIndex).x > coordinates.x){
					currentDirection = EAST;
					coordinates.x++;
				}else if(path.get(currentIndex).y < coordinates.y){
					currentDirection = NORTH;
					coordinates.y--;
				}else if(path.get(currentIndex).y > coordinates.y){
					currentDirection = SOUTH;
					coordinates.y++;
				}
				
				if(index > speed){
					index = 0;
					nextFrame(currentDirection);
				}
				
				publish(coordinates);
				
				if(coordinates.equals(path.get(currentIndex)))
					currentIndex++;
				
				Thread.sleep(30);
			}
			return true;
		}
		
		protected void done() {
			System.out.println("Thread stopped");
			unit.setTexture(images[currentDirection][1]);
			unit.setAnimated(false);
			UNITMOVING = false;
			super.done();
		}

		@Override
		protected void process(List<Point> arg0) {
			
			if(textureChanged)
				this.unit.setTexture(images[currentFrame.y][currentFrame.x]);
			
			this.unit.setScreenCoordinate(arg0.get(arg0.size() - 1));
			//System.out.println("CurrentFrame: " + currentFrame);
			//System.out.println("Coordinates: " + coordinates);
			UNITMOVING = true;
		}
		
	}//End of Animator inner-class
	
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
				}
				break;
				
			case 2:
				
				break;
					
			}//End of switch
		}//End of mouseClicked()
	} //End of inner class
	
	
}//End of Level class.
