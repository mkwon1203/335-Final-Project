package model;

import java.awt.Point;

public class Priest extends Character {

	private static final int PRIEST_HEALTH = 100;
	private static final int PRIEST_MANA = 0;
	private static final int PRIEST_STRENGTH = 10;
	private static final int PRIEST_DEFENCE = 10;
	private static final int PRIEST_MOVEDISTANCE = 1;
	private static final int PRIEST_ATTACKDISTANCE = 1;
	private static final String PRIEST_IMAGEPATH = "Priest Path";

	public Priest(Point initialPosition) {
		super("Priest", "Priest desription", PRIEST_HEALTH, PRIEST_MANA,
				PRIEST_STRENGTH, PRIEST_DEFENCE, initialPosition, true,
				PRIEST_MOVEDISTANCE, PRIEST_ATTACKDISTANCE, PRIEST_IMAGEPATH);
	}

	public void revive()
	{
		setAlive();
		setHealth(PRIEST_HEALTH);
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
		if (getHealth() == PRIEST_HEALTH || !isAlive())
			// case 1
			return false;
		else if (getHealth() + delta > PRIEST_HEALTH)
			// case 2
			setHealth(PRIEST_HEALTH);
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
		if (getMana() == PRIEST_MANA || !isAlive())
			return false;
		else if (getMana() + delta > PRIEST_MANA)
			setMana(PRIEST_MANA);
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
