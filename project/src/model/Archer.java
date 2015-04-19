package model;

import java.awt.Image;
import java.awt.Point;

import javax.imageio.ImageIO;

public class Archer extends Character {

	private static final int ARCHER_HEALTH = 100;
	private static final int ARCHER_MANA = 0;
	private static final int ARCHER_STRENGTH = 10;
	private static final int ARCHER_DEFENCE = 10;
	private static final int ARCHER_MOVEDISTANCE = 1;
	private static final int ARCHER_ATTACKDISTANCE = 1;
	private static final String ARCHER_IMAGEPATH = "PATHPATH";
	
	public Archer(Point initialPosition)
	{	
		super("Archer", "Archer desription", ARCHER_HEALTH, ARCHER_MANA, ARCHER_STRENGTH,
				ARCHER_DEFENCE, initialPosition, true, ARCHER_MOVEDISTANCE, ARCHER_ATTACKDISTANCE,
				ARCHER_IMAGEPATH);
	}

	@Override
	public boolean addHealth()
	{
		return false;
	}

	@Override
	public boolean addMana()
	{
		return false;
	}

	@Override
	public boolean addStrength()
	{
		return false;
	}

	@Override
	public boolean addDefence()
	{
		return false;
	}
}
