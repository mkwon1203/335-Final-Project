package model;

import java.awt.Image;
import java.util.List;
import java.awt.Point;

import javax.swing.SwingWorker;

import controller.Client;


public class Animation extends SwingWorker<Boolean, Point>{
		
		public static boolean UNITMOVING = false;
		
		private int framesX;
		private int speed;
		private int index;
		private Point currentFrame, coordinates;
		private List<Point> path;
		private int frameDirection;
		private int currentIndex;
		
		private CharacterInterface unit;
		
		private int currentDirection;
		private final int SOUTH = 0;
		private final int WEST = 1;
		private final int EAST = 2;
		private final int NORTH = 3;
		
		private boolean textureChanged;
		
		private Image[][] images;
		
		public Animation(List<Point> path, CharacterInterface unit){
			
			images = model.LoadSprites.loadSpriteSheet(unit.getTextureFilePath(), 4, 3, Client.BLOCKSIZE);
			textureChanged = false;
			this.unit = unit;
			
			for(int x = 0; x < path.size(); x++)
				path.set(x, new Point(path.get(x).y * Client.BLOCKSIZE, path.get(x).x * Client.BLOCKSIZE));
			
			this.path = path;
			
			this.coordinates = unit.getScreenCoordinate();
//			System.out.println("Starting position: " + coordinates);
			currentFrame = new Point(0,1);
			framesX = images[0].length;
			frameDirection = 1;
			currentDirection = 0;
			currentIndex = 0;
			speed = 3;
			index = 0;
		}
		
		private void nextFrame(int direction){
			currentFrame.y = direction;
			textureChanged = true;
			
			currentFrame.x += frameDirection;
			if(currentFrame.x >= framesX - 1)
				frameDirection = -1;
			else if(currentFrame.x <= 0)
				frameDirection = 1;
			
//			System.out.println("texture changed. " + currentFrame);
			
		}
		
		@Override
		protected Boolean doInBackground() throws Exception {
			
			while(currentIndex != path.size()){
				index++;
				
				if(path.get(currentIndex).x < coordinates.x){
					currentDirection = WEST;
					coordinates.x--;
				}else if(path.get(currentIndex).x > coordinates.x){
					currentDirection = EAST;
					coordinates.x++;
				}else if(path.get(currentIndex).y < coordinates.y){
					currentDirection = NORTH;
					coordinates.y--;
				}else if(path.get(currentIndex).y > coordinates.y){
					currentDirection = SOUTH;
					coordinates.y++;
				}
				
				if(index > speed){
					index = 0;
					nextFrame(currentDirection);
				}
				
				publish(coordinates);
				
				if(coordinates.equals(path.get(currentIndex)))
					currentIndex++;
				
				Thread.sleep(30);
			}
			return true;
		}
		
		protected void done() {
//			System.out.println("Thread stopped");
			unit.setTexture(images[currentDirection][1]);
			unit.setAnimated(false);
			UNITMOVING = false;
			super.done();
		}

		@Override
		protected void process(List<Point> arg0) {
			
			if(textureChanged)
				this.unit.setTexture(images[currentFrame.y][currentFrame.x]);
			
			this.unit.setScreenCoordinate(arg0.get(arg0.size() - 1));
			//System.out.println("CurrentFrame: " + currentFrame);
//			System.out.println("Coordinates: " + coordinates);
			UNITMOVING = true;
		}
		
	
}//End of Animate Class
