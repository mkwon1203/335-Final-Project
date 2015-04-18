package model;

import java.awt.Point;
import java.util.List;

public class Map
{
	private Block[][] levelArray;
	private String fileName;

	public Map(String inputFileName)
	{
		fileName = inputFileName;
//		// small map, placeholder value 32x32 size
//		levelArray = new Block[8][8];
//
//		/*
//		 * should be handled by loadgame
//		 */
//		for (int i = 0; i < levelArray.length; i++)
//		{
//			for (int j = 0; j < levelArray[0].length; j++)
//			{
//				levelArray[i][j] = new Wall();
//			}
//		}
		loadLevel(fileName);
	}

	/**
	 * This method returns the 2D array representing the tiles on the map
	 * 
	 * @return 2D array of Blocks
	 */
	public Block[][] getLevel()
	{
		return levelArray;
	}
	
	public int getLevelX()
	{
		return levelArray.length;
	}
	
	public int getLevelY()
	{
		return levelArray[0].length;
	}

	/**
	 * This method uses the LoadGame class to load the game using the given
	 * levelName parameter to find which level to load.
	 * 
	 * @param levelName
	 *            Name of the level to load
	 * @return True if the level was loaded correctly False if error occured and
	 *         level was not loaded
	 */
	public boolean loadLevel(String levelName)
	{
		int[][] levelArrayInt = LoadGame.loadFile(levelName);
//		for (int i = 0; i < levelArrayInt.length; i ++)
//		{
//			for (int j = 0; j < levelArrayInt[0].length; j ++)
//			{
//				System.out.print(levelArrayInt[i][j] + " ");
//			}
//			System.out.println();
//		}
		levelArray = new Block[levelArrayInt.length][levelArrayInt[0].length];
		
		for (int i = 0; i < levelArrayInt.length; i++)
		{
			for (int j = 0; j < levelArrayInt[0].length; j++)
			{
				int blockInt = levelArrayInt[i][j];
				
				Block block;
				
				if (blockInt == 0)
					block = new Floor();
				else if (blockInt == 1)
					block = new Wall();
				else if (blockInt == 2)
					block = new Air();
				else
					block = null;
				
				levelArray[i][j] = block;
			}
		}
		
		return true;
	}

	/**
	 * This method will save the level using the SaveGame class using the given
	 * levelName parameter to set the name of the level.
	 * 
	 * @param levelName
	 *            The name of the level to be saved
	 * @return True if the level is saved correctly False if the level was not
	 *         saved correctly due to an error
	 */
	public boolean saveLevel(String levelName)
	{
		return false;
	}

	/**
	 * This method will return the Block at the given point if possible
	 * 
	 * @param p
	 *            the coordinate of the block to be retrieved
	 * @return the Block object at the given point p null if the coordinate is
	 *         out of bounds
	 */
	public Block getValue(Point p)
	{
		return null;
	}

	/**
	 * This method will set the given Block b at the given Point p
	 * 
	 * @param p
	 *            coordinate of where the block will be placed
	 * @param b
	 *            the Block object which will be placed at point p
	 * @return True if the block was placed successfully False if the point was
	 *         out of bounds or occured
	 */
	public boolean setValue(Point p, Block b)
	{
		
		return true;
	}

	public String toString()
	{
		String toReturn = "";

		for (int i = 0; i < levelArray.length; i++)
		{
			for (int j = 0; j < levelArray[0].length; j++)
			{
				Block curr = levelArray[i][j];

				toReturn += curr.toString() + " ";
			}
			toReturn += "\n";
		}

		return toReturn;
	}
}
