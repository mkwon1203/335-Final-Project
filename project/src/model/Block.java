package model;

import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Block
{
	private boolean solidState;
	private CharacterInterface character;
	private boolean animated;
	private Image texture;
	private String type;
	
	public Block(boolean solidState, boolean animated, String textureName, String typeName)
	{
		this.solidState = solidState;
		character = null;
		this.animated = animated;
		type = typeName;
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
	
	public CharacterInterface getCharacter()
	{
		return character;
	}
	
	public boolean isAnimated()
	{
		return animated;
	}
	
	public Image getTexture()
	{
		return texture;
	}
	
	public boolean isOccupied()
	{
		return character != null;
	}
	
	public void setCharacter(CharacterInterface ch)
	{
		character = ch;
	}
	
	public void setSolid(boolean solid)
	{
		solidState = solid;
	}
	
	public void setAnimated(boolean animate)
	{
		animated = animate;
	}
	
	// placeholder for console GUI
	public String toString()
	{
		return type;
	}
	
	public abstract String toStringGUI();
	
	/**
	 * This method will draw the block tile using the texture 
	 */
	public abstract void draw();
}
