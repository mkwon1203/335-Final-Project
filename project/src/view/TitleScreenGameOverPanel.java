package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Game;
import controller.SoundEffects;

public class TitleScreenGameOverPanel extends JPanel
{
	private Image background;
	private Image backButtonImage;
	private JButton backButton;
	private JLabel aboutLabel;
	private MenuListener listener;
	
	private TitleScreen title;
	
	public TitleScreenGameOverPanel()
	{
		this(800,612);
	}
	
	public TitleScreenGameOverPanel(int x, int y)
	{
		setPreferredSize(new Dimension(x, y));
		setLayout(null);
		// is background color neccesary at all?
		setBackground(Color.BLACK);

		listener = new MenuListener();

		title = TitleScreen.getTitleScreen();

		loadBackground();
		loadButton();
		addButton();
	}

	private void loadBackground()
	{
		try
		{
			background = ImageIO.read(new File(
					"res/titleScreen/titleBackground2.png"));
			// backgroundLabel = new JLabel(new ImageIcon(titlePic));
		}
		catch (IOException e)
		{
			System.out.println("Couldn't find title picture.");
		}
	}

	// This method is just to load in the images for the title screen's buttons
	private void loadButton()
	{
		try
		{
			backButtonImage = ImageIO.read(new File(
					"res/titleScreen/back.png"));

			backButton = new JButton(new ImageIcon(backButtonImage));

			backButton.setOpaque(false);
			backButton.setContentAreaFilled(false);
			backButton.setBorderPainted(false);
			backButton.setRolloverIcon(new ImageIcon(ImageIO.read(new File(
					"res/titleScreen/back2.png"))));
			
			backButton.setName("back");
			
			backButton.addActionListener(listener);
		}
		catch (IOException e)
		{
			System.out.println("Couldn't find button image.");
		}
	}

	public void addButton()
	{
		this.add(backButton);

		backButton.setBounds(500, 100, backButtonImage.getWidth(null),
				backButtonImage.getHeight(null));
	}
	
	private void loadLabel()
	{
		// TODO: fill out the label
		aboutLabel = new JLabel();
		aboutLabel.setForeground(Color.WHITE);
		String reason = "Game Over";
		switch(Game.GAMEOVER){
		
		case 1:
			reason = "Victory!\nWon by Conquest!";
			break;
			
		case 2:
			reason = "Defeat!\nYou're team was defeated by the enemy!";
			break;
			
		case 3:
			reason = "Victory!\nTime ended, and your'e team is the strongest!";
			break;
			
		case 4:
			reason = "Defeat!\nTime ended, end the enemy has a stronger team than you!";
			break;
		}
		
		aboutLabel.setText(reason);
		addLabel();
	}
	
	private void addLabel()
	{
		add(aboutLabel);
		
		aboutLabel.setBounds(375,275, 300,300);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);
	}
	
	private class MenuListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() instanceof JButton)
			{
				JButton button = (JButton) e.getSource();

				if (button.getName() == "back")
				{
					SoundEffects.clearSoundClips();
					SoundEffects.addSound("res/music/8bitDungeonTitle.wav");
					TitleScreen.TITLESTATE = 0;
					title.draw();
				}
			}
		} // end of ActionPerformed
	} // end of MenuListener
}
