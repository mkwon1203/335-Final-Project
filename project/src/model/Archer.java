package model;

import java.awt.Point;

public class Archer extends Character {

	public Archer(String name, String type, int health, int strength,
			Point position) {

		super(name, type, health, strength, position);
	}

	@Override
	public int moveDistance() {

		int archerMove = 2;
		return archerMove;
	}

}
