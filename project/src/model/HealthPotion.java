package model;

public class HealthPotion extends Potion
{
	public HealthPotion()
	{
		// placeholder values
		super(1, "Health Potion", "Health potion description", null);
	}
	
	public boolean useItem(Character ch)
	{
		// use ch's setHealth method and add to it
		ch.setHealth(50);
	}
}
