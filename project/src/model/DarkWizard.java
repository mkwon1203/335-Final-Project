package model;

import java.awt.Point;

public class DarkWizard extends Enemy
{
	private static final int DARKWIZARD_HEALTH = 100;
	private static final int DARKWIZARD_MANA = 0;
	private static final int DARKWIZARD_STRENGTH = 8;
	private static final int DARKWIZARD_DEFENCE = 2;
	private static final int DARKWIZARD_MOVEDISTANCE = 3;
	private static final int DARKWIZARD_ATTACKDISTANCE = 3;
	private static final String DARKWIZARD_IMAGEPATH = "res/sprites/units/darkwizard.png";
	private static final String DARKWIZARD_DESCRIPTION = "DARKWIZARD description";
	private static final String DARKWIZARD_ATTACKSOUND = "res/sounds/magic_sound.wav";
	private static final int DARKWIZARD_VALUE = 5;

	public DarkWizard(Point initialPosition)
	{

		super("Darkwizard", DARKWIZARD_DESCRIPTION, DARKWIZARD_HEALTH,
				DARKWIZARD_MANA, DARKWIZARD_STRENGTH, DARKWIZARD_DEFENCE,
				initialPosition, true, DARKWIZARD_MOVEDISTANCE,
				DARKWIZARD_ATTACKDISTANCE, DARKWIZARD_IMAGEPATH, DARKWIZARD_VALUE, DARKWIZARD_ATTACKSOUND);
	}
	
	public static String getUnitDescription()
	{
		return DARKWIZARD_DESCRIPTION;
	}

	public void revive()
	{
		setAlive();
		setHealth(DARKWIZARD_HEALTH);
	}
	
	public double getPercentHealth()
	{
		return ((double) getHealth()) / DARKWIZARD_HEALTH;
	}
	
	public double getPercentMana()
	{
		return ((double) getMana()) / DARKWIZARD_MANA;
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
			if (getHealth() == DARKWIZARD_HEALTH)
				return false;
			else if (getHealth() + delta > DARKWIZARD_HEALTH)
				// case 2
				setHealth(DARKWIZARD_HEALTH);
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
		if (getMana() == DARKWIZARD_MANA || !isAlive())
			return false;
		else if (getMana() + delta > DARKWIZARD_MANA)
			setMana(DARKWIZARD_MANA);
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
		 * case 0: dead unit, return false case 1: adding strength takes it
		 * below 0 set to 0 otherwise, just add and then return true
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
		 * case 0: dead unit, return false case 1: adding defence takes it below
		 * 0 set to 0 otherwise, just add and then return true
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
		return getHealth() == DARKWIZARD_HEALTH;
	}
}
