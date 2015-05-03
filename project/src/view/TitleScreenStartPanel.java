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

public class TitleScreenStartPanel extends JPanel
{
	private Image background;
	private Image newGameButtonImage, loadGameButtonImage, backButtonImage;
	private JButton newGameButton, loadGameButton, backButton;
	private MenuListener listener;

	private TitleScreen title;

	public TitleScreenStartPanel()
	{
		this(800, 612);
	}

	public TitleScreenStartPanel(int x, int y)
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
			newGameButtonImage = ImageIO.read(new File(
					"res/titleScreen/newGame.png"));
			loadGameButtonImage = ImageIO
					.read(new File("res/titleScreen/loadGame.png"));
			backButtonImage = ImageIO.read(new File(
					"res/titleScreen/back.png"));

			newGameButton = new JButton(new ImageIcon(newGameButtonImage));
			loadGameButton = new JButton(new ImageIcon(loadGameButtonImage));
			backButton = new JButton(new ImageIcon(backButtonImage));

			newGameButton.setOpaque(false);
			newGameButton.setContentAreaFilled(false);
			newGameButton.setBorderPainted(false);
			newGameButton.setRolloverIcon(new ImageIcon(ImageIO.read(new File(
					"res/titleScreen/newGame2.png"))));

			loadGameButton.setOpaque(false);
			loadGameButton.setContentAreaFilled(false);
			loadGameButton.setBorderPainted(false);
			loadGameButton.setRolloverIcon(new ImageIcon(ImageIO.read(new File(
					"res/titleScreen/loadGame2.png"))));

			backButton.setOpaque(false);
			backButton.setContentAreaFilled(false);
			backButton.setBorderPainted(false);
			backButton.setRolloverIcon(new ImageIcon(ImageIO.read(new File(
					"res/titleScreen/back2.png"))));

			newGameButton.setName("newGame");
			loadGameButton.setName("loadGame");
			backButton.setName("back");

			newGameButton.addActionListener(listener);
			loadGameButton.addActionListener(listener);
			backButton.addActionListener(listener);
		}
		catch (IOException e)
		{
			System.out.println("Couldn't find button image.");
		}
	}

	public void addButton()
	{
		this.add(newGameButton);
		this.add(loadGameButton);
		this.add(backButton);

		newGameButton.setBounds(500, 100, newGameButtonImage.getWidth(null),
				newGameButtonImage.getHeight(null));
		loadGameButton.setBounds(500, 175, loadGameButtonImage.getWidth(null),
				loadGameButtonImage.getHeight(null));
		backButton.setBounds(500, 250, backButtonImage.getWidth(null),
				backButtonImage.getHeight(null));
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

				if (button.getName() == "newGame")
				{
					TitleScreen.TITLESTATE = 4;
					title.draw();
				}

				else if (button.getName() == "loadGame")
				{
					// TODO: figure out what to do here
				}

				else if (button.getName() == "back")
				{
					TitleScreen.TITLESTATE = 0;
					title.draw();
				}
			}
		} // end of ActionPerformed
	} // end of MenuListener
}
