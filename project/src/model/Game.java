package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;

public class Game extends Observable
{
	private int turnCounter;
	private Player player;
	private AI enemy;
	private Map map;
	private CharacterInterface currentCharacter; // currently selected
													// character, mainly

	// for GUI

	public Game(String playerName, List<Character> playerCharacters,
			List<Enemy> enemyCharacters, String mapName)
	{
		turnCounter = 1;
		player = new Player(playerName, playerCharacters);
		enemy = new AIEasy(enemyCharacters);
		map = new Map(mapName);
		populateMap();
		currentCharacter = null;
		playerTurnStart();
	}

	/**
	 * This method will take
	 */
	public void populateMap()
	{
		for (CharacterInterface ch : player.getCharacters())
		{
			map.setCharacter(ch.getLocation(), ch);
		}

		for (CharacterInterface ch : enemy.getEnemies())
		{
			map.setCharacter(ch.getLocation(), ch);
		}
	}

	public int getTurnCounter()
	{
		return turnCounter;
	}

	// returns the map object
	public Map getMap()
	{
		return map;
	}

	public Player getPlayer()
	{
		return player;
	}

	public AI getAI()
	{
		return enemy;
	}

	public CharacterInterface getSelectedCharacter()
	{
		return currentCharacter;
	}

	public void setSelectedCharacter(CharacterInterface ch)
	{
		currentCharacter = ch;
	}

	public void setSelectedCharacter(int row, int col)
	{
		currentCharacter = map.getCharacter(row, col);
	}

	public boolean isOccupied(int row, int col)
	{
		return map.isOccupied(row, col);
	}

	public CharacterInterface getCharacterAt(int row, int col)
	{
		return map.getCharacter(row, col);
	}

	public boolean isCharacterSelected()
	{
		return currentCharacter != null;
	}

	public boolean isPlayersTurn()
	{
		return turnCounter % 2 == 1;
	}

	public void advanceTurn()
	{
		turnCounter++;

		if (isPlayersTurn())
			playerTurnStart();
		else
			enemyTurnStart();
	}

	public List<Point> movablePositionList(CharacterInterface ch)
	{
		// returns a List containing all points that given character can move to
		Point currentPosition = ch.getLocation();
		int currentX = currentPosition.x;
		int currentY = currentPosition.y;
		int moveDistance = ch.getMoveDistance();
		int leftX = currentX;
		int rightX = currentX;

		List<Point> movablePositions = new ArrayList<Point>();

		if (ch.isAlive())
		{
			while (moveDistance >= 0)
			{
				for (int y = currentY - moveDistance; y <= currentY
						+ moveDistance; y++)
				{
					Point p = new Point(leftX, y);
					movablePositions.add(p);
					p = new Point(rightX, y);
					movablePositions.add(p);
				}

				leftX--;
				rightX++;
				moveDistance--;
			}

			// prune the list to make sure it only contains valid points
			for (int i = 0; i < movablePositions.size(); i++)
			{
				Point p = movablePositions.get(i);
				if (!map.verifyBounds(p) || map.getBlock(p).isSolid()
						|| map.isOccupied(p))
					movablePositions.remove(p);
			}
		}

		return movablePositions;
	}

	public boolean move(CharacterInterface ch, Point location)
	{
		boolean movable = movablePositionList(ch).contains(location);
		if (!ch.getMoveAvailable() || !movable)
			return false;

		// move the character
		Point oldLocation = ch.getLocation();
		map.setCharacter(oldLocation, null);
		ch.setLocation(location);
		map.setCharacter(location, ch);

		ch.setMoveAvailable(false);
		return true;
	}

	public boolean move(CharacterInterface ch, int row, int col)
	{
		return move(ch, new Point(row, col));
	}

	public List<CharacterInterface> attackableCharacterList(
			CharacterInterface ch)
	{
		// returns a list containing all points that given character can attack

		// go through the other guy's units and see if distance to that unit is
		// less than or equal to the attackDistance of given character ch
		List<CharacterInterface> attackableCharacters = new ArrayList<CharacterInterface>();

		if (ch.isAlive())
		{
			if (ch instanceof Character)
			{
				// get list of AI's units
				List<Enemy> enemies = enemy.getEnemies();

				for (Enemy e : enemies)
				{
					if (attackable(ch, e) && e.isAlive())
						attackableCharacters.add(e);
				}
			}
			else
			// ch is AI's unit
			{
				List<Character> characters = player.getCharacters();

				for (Character c : characters)
				{
					if (attackable(ch, c) && c.isAlive())
						attackableCharacters.add(c);
				}
			}
		}

		return attackableCharacters;
	}

	public boolean attackable(CharacterInterface attacker,
			CharacterInterface defender)
	{
		// checks to make sure attacker and defender lies on same horizontal
		// or vertical line
		if (attacker.getLocation().x != defender.getLocation().x
				&& attacker.getLocation().y != defender.getLocation().y)
			return false;

		// checks to make sure defender is within attackable distance of
		// attacker
		int distance = distance(attacker.getLocation(), defender.getLocation());

		return distance <= attacker.getAttackDistance();
	}

