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
	private int playerCurrency;

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
	
	public void buyItem(){
		
	}
	
	//player add item(item selected)
	
	public int getPlayerCurrency(){
		return playerCurrency;
		
	}
	
	
}
