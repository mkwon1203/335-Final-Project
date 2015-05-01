package model;

public class Shop
{
	private Item currentItem;
	private Player player;
	private Item attackupBuff;
	private Item defenceupBuff;
	private Item healthpotion;
	private Item manapotion;
	private Item revivepotion;

	public Shop(Player player)
	{
		this.player = player;
		currentItem = null;
	}

	public Player getPlayer()
	{
		return player;
	}

	public Item getCurrentItem()
	{
		return currentItem;
	}
	
	
}
