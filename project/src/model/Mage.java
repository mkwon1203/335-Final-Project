package model;

import java.awt.Image;
import java.awt.Point;

import controller.Client;

public class Mage extends Character {

	private static final int MAGE_HEALTH = 30;
	private static final int MAGE_MANA = 100;
	private static final int MAGE_STRENGTH = 10;
	private static final int MAGE_DEFENCE = 3;
	private static final int MAGE_MOVEDISTANCE = 3;
	private static final int MAGE_ATTACKDISTANCE = 3;
	private static final String MAGE_IMAGEPATH = "res/sprites/units/mage.png";
	private static final String MAGE_DESCRIPTION = "A Mage's attack will make the enemy tremble in fear, but if a Mage were to be cought in close quarters; they could be in danger.";
	private static final String MAGE_ATTACKSOUND = "res/sounds/magic_sound.wav";
	
	public Mage(Point initialPosition) {

		super("Mage", MAGE_DESCRIPTION, MAGE_HEALTH, MAGE_MANA, MAGE_STRENGTH,
				MAGE_DEFENCE, initialPosition, true, MAGE_MOVEDISTANCE,
				MAGE_ATTACKDISTANCE, MAGE_IMAGEPATH, MAGE_ATTACKSOUND);
	}
	
	public static String getUnitDescription()
	{
		return MAGE_DESCRIPTION;
	}
	
	public static Image returnTexture(){
		Image[][] mageSprites = model.LoadSprites.loadSpriteSheet(MAGE_IMAGEPATH, 4, 3, Client.BLOCKSIZE);
		return mageSprites[0][1];
	}


	public void revive()
	{
		setAlive();
		setHealth(MAGE_HEALTH);
	}
	
	public double getPercentHealth()
	{
		return ((double) getHealth()) / MAGE_HEALTH;
	}
	
	public double getPercentMana()
	{
		return ((double) getMana()) / MAGE_MANA;
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
			if (getHealth() == MAGE_HEALTH)
				return false;
			else if (getHealth() + delta > MAGE_HEALTH)
				// case 2
				setHealth(MAGE_HEALTH);
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
			if (getMana() == MAGE_MANA)
				return false;
			else if (getMana() + delta > MAGE_MANA)
				setMana(MAGE_MANA);
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
		return getHealth() == MAGE_HEALTH;
	}
	
	public boolean useMagic(CharacterInterface target)
	{
		int magicCost = 33;
		int magicEffect = -15;
		
		if (getMana() < magicCost)
			return false;
		
		// subtract mana accordingly
		addMana(-magicCost);
		
		// mage will attack
		return target.addHealth(magicEffect);
	}
}
