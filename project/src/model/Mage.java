package model;

import java.awt.Point;

public class Mage extends Character {

	public Mage(String name, String type, int health, int strength,
			Point position) {

		super(name, type, health, strength, position);
	}

	@Override
	public int moveDistance() {
		// TODO Auto-generated method stub
		int mageMove = 2;
		return mageMove;
	}

}
