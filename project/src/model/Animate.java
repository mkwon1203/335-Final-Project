package model;

import java.awt.Image;
import java.util.List;
import java.awt.Point;

import javax.swing.SwingWorker;


public class Animate {

//	private ArrayList<Image> frames = new ArrayList<Image>();
	private Image[][] images;
	
	
	public static final int NORTH = 3;
	public static final int SOUTH = 0;
	public static final int EAST = 2;
	public static final int WEST = 1;
	
	public Animate(Image[][] images){
		this.images = images;
//		addSprites();
	}
	
	//Sets the idle image for given character based on the direction
	//	 they are facing.
	public void setIdleImage(Character c, int direction){
		
		switch(direction){
		
		case SOUTH:
			c.setTexture(images[SOUTH][1]);
			break;
			
		case WEST:
			c.setTexture(images[WEST][1]);
			break;
			
		case EAST:
			c.setTexture(images[EAST][1]);
			break;
			
		case NORTH:
			c.setTexture(images[NORTH][1]);
			break;
		
		}
	}
	
	public void animate(List<Point> path, Character unit, int speed){
		
		//I have no idea if this shit will work, probably not; but will find out later.
		//TODO: Find out if this shit will actually work.
		//OH GOD! PLEASE FUCKING WORK THE WAY I WANT YOU TO!!!!
		//TODO: Remove swear words from comments.
		Animator worker = new Animator(path, unit, speed);
		
		worker.execute();
	}//End of animate method
	
	
	private class Animator extends SwingWorker<Boolean, Integer>{
		
		private Point coordinates;
		private boolean changeImage = false;
		private int frameCounter = 0;
		private int currentFrame = 0;
		private int previousDirection = 0;
		private List<Point> path;
		private Character unit;
		private int speed;
		
		
		public Animator(List<Point> path, Character unit, int speed){
			this.path = path;
			this.unit = unit;
			this.speed = speed;
			this.coordinates = unit.getLocation();
		}
		
		@Override
		protected Boolean doInBackground() throws Exception {
			
			for(Point p : path){
				
				if(p.x > unit.getLocation().x){
					while(p.x > unit.getLocation().x){
						coordinates.x++;
						if(speed % frameCounter == 0){
							changeImage = true;
							if(++currentFrame >= 2)
								currentFrame = 0;
						}
						publish(EAST);
					}
				}else if(p.x < unit.getLocation().x){
					while(p.x < unit.getLocation().x){
						coordinates.x--;
						if(speed % frameCounter == 0){
							changeImage = true;
							if(++currentFrame >= 2)
								currentFrame = 0;
						}
						publish(WEST);
					}
				}else if(p.y > unit.getLocation().y){
					while(p.y > unit.getLocation().y){
						coordinates.y++;
						if(speed % frameCounter == 0){
							changeImage = true;
							if(++currentFrame >= 2)
								currentFrame = 0;
						}
						publish(NORTH);
					}
				}else if(p.y < unit.getLocation().y){
					while(p.y < unit.getLocation().y){
						coordinates.y--;
						if(speed % frameCounter == 0){
							changeImage = true;
							if(++currentFrame >= 2)
								currentFrame = 0;
						}
						publish(SOUTH);
					}
				}
				
				Thread.sleep(25);
			}//End of for each loop
			
			return true;
		}

		@Override
		protected void done() {
			setIdleImage(unit, previousDirection);
			super.done();
		}

		@Override
		protected void process(List<Integer> arg0) {
			if(changeImage){
				Image currentImage = images[arg0.get(arg0.size() - 1)][currentFrame];
				unit.setTexture(currentImage);
				changeImage = false;
			}
			
			//TODO: Add some way to manually change a unit's coordinates from here.
			// Kind of like this line below.
//			unit.setCoordinates(coordinates.x * Client.BLOCKSIZE, coordinates.y * Client.BLOCKSIZE);
			previousDirection = arg0.get(arg0.size() - 1);
		}
		
	} //End of Animator inner-class
	
}//End of Animate Class
