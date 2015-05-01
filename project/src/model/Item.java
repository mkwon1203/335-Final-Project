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
	
	public int getCost()
	{
		return cost;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public Image getTexture()
	{
		return texture;
	}
	
	public abstract boolean useItem(CharacterInterface ch);
	
	// comparing is done with the name of the item
	public int compareTo(Item other)
	{
		return name.compareTo(other.getName());
	}
}
