package model;

import java.awt.Point;

public class Archer extends Character
{

	private static final int ARCHER_HEALTH = 100;
	private static final int ARCHER_MANA = 0;
	private static final int ARCHER_STRENGTH = 10;
	private static final int ARCHER_DEFENCE = 10;
	private static final int ARCHER_MOVEDISTANCE = 1;
	private static final int ARCHER_ATTACKDISTANCE = 1;
	private static final String ARCHER_IMAGEPATH = "PATHPATH";

	public Archer(Point initialPosition)
	{
		super("Archer", "Archer desription", ARCHER_HEALTH, ARCHER_MANA,
				ARCHER_STRENGTH, ARCHER_DEFENCE, initialPosition, true,
				ARCHER_MOVEDISTANCE, ARCHER_ATTACKDISTANCE, ARCHER_IMAGEPATH);
	}

	public void revive()
	{
		setAlive();
		setHealth(ARCHER_HEALTH);
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
		if (getHealth() == ARCHER_HEALTH || !isAlive())
			// case 1
			return false;
		else if (getHealth() + delta > ARCHER_HEALTH)
			// case 2
			setHealth(ARCHER_HEALTH);
		else if (getHealth() + delta <= 0)
		{
			// case 3
			setHealth(0);
			setDead();
		}
		else
			setHealth(getHealth() + delta);
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
		if (getMana() == ARCHER_MANA || !isAlive())
			return false;
		else if (getMana() + delta > ARCHER_MANA)
			setMana(ARCHER_MANA);
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
}