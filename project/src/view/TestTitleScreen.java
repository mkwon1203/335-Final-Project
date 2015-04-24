package view;

import model.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class TestTitleScreen extends JPanel
{
	JButton start, option, quit;
	Image background;
	
	public static void main(String[]args) throws IOException
	{
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800,600);
		
		TestTitleScreen panel = new TestTitleScreen();
		frame.add(panel);
		frame.pack();
		frame.setResizable(false);
	}
	
	public TestTitleScreen() throws IOException
	{
		background = ImageIO.read(new File("res/titleScreen/titleBackground.png"));
		repaint();
		start = new JButton(new ImageIcon(ImageIO.read(new File("res/titleScreen/startgame.png"))));
		option = new JButton("Option"); // change to icon here too 
		quit = new JButton(new ImageIcon(ImageIO.read(new File("res/titleScreen/quit.png"))));
		
		setPreferredSize(new Dimension(800,600));
		setLayout(null);
		add(start);
		
		start.setBounds(500,175, 266,32);
		start.setRolloverIcon(new ImageIcon(ImageIO.read(new File("res/titleScreen/startGame2.png"))));
		start.setOpaque(false);
		start.setContentAreaFilled(false);
		start.setBorderPainted(false);
		add(option);
		option.setBounds(500,275, 266, 32);
		// option needs to have correct icon
		add(quit);
		quit.setBounds(500,375,266,32);
		quit.setRolloverIcon(new ImageIcon(ImageIO.read(new File("res/titleScreen/quit2.png"))));
		quit.setOpaque(false);
		quit.setContentAreaFilled(false);
		quit.setBorderPainted(false);
	}
	
	public void paintComponent(Graphics page)
	{
		super.paintComponent(page);
		page.drawImage(background, 0, 0, null);
	}
	
	
}
