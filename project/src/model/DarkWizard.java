package model;

import java.awt.Image;
import java.awt.Point;

public class DarkWizard extends Enemy {

	private String myName;
	private String myType;
	private int myHealth;
	private int myStrength;
	private Point myPosition;
	private int myMana;

	public DarkWizard(String name, String type, int health, int strength,
			int mana, Point position) {

		super(name, type, health, strength, position);
		myMana = mana;

	}

	@Override
	public int getHealth() {

		return myHealth;
	}

	@Override
	public int getMana() {

		return myMana;
	}

	@Override
	public int getEnergy() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getStrength() {

		return myStrength;
	}

	@Override
	public int getDefence() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Point getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAlive() {

		if (myHealth > 0)

			return true;
		else
			return false;

	}

	@Override
	public int moveDistance() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int attackDistance() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Image getTexture() {
		// TODO Auto-generated method stub
		return null;
	}

}
