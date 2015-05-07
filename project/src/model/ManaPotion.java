package model;

public class ManaPotion extends Potion
{
	private static final int MANAPOTION_VALUE = 30;
	
	/**
	 * Constructor for the Mana potion
	 * @param costInput	cost of item
	 * @param nameInput	name of item
	 * @param descriptionInput	description of item
	 * @param textureInput	path of item image
	 */
	public ManaPotion()
	{
		super(20, "Mana Potion", "Mana potion wil restore the mana of the selected unit by 30.", "res/sprites/items/manapotion.png");
	}

	/**
	 * uses the item on the given Character ch using the addMana method of character
	 * @param ch	the Character to use the item on
	 * @return	true if item was used succesfully
	 * 			false if item could not be used
	 */
	@Override
	public boolean useItem(CharacterInterface ch)
	{
		// use ch's setMana method
		return ch.addMana(MANAPOTION_VALUE);
	}
}
