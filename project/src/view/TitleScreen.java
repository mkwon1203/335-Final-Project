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
	private TitleScreenMainPanel main;
	private TitleScreenAboutPanel about;

	private TitleScreen()
	{
		this(800, 600);
	}

	// Main constructor for the title screen
	private TitleScreen(int x, int y)
	{
		setVisible(true);
		setPreferredSize(new Dimension(x, y));
		setLayout(null);
		// is background neccesary???
		setBackground(Color.BLUE);
	}

	// This method can be called to get the title screen object
	public static TitleScreen getTitleScreen()
	{
		if (title == null)
			title = new TitleScreen(800, 600);
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
	}
}
