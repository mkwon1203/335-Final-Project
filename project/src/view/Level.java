

package view;

import model.CharacterInterface;
import model.Game;
import model.Block;
import model.Character;
import model.Enemy;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import java.io.*;
import java.util.List;

import controller.Client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Level extends JPanel
{
	
	private JPanel gamePanel, statsPanel;
	private Game game;
	
	private List<Character> playerUnits;
	private List<Enemy> aiUnits;
	
	private JLabel unitImage, unitHealthLabel, unitManaLabel, unitName;
	private JButton attackButton, endTurnButton;
	private JList inventoryList;
	
	private Point clickLocation;
	
	Image attackImage, endTurnImage;
	Image screen, statsScreen;
	
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
		statsPanel.setLayout(null);
		statsPanel.setBackground(Color.BLACK);
		
		gamePanel.addMouseListener(new InputListener());
		
		loadStatButtons();
		
		unitHealthLabel = new JLabel();
		unitHealthLabel.setForeground(Color.WHITE);
		unitHealthLabel.setText("HP: ");
		
		unitManaLabel = new JLabel();
		unitManaLabel.setForeground(Color.WHITE);
		unitManaLabel.setText("Mana: ");
		
		statsPanel.add(unitHealthLabel);
		statsPanel.add(unitManaLabel);
		
		add(gamePanel, BorderLayout.NORTH);
		add(statsPanel, BorderLayout.SOUTH);
		
	}
	
	//This method is used to load the button images and create the buttons
	//	for the stats panel.
	public void loadStatButtons(){
		
		try{
			
			attackImage = ImageIO.read(new File("res/sprites/statpanel/attack.png"));
			endTurnImage = ImageIO.read(new File("res/sprites/statpanel/endTurn.png"));
			
			attackButton = new JButton(new ImageIcon(attackImage));
			endTurnButton = new JButton(new ImageIcon(endTurnImage));
			
			attackButton.setOpaque(false);
			attackButton.setContentAreaFilled(false);
			attackButton.setBorderPainted(false);
			attackButton.setRolloverIcon(new ImageIcon(ImageIO.read(new File("res/sprites/statpanel/attack2.png"))));
			
			endTurnButton.setOpaque(false);
			endTurnButton.setContentAreaFilled(false);
			endTurnButton.setBorderPainted(false);
			endTurnButton.setRolloverIcon(new ImageIcon(ImageIO.read(new File("res/sprites/statpanel/endTurn2.png"))));
			
			attackButton.setName("attack");
			endTurnButton.setName("endTurn");
			
			statsPanel.add(attackButton);
			statsPanel.add(endTurnButton);
			
			attackButton.addActionListener(new StatPanelListener());
			endTurnButton.addActionListener(new StatPanelListener());
			
		}catch(IOException e){
			System.out.println("Couldn't find a stat button image file.");
		}
		
	}
	
	//This method draws the stats panel at the bottom of the screen, which
	//	contains the selected character's stats, and action buttons.
	public void drawStatsPanel(){
		
		//In case we want to add some kind of image background or border, we can
		//	use this graphics object to do so. Won't be used otherwise.
		statsScreen = createVolatileImage(statsPanel.getWidth(), statsPanel.getHeight());
		Graphics2D g = (Graphics2D)statsScreen.getGraphics();
		
		attackButton.setBounds(500, 15, attackImage.getWidth(null), attackImage.getHeight(null));
		endTurnButton.setBounds(485, 50, endTurnImage.getWidth(null), endTurnImage.getHeight(null));
		
		unitHealthLabel.setBounds(200, 25, 75, 25);
		unitManaLabel.setBounds(200, 50, 75, 25);
		
		if(game != null){
			if(game.isCharacterSelected()){
				unitHealthLabel.setText("HP: " + game.getSelectedCharacter().getHealth());
				unitManaLabel.setText("Mana: " + game.getSelectedCharacter().getMana());
			}
		}
		
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
			
			//TODO: Draw the health bars above all the units on the map.
			List<Character> playerCharacters = game.getPlayer().getCharacters();
			for(Character c : playerCharacters){
				g.setColor(Color.RED);
				g.fillRect(c.getLocation().y * Client.BLOCKSIZE + 1, c.getLocation().x * Client.BLOCKSIZE - 10, 30, 5);
				g.setColor(Color.GREEN);
				g.fillRect(c.getLocation().y * Client.BLOCKSIZE + 1, c.getLocation().x * Client.BLOCKSIZE - 10, c.getPercentHealth() * 30, 5);
			}
			
			List<Enemy> aiCharacters = game.getAI().getEnemies();
			for(Enemy c : aiCharacters){
				g.setColor(Color.RED);
				g.fillRect(c.getLocation().y * Client.BLOCKSIZE + 1, c.getLocation().x * Client.BLOCKSIZE - 10, 30, 5);
				g.setColor(Color.GREEN);
				g.fillRect(c.getLocation().y * Client.BLOCKSIZE + 1, c.getLocation().x * Client.BLOCKSIZE - 10, c.getPercentHealth() * 30, 5);
			}
			
			//This highlights the movable locations for the selected character
			if(game.isCharacterSelected()){
				if(game.getSelectedCharacter().getMoveAvailable()){
					List<Point> movableTiles = game.movablePositionList(game.getSelectedCharacter());
					for(Point p : movableTiles){
						if(game.isOccupied(p.x, p.y)){
							if(game.attackable(game.getSelectedCharacter(), game.getCharacterAt(p.x, p.y))){
								g.setColor(Color.RED);
								g.drawRect(p.y * Client.BLOCKSIZE, p.x * Client.BLOCKSIZE, Client.BLOCKSIZE, Client.BLOCKSIZE);
							}
						}else{
							g.setColor(Color.GREEN);
							g.drawRect(p.y * Client.BLOCKSIZE, p.x * Client.BLOCKSIZE, Client.BLOCKSIZE, Client.BLOCKSIZE);
						}
					}
				}
			}
			
			g = (Graphics2D)gamePanel.getGraphics();
			g.drawImage(screen, 0, 0, null);
			g.dispose();
		}
		
	}
	
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
					//System.out.println("Block is occupied: " + b.isOccupied());
					
					if(game.isPlayersTurn()){
						
						if(game.isCharacterSelected()){
							//System.out.println("trying to move.");
							game.move(game.getSelectedCharacter(), clickLocation);
						}
						
						if(b.isOccupied()){
							game.setSelectedCharacter(game.getCharacterAt(clickLocation.x, clickLocation.y));
							//System.out.println("Character selected: " + game.isCharacterSelected());
						}
						
					}
					
					//System.out.println("Is block Solid: " + b.isSolid());
					//System.out.println("Mouse coordinates: " + e.getX() + ", " + e.getY());
					
				}
				break;
				
			case 2:
				
				break;
					
			}//End of switch
			
			
		}//End of mouseClicked()
		
		
	} //End of inner class
	
	private class StatPanelListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			if(e.getSource() instanceof JButton){
				JButton button = (JButton)e.getSource();
				
				if(button.getName() == "attack"){
					
					if(game.isPlayersTurn()){
						if(game.isCharacterSelected()){
							List<CharacterInterface> attackableCharacters = game.attackableCharacterList(game.getSelectedCharacter());
							for(CharacterInterface c : attackableCharacters){
								if(game.attack(game.getSelectedCharacter(), c)){
									System.out.println("attack successful!");
									break;
								}
							}
						}
					}
					
				} else if(button.getName() == "endTurn"){
					if(game.isPlayersTurn())
						game.advanceTurn();
				}
				
			}
		}
		
	}
	
	
}
