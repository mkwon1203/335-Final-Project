package model;

import java.awt.Image;
import java.awt.Point;

public abstract class Character extends CharacterInterface
{

	public Character(String type, String description, int health, int mana,
			int strength, int defence, Point location, boolean isAlive,
			int moveDistance, int attackDistance, Image texture)
	{
		super(type, description, health, mana, strength, defence, location,
				isAlive, moveDistance, attackDistance, texture);

	}

}
