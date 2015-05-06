package model;

import java.awt.Point;
import java.util.List;

public class Map
{
	private Block[][] levelArray;
	private String fileName;
	private List<Point> playerSpawns;
	private List<Point> enemySpawns;

	public Map(String inputFileName)
	{
		fileName = inputFileName;
		// load up the map and spawns
		loadLevel(fileName);
		loadPlayerSpawns(fileName);
		loadEnemySpawns(fileName);
		// at this point, all fields are given values
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
	
	// returns height of map
	public int getLevelRow()
	{
		return levelArray.length;
	}
	
	// returns number of columns
	public int getLevelCol()
	{
		return levelArray[0].length;
	}
	
	public List<Point> getPlayerSpawns()
	{
		return playerSpawns;
	}
	
	public List<Point> getEnemySpawns()
	{
		return enemySpawns;
	}
	
	public void setPlayerSpawns(List<Point> spawns)
	{
		playerSpawns = spawns;
	}
	
	public void setEnemySpawns(List<Point> spawns)
	{
		enemySpawns = spawns;
	}
	
	/**
	 * Uses the currently set list of Points, playerSPawns, to set the
	 * individual locations of given list of player units
	 * @param playerUnits
	 */
	public void setPlayerLocations(List<Character> playerUnits)
	{
		// atm, 'randomized' assignment
		if (playerSpawns != null && playerUnits.size() == playerSpawns.size())
		{
			for (int i = 0; i < playerUnits.size(); i ++)
			{
				playerUnits.get(i).setLocation(playerSpawns.get(i));
			}
		}
	}
	
	public void setEnemyLocations(List<Enemy> enemyUnits)
	{
		// atm, randomized assignment 
		if (enemySpawns != null && enemyUnits.size() == enemySpawns.size())
		{
			for (int i = 0; i < enemyUnits.size(); i ++)
			{
				enemyUnits.get(i).setLocation(enemySpawns.get(i));
			}
		}
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

		levelArray = new Block[levelArrayInt.length][levelArrayInt[0].length];
		
		for (int row = 0; row < levelArrayInt.length; row++)
		{
			for (int col = 0; col < levelArrayInt[0].length; col++)
			{
				int blockInt = levelArrayInt[row][col];
				
				Block block;
				
				if (blockInt == 0)
					block = new Floor();
				else if (blockInt == 1)
					block = new Wall();
				else if (blockInt == 2)
					block = new Air();
				else
					// critical error
					return false;
				
				levelArray[row][col] = block;
			}
		}
		
		return true;
	}
	
	public static Block[][] loadBlocks(String levelName)
	{
		int[][] levelArrayInt = LoadGame.loadFile(levelName);
		Block[][] toReturn = new Block[levelArrayInt.length][levelArrayInt[0].length];
		
		for (int row = 0; row < levelArrayInt.length; row++)
		{
			for (int col = 0; col < levelArrayInt[0].length; col++)
			{
				int blockInt = levelArrayInt[row][col];
				
				Block block = null;
				
				if (blockInt == 0)
					block = new Floor();
				else if (blockInt == 1)
					block = new Wall();
				else if (blockInt == 2)
					block = new Air();
				else if (blockInt == 3)
					block = new Door(Door.VERTICAL);
				else if (blockInt == 4)
					block = new Door(Door.HORIZONTAL);
				else
					//TODO: maybe turn this into exception
					System.out.println("loadBlocks failed. critical error");
				
				toReturn[row][col] = block;
			}
		}
		
		return toReturn;
	}
	
	public void loadPlayerSpawns(String levelName)
	{
		List<Point> playerSpawns = LoadGame.loadPlayerSpawns(levelName);
		
		setPlayerSpawns(playerSpawns);
	}
	
	public void loadEnemySpawns(String levelName)
	{
		List<Point> enemySpawns = LoadGame.loadEnemySpawns(levelName);
		
		setEnemySpawns(enemySpawns);
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
	public Block getBlock(Point p)
	{
		// check to make sure p lies within boundary of 2D array
		if (!verifyBounds(p))
			// point p is not a valid point in relation to map
			return null;

		return levelArray[p.x][p.y];
	}
	
	// overloaded method, using 2 ints for x and y instead of point
	public Block getBlock(int x, int y)
	{
		if (!verifyBounds(x,y))
			return null;
		
		return levelArray[x][y];
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
	public boolean setBlock(Point p, Block b)
	{
		// check to make sure p lies within boundary of 2D array
		if (!verifyBounds(p))
			// point p is not a valid point in relation to map
			return false;
		
		// set the new block
		levelArray[p.x][p.y] = b;
		
		return true;
	}
	
	public boolean setBlock(int x, int y, Block b)
	{
		if (!verifyBounds(x,y))
			return false;
		
		levelArray[x][y] = b;
		
		return true;
	}
	
	// helper method to make sure point p is a valid one
	// with respect to our 2D array map
	public boolean verifyBounds(Point p)
	{
		int x = p.x;
		int y = p.y;

		if (x >= levelArray.length || y >= levelArray[0].length)
			// out of bounds
			return false;
		else if (x < 0 || y < 0)
			// invalid values, can't have negative coordinates
			return false;
		
		return true;
	}
	
	public boolean verifyBounds(int x, int y)
	{

		if (x >= levelArray.length || y >= levelArray[0].length)
			// out of bounds
			return false;
		else if (x < 0 || y < 0)
			// invalid values, can't have negative coordinates
			return false;
		
		return true;
	}
	
	public CharacterInterface getCharacter(Point p)
	{
		return levelArray[p.x][p.y].getCharacter();
	}
	
	// returns the character at location (x,y)
	public CharacterInterface getCharacter(int x, int y)
	{
		return levelArray[x][y].getCharacter();
	}
	
	public boolean isOccupied(Point p)
	{
		return levelArray[p.x][p.y].isOccupied();
	}
	
	public boolean isOccupied(int x, int y)
	{
		return levelArray[x][y].isOccupied();
	}
	
	public boolean isSolid(Point p)
	{
		return levelArray[p.x][p.y].isSolid();
	}
	
	public boolean isSolid(int x, int y)
	{
		return levelArray[x][y].isSolid();
	}
	
	public boolean canMoveTo(Point p)
	{
		return verifyBounds(p) && !isOccupied(p) && !isSolid(p);
	}
	
	public boolean setCharacter(int x, int y, CharacterInterface ch)
	{
		if (!verifyBounds(x,y))
			return false;
		
		levelArray[x][y].setCharacter(ch);
		return true;
	}
	
	public boolean setCharacter(Point p, CharacterInterface ch)
	{
		if (!verifyBounds(p))
			return false;
		
		levelArray[p.x][p.y].setCharacter(ch);
		return true;
	}
	
	public void clearCharacters()
	{
		// run through array, clear all characters off the blocks
	}
}
