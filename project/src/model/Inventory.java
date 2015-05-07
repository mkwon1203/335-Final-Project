package model;

import java.util.ArrayList;
import java.util.List;

public class Inventory
{
	private List<Item> items;
	
	public Inventory()
	{
		items = new ArrayList<Item>();
	}
	
	/**
	 * Returns the list of items inside inventory
	 * @return list of items as List<Item>
	 */
	public List<Item> getItems()
	{
		return items;
	}
	
	/**
	 * Returns how many items are present in the inventory
	 * @return	number of items as int
	 */
	public int getSize()
	{
		return items.size();
	}
	
	/**
	 * Adds the given item into the inventory
	 * @param toAdd	the Item object to be added to inventory
	 */
	public void addItem(Item toAdd)
	{
		items.add(toAdd);
	}
	
	/**
	 * Tries to remove the given Item from the inventory.
	 * If the item existed in the inventory to begin with,
	 * then the item is removed. Otherwise, nothing happens
	 * @param toRemove	the Item to be removed from inventory
	 * @return	true if item was succesfully removed
	 * 			false if item was not removed
	 */
	public boolean removeItem(Item toRemove)
	{
		return items.remove(toRemove);
	}
}
