package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import controller.Client;

public class PauseMenu extends JPanel implements Serializable{
	private Image saveImage, resumeImage, quitImage;
	private JButton saveButton, resumeButton, quitButton;
	private ButtonListener buttonListener;
	public PauseMenu()
	{
		this(800, 612);
	}
    
	public PauseMenu(int x, int y)
	{
		setPreferredSize(new Dimension(x, y));
		setLayout(null);
		this.setBackground(Color.BLACK);
		
		buttonListener = new ButtonListener();
		loadButton();
		addButton();
	}
	
	public boolean saveGame(String saveFileName){
		try{
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(saveFileName));
			os.writeObject(MainView.getMainView().getGame());
			os.close();
			return true;
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		return false;
	}
    
	private void loadButton() {
        try
        {
            // load images for the buttons
            saveImage = ImageIO.read(new File("res/titleScreen/saveGame.png"));
            resumeImage = ImageIO.read(new File("res/titleScreen/resume.png"));
            quitImage = ImageIO.read(new File("res/titleScreen/quit.png"));
            
            // Initialize buttons
            saveButton = new JButton(new ImageIcon(saveImage));
            resumeButton = new JButton(new ImageIcon(resumeImage));
            quitButton = new JButton(new ImageIcon(quitImage));
            
            // set button look and feel
            saveButton.setOpaque(false);
            saveButton.setContentAreaFilled(false);
            saveButton.setBorderPainted(false);
            saveButton.setRolloverIcon(new ImageIcon(ImageIO.read(new File(
                                                                           "res/titleScreen/saveGame2.png"))));
            
            resumeButton.setOpaque(false);
            resumeButton.setContentAreaFilled(false);
            resumeButton.setBorderPainted(false);
            resumeButton.setRolloverIcon(new ImageIcon(ImageIO.read(new File(
                                                                             "res/titleScreen/resume2.png"))));
            
            quitButton.setOpaque(false);
            quitButton.setContentAreaFilled(false);
            quitButton.setBorderPainted(false);
            quitButton.setRolloverIcon(new ImageIcon(ImageIO.read(new File(
                                                                           "res/titleScreen/quit2.png"))));
            
            saveButton.setName("save");
            resumeButton.setName("resume");
            quitButton.setName("quit");
            
            // attach action listeners
            saveButton.addActionListener(buttonListener);
            resumeButton.addActionListener(buttonListener);
            quitButton.addActionListener(buttonListener);
            
        }
        catch(IOException ex)
        {
            System.out.println("couldn't load shopPanel item label images");
        }
	}
	private void addButton() {
		this.add(saveButton);
		this.add(resumeButton);
		this.add(quitButton);
		
		saveButton.setBounds(350, 175, saveImage.getWidth(null), saveImage.getHeight(null));
		resumeButton.setBounds(350, 275, resumeImage.getWidth(null), resumeImage.getHeight(null));
		quitButton.setBounds(350, 375, quitImage.getWidth(null), quitImage.getHeight(null));
	}
	
	private class ButtonListener implements ActionListener
	{
        
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() instanceof JButton)
			{
				JButton button = (JButton) e.getSource();
                
				if (button.getName() == "save")
				{
					saveGame("SavedGame.bin");
				}
				else if (button.getName() == "resume"){
					Client.GAMESTATE = 1;
				}
				else if (button.getName() == "quit"){
					System.exit(0);
				}
			}
			
		}
		
	}
}
