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

	public Block(boolean solidState, boolean animated, String textureName,
			String typeName)
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
	 * This method returns a boolean value whether the block is solid or not.
	 * True if block is solid, false if block is not solid.
	 * A solid block will not allow a character to move on the block or pass through
	 * the block. A nonsolid block will allow movement and character to move on the
	 * block.
	 * 
	 * @return boolean whether the block is solid or not
	 */
	public boolean isSolid()
	{
		return solidState;
	}

	/**
	 * Returns the CharacterInterface located on the block.
	 * If there is no CharacterInterface on the block, then null is returned
	 * 
	 * @return	CharacterInterface at the block
	 */
	public CharacterInterface getCharacter()
	{
		return character;
	}

	/**
	 * Returns whether or not the block is currently animated.
	 * True if block is animated, false if block is not animated.
	 * 
	 * @return	boolean whether block is animated or not
	 */
	public boolean isAnimated()
	{
		return animated;
	}

	/**
	 * Returns the texture of 
	 * @return
	 */
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

	public void setImage(String fileName)
	{
		try
		{
			this.texture = ImageIO.read(new File(fileName));
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
}
