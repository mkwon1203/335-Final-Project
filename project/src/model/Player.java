package model;

import java.util.List;

public class Player
{
	private Inventory inventory;
	private List<Character> characters;
	
	public Player()
	{
		
	}
	
	/**
	 * This method returns the inventory object of the player
	 * @return Inventory object containing items
	 */
	public Inventory getInventory()
	{
		return inventory;
	}
	
	/**
	 * This method returns a List of characters that the player is controlling
	 * @return a List containing Characters
	 */
	public List<Character> getCharacters()
	{
		return characters;
	}
}
