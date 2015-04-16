package model;

import java.awt.Image;
import java.awt.Point;

public class Knight extends Character
{

	@Override
	public int getHealth()
	{
		return 0;
	}

	@Override
	public int getMana()
	{
		return 0;
	}

	@Override
	public int getEnergy()
	{
		return 0;
	}

	@Override
	public int getStrength()
	{
		return 0;
	}

	@Override
	public int getDefence()
	{
		return 0;
	}

	@Override
	public Point getLocation()
	{
		return null;
	}

	@Override
	public boolean isAlive()
	{
		return false;
	}

	@Override
	public int moveDistance()
	{
		return 0;
	}

	@Override
	public int attackDistance()
	{
		return 0;
	}

	@Override
	public Image getTexture()
	{
		return null;
	}

}
