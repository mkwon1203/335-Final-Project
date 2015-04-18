package model;

import java.awt.Image;

public class RevivePotion extends Potion
{

	public RevivePotion()
	{
		super(1, "Revive potion", null);
	}

	@Override
	public void useItem(Character ch)
	{
		// use ch's setAlive method
	}

}
