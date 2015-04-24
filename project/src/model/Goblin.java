package model;

import java.awt.Point;

public class Goblin extends Enemy {
	private static final int GOBLIN_HEALTH = 100;
	private static final int GOBLIN_MANA = 0;
	private static final int GOBLIN_STRENGTH = 10;
	private static final int GOBLIN_DEFENCE = 10;
	private static final int GOBLIN_MOVEDISTANCE = 1;
	private static final int GOBLIN_ATTACKDISTANCE = 1;
	private static final String GOBLIN_IMAGEPATH = "Goblin Path";

	public Goblin(Point initialPosition) {
		super("Goblin", "Goblin desription", GOBLIN_HEALTH, GOBLIN_MANA,
				GOBLIN_STRENGTH, GOBLIN_DEFENCE, initialPosition, true,
				GOBLIN_MOVEDISTANCE, GOBLIN_ATTACKDISTANCE, GOBLIN_IMAGEPATH);
	}

	public void revive()
	{
		setAlive();
		setHealth(GOBLIN_HEALTH);
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
			if (getHealth() == GOBLIN_HEALTH)
				return false;
			else if (getHealth() + delta > GOBLIN_HEALTH)
				// case 2
				setHealth(GOBLIN_HEALTH);
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
		if (getMana() == GOBLIN_MANA || !isAlive())
			return false;
		else if (getMana() + delta > GOBLIN_MANA)
			setMana(GOBLIN_MANA);
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
