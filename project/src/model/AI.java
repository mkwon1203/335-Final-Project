package model;

import java.util.List;

public interface AI
{
	/**
	 * The AI will take in a Game object and make its move with
	 * all of its units inside this method.
	 * @param currentGame	The game object that AI will use to make its moves
	 */
	public abstract void makeMove(Game currentGame);
	
	/**
	 * Returns the list of units that the AI controls
	 * @return	List of Enemy objeects belonging to the AI
	 */
	public abstract List<Enemy> getEnemies();
}
