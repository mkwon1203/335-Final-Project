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
	
	// note, list of items is always kept in sorted order
	public List<Item> getItems()
	{
		return items;
	}
	
	public int getSize()
	{
		return items.size();
	}
	
	public void addItem(Item toAdd)
	{
		items.add(toAdd);
	}
	
	public boolean removeItem(Item toRemove)
	{
		return items.remove(toRemove);
	}
}
