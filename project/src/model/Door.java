package model;

public class Door extends Block
{
	private int doorType;
	
	public static final int VERTICAL = 0;
	public static final int HORIZONTAL = 1;
	
	public Door(int orientation)
	{
		super(true, false, "res/sprites/door.png", "Door");
		doorType = orientation;
		updateImage();
	}
	
	public boolean isOpen()
	{
		return !isSolid();
	}
	
	public void open()
	{
		setSolid(false);
		updateImage();
	}
	
	public void close()
	{
		setSolid(true);
		updateImage();
	}
	
	private void updateImage()
	{
		// TODO: need to change the image names later
		if (doorType == VERTICAL)
		{
			if (isOpen())
				setImage("res/sprites/verticalOpenDoor.png");
			else
				setImage("res/sprites/verticalClosedDoor.png");
		}
		else if (doorType == HORIZONTAL)
		{
			if (isOpen())
				setImage("res/sprites/horizontalOpenDoor.png");
			else
				setImage("res/sprites/horizontalClosedDoor.png");
		}
	}
}
