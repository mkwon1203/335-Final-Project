package model;

public class AttackUp extends Buff
{
	private static final int ATTACKUP_VALUE = 5;
	
	/**
	 * Constructor for the attack up item
	 */
	public AttackUp()
	{
		super(40, "Attack Up", "Attack Up item will increase the strength of a character permanently by 5.", "res/sprites/items/attackup.png");
	}
	
	/**
	 * uses the item on the given Character ch using the addStrength method of character
	 * @param ch	the Character to use the item on
	 * @return	true if item was used succesfully
	 * 			false if item could not be used
	 */
	@Override
	public boolean useItem(CharacterInterface ch)
	{
		// use ch's setAttack method
		return ch.addStrength(ATTACKUP_VALUE);
	}
	
	
}
