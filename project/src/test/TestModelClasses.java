package test;

import static org.junit.Assert.*;

import java.awt.Image;
import java.awt.List;
import java.awt.Point;
import java.util.ArrayList;

import model.Enemy;
import model.Game;
import model.HealthPotion;
import model.Knight;
import model.ManaPotion;
import model.Map;
import model.RevivePotion;

import org.junit.Test;

public class TestModelClasses {
	private Point knightLoc = new Point (2,2 );
	private Image image;
	private Knight knight = new Knight(knightLoc );
//	private HealthPotion healthPotion = new HealthPotion();
//	private ManaPotion manaPotion = new ManaPotion();
//	private RevivePotion revivePotion = new RevivePotion();
	private java.util.List<model.Character> characterList;// = new List<Character>();
	private ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
	private Game game;
	private Character knight2 = new Character('b');
	private Map map = new Map("res/level/milestone.lvl");
	//Knight knight2 = new Character(knightLoc);
	
	
	@Test
	// checking basic methods with knight
	public void testKnight(){
		
//		assertEquals("knight", knight.getType()); // testing getType
//		//assertEquals("John", knight.getName()); // checking getName
//		assertEquals("100" , knight.getHealth()); // checking getHealth
//		assertEquals("0" , knight.getMana()); // checking getMana
//		assertEquals("10" , knight.getStrength()); // checking getStrength
//		assertEquals("10", knight.getDefence()); // cheking getDefence
		assertEquals(knightLoc, knight.getLocation()); // checking getLocation
//		assertTrue(knight.isAlive()); // checking isAlive
//		assertEquals(1, knight.getMoveDistance()); // checking moveDistance
//		assertEquals(1, knight.getAttackDistance()); // cheking getAttackDistance
		// need to check image
		
		//characterList.add((Character)knight);
		game = new Game("knight", characterList, enemyList, "res/level/milestone.lvl");
		Point nextMove = new Point(3,3);
		game.move(knight, nextMove);
		assertEquals(nextMove, knight.getLocation());
	}
	
	//@Test
	// will test various HealthPotion methods
	/*
	 * int costInput, String nameInput, String descriptionInput, Image textureInput
	 */
//	public void testHealthPotion(){
//		assertEquals(1, healthPotion.getCost()); // checking getCost
//		assertEquals("Health Potion", healthPotion.getName()); // checking getName
//		assertEquals("Health potion description", healthPotion.getDescription()); // checking getDescription
//		// check Image
//	}
	
//	@Test
//	// will test various ManaPotion methods
//	public void testManaPotion(){
//		assertEquals(1, manaPotion.getCost()); // checking getCost
//		assertEquals("Mana Potion", manaPotion.getName()); // checking getName
//		assertEquals("Mana potion description", manaPotion.getDescription()); // checking getDescription
//		// check Image
//	}
	
//	@Test
//	// will test various RevivePotion methods
//	public void testRevivePotion(){
//		assertEquals(1, revivePotion.getCost()); // checking getCost
//		assertEquals("Revive Potion", revivePotion.getName()); // checking getName
//		assertEquals("Revive potion description", revivePotion.getDescription()); // checking getDescription
//		// check Image
//		
//		
//		// checking revive function
//		//knight.isAlive() = false;
//		revivePotion.useItem(knight);
//		assertFalse(knight.isAlive());
//	}
}
