package model;

import java.io.*;
import java.util.*;

public class LoadGame {
	
	public static int[][] LEVELARRAY = null;
	public static int LEVELWIDTH = 0;
	public static int LEVELHEIGHT = 0;
	
	//To ensure only one instance of this class
	private LoadGame(String fileName){ }
	
	//Call this method to load a level file
	public static int[][] loadFile(String fileName){
		
		//  "res/levels/testLevel.lvl"
		
		try{
			Scanner loadScanner = new Scanner(new File(fileName));
			
			boolean firstPass = true;
			while(loadScanner.hasNextLine()) {
				String line = loadScanner.nextLine();
				if(line.equals(""))
					break;
				if(firstPass){
					Scanner scan = new Scanner(line);
					while(scan.hasNextInt()){
						scan.nextInt();
						LEVELWIDTH++;
					}
					firstPass = false;
					scan.close();
				}
				LEVELHEIGHT++;
			}
			
			LEVELARRAY = new int[LEVELHEIGHT][LEVELWIDTH];
			
			loadScanner = new Scanner(new File(fileName));
			while(loadScanner.hasNextInt()){
				for(int y=0;y<LEVELHEIGHT;y++){
					for(int x=0;x<LEVELWIDTH;x++){
						LEVELARRAY[y][x] = loadScanner.nextInt();
					}
				}
			}
			
			loadScanner.close();
			
			return LEVELARRAY;
		}catch(IOException e){
			System.out.println("Couldn't find given level file.");
			return null;
		}
		
	}
	
}
