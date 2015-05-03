package view;

import controller.Client;
import model.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.event.*;
import javax.swing.*;

public class TitleScreen extends JPanel
{

	public static int TITLESTATE = 0;
	private static TitleScreen title = null;

	// list of panels
	private TitleScreenMainPanel main; // titlestate = 0
	private TitleScreenStartPanel start; // 1
	// private TitleScreenShopPanel shop;// 2
	private TitleScreenAboutPanel about; // 3
	private TitleScreenLevelPanel level; // 4
	private TitleScreenUnitPanel unit; // 5

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

	public void draw()
	{
		// make panel clear for drawing new stuff
		removeAll();

		if (TITLESTATE == 0)
		{
			// very start of title screen
			if (main == null)
			{
				main = new TitleScreenMainPanel();
				main.setBounds(0, 0, main.getPreferredSize().width,
						main.getPreferredSize().height);
			}
			this.add(main);
			main.setVisible(true);
			main.repaint();
		}
		else if (TITLESTATE == 1)
		{
			// start button pressed from main title screen
			if (start == null)
			{
				start = new TitleScreenStartPanel();
				start.setBounds(0, 0, start.getPreferredSize().width,
						start.getPreferredSize().height);
			}
			this.add(start);
			start.setVisible(true);
			start.repaint();
		}
		else if (TITLESTATE == 2)
		{
			// store interface

		}
		else if (TITLESTATE == 3)
		{
			// about interface
			if (about == null)
			{
				about = new TitleScreenAboutPanel();
				about.setBounds(0, 0, about.getPreferredSize().width,
						about.getPreferredSize().height);
			}
			this.add(about);
			about.setVisible(true);
			about.repaint();
		}
		else if (TITLESTATE == 4)
		{
			if (level == null)
			{
				level = new TitleScreenLevelPanel();
				level.setBounds(0, 0, level.getPreferredSize().width,
						level.getPreferredSize().height);
			}
			this.add(level);
			level.setVisible(true);
			level.repaint();
		}
		else if (TITLESTATE == 5)
		{
			if (unit == null)
			{
				unit = new TitleScreenUnitPanel();
				unit.setBounds(0, 0, unit.getPreferredSize().width,
						unit.getPreferredSize().height);
			}
			this.add(unit);
			unit.setVisible(true);
			unit.repaint();
		}
	}
}
