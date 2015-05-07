package model;

import java.util.List;

public class Player
{
	private Inventory inventory;
	private List<Character> characters;
	private int money;
	private static Player player;
	
	/**
	 * Constructor of player as private, so that it can't be instantiated
	 */
	private Player()
	{
		inventory = new Inventory();
		money = 100;
	}
	
	/**
	 * Returns the single instance of Player (singleton design)
	 * Creates a new player instance if one does not exist
	 * @return	instance of Player
	 */
	public static Player getPlayer()
	{
		if (player == null)
			player = new Player();
		return player;
	}
	
	/**
	 * Sets the characters of Player to be the given list of characters
	 * @param characterInput	list of characters to set as player's units
	 */
	public void initializePlayer(List<Character> characterInput)
	{
		characters = characterInput;
	}
	
	/**
	 * Given list of Strings which are item names, adds all the
	 * appropriate items into the inventory. Used to initialize the inventory
	 * @param inventoryInput	List of strings containing item names to add
	 */
	public void addToInventory(List<String> inventoryInput)
	{
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
	
	/**
	 * Returns how much money player currently has
	 * @return	amount of money player has as int
	 */
	public int getMoney()
	{
		// how much money player has currently
		return money;
	}
	
	/**
	 * This method adds the given amount to player's money
	 * @param amount	amount to add to player's money
	 * @return	true if money was added succesfully
	 * 			false if adding the money would drop player's money below 0
	 */
	public boolean setMoney(int amount)
	{
		if (money + amount < 0)
			return false;
		
		money += amount;
		return true;
	}
	
	/**
	 * Given a unit and an item, use the item on the unit and return whether
	 * the item was used succesfully or not
	 * @param ch	unit to use item on
	 * @param i		item to be used
	 * @return		true if item was used succesfully
	 * 				false if the item could not be used on the unit
	 */
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
	
	/**
	 * Adds a single given item into the inventory
	 * @param i	Item to add to inventory
	 */
	public void addItem(Item i)
	{
		inventory.addItem(i);
	}
}