package model;

import java.awt.Point;

public abstract class Enemy extends CharacterInterface {

	private int value;
	
	public Enemy(String type, String description, int health, int mana,
			int strength, int defence, Point location, boolean isAlive,
			int moveDistance, int attackDistance, String texture, int value, String attackSoundPath)
	{
		super(type, description, health, mana, strength, defence, location,
				isAlive, moveDistance, attackDistance, texture, attackSoundPath);
		this.value = value;
	}
	
	public int getValue()
	{
		return value;
	}
}
