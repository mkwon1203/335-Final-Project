package view;

import controller.Client;
import controller.SoundEffects;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.event.MouseInputAdapter;

public class MenuListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			if(e.getSource() instanceof JButton){
				JButton button = (JButton)e.getSource();
				
				if(button.getName() == "start"){
					//TitleScreen.TITLESTATE = 1;
					//TODO: For milestone 1, just set gamestate to 1. Later change it.
					Client.GAMESTATE = 1;
					//TODO: Hard code player positions here. Also Enemy Locations.
					//SoundEffects.addSound("res/music/smb_over.mid");
				} else if(button.getName() == "quit"){
					Client.exit();
				}
				
			}
		}
		
	}