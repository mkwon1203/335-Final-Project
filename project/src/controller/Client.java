


package controller;
import model.LoadGame;
import view.MainView;

public class Client implements Runnable {
	
	private MainView mainFrame;
	
	public static final int BLOCKSIZE = 32;
	
	public static int GAMESTATE = 0;
	
	private boolean isRunning = false;
	
	public static void main(String[]args){
		
		Client client = new Client();
		
		client.start();
	}
	
	public void start(){
		isRunning = true;
		
		new Thread(this).start();
	}
	
	public void stop(){
		isRunning = false;
	}
	
	public static void exit(){
		System.exit(0);
	}
	
	public Client(){
		
		GAMESTATE = 0;
		
		mainFrame = new MainView();
		
		//LoadGame.loadFile("res/levels/milestone.lvl");
		//System.out.println("Width: " + LoadGame.LEVELWIDTH + " Height: " + LoadGame.LEVELHEIGHT);
		
		//This is how to play a sound
		//SoundEffects.addSound("res/music/smb_over.mid");
		
		mainFrame.draw();
	}
	
	public void update(){
		
		
			
		
	}
	
	public void draw(){
		
		//This line will draw the main window for the game
		//	inside of mainFrame the draw method will decide what should
		//	be drawn. i.e. the level or the title screen.
		mainFrame.draw();
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(isRunning){
			
			if(GAMESTATE > 0){
				//System.out.println("Game Started");
				update();
				draw();
			}
			
			try{
				Thread.sleep(5);
			}catch(Exception e)	{System.out.println("Error with sleeping thread.");}
			
		}
		
	}
	
	
	
}
