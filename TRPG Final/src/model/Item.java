package model;

import java.awt.Image;

public abstract class Item {
	private int cost;
	private String description;
	private Image texture;

	public Item(int costInput, String descriptionInput, Image textureInput) {
		cost = costInput;
		description = descriptionInput;
		texture = textureInput;
	}
<<<<<<< Updated upstream
	
	public String getDescription()
	{
		return description;
	}
	
	public Image getTexture()
	{
		return texture;
	}
	
	public int getCost()
	{
		return cost;
	}
	
=======

>>>>>>> Stashed changes
	public abstract void useItem(Character ch);
}
