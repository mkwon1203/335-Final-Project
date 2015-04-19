package model;

import java.awt.Point;

public class Knight extends Character {

	// Updated upstream

	// Stashed changes
	public Knight(String name, String type, int health, int strength,
			Point position) {

		super(name, type, health, strength, position);

	}

	@Override
	public int moveDistance() {
		// TODO Auto-generated method stub
		int knightMove = 1;
		return knightMove;
	}

}
