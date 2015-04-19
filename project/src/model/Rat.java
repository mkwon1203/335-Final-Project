package model;

import java.awt.Image;
import java.awt.Point;

public class Rat extends Enemy {

	public Rat(String name, String type, int health, int strength,
			Point position) {
		super(name, type, health, strength, position);
	}

	@Override
	public int getMana() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getEnergy() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getStrength() {
		// TODO Auto-generated method stub
		return 0;
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

	@Override
	public boolean isAlive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getHealth() {
		// TODO Auto-generated method stub
		return 0;
	}
}
