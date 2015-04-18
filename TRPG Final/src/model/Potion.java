package model;

import java.awt.Image;

public abstract class Potion extends Item {
	private Character role;

	public Potion(int costInput, String descriptionInput, Image textureInput) {
		super(costInput, descriptionInput, textureInput);
	}

	public void usePotion(Character ch) {

	}

}
