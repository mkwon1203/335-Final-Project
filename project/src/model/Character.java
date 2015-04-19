package model;

import java.awt.Image;
import java.awt.Point;

public abstract class Character implements CharacterInterface {

	private String myName;
	private String myType;
	private int myHealth;
	private int myStrength;
	private Point myLocation;
	private Image texture;

	public Character(String name, String type, int health, int strength,
			Point location) {

		myName = name;
		myType = type;
		myHealth = health;
		myStrength = strength;
		myLocation = location;

	}

	/**
	 * This method returns the health of the character
	 * 
	 * @return health of character as int value
	 */
	public int getHealth() {

		return myHealth;

	}

	/**
	 * This method returns the mana of the character
	 * 
	 * @return mana of character as int value
	 */
	public int getMana() {

		return 0;
	}

	/**
	 * This method returns the strength stat value of the character
	 * 
	 * @return strength of character as int value
	 */
	public int getStrength() {

		return myStrength;

	}

	/**
	 * This method returns the defence stat value of the character
	 * 
	 * @return defence of character as int value
	 */
	public int getDefence() {

	}

	/**
	 * This method returns the Point object that contains information about
	 * where the character is located on the game map
	 * 
	 * @return position of the character
	 */
	public Point getLocation() {

		return myLocation;
	}

	/**
	 * This method returns whether or not the character is alive or dead. If
	 * health is less than zero, they are die Otherwise, they still alive
	 * 
	 * @return true if character is alive false if character is dead
	 */
	public boolean isAlive() {

		if (myHealth > 0)

			return true;
		else
			return false;
	}

	/**
	 * This method used to defense enemy
	 * 
	 * 
	 */
	public void defence() {

	}

	/**
	 * 
	 * 
	 * 
	 */
	public void attack() {

	}

	/**
	 * This method returns how many tile blocks the character can move
	 * 
	 * @param target
	 *            the destintation we want to move to
	 * 
	 * @return distance the character can travel as int value
	 */
	public int moveDistance() {

		return 0;
	}

	/**
	 * This method returns how many tiles away the character can perform its
	 * attack
	 * 
	 * @return distance the character can attack as int value
	 */
	public int attackDistance() {

	}

	/**
	 * This method returns the Image which is the texture of the character
	 * 
	 * @return image of the character
	 */
	public Image getTexture() {

		return texture;

	}

}
