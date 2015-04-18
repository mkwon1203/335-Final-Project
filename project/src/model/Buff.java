package model;

import java.awt.Image;

public abstract class Buff extends Item
{
	public Buff(int costInput, String nameInput, String descriptionInput, Image textureInput)
	{
		super(costInput, nameInput, descriptionInput, textureInput);
	}
}