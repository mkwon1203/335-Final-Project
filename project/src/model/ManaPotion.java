package model;

public class ManaPotion extends Potion
{
	
	public ManaPotion()
	{
		// placeholder values
		super(1, "Mana Potion", "Mana potion description", null);
	}

	@Override
	public boolean useItem(Character ch)
	{
		// use ch's setMana method
	}
}
