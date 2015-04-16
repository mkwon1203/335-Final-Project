package model;

import java.util.List;

public class Player
{
	private Inventory inventory;
	private List<Character> characters;
	
	public Player()
	{
		
	}
	
	public Inventory getInventory()
	{
		return inventory;
	}
	
	public List<Character> getCharacters()
	{
		return characters;
	}
}
