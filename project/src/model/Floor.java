package model;

public class Floor extends Block
{
	public Floor()
	{
		super(false, false, "res/sprites/brickFloor.png", "Floor");
	}

	@Override
	public void draw()
	{
	}

	@Override
	public String toStringGUI()
	{
		String middle = "";
		if (isOccupied())
			middle += getCharacter().toStringGUI();
		else
			middle += " ";
		
		return "[" + middle + "]";
	}
}
