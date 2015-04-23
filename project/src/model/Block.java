package model;

import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Block
{
	private boolean solidState;
	private boolean occupied;
	private boolean animated;
	private Image texture;
	
	public Block(boolean solidState, boolean occupied, boolean animated, String textureName)
	{
		this.solidState = solidState;
		this.occupied = occupied;
		this.animated = animated;
		try
		{
			this.texture = ImageIO.read(new File(textureName));
		}
		catch (FileNotFoundException fnfe)
		{
			fnfe.printStackTrace();
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}
	
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
	
	public boolean isOccupied()
	{
		return occupied;
	}
	
	public boolean isAnimated()
	{
		return animated;
	}
	
	public Image getTexture()
	{
		return texture;
	}
	
	public void setOccupied(boolean newOccupied)
	{
		occupied = newOccupied;
	}
	
	// placeholder for console GUI
	public String toString()
	{
		String toReturn = "";
		
		if (solidState)
			toReturn += "W"; // for wall
		else if (!solidState && !occupied)
			toReturn += "F"; // for floor
		else if (occupied)
			toReturn += "C"; // for character
		else
			toReturn += "E"; // error!
		
		return toReturn;
	}
	
	/**
	 * This method will draw the block tile using the texture 
	 */
	public abstract void draw();
}
