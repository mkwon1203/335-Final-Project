package model;

import java.util.List;

public class AIEasy implements AI
{
	List<Enemy> enemies;
	
	public AIEasy(List<Enemy> enemyInput)
	{
		enemies = enemyInput;
	}
	
	@Override
	public List<Enemy> getEnemies()
	{
		return enemies;
	}
	
	@Override
	public void makeMove(Game currentGame)
	{
		// for each unit, move it randomly then attack whatever it can
	}
}
