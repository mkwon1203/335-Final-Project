package model;

public class ManaPotion extends Potion
{
	private static final int MANAPOTION_VALUE = 10;
	public ManaPotion()
	{
		// placeholder values
		super(1, "Mana Potion", "Mana potion description", null);
	}

	@Override
	public boolean useItem(Character ch)
	{
		// use ch's setMana method
		return ch.addMana(MANAPOTION_VALUE);
	}
}
