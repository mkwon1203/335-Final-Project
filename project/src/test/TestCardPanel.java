package test;

import view.*;
import model.*;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.*;
import java.awt.*;

import javax.swing.*;

public class TestCardPanel extends JPanel
{
	public static void main(String[]args) throws InterruptedException
	{
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(800, 600);
		TestCardPanel p = new TestCardPanel();
		frame.add(p);
		frame.pack();
		p.run();
	}
	public TestCardPanel() throws InterruptedException
	{
		setPreferredSize(new Dimension(800,600));
		setLayout(new CardLayout());
		JButton b = new JButton("CLICK MEEE");
		
		
		TitleScreenAboutPanel p1 = new TitleScreenAboutPanel(800,600);
		add(p1, "ABOUTPANEL");
		
		TitleScreen p2 = TitleScreen.getTitleScreen();
		add(p2, "TITLEPANEL");
	}
	
	public void run() throws InterruptedException
	{
		((CardLayout) this.getLayout()).show(this, "ABOUTPANEL");
		Thread.sleep(5000);
		((CardLayout) this.getLayout()).show(this, "TITLEPANEL");
	}
}
