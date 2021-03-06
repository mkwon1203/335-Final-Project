package model;

import java.awt.Image;
import java.awt.Point;

import controller.Client;

public class Knight extends Character {
	private static final int KNIGHT_HEALTH = 100;
	private static final int KNIGHT_MANA = 100;
	private static final int KNIGHT_STRENGTH = 100;
	private static final int KNIGHT_DEFENCE = 10;
	private static final int KNIGHT_MOVEDISTANCE = 2;
	private static final int KNIGHT_ATTACKDISTANCE = 1;
	private static final String KNIGHT_IKNIGHTPATH = "res/sprites/units/knight.png";
	private static final String KNIGHT_DESCRIPTION = "Knights are not very mobile, and their attacks are average, but they can survive more attacks than anyone else.";
	private static final String KNIGHT_ATTACKSOUND = "res/sounds/sword_sound.wav";
	
	public Knight(Point initialPosition) {

		super("Knight", KNIGHT_DESCRIPTION, KNIGHT_HEALTH, KNIGHT_MANA,
				KNIGHT_STRENGTH, KNIGHT_DEFENCE, initialPosition, true,
				KNIGHT_MOVEDISTANCE, KNIGHT_ATTACKDISTANCE, KNIGHT_IKNIGHTPATH, KNIGHT_ATTACKSOUND);
	}
	
	public static String getUnitDescription()
	{
		return KNIGHT_DESCRIPTION;
	}
	
	public static Image returnTexture(){
		Image[][] knightSprites = model.LoadSprites.loadSpriteSheet(KNIGHT_IKNIGHTPATH, 4, 3, Client.BLOCKSIZE);
		return knightSprites[0][1];
	}


	public void revive()
	{
		setAlive();
		setHealth(KNIGHT_HEALTH);
	}
	
	public double getPercentHealth()
	{
		return ((double) getHealth()) / KNIGHT_HEALTH;
	}
	
	public double getPercentMana()
	{
		return ((double) getMana()) / KNIGHT_MANA;
	}

	@Override
	public boolean addHealth(int delta)
	{
		/*
		 * case 1: trying to add health to full health unit or dead unit just
		 * return false case 2: trying to add health to unit with less than full
		 * health but health will go past max health set to max health case 3:
		 * trying to decrease health below 0 set to 0, setDead
		 * 
		 * case 2 and 3 returns true
		 */
		if (!isAlive())
			// case 1
			return false;
		if (delta > 0)
		{
			// can't add to full health unit
			if (getHealth() == KNIGHT_HEALTH)
				return false;
			else if (getHealth() + delta > KNIGHT_HEALTH)
				// case 2
				setHealth(KNIGHT_HEALTH);
		}
		// else delta is <= 0
		else
		{
			if (getHealth() + delta <= 0)
			{
				// case 3
				setHealth(0);
				setDead();
			}
			else
				setHealth(getHealth() + delta);
		}
		return true;
	}

	@Override
	public boolean addMana(int delta)
	{
		/*
		 * case 1: trying to add mana to full mana unit or dead unit just return
		 * false case 2: trying to add mana to unit with less than full mana but
		 * mana will go past max set to max mana defined by the final variable
		 * case 3: trying to decrease mana below 0 set to 0
		 * 
		 * case 2 and 3 returns true
		 */
		if (!isAlive())
			return false;
		if (delta > 0)
		{
			if (getMana() == KNIGHT_MANA)
				return false;
			else if (getMana() + delta > KNIGHT_MANA)
				setMana(KNIGHT_MANA);
		}
		else
		{
			// delta is negative
			setMana(getMana() + delta);
		}
		
		return true;
	}

	@Override
	public boolean addStrength(int delta)
	{
		/*
		 * case 0: dead unit, return false
		 * case 1: adding strength takes it below 0 set to 0 otherwise, just add
		 * and then return true
		 */
		if (!isAlive())
			return false;
		else if (getStrength() + delta < 0)
			setStrength(0);
		else
			setStrength(getStrength() + delta);

		return true;
	}

	@Override
	public boolean addDefence(int delta)
	{
		/*
		 * case 0: dead unit, return false
		 * case 1: adding defence takes it below 0 set to 0 otherwise, just add
		 * and then return true
		 */
		if (!isAlive())
			return false;
		else if (getDefence() + delta < 0)
			setDefence(0);
		else
			setDefence(getDefence() + delta);

		return true;
	}

	public boolean isMaxHealth()
	{
		return getHealth() == KNIGHT_HEALTH;
	}
	
	public boolean useMagic(Point p)
	{
		int magicCost = 50;
		
		if (getMana() < magicCost)
			return false;
		
		addMana(-magicCost);
		return true;
	}
}
