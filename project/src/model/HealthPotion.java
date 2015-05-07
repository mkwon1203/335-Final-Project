package model;

public class HealthPotion extends Potion
{
	private static final int HEALTHPOTION_VALUE = 50;
	
	/**
	 * Constructor for the health potion
	 */
	public HealthPotion()
	{
		super(20, "Health Potion", "Health potion will restore health of a character by 50", "res/sprites/items/healthpotion.png");
	}
	
	/**
	 * uses the item on the given Character ch using the addHealth method of character
	 * @param ch	the Character to use the item on
	 * @return	true if item was used succesfully
	 * 			false if item could not be used
	 */
	public boolean useItem(CharacterInterface ch)
	{
		// use ch's setHealth method and add to it
		return ch.addHealth(HEALTHPOTION_VALUE);
	}
}
