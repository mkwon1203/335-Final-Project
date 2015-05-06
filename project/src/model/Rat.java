package model;

import java.awt.Image;
import java.awt.Point;

public class Rat extends Enemy {

	private static final int RAT_HEALTH = 100;
	private static final int RAT_MANA = -1;
	private static final int RAT_STRENGTH = 10;
	private static final int RAT_DEFENCE = 10;
	private static final int RAT_MOVEDISTANCE = 1;
	private static final int RAT_ATTACKDISTANCE = 1;
	private static final String RAT_IMAGEPATH = "res/sprites/units/rat.png";
	private static final String RAT_DESCRIPTION = "RAT description";
	private static final int RAT_VALUE = 5;

	public Rat(Point initialPosition) {
		super("Rat", RAT_DESCRIPTION, RAT_HEALTH, RAT_MANA, RAT_STRENGTH,
				RAT_DEFENCE, initialPosition, true, RAT_MOVEDISTANCE,
				RAT_ATTACKDISTANCE, RAT_IMAGEPATH, RAT_VALUE);
	}
	
	public static String getUnitDescription()
	{
		return RAT_DESCRIPTION;
	}

	public void revive()
	{
		setAlive();
		setHealth(RAT_HEALTH);
	}
	
	public double getPercentHealth()
	{
		return ((double) getHealth()) / RAT_HEALTH;
	}
	
	public double getPercentMana()
	{
		return ((double) getMana()) / RAT_MANA;
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
			if (getHealth() == RAT_HEALTH)
				return false;
			else if (getHealth() + delta > RAT_HEALTH)
				// case 2
				setHealth(RAT_HEALTH);
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
		if (getMana() == RAT_MANA || !isAlive())
			return false;
		else if (getMana() + delta > RAT_MANA)
			setMana(RAT_MANA);
		else if (getMana() + delta <= 0)
			setMana(0);
		else
			setMana(getMana() + delta);
		
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
		return getHealth() == RAT_HEALTH;
	}
}
