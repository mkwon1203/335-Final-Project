package model;

import java.awt.Point;
import java.util.List;
import java.util.Random;

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
		for (Enemy e : enemies)
		{
			// check if any target in vicinity it can attack
			List<CharacterInterface> attackable = currentGame
					.attackableCharacterList(e);

			if (attackable.isEmpty())
			{
				// perform move then see about attacking
				List<Point> movable = currentGame.movablePositionList(e);
				
				if (!movable.isEmpty())
				{
					// move randomly for now
					Random rand = new Random();
					int i = rand.nextInt(movable.size());
					Point toMove = movable.get(i);
					
					currentGame.move(e, toMove);
					
					// now see if we can attack, if so do it otherwise wait
					attackable = currentGame.attackableCharacterList(e);
					if (attackable.isEmpty())
						currentGame.wait(e);
					else
						currentGame.attack(e, attackable.get(0));
				}
				else
				{
					currentGame.wait(e);
				}
			}
			else
			{
				// attack right away
				currentGame.attack(e, attackable.get(0));
			}
		}
	}
}
