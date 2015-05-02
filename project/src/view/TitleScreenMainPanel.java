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
import javax.swing.JPanel;

import controller.Client;

public class TitleScreenMainPanel extends JPanel
{
	private Image background;
	private Image startButtonImage, storeButtonImage, aboutButtonImage,
			exitButtonImage;
	private JButton startButton, storeButton, aboutButton, exitButton;
	private MenuListener listener;

	private TitleScreen title;

	public TitleScreenMainPanel()
	{
		this(800, 600);
	}

	public TitleScreenMainPanel(int x, int y)
	{
		setPreferredSize(new Dimension(x, y));
		setLayout(null);
		// is background color neccesary at all?
		setBackground(Color.RED);

		listener = new MenuListener();

		title = TitleScreen.getTitleScreen();

		loadBackground();
		loadButton();
		addButton();
	}

	// This method is just to load in the panel's background image
	private void loadBackground()
	{
		try
		{
			background = ImageIO.read(new File(
					"res/titleScreen/titleBackground.png"));
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
			startButtonImage = ImageIO.read(new File(
					"res/titleScreen/startGame.png"));
			exitButtonImage = ImageIO
					.read(new File("res/titleScreen/quit.png"));
			storeButtonImage = ImageIO.read(new File(
					"res/titleScreen/store.png"));
			aboutButtonImage = ImageIO.read(new File(
					"res/titleScreen/about.png"));

			startButton = new JButton(new ImageIcon(startButtonImage));
			exitButton = new JButton(new ImageIcon(exitButtonImage));
			storeButton = new JButton(new ImageIcon(storeButtonImage));
			aboutButton = new JButton(new ImageIcon(aboutButtonImage));

			startButton.setOpaque(false);
			startButton.setContentAreaFilled(false);
			startButton.setBorderPainted(false);
			startButton.setRolloverIcon(new ImageIcon(ImageIO.read(new File(
					"res/titleScreen/startGame2.png"))));

			exitButton.setOpaque(false);
			exitButton.setContentAreaFilled(false);
			exitButton.setBorderPainted(false);
			exitButton.setRolloverIcon(new ImageIcon(ImageIO.read(new File(
					"res/titleScreen/quit2.png"))));

			storeButton.setOpaque(false);
			storeButton.setContentAreaFilled(false);
			storeButton.setBorderPainted(false);
			storeButton.setRolloverIcon(new ImageIcon(ImageIO.read(new File(
					"res/titleScreen/store2.png"))));

			aboutButton.setOpaque(false);
			aboutButton.setContentAreaFilled(false);
			aboutButton.setBorderPainted(false);
			aboutButton.setRolloverIcon(new ImageIcon(ImageIO.read(new File(
					"res/titleScreen/about2.png"))));

			startButton.setName("start");
			exitButton.setName("quit");
			storeButton.setName("store");
			aboutButton.setName("about");

			startButton.addActionListener(listener);
			exitButton.addActionListener(listener);
			storeButton.addActionListener(listener);
			aboutButton.addActionListener(listener);
		}
		catch (IOException e)
		{
			System.out.println("Couldn't find button image.");
		}
	}

	public void addButton()
	{
		this.add(startButton);
		this.add(exitButton);
		this.add(storeButton);
		this.add(aboutButton);

		startButton.setBounds(500, 100, startButtonImage.getWidth(null),
				startButtonImage.getHeight(null));
		storeButton.setBounds(500, 175, storeButtonImage.getWidth(null),
				storeButtonImage.getHeight(null));
		aboutButton.setBounds(500, 250, aboutButtonImage.getWidth(null),
				aboutButtonImage.getHeight(null));
		exitButton.setBounds(575, 325, exitButtonImage.getWidth(null),
				exitButtonImage.getHeight(null));
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

				if (button.getName() == "start")
				{
					// TitleScreen.TITLESTATE = 1;
					// TODO: For milestone 1, just set gamestate to 1.
					// TODO: change to titlestate = 1 later
					Client.GAMESTATE = 1;
					// SoundEffects.addSound("res/music/smb_over.mid");
				}

				else if (button.getName() == "quit")
				{
					Client.exit();
				}

				else if (button.getName() == "store")
				{
					// TODO: change this back to 2 later
					TitleScreen.TITLESTATE = 5;
					title.draw();
				}

				else if (button.getName() == "about")
				{
					TitleScreen.TITLESTATE = 3;
					title.draw();
				}
			}
		} // end of ActionPerformed
	} // end of MenuListener
}
