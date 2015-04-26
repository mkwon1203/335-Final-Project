package test;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import model.Character;
import model.Enemy;
import model.Game;
import model.Goblin;
import model.Knight;

public class TestAI
{
	public static void main(String[]args)
	{
		// initialization
		List<Character> player = new ArrayList<Character>();
		player.add(new Knight(new Point(0,0)));
		List<Enemy> enemy = new ArrayList<Enemy>();
		enemy.add(new Goblin(new Point(1,2)));
		Game g = new Game("Player1", player, enemy, "res/levels/testLevel2.lvl");
		
		Character knight = player.get(0);
		
		print(g);
		g.move(knight, 0,1);
		print(g);
		g.advanceTurn();
		print(g);
		g.getCharacterAt(0, 1).addHealth(-100);
		System.out.println(knight.isAlive());
		System.out.println(g.getTurnCounter());
		System.out.println(g.isGameOver());
	}
	
	public static void print(Game m)
	{
		System.out.println(m.toStringGUI());
	}
	
	public static void print2(Game g)
	{
		System.out.println(g.toStringGUI2());
	}
}
