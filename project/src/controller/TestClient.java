package controller;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.*;
import model.Character;

public class TestClient
{
	Game g;

	public TestClient()
	{
		// initialization
		List<Character> player = new ArrayList<Character>();
		player.add(new Knight(new Point(0, 0)));
		List<Enemy> enemy = new ArrayList<Enemy>();
		enemy.add(new Goblin(new Point(1, 2)));
		g = new Game("Player1", player, enemy, "res/levels/testLevel2.lvl");
	}

	public void run()
	{
		Scanner scan = new Scanner(System.in);
		boolean gameOver = false;
		while (!gameOver)
		{
			while (!g.isTurnOver())
			{
				System.out.println(g.toStringGUI());
				System.out.print("Select unit at row > ");
				int row = Integer.parseInt(scan.nextLine());
				System.out.print("unit at column > ");
				int col = Integer.parseInt(scan.nextLine());
				g.setSelectedCharacter(row,col);
				
				System.out.print("Select action: move/attack/end/printStat > ");
				String userInput = scan.nextLine();

				if (userInput.equalsIgnoreCase("move"))
				{
					System.out.println("To move row > ");
					row = Integer.parseInt(scan.nextLine());
					System.out.println("To move col > ");
					col = Integer.parseInt(scan.nextLine());
					
					if(g.move(g.getSelectedCharacter(), row, col))
					{
						System.out.println("movement successful!");
					}
					else
						System.out.println("invalid coordinate!");
				}
				else if (userInput.equalsIgnoreCase("attack"))
				{
					System.out.println("To attack row > ");
					row = Integer.parseInt(scan.nextLine());
					System.out.println("To attack col > ");
					col = Integer.parseInt(scan.nextLine());
					
					if(g.attack(g.getSelectedCharacter(), g.getCharacterAt(row,col)))
					{
						System.out.println("attack successful!");
					}
					else
						System.out.println("invalid coordinate!");
				}
				else if (userInput.equalsIgnoreCase("end"))
				{
					g.endTurn();
				}
				else if (userInput.equalsIgnoreCase("print"))
				{
					System.out.println("Health of selected unit: " + g.getSelectedCharacter().getHealth());
				}
				else
					System.out.println("Invalid command");
				
				if (g.isGameOver())
					gameOver = true;
			}
			g.advanceTurn();
		}
		
		System.out.println("GAME OVER");
	}

	public static void main(String[] args)
	{
		TestClient c = new TestClient();
		c.run();
	}
}
