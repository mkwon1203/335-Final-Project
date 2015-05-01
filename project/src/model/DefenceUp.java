package model;

public class DefenceUp extends Buff
{
	private static final int DEFENCEUP_VALUE = 5;
	public DefenceUp()
	{
		// placeholder values
		super(1, "Defence Up", "Defence up description", "res/sprites/items/defenceup.png");
	}

	@Override
	public boolean useItem(CharacterInterface ch)
	{
		// use ch's set defence method
		return ch.addDefence(DEFENCEUP_VALUE);
	}
	
	
}
