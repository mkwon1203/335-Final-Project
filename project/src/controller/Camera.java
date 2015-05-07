

package controller;

import java.awt.Point;

public class Camera {

	public static Point CAMERAPOSITION = new Point();
	private static Camera camera = null;
	
	private Camera(){ }
	
	//This method will return the Camera object, it will also create a Camera object
	//	if one has not already been created.
	public static Camera getCamera(){
		if(camera == null){
			camera = new Camera();
			return camera;
		}else
			return camera;
	}
	
	//Returns the Camera's position
	public Point getCameraPosition(){
		return CAMERAPOSITION;
	}
	
	//Sets the Camera's position.
	public void setCameraPosition(Point newLocation){
		CAMERAPOSITION = newLocation;
	}
	
	//This method will update the camera's location, based on a given direction.
	public void updateCameraPosition(int direction){
		
		
		switch(direction){
		
		case 0:
			CAMERAPOSITION.y+=4;
			break;
			
		case 1:
			CAMERAPOSITION.x-=4;
			break;
			
		case 2:
			CAMERAPOSITION.x+=4;
			break;
			
		case 3:
			CAMERAPOSITION.y-=4;
			break;
			
		}//End of switch statement.
		
	}
	
	
}
