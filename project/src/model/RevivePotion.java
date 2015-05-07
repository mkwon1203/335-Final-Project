package model;

import java.awt.Image;

public class RevivePotion extends Potion
{
	/**
	 * Constructor for the revive potion
	 * @param costInput	cost of item
	 * @param nameInput	name of item
	 * @param descriptionInput	description of item
	 * @param textureInput	path of item image
	 */
	public RevivePotion()
	{
		super(60, "Revive Potion", "Revive potion will revive the selected character to full health! This can only be used on dead units", "res/sprites/items/revivepotion.png");
	}

	/**
	 * uses the item on the given Character ch. If character is alive, then 
	 * nothing happens as revive can only be used on dead units.
	 * If character is dead, then it is revived using its revive() method
	 * @param ch	the Character to use the item on
	 * @return	true if item was used succesfully
	 * 			false if item could not be used
	 */
	@Override
	public boolean useItem(CharacterInterface ch)
	{
		// use ch's setAlive method
		if (ch.isAlive())
			return false;
		ch.revive();
		return true;
	}

}
