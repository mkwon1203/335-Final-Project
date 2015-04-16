package model;

import java.awt.Image;

public abstract class Block
{
	private boolean solidState;
	private boolean animated;
	private Image texture;
	
	public boolean isSolid()
	{
		return solidState;
	}
	
	public abstract void draw();
}
