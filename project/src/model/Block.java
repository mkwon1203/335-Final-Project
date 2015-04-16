package model;

import java.awt.Image;

public abstract class Block
{
	// comment
	private boolean solidState;
	private boolean animated;
	private Image texture;
	
	/**
	 * This method returns a boolean value whether the block is solid or not
	 * A solid block will not allow characters to travel through, while a
	 * non-solid block will allow travel
	 * @return
	 */
	public boolean isSolid()
	{
		return solidState;
	}
	
	/**
	 * This method will draw the block tile using the texture 
	 */
	public abstract void draw();
}