	public int distance(Point initialPoint, Point finalPoint)
	{
		/*
		 * needs revision when moveDistance can be >1 later on maybe
		 */
		int horizontalDistance = Math.abs(initialPoint.x - finalPoint.x);
		int verticalDistance = Math.abs(initialPoint.y - finalPoint.y);

		return horizontalDistance + verticalDistance;
	}

	// path finding algorithm
	public List<Point> pathFind(Point initialPoint, Point finalPoint)
	{
		// returns null if path isn't found
		return null;
	}

	public boolean attack(CharacterInterface attacker,
			CharacterInterface defender)
	{
		// given attacker will attack given defender if possible
		if (!attacker.getActionAvailable()
				|| !attackableCharacterList(attacker).contains(defender))
			return false;

		// attack
		// roll dice for critical hit first
		boolean critical = false;

		Random rand = new Random();
		if (rand.nextInt(1000) < 100) // 10% chance
			critical = true;

		/*
		 * damage calculation defence will mitigate % of the attack based on the
		 * function defencePercent attack is based on strength inclusive range
		 * [strength-5 ... strength + 5] determines raw attack -critical hit
		 * will mean raw is multipled by 1.5 actual attack is raw attack *
		 * defencePercent/100
		 */
		int str = attacker.getStrength();
		int raw = rand.nextInt((str + 5) - (str - 5) + 1) + str - 5;
		if (critical)
			raw = (int) (raw * 1.5);
		double percent = defencePercent(defender.getDefence()) / 100.0;
		int actualAttack = (int) (raw * percent);

		defender.addHealth(-actualAttack);

		wait(attacker);
		return true;
	}

	public int defencePercent(int defence)
	{
		// do log function
		return 100 - defence;
	}

	public boolean useItem(CharacterInterface ch, Item item)
	{
		// uses item given on the given character
		if (!ch.getActionAvailable())
			return false;

		// assume the given item came from player's inventory
		boolean itemUsed = player.useItem(ch, item);

		if (itemUsed)
			wait(ch);

		return itemUsed;
	}

	public boolean wait(CharacterInterface ch)
	{
		// this method makes the ch unavailable for the turn and thus waiting
		if (!ch.getActionAvailable())
			// unit couldn't do anything to start with
			return false;

		ch.setMoveAvailable(false);
		ch.setActionAvailable(false);
		ch.setAvailable(false);
		return true;
	}

	public void updateCurrentCharacter(CharacterInterface selected)
	{
		currentCharacter = selected;
	}

	public void updateCharacterAvailable(CharacterInterface ch)
	{
		// this method will check if given unit is unable to move or attack
		// if so, then the unit is set as overall unavailable
		if (!ch.getActionAvailable() && !ch.getMoveAvailable())
			ch.setAvailable(false);
	}

	public void endTurn()
	{
		if (turnCounter % 2 == 1)
		{
			// turnCounter is odd, so it's player's turn
			for (Character ch : player.getCharacters())
			{
				wait(ch);
			}
		}
		else
		{
			// turnCounter is even, it's AI's turn
			for (Enemy e : enemy.getEnemies())
			{
				wait(e);
			}
		}
	}

	public void playerTurnStart()
	{
		// set all of player units to available
		for (Character ch : player.getCharacters())
		{
			ch.resetAvailable();
		}
	}

	public void enemyTurnStart()
	{
		for (Enemy e : enemy.getEnemies())
		{
			e.resetAvailable();
		}

		enemy.makeMove(this);

		advanceTurn();
	}

	public boolean isTurnOver()
	{
		// checks if the current turn should be over
		if (turnCounter % 2 == 1)
		{
			// turnCounter is odd, so it's player's turn
			for (Character ch : player.getCharacters())
			{
				if (ch.getAvailable())
					return false;
			}

			return true;
		}
		else
		{
			// turnCounter is even, it's AI's turn
			// check if all of AI's units are unavailable
			for (Enemy e : enemy.getEnemies())
			{
				if (e.getAvailable())
					return false;
			}

			return true;
		}

	}

	public boolean isGameOver()
	{
		// checks if any victory conditions are met
		// condition 1: all of either player or AI's units are ded
		// if (!isPlayersTurn())
		// {
		// for (Character ch : player.getCharacters())
		// {
		// if (ch.isAlive())
		// return false;
		// }
		// }
		// else
		// {
		// for (Enemy e : enemy.getEnemies())
		// {
		// if (e.isAlive())
		// return false;
		// }
		// }

		boolean playerDead = true;
		for (Character ch : player.getCharacters())
		{
			if (ch.isAlive())
				playerDead = false;
		}
		boolean enemyDead = true;
		for (Enemy e : enemy.getEnemies())
		{
			if (e.isAlive())
				enemyDead = false;
		}

		return playerDead || enemyDead;

		// more conditions below

		// return true;
	}

	public String toStringGUI()
	{
		return map.toStringGUI();
	}

	public String toStringGUI2()
	{
		return map.toStringGUI2();
	}
}
