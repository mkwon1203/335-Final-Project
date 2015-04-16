package model;

import java.awt.Point;
import java.util.List;

public class Map
{
	private List<Integer> levelArray;
	private String fileName;
	
	public List<Integer> getLevel()
	{
		return levelArray;
	}
	
	public boolean loadLevel(String levelName)
	{
		return false;
	}
	
	public boolean saveLevel(String levelName)
	{
		return false;
	}
	
	public int getValue(Point p)
	{
		return 0;
	}
	
	public boolean setValue(Point p, int x)
	{
		return false;
	}
}
