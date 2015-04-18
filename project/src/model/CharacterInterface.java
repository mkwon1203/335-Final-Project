package model;

import java.awt.Image;
import java.awt.Point;

public interface CharacterInterface
{
	/**
	 * This method returns the health of the character
	 * 
	 * @return health of character as int value
	 */
	public abstract int getHealth();
	
	/**
	 * This method returns the mana of the character
	 * 
	 * @return mana of character as int value
	 */
	public abstract int getMana();
	
	
	/**
	 * This method returns the strength stat value of the character
	 * 
	 * @return strength of character as int value
	 */
	public abstract int getStrength();
	
	/**
	 * This method returns the defence stat value of the character
	 * 
	 * @return defence of character as int value
	 */
	public abstract int getDefence();
	
	/**
	 * This method returns the Point object that contains information
	 * about where the character is located on the game map
	 * @return position of the character
	 */
	public abstract Point getLocation();
	
	/**
	 * This method returns whether or not the character is alive or dead.
	 * 
	 * @return true if character is alive
	 * 		   false if character is dead
	 */
	public abstract boolean isAlive();
	
	/**
	 * This method returns how many tile blocks the character can move
	 * 
	 * @return distance the character can travel as int value
	 */
	public abstract int moveDistance();
	
	/**
	 * This method returns how many tiles away the character can perform its attack
	 * 
	 * @return distance the character can attack as int value
	 */
	public abstract int attackDistance();
	
	/**
	 * This method returns the Image which is the texture of the character
	 * @return image of the character
	 */
	public abstract Image getTexture();
}
