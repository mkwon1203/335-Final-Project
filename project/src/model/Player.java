package model;

import java.util.List;

public class Player
{
	private Inventory inventory;
	private List<Character> characters;
	private String name;
	private int money;
	
	public Player(String nameInput, List<Character> characterInput)
	{
		name = nameInput;
		characters = characterInput;
		inventory = new Inventory();
		initializeInventory();
		// starting money placeholder value
		money = 100;
	}
	
	private void initializeInventory()
	{
		for (int i = 0; i < 5; i ++)
		{
			Item health = new HealthPotion();
			inventory.addItem(health);
			Item mana = new ManaPotion();
			inventory.addItem(mana);
		}
		
		for (int i = 0; i < 1; i ++)
		{
			Item revive = new RevivePotion();
			inventory.addItem(revive);
		}
		
		for (int i = 0; i < 1; i ++)
		{
			Item att = new AttackUp();
			inventory.addItem(att);
			Item def = new DefenceUp();
			inventory.addItem(def);
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
	
	public String getName()
	{
		// name of player, aka profile name
		return name;
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