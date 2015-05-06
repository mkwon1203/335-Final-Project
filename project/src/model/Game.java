package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Queue;
import java.util.Random;

public class Game extends Observable
{
	private int turnCounter;
	private Player player;
	private AI enemy;
	private Map map;
	private PathfindAlgorithm path;
	private CharacterInterface currentCharacter; // currently selected
													// character, mainly
	public static int GAMEOVER;
	public static final int GAMEOVER_NOT = -1;
	public static final int GAMEOVER_PLAYERWIN_DEATHMATCH = 1;
	public static final int GAMEOVER_ENEMYWIN_DEATHMATCH = 2;
	public static final int GAMEOVER_PLAYERWIN_TIMELIMIT = 3;
	public static final int GAMEOVER_ENEMYWIN_TIMELIMIT = 4;

	public Game()
	{
		GAMEOVER = GAMEOVER_NOT;
		turnCounter = 1;
	}
	
	public void initializeMap(String mapName)
	{
		List<Enemy> enemies = loadEnemies(mapName);
		map = new Map(mapName);
		map.setEnemyLocations(enemies);
		enemy = new AIEasy(enemies);
		path = new PathfindAlgorithm(map);
	}
	
	public void initializePlayer(List<Character> playerCharacters)
	{
		map.setPlayerLocations(playerCharacters);
		player = Player.getPlayer();
		player.initializePlayer(playerCharacters);
		populateMap();
		playerTurnStart();
	}

