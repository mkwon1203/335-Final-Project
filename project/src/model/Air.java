package model;

public class Air extends Block
{
	public Air()
	{
		// palceholder values
		super(false, false, null, "Air");
	}

	@Override
	public void draw()
	{
	}

	@Override
	public String toStringGUI()
	{
		return "[A]";
	}
	
	
}
