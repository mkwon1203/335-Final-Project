package model;

public class HealthPotion extends Potion
{
	private static final int HEALTHPOTION_VALUE = 50;
	
	public HealthPotion()
	{
		// placeholder values
		super(1, "Health Potion", "Health potion description", null);
	}
	
	public boolean useItem(CharacterInterface ch)
	{
		// use ch's setHealth method and add to it
		return ch.addHealth(HEALTHPOTION_VALUE);
	}
}
