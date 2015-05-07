package model;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Item implements Comparable<Item>{
	private int cost;
	private String name;
	private String description;
	private Image texture;

	public Item(int costInput, String nameInput, String descriptionInput, String texture) {
		cost = costInput;
		name = nameInput;
		description = descriptionInput;
		try{
			this.texture = ImageIO.read(new File(texture));
		}
		catch (IOException ioex)
		{
			ioex.printStackTrace();
		}
	}
	
	/**
	 * Returns the cost of the item
	 * @return	cost of item as int
	 */
	public int getCost()
	{
		return cost;
	}
	
	/**
	 * Returns the name of the item
	 * @return	name of item as String
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Returns the description of the item
	 * @return	description of item as String
	 */
	public String getDescription()
	{
		return description;
	}
	
	/**
	 * Returns the image of the item
	 * @return	image of item as Image object
	 */
	public Image getTexture()
	{
		return texture;
	}
	
	/**
	 * The item uses itself on the given character ch and returns
	 * a boolean value to declare whether or not using the item
	 * was succesful
	 * @param ch	the character that the item will be used on
	 * @return true if item was succesfully used
	 * 			false if item was not used
	 */
	public abstract boolean useItem(CharacterInterface ch);
	
	
	public int compareTo(Item other)
	{
		return name.compareTo(other.getName());
	}
	
	@Override
	public boolean equals(Object other)
	{
		if (other instanceof Item)
		{
			return ((Item) other).getName().equals(name);
		}
		
		return false;
	}
}
