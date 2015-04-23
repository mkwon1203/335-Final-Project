package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Game
{
	private int turnCounter;
	Player player;
	// needs AI field
	Map map;
	CharacterInterface currentCharacter; // currently selected character, mainly
											// for GUI

	public Game(String playerName, List<Character> playerCharacters,
			String mapName)
	{
		turnCounter = 1;
		player = new Player(playerName, playerCharacters);
		// initialize AI here
		map = new Map(mapName);
		currentCharacter = null;
	}

	public void mainLoop()
	{

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

		while (moveDistance > 0)
		{
			for (int y = currentY - moveDistance; y <= currentY + moveDistance; y++)
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
		for (Point p : movablePositions)
		{
			if (!map.verifyBounds(p) || map.getValue(p).isSolid()
					|| map.getValue(p).isOccupied())
				movablePositions.remove(p);
		}

		return movablePositions;
	}

	public boolean move(CharacterInterface ch, Point location)
	{
		// moves given character to a given location
		if (!ch.getMoveAvailable() || !map.verifyBounds(location))
			return false; // ch can't move or location is invalid

		// move the character
		// make sure to set the block at the new location to be occupied
		// make sure to set block at old location to be unoccupied
		// update ch's position (that is what it means to move)
		
		ch.setMoveAvailable(false);
		return true;
	}

	public List<CharacterInterface> attackableCharacterList(CharacterInterface ch)
	{
		// returns a list containing all points that given character can attack
		
		// go through the other guy's units and see if distance to that unit is 
		// less than or equal to the attackDistance of given character ch
		List<CharacterInterface> attackableCharacters = new ArrayList<CharacterInterface>();
		
		if (ch instanceof Character)
		{
			
		}
		else // ch is AI's unit
		{
			
		}
		
		return attackableCharacters;
	}

	public boolean attack(CharacterInterface attacker,
			CharacterInterface defender)
	{
		// given attacker will attack given defender if possible
		if (!attacker.getActionAvailable() || !attackableCharacterList(attacker).contains(defender))
			return false;
		
		// make the attacker hit the defender
		
		return true;
	}

	public boolean useItem(CharacterInterface ch, Item item)
	{
		// uses item given on the given character
		if (!ch.getActionAvailable())
			return false;

		// assume the given item came from player's inventory
		boolean itemUsed = player.useItem(ch, item);

		if (itemUsed)
			ch.setActionAvailable(false);

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

	public void updateCharacterAvailable(CharacterInterface ch)
	{
		// this method will check if given unit is unable to move or attack
		// if so, then the unit is set as overall unavailable
		if (!ch.getActionAvailable() && !ch.getMoveAvailable())
			ch.setAvailable(false);
	}

	public void endTurn()
	{
		// sets all units to be unavailable to advance the turn
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
		}

	}

	public boolean isGameOver()
	{
		// checks if any victory conditions are met
	}
}
