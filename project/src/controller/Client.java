


package controller;
import model.LoadGame;
import view.MainView;

public class Client implements Runnable {
	
	private MainView mainFrame;
	
	public static int GAMESTATE = 0;
	
	public static void main(String[]args){
		
		new Client();
		
	}
	
	public Client(){
		
		GAMESTATE = 0;
		
		mainFrame = new MainView();
		
		LoadGame.loadFile("res/levels/testLevel.lvl");
		System.out.println("Width: " + LoadGame.LEVELWIDTH + " Height: " + LoadGame.LEVELHEIGHT);
		
		//This is how to play a sound
		SoundEffects.addSound("res/music/smb_over.mid");
		
		mainFrame.draw();
		
		
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
