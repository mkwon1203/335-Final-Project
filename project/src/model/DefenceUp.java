package model;

public class DefenceUp extends Buff
{
	private static final int DEFENCEUP_VALUE = 5;
	
	/**
	 * Constructor for the defence up item
	 */
	public DefenceUp()
	{
		super(40, "Defence Up", "Defence up item will increase the defence of the selected unit by 5 permanently.", "res/sprites/items/defenceup.png");
	}

	/**
	 * uses the item on the given Character ch using the addDefence method of character
	 * @param ch	the Character to use the item on
	 * @return	true if item was used succesfully
	 * 			false if item could not be used
	 */
	@Override
	public boolean useItem(CharacterInterface ch)
	{
		// use ch's set defence method
		return ch.addDefence(DEFENCEUP_VALUE);
	}
	
	
}
