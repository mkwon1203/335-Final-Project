
package model;

import java.io.*;
import java.util.*;
import java.awt.Point;

public class LoadGame {
	
	public static int[][] LEVELARRAY = null;
	public static int LEVELWIDTH = 0;
	public static int LEVELHEIGHT = 0;
	
	//To ensure only one instance of this class
	private LoadGame(String fileName){ }
	
	//Call this method to load a level file
	public static int[][] loadFile(String fileName){
		
		//  Example file name: "testLevel"
		LEVELWIDTH = 0;
		LEVELHEIGHT = 0;
		LEVELARRAY = null;
		
		fileName = "res/levels/" + fileName + ".lvl";
		
		try{
			Scanner loadScanner = new Scanner(new File(fileName));
			String line = "";
			
			boolean firstPass = true;
			while(loadScanner.hasNextLine()) {
				line = loadScanner.nextLine();
				if(firstPass){
					Scanner scan = new Scanner(line);
					while(scan.hasNextInt()){
						scan.nextInt();
						LEVELWIDTH++;
					}
					firstPass = false;
					scan.close();
				}
				if(line.equals(";"))
					break;
				LEVELHEIGHT++;
			}
			
			LEVELARRAY = new int[LEVELHEIGHT][LEVELWIDTH];
			try{
				loadScanner = new Scanner(new File(fileName));
				while(loadScanner.hasNextInt()){
					for(int y=0;y<LEVELHEIGHT;y++){
						for(int x=0;x<LEVELWIDTH;x++){
							LEVELARRAY[y][x] = loadScanner.nextInt();
						}
					}
				}
			}catch(Exception ex){System.out.println("found something other than int.");}
			
			loadScanner.close();
			
			return LEVELARRAY;
		}catch(IOException e){
			System.out.println("Couldn't find given level file.");
			return null;
		}
		
	}
	
	//Call this method to load the enemies that are in this level. All this method
	//	will do is return a list of enemy objects set to a default spawn location.
	//	The spawn locations should be changed and decided based upon the 
	//	possible enemy spawn locations on the map.
	public static List<String> loadEnemies(String fileName){
		List<String> enemyList = new ArrayList<String>();
		String path = "res/levels/" + fileName + ".lvl";
		
		try{
			Scanner scan = new Scanner(new File(path));
			String word = "";
			
			try{
				word = scan.next();
				while(!word.equals("<Enemies>"))
					word = scan.next();
			}catch(Exception ex){System.out.println("Couldn't find <Enemies> tag in file."); }
			
			word = scan.next();
			while(!word.equals("</Enemies>")){
				enemyList.add(word.toUpperCase());
				word = scan.next();
			}
			
		}catch(IOException e){ System.out.println("Couldn't find file to load enemies."); }
		
		return enemyList;
	}
	
	//Call this method to load in the possible spawn locations for the enemies
	//	on the map. This method is seporate from the loadEnemies() method
	//	in order to add some sense of randomness to the game.
	//		Returns a list of points that contain the possible spawn locations
	//			on the map.
	public static List<Point> loadEnemySpawns(String fileName){
		List<Point> enemySpawns = new ArrayList<Point>();
		String path = "res/levels/" + fileName + ".lvl";
		
		try{
			Scanner scan = new Scanner(new File(path));
			
			String word = "";
			
			try{
				word = scan.next();
				while(!word.equals("<EnemySpawns>"))
					word = scan.next();
			}catch(Exception ex){System.out.println("Couldn't find <EnemySpawns> tag in file."); }
			
			word = scan.next();
			while(!word.equals("</EnemySpawns>")){
				
				enemySpawns.add(new Point(Integer.parseInt(word), Integer.parseInt(scan.next())));
				
				word = scan.next();
			}
			
		}catch(IOException e){ System.out.println("Couldn't find file to load enemies."); }
		
		
		return enemySpawns;
	}
	
	//Call this method to load the possible spawn locations for the player's units.
	//	The method accepts in the name of the level file that will be read.
	//		Returns a list of points that are the possible spawn locations on
	//		the map.
	public static List<Point> loadPlayerSpawns(String fileName){
		List<Point> playerSpawns = new ArrayList<Point>();
		String path = "res/levels/" + fileName + ".lvl";
		
		try{
			Scanner scan = new Scanner(new File(path));
			String word = "";
			
			try{
				word = scan.next();
				while(!word.equals("<PlayerSpawns>"))
					word = scan.next();
			}catch(Exception ex){System.out.println("Couldn't find <PlayerSpawns> tag in file."); }
			
			word = scan.next();
			while(!word.equals("</PlayerSpawns>")){
				
				playerSpawns.add(new Point(Integer.parseInt(word), Integer.parseInt(scan.next())));
				
				word = scan.next();
			}
			
		}catch(IOException e){ System.out.println("Couldn't find file to load enemies."); }
		
		return playerSpawns;
	}
	
	//This method can be used to get the descriptions from the level files.
	//	Returns a String.
	public static String loadLevelDescription(String fileName){
		String levelDescription = "";
		String path = "res/levels/" + fileName + ".lvl";
		
		try{
			Scanner scan = new Scanner(new File(path));
			String word = "";
			
			try{
				word = scan.next();
				while(!word.equals("<Description>"))
					word = scan.next();
			}catch(Exception ex){System.out.println("Couldn't find <Description> tag in file."); }
			
			word = scan.next();
			while(!word.equals("</Description>")){
				
				levelDescription = levelDescription + " " + word;
				
				word = scan.next();
			}
			
			levelDescription = levelDescription.trim();
			
		}catch(IOException e){ System.out.println("Couldn't find file to load enemies."); }
		
		return levelDescription;
	}
	
}
