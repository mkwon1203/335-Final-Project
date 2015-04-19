package model;

public class DefenceUp extends Buff
{
	private static final int DEFENCEUP_VALUE = 5;
	public DefenceUp()
	{
		// placeholder values
		super(1, "Defence Up", "Defence up description", null);
	}

	@Override
	public boolean useItem(Character ch)
	{
		// use ch's set defence method
		return ch.addDefence(DEFENCEUP_VALUE);
	}
	
	
}
