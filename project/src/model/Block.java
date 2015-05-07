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
	 * Returns the texture of the block as an Image
	 * @return	Image of the block
	 */
	public Image getTexture()
	{
		return texture;
	}

	/**
	 * Returns whether or not a character is currently on the block.
	 * 
	 * @return true if character is on the block
	 * 			false if there is no character on the block
	 */
	public boolean isOccupied()
	{
		return character != null;
	}

	/**
	 * Sets the character on the block to be the input character, ch
	 * @param ch	the CharacterInterface to be put on the block
	 */
	public void setCharacter(CharacterInterface ch)
	{
		character = ch;
	}

	/**
	 * Sets the block to be the input solid value
	 * @param solid	the input boolean to determine whether the block will
	 * 			become solid or not
	 */
	public void setSolid(boolean solid)
	{
		solidState = solid;
	}

	/**
	 * Sets the block's animated value
	 * @param animate	the new animated value of the block
	 */
	public void setAnimated(boolean animate)
	{
		animated = animate;
	}

	/**
	 * Sets the block's Image value
	 * @param fileName	the file path of the new Image for the block
	 */
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
