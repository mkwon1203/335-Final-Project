package model;

import java.awt.Image;

public abstract class Item implements Comparable<Item>{
	private int cost;
	private String name;
	private String description;
	private Image texture;

	public Item(int costInput, String nameInput, String descriptionInput, Image textureInput) {
		cost = costInput;
		name = nameInput;
		description = descriptionInput;
		texture = textureInput;
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
