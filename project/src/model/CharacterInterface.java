package model;

import java.awt.Image;
import java.awt.Point;

public interface CharacterInterface
{
	public abstract int getHealth();
	public abstract int getMana();
	public abstract int getEnergy();
	public abstract int getStrength();
	public abstract int getDefence();
	public abstract Point getLocation();
	public abstract boolean isAlive();
	public abstract int moveDistance();
	public abstract int attackDistance();
	public abstract Image getTexture();
}
