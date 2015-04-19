package model;

import java.awt.Point;

public class Priest extends Character {

	private String myName;
	private String myType;
	private int myHealth;
	private int myStrength;
	private int myMana;
	private Point myLocation;

	public Priest(String name, String type, int health, int strength, int mana,
			Point location) {

		super(name, type, health, strength, location);

		myMana = mana;

	}

	@Override
	public int moveDistance() {

		int priestMove = 4;

		return priestMove;

	}

	public void setPosition(Point anotherLocation) {

		myLocation = anotherLocation;
	}
}
