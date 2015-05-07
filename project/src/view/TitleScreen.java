package view;

import controller.SoundEffects;

import java.awt.*;
import javax.swing.*;

public class TitleScreen extends JPanel
{

	public static int TITLESTATE = 0;
	private static TitleScreen title = null;

	// list of panels
	private TitleScreenMainPanel main; // titlestate = 0
	private TitleScreenStartPanel start; // 1
	private TitleScreenShopPanel shop;// 2
	private TitleScreenAboutPanel about; // 3
	private TitleScreenLevelPanel level; // 4
	private TitleScreenUnitPanel unit; // 5
	private TitleScreenGameOverPanel gameOver; // 6

	private TitleScreen()
	{
		this(800, 612);
	}

	// Main constructor for the title screen
	private TitleScreen(int x, int y)
	{
		setVisible(true);
		setPreferredSize(new Dimension(x, y));
		setLayout(null);
		// is background neccesary???
		setBackground(Color.BLACK);
	}

	// This method can be called to get the title screen object
	public static TitleScreen getTitleScreen()
	{
		if (title == null)
			title = new TitleScreen(800, 612);
		return title;
	}

	public void resetGame()
	{
		level = null;
		unit = null;
	}

	public void draw()
	{
		// make panel clear for drawing new stuff
		removeAll();

		if (TITLESTATE == 0)
		{
			// very start of title screen
			if (!SoundEffects
					.containsSoundClip("res/music/8bitDungeonTitle.wav"))
				SoundEffects.addSound("res/music/8bitDungeonTitle.wav");

			main = new TitleScreenMainPanel();
			main.setBounds(0, 0, main.getPreferredSize().width,
					main.getPreferredSize().height);

			this.add(main);
			main.setVisible(true);
			main.repaint();
		}
		else if (TITLESTATE == 1)
		{
			// start button pressed from main title screen
			start = new TitleScreenStartPanel();
			start.setBounds(0, 0, start.getPreferredSize().width,
					start.getPreferredSize().height);

			this.add(start);
			start.setVisible(true);
			start.repaint();
		}
		else if (TITLESTATE == 2)
		{
			// store interface
			shop = new TitleScreenShopPanel();
			shop.setBounds(0, 0, shop.getPreferredSize().width,
					shop.getPreferredSize().height);

			this.add(shop);
			shop.setVisible(true);
			shop.repaint();
		}
		else if (TITLESTATE == 3)
		{
			// about interface

			about = new TitleScreenAboutPanel();
			about.setBounds(0, 0, about.getPreferredSize().width,
					about.getPreferredSize().height);

			this.add(about);
			about.setVisible(true);
			about.repaint();
		}
		else if (TITLESTATE == 4)
		{
			level = new TitleScreenLevelPanel();
			level.setBounds(0, 0, level.getPreferredSize().width,
					level.getPreferredSize().height);

			this.add(level);
			level.setVisible(true);
			level.repaint();
		}
		else if (TITLESTATE == 5)
		{
			unit = new TitleScreenUnitPanel();
			unit.setBounds(0, 0, unit.getPreferredSize().width,
					unit.getPreferredSize().height);

			this.add(unit);
			unit.setVisible(true);
			unit.repaint();
		}
		else if (TITLESTATE == 6)
		{
			gameOver = new TitleScreenGameOverPanel();
			gameOver.setBounds(0, 0, gameOver.getPreferredSize().width,
					gameOver.getPreferredSize().height);

			this.add(gameOver);
			gameOver.setVisible(true);
			gameOver.repaint();
		}
	}
}
