package model;

import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import controller.Client;

public abstract class CharacterInterface
{
	private String type;
	private String description;
	private int health;
	private int mana;
	private int strength;
	private int defence;
	private Point location;
	private boolean isAlive;
	private int moveDistance;
	private int attackDistance;
	private Image texture;
	private boolean available;
	private boolean moveAvailable;
	private boolean actionAvailable;
	private String textureFilePath;
	private boolean animated;
	private Point screenCoordinate;

	public CharacterInterface(String type, String description, int health,
			int mana, int strength, int defence, Point location,
			boolean isAlive, int moveDistance, int attackDistance,
			String texture)
	{
		this.type = type;
		this.description = description;
		this.health = health;
		this.mana = mana;
		this.strength = strength;
		this.defence = defence;
		this.location = location;
		this.isAlive = isAlive;
		this.moveDistance = moveDistance;
		this.attackDistance = attackDistance;
		this.textureFilePath = texture;
		try{
			this.texture = ImageIO.read(new File(texture));
		}
		catch (IOException ioex)
		{
			ioex.printStackTrace();
		}
		// hard coded for now, might change to be included as parameter
		available = true;
	}
	
	public String getType()
	{
		return type;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public String getTextureFilePath(){
		return textureFilePath;
	}

	/**
	 * This method returns the health of the character
	 * 
	 * @return health of character as int value
	 */
	public int getHealth()
	{
		return health;
	}

	/**
	 * This method returns the mana of the character
	 * 
	 * @return mana of character as int value
	 */
	public int getMana()
	{
		return mana;
	}

	/**
	 * This method returns the strength stat value of the character
	 * 
	 * @return strength of character as int value
	 */
	public int getStrength()
	{
		return strength;
	}

	/**
	 * This method returns the defence stat value of the character
	 * 
	 * @return defence of character as int value
	 */
	public int getDefence()
	{
		return defence;
	}
	
	/**
	 * This method returns the Point object that contains information about
	 * where the character is located on the game map
	 * 
	 * @return position of the character
	 */
	public Point getLocation()
	{
		return location;
	}

	/**
	 * This method returns whether or not the character is alive or dead.
	 * 
	 * @return true if character is alive false if character is dead
	 */
	public boolean isAlive()
	{
		return isAlive;
	}

	/**
	 * This method returns how many tile blocks the character can move
	 * 
	 * @return distance the character can travel as int value
	 */
	public int getMoveDistance()
	{
		return moveDistance;
	}

	/**
	 * This method returns how many tiles away the character can perform its
	 * attack
	 * 
	 * @return distance the character can attack as int value
	 */
	public int getAttackDistance()
	{
		return attackDistance;
	}
	/**
	 * This method returns the Image which is the texture of the character
	 * 
	 * @return image of the character
	 */
	public Image getTexture()
	{
		return texture;
	}
	
	public boolean getAvailable()
	{
		return available;
	}
	
	public boolean getMoveAvailable()
	{
		return moveAvailable;
	}
	
	public boolean getActionAvailable()
	{
		return actionAvailable;
	}
	
	public Point getScreenCoordinate()
	{
		return screenCoordinate;
	}
	
	public void setAvailable(boolean availability)
	{
		available = availability;
	}
	
	public void setMoveAvailable(boolean a)
	{
		moveAvailable = a;
	}
	
	public void setActionAvailable(boolean a)
	{
		actionAvailable = a;
	}
	
	public void resetAvailable()
	{
		available = true;
		moveAvailable = true;
		actionAvailable = true;
	}
	
	public abstract boolean addHealth(int delta);
	
	public abstract double getPercentHealth();
	
	public abstract double getPercentMana();
	
	public void setHealth(int newHealth)
	{
		health = newHealth;
	}
	
	public abstract boolean addMana(int delta);
	
	public void setMana(int newMana)
	{
		mana = newMana;
	}
	
	public abstract boolean addStrength(int delta);
	
	public void setStrength(int newStrength)
	{
		strength = newStrength;
	}
	
	public abstract boolean addDefence(int delta);
	
	public void setDefence(int newDefence)
	{
		defence = newDefence;
	}
	
	public void setIsAlive(boolean status)
	{
		isAlive = status;
	}
	
	public void setAlive()
	{
		isAlive = true;
	}
	
	public abstract void revive();
	
	public void setDead()
	{
		isAlive = false;
	}
	
	public void setLocation(Point newLocation)
	{
		location = newLocation;
		if (screenCoordinate == null)
			setScreenCoordinate(new Point(newLocation.y*Client.BLOCKSIZE, newLocation.x*Client.BLOCKSIZE));
	}
	
	public void setScreenCoordinate(Point newCoordinate)
	{
		screenCoordinate = newCoordinate;
	}
	
	public void setTexture(Image texture){
		this.texture = texture;
	}
	
	public boolean isAnimated()
	{
		return animated;
	}
	
	public void setAnimated(boolean animate)
	{
		animated = animate;
	}
	
	public abstract String toStringGUI();
}
