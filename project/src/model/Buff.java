package model;

import java.awt.Image;

public abstract class Buff extends Item
{
	/**
	 * Constructor for the Buff items
	 * @param costInput	cost of item
	 * @param nameInput	name of item
	 * @param descriptionInput	description of item
	 * @param textureInput	path of item image
	 */
	public Buff(int costInput, String nameInput, String descriptionInput, String textureInput)
	{
		super(costInput, nameInput, descriptionInput, textureInput);
	}
}
