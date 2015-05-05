package model;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class LoadSprites {
	
	
	public static Image[][] loadSpriteSheet(String fileName, int row, int col, int size){
		
		Image[][] images = new Image[row][col];
		BufferedImage spriteSheet;
		try{
			spriteSheet = ImageIO.read(new File(fileName));
			
			for(int r = 0; r < row; r++)
				for(int c = 0; c < col; c++)
					images[r][c] = spriteSheet.getSubimage(c * size, r * size, 
							size, size);
			
		}catch(IOException e){ System.out.println("Couldn't find specified spriteSheet."); }
		
		return images;
	}
	
	
}
