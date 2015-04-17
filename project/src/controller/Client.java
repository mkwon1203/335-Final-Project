package controller;

import view.MainView;
import model.*;

public class Client implements Runnable {
	
	private MainView mainFrame;
	
	public static int GAMESTATE = 0;
	
	public static void main(String[]args){
		
		//new Client();
		Map map = new Map("res/levels/example.lvl");
		System.out.println(map);
	}
	
	public Client(){
		
		GAMESTATE = 0;
		
		mainFrame = new MainView();
		
		mainFrame.draw();
		
	}
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
