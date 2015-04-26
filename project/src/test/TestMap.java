package test;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import model.*;
import model.Character;

public class TestMap
{
	public static void main(String[]args)
	{
		// initialization
		List<Character> player = new ArrayList<Character>();
		player.add(new Knight(new Point(0,0)));
		List<Enemy> enemy = new ArrayList<Enemy>();
		enemy.add(new Goblin(new Point(1,2)));
		Game g = new Game("Player1", player, enemy, "res/levels/testLevel2.lvl");
		
		print(g);
		System.out.println(g.move(player.get(0), new Point(1,0)));
		print(g);
		System.out.println(g.move(player.get(0), new Point(2,0)));
		print(g);
		System.out.println(g.move(player.get(0), new Point(0,1)));
		print(g);
		System.out.println(g.attackableCharacterList(player.get(0)));
		System.out.println(player.get(0).getLocation());
		print(g);
		g.playerTurnStart();
		System.out.println(g.move(player.get(0), new Point(1,1)));
		System.out.println(g.attackableCharacterList(player.get(0)));
		System.out.println(enemy.get(0).getHealth());
		System.out.println(g.attack(player.get(0), enemy.get(0)));
		System.out.println(enemy.get(0).getHealth());
		print(g);
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
