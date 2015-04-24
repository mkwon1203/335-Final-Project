package model;

import java.util.List;

public interface AI
{
	public abstract void makeMove(Game currentGame);
	
	public abstract List<Enemy> getEnemies();
}
