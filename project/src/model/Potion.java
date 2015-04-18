package model;

import java.awt.Image;

public abstract class Potion extends Item {

	public Potion(int costInput, String nameInput, String descriptionInput, Image textureInput) {
		super(costInput, nameInput, descriptionInput, textureInput);
	}

}
