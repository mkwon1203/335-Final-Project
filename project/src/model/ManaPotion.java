package model;

public class ManaPotion extends Potion
{
	private static final int MANAPOTION_VALUE = 10;
	public ManaPotion()
	{
		// placeholder values
		super(1, "Mana Potion", "Mana potion description", "res/sprites/items/manapotion.png");
	}

	@Override
	public boolean useItem(CharacterInterface ch)
	{
		// use ch's setMana method
		return ch.addMana(MANAPOTION_VALUE);
	}
}