	public List<Enemy> loadEnemies(String levelName)
	{
		List<String> enemyListString = LoadGame.loadEnemies(levelName);
		List<Enemy> enemies = new ArrayList<Enemy>();

		for (String s : enemyListString)
		{
			if (s.equalsIgnoreCase("Goblin"))
				enemies.add(new Goblin(null));
			else if (s.equalsIgnoreCase("Rat"))
				enemies.add(new Rat(null));
			else if (s.equalsIgnoreCase("DarkWizard"))
				enemies.add(new DarkWizard(null));
			else
				System.out.println("FATAL ERROR: LOAD ENEMY FAILED");
		}

		return enemies;
	}

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
		setChanged();
		notifyObservers(null);
	}

	public void setSelectedCharacter(int row, int col)
	{
		currentCharacter = map.getCharacter(row, col);
		setChanged();
		notifyObservers(null);
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
		int moveDistance = ch.getMoveDistance();

		if (ch.isAlive())
			return path.movablePositions(currentPosition, moveDistance);
		else
			return new ArrayList<Point>();
	}

	public List<Point> findPath(CharacterInterface ch, Point end)
	{
		return path.findPath(ch, end);
	}

	public boolean move(CharacterInterface ch, Point location)
	{
		boolean movable = movablePositionList(ch).contains(location);

		if (!ch.getMoveAvailable() || !movable || !ch.isAlive())
			return false;

		// move the character
		Point oldLocation = ch.getLocation();
		map.setCharacter(oldLocation, null);
		ch.setLocation(location);
		map.setCharacter(location, ch);

		ch.setMoveAvailable(false);
		setChanged();
		notifyObservers(null);
		return true;
	}

	public boolean move(CharacterInterface ch, int row, int col)
	{
		return move(ch, new Point(row, col));
	}
	
	public boolean canMoveTo(CharacterInterface ch, Point location)
	{
		boolean movable = movablePositionList(ch).contains(location);
		
		if (!ch.getMoveAvailable() || !movable || !ch.isAlive())
			return false;
		
		return true;
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
//		if (attacker.getLocation().x != defender.getLocation().x
//				&& attacker.getLocation().y != defender.getLocation().y)
//			return false;

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

	public boolean attack(CharacterInterface attacker, Point defenderPosition)
	{
		CharacterInterface defender = map.getCharacter(defenderPosition);

		if (defender == null)
			// trying to attack on a block without character
			return false;

		return attack(attacker, defender);
	}

	public boolean attack(CharacterInterface attacker,
			CharacterInterface defender)
	{
		// check if attacker can attack and also if the defender is attackable
		if (!attacker.getActionAvailable()
				|| !attackableCharacterList(attacker).contains(defender))
			return false;

		// check if attacker and defender are on same team, friendly fire is not
		// allowed
		if ((attacker instanceof Character && defender instanceof Character)
				|| (attacker instanceof Enemy && defender instanceof Enemy))
			return false;

		// attack sequence start

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

		// modify the defender health
		defender.addHealth(-actualAttack);
		
		// add money to player if he killed an enemy unit
		if (defender instanceof Enemy)
		{
			if (!defender.isAlive())
				player.setMoney(((Enemy)defender).getValue());
		}

		wait(attacker);
		setChanged();
		notifyObservers(null);
		return true;
	}

	/**
	 * Helper method used inside attack method
	 * 
	 * @param defence
	 *            Defence of the defender unit from the attack method
	 * @return
	 */
	public int defencePercent(int defence)
	{
		// do log function
		return 100 - defence;
	}
	
	public List<CharacterInterface> magicUsableCharacterList(CharacterInterface ch)
	{
		List<CharacterInterface> magicUsableCharacters = new ArrayList<CharacterInterface>();

		if (ch.isAlive())
		{
			if (ch instanceof Priest)
			{
				List<Character> characters = player.getCharacters();

				for (Character c : characters)
				{
					if (attackable(ch, c) && c.isAlive() && !c.isMaxHealth())
						magicUsableCharacters.add(c);
				}
			}
			else if (ch instanceof Mage)
			{
				List<Enemy> enemies = enemy.getEnemies();
				
				for (Enemy e : enemies)
				{
					if (attackable(ch, e) && e.isAlive())
						magicUsableCharacters.add(e);
				}
			}
		}

		return magicUsableCharacters;
	}

	public boolean useMagic(CharacterInterface magicUser, CharacterInterface target)
	{
		if (!magicUser.isAlive() || !magicUser.getActionAvailable() ||
				!magicUsableCharacterList(magicUser).contains(target))
			return false;
		
		if ((magicUser instanceof Priest && target instanceof Enemy) ||
				(magicUser instanceof Mage && target instanceof Character))
			return false;

		boolean magicUsed = false;
		// magic sequence start
		if (magicUser instanceof Priest)
			magicUsed = ((Priest) magicUser).useMagic(target);
		else if (magicUser instanceof Mage)
			magicUsed = ((Mage) magicUser).useMagic(target);

		if (magicUsed)
		{
			wait(magicUser);
			setChanged();
			notifyObservers(null);
		}
		
		return magicUsed;
	}

	public boolean useItem(CharacterInterface ch, Item item)
	{
		// uses item given on the given character
		if (!ch.getActionAvailable())
			return false;

		// assume the given item came from player's inventory
		boolean itemUsed = player.useItem(ch, item);

		if (itemUsed)
		{
			wait(ch);
			setChanged();
			notifyObservers(null);
		}

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
		setChanged();
		notifyObservers(null);
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
		if (isPlayersTurn())
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
		if (isPlayersTurn())
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
		// game over by all units dying
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

		if (playerDead)
			GAMEOVER = GAMEOVER_PLAYERWIN_DEATHMATCH;
		if (enemyDead)
			GAMEOVER = GAMEOVER_ENEMYWIN_DEATHMATCH;

		// game over by turn counter going over
		boolean timeoutPlayerWin = false;
		boolean timeoutEnemyWin = false;

		if (turnCounter > 200)
		{
			// determine cumulative health of player units
			int playerHealth = 0;
			for (Character ch : player.getCharacters())
			{
				if (ch.isAlive())
					playerHealth += ch.getHealth();
			}

			// cumulative health of enemy units
			int enemyHealth = 0;
			for (Enemy e : enemy.getEnemies())
			{
				if (e.isAlive())
					enemyHealth += e.getHealth();
			}

			// whichever one is higher will win
			if (playerHealth >= enemyHealth)
			{
				timeoutPlayerWin = true;
				GAMEOVER = GAMEOVER_PLAYERWIN_TIMELIMIT;
			}
			else
			{
				timeoutEnemyWin = true;
				GAMEOVER = GAMEOVER_ENEMYWIN_TIMELIMIT;
			}
		}

		return playerDead || enemyDead || timeoutPlayerWin || timeoutEnemyWin;
	}
}
