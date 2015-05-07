package model;

import java.awt.Point;
import java.io.Serializable;
import java.util.List;
import java.util.Random;

import controller.SoundEffects;

public class AIEasy implements AI, Serializable
{
	List<Enemy> enemies;
	private Animation animate;

	public AIEasy(List<Enemy> enemyInput)
	{
		enemies = enemyInput;
	}

	/**
	 * Returns the list of units that the AI controls
	 * @return	List of Enemy objeects belonging to the AI
	 */
	@Override
	public List<Enemy> getEnemies()
	{
		return enemies;
	}

	/**
	 * The AI will take in a Game object and make its move with
	 * all of its units inside this method.
	 * AIEasy will cycle through all of its units. For each unit
	 * it will attempt to attack any nearby enemies, which would be
	 * Player's Characters. If no Character is within range, then
	 * the unit will move in a random direction and again check
	 * if a Character is within attacking range. If so, it will attack
	 * and otherwise, it will wait for the current turn.
	 * @param currentGame	The game object that AI will use to make its moves
	 */
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
				System.out.println("GameTurn: " + currentGame.getTurnCounter());
				for (Point p : movable)
				{
					System.out.println("Row: " + p.x + " Col: " + p.y);
				}
				
				if (!movable.isEmpty())
				{
					// move randomly for now
					Random rand = new Random();
					int i = rand.nextInt(movable.size());
					Point toMove = movable.get(i);
					
					e.setAnimated(true);
					animate = new Animation(currentGame.findPath(e, toMove), e);
					animate.execute();
					
					currentGame.move(e, toMove);
					
					// now see if we can attack, if so do it otherwise wait
					attackable = currentGame.attackableCharacterList(e);
					if (attackable.isEmpty())
						currentGame.wait(e);
					else{
						currentGame.attack(e, attackable.get(0));
						SoundEffects.addSound(e.getAttackSoundPath());
					}
				}
				else
				{
					if(!Animation.UNITMOVING)
						currentGame.wait(e);
				}
			}
			else
			{
				// attack right away
				currentGame.attack(e, attackable.get(0));
				SoundEffects.addSound(e.getAttackSoundPath());
			}
		}
	}
}
