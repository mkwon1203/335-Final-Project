package model;

import java.awt.Point;

public class Spearman extends Character {

	private static final int SPEARMAN_HEALTH = 100;
	private static final int SPEARMAN_MANA = 0;
	private static final int SPEARMAN_STRENGTH = 10;
	private static final int SPEARMAN_DEFENCE = 10;
	private static final int SPEARMAN_MOVEDISTANCE = 1;
	private static final int SPEARMAN_ATTACKDISTANCE = 1;
	private static final String SPEARMAN_IMAGEPATH = "res/sprites/units/spearman.png";
	private static final String SPEARMAN_DESCRIPTION = "SPEARMAN description";

	public Spearman(Point initialPosition) {
		super("Spearman", SPEARMAN_DESCRIPTION, SPEARMAN_HEALTH,
				SPEARMAN_MANA, SPEARMAN_STRENGTH, SPEARMAN_DEFENCE,
				initialPosition, true, SPEARMAN_MOVEDISTANCE,
				SPEARMAN_ATTACKDISTANCE, SPEARMAN_IMAGEPATH);
	}
	
	public static String getUnitDescription()
	{
		return SPEARMAN_DESCRIPTION;
	}
	
	public void revive()
	{
		setAlive();
		setHealth(SPEARMAN_HEALTH);
	}
	
	public double getPercentHealth()
	{
		return ((double) getHealth()) / SPEARMAN_HEALTH;
	}
	
	public double getPercentMana()
	{
		return ((double) getMana()) / SPEARMAN_MANA;
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
			if (getHealth() == SPEARMAN_HEALTH)
				return false;
			else if (getHealth() + delta > SPEARMAN_HEALTH)
				// case 2
				setHealth(SPEARMAN_HEALTH);
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
		if (getMana() == SPEARMAN_MANA || !isAlive())
			return false;
		else if (getMana() + delta > SPEARMAN_MANA)
			setMana(SPEARMAN_MANA);
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

	@Override
	public String toStringGUI()
	{
		return "S";
	}
}
