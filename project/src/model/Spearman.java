package model;

import java.awt.Point;

public class Spearman extends Character {

	public Spearman(String name, String type, int health, int strength,
			Point position) {
		super(name, type, health, strength, position);
	}

	@Override
	public int moveDistance() {

		int spearMove = 3;

		return spearMove;
	}

}
