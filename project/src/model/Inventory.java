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
	
	public List<Item> getItems()
	{
		return items;
	}
	
	public void addItem(Item toAdd)
	{
		items.add(toAdd);
	}
	
	public void removeItem(Item toRemove)
	{
		
	}
}
