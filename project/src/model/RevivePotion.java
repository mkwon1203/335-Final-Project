package model;

import java.awt.Image;

public class RevivePotion extends Potion
{

	public RevivePotion()
	{
		// placeholder values
		// need to update Image parameter being passed in
		super(1, "Revive Potion", "Revive potion description", null);
	}

	@Override
	public boolean useItem(Character ch)
	{
		// use ch's setAlive method
		if (ch.isAlive())
			return false;
		// else
		ch.setAlive(true);
		return true;
	}

}
