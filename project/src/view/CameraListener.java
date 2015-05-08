
package view;

import controller.Camera;
import controller.Client;

import java.awt.event.*;

public class CameraListener implements KeyListener{

		private int SOUTH = 0;
		private int WEST = 1;
		private int EAST = 2;
		private int NORTH = 3;
		
		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
//			System.out.println("Key pressed: " + e.getKeyCode());
			
			if(Client.GAMESTATE == 1){
				switch(key){
				
				case KeyEvent.VK_RIGHT:
					Camera.getCamera().updateCameraPosition(WEST);
//					System.out.println("Camera coordinates: " + Camera.CAMERAPOSITION);
					break;
					
				case KeyEvent.VK_LEFT:
					Camera.getCamera().updateCameraPosition(EAST);
//					System.out.println("Camera coordinates: " + Camera.CAMERAPOSITION);
					break;
					
				case KeyEvent.VK_UP:
					Camera.getCamera().updateCameraPosition(SOUTH);
//					System.out.println("Camera coordinates: " + Camera.CAMERAPOSITION);
					break;
					
				case KeyEvent.VK_DOWN:
					Camera.getCamera().updateCameraPosition(NORTH);
//					System.out.println("Camera coordinates: " + Camera.CAMERAPOSITION);
					break;
					
				case KeyEvent.VK_ESCAPE:
					Client.GAMESTATE = 2;
					break;
				
				}//End of switch statement.
			}
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		
	}//End of CameraListener Inner-class
