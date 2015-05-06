package model;

import java.util.List;

public class Player
{
	private Inventory inventory;
	private List<Character> characters;
	private int money;
	private static Player player;
	
	private Player()
	{
		inventory = new Inventory();
		// starting money placeholder value
		money = 100;
	}
	
	public static Player getPlayer()
	{
		if (player == null)
			player = new Player();
		return player;
	}
	
	public void resetPlayer()
	{
		player = new Player();
	}
	
	public void initializePlayer(List<Character> characterInput)
	{
		characters = characterInput;
	}
	
	public void addToInventory(List<String> inventoryInput)
	{
		// TODO: finish this
		for (String itemName : inventoryInput)
		{
			if (itemName.equalsIgnoreCase("health potion"))
				inventory.addItem(new HealthPotion());
			else if (itemName.equalsIgnoreCase("mana potion"))
				inventory.addItem(new ManaPotion());
			else if (itemName.equalsIgnoreCase("revive potion"))
				inventory.addItem(new RevivePotion());
			else if (itemName.equalsIgnoreCase("attack up"))
				inventory.addItem(new AttackUp());
			else if (itemName.equalsIgnoreCase("defence up"))
				inventory.addItem(new DefenceUp());
		}
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
	
	public int getMoney()
	{
		// how much money player has currently
		return money;
	}
	
	// this method changes the money held by player by amount
	// to decrease money, pass in negative value for amount
	public boolean setMoney(int amount)
	{
		if (money + amount < 0)
			return false;
		
		money += amount;
		return true;
	}
	
	public boolean useItem(CharacterInterface ch, Item i)
	{
		// have to add conditional to see if item i is usable on ch at all first
		boolean itemUsed = i.useItem(ch);
		
		if (itemUsed)
		{
			inventory.removeItem(i);
		}
		// item could not be used
		// throw exception or something
		else
		{
			
		}
		
		return itemUsed;
	}
	
	public void addItem(Item i)
	{
		inventory.addItem(i);
	}
}