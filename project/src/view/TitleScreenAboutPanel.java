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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class TitleScreenAboutPanel extends JPanel
{
	private Image background;
	private Image backButtonImage, nextButtonImage, quitButtonImage;
	private JButton backButton, tutorialButton, nextButton, backTutorial, quitTutorial;
	private JTextArea aboutLabel;
	private MenuListener listener;
	private JScrollPane aboutScrollPane;
	private int aboutState = 0;
	
	private TitleScreen title;
	
	public TitleScreenAboutPanel()
	{
		this(800,612);
	}
	
	public TitleScreenAboutPanel(int x, int y)
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
		loadTextArea();
		addTextArea();
	}
    
	private void loadBackground()
	{
		if (aboutState == 0){
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
		
		// attack and move tutorial
		else if (aboutState == 1){
			try{
				background = ImageIO.read(new File("res/titleScreen/Attack&MoveTutorial.png"));
			}
			catch (IOException e1){
				System.out.println("Couldn't find button image.");
			}
		}
		// item tutorial
		else if (aboutState == 2){
			try{
				background = ImageIO.read(new File("res/titleScreen/ItemTutorial.png"));
			}
			catch (IOException e1){
				System.out.println("Couldn't find button image.");
			}
		}
		else if (aboutState == 3){
			try{
				background = ImageIO.read(new File("res/titleScreen/SkillTutorial.png"));
			}
			catch (IOException e1){
				System.out.println("Couldn't find button image.");
			}
		}
	}
    
	// This method is just to load in the images for the title screen's buttons
	private void loadButton()
	{
		try
		{
			backButtonImage = ImageIO.read(new File(
                                                    "res/titleScreen/back.png"));
			nextButtonImage = ImageIO.read(new File(
                    								"res/titleScreen/next.png"));
			
			quitButtonImage = ImageIO.read(new File("res/titleScreen/quit.png"));
            
			backButton = new JButton(new ImageIcon(backButtonImage));
            
			backButton.setOpaque(false);
			backButton.setContentAreaFilled(false);
			backButton.setBorderPainted(false);
			backButton.setRolloverIcon(new ImageIcon(ImageIO.read(new File(
                                                                           "res/titleScreen/back2.png"))));
			
			backButton.setName("back");
			
			backButton.addActionListener(listener);
			
			// back button in tutorial
			backTutorial = new JButton(new ImageIcon(backButtonImage));
			backTutorial.setOpaque(false);
			backTutorial.setContentAreaFilled(false);
			backTutorial.setBorderPainted(false);
			backTutorial.setRolloverIcon(new ImageIcon(ImageIO.read(new File("res/titleScreen/back2.png"))));
			
			// next button in tutorial
			nextButton = new JButton(new ImageIcon(nextButtonImage));
			nextButton.setOpaque(false);
			nextButton.setContentAreaFilled(false);
			nextButton.setBorderPainted(false);
			nextButton.setRolloverIcon(new ImageIcon(ImageIO.read(new File(
                                                                           "res/titleScreen/next2.png"))));
			
			// quit tutorial button
			quitTutorial = new JButton(new ImageIcon(quitButtonImage));
			quitTutorial.setOpaque(false);
			quitTutorial.setContentAreaFilled(false);
			quitTutorial.setBorderPainted(false);
			quitTutorial.setRolloverIcon(new ImageIcon(ImageIO.read(new File("res/titleScreen/quit2.png"))));
		}
		catch (IOException e)
		{
			System.out.println("Couldn't find button image.");
		}
		
		// adding the tutorial button
		tutorialButton = new JButton("Tutorial");
		tutorialButton.setName("tutorial");
		tutorialButton.addActionListener(listener);
		
		nextButton.setName("next");
		nextButton.addActionListener(listener);
		
		backTutorial.setName("backTutorial");
		backTutorial.addActionListener(listener);
		
		quitTutorial.setName("quitTutorial");
		quitTutorial.addActionListener(listener);
	}
    
	private void addButton()
	{
		this.add(backButton);
        
		backButton.setBounds(500, 550, backButtonImage.getWidth(null),
                             backButtonImage.getHeight(null));
		
		this.add(tutorialButton);
		tutorialButton.setBounds(500, 100, 100, 50);
		
		this.add(nextButton);
		nextButton.setBounds(625, 550, 100, 50);
		nextButton.setVisible(false); // want this button to appear after you click tutorial
		
		this.add(backTutorial);
		backTutorial.setBounds(500, 550, 100, 50);
		backTutorial.setVisible(false);
		
		this.add(quitTutorial);
		quitTutorial.setBounds(625,550,100,50);
		quitTutorial.setVisible(false);
	}
	
	private void loadTextArea()
	{
		// TODO: fill out the label
		aboutLabel = new JTextArea();
		aboutLabel.setText("This TRPG game was created by team GMCM for a final project at the University of Arizona. Team members include Christopher Grundy, Min Kwon, Josue Morga, and Shunchen Cao.");
		aboutLabel.setBounds(500,200, 250,250);
		aboutLabel.setEditable(false);
		aboutLabel.setWrapStyleWord(true);
		aboutLabel.setLineWrap(true);
		aboutLabel.setOpaque(false);
	}
	
	private void addTextArea()
	{
		this.add(aboutLabel);
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
					TitleScreen.TITLESTATE = 0;
					title.draw();
				} // adding code for tutorial
				else if (button.getName() == "tutorial"){
                    aboutState = 1;
                    loadBackground();
                    repaint();
                    tutorialButton.setVisible(false);
                    aboutLabel.setVisible(false);
                    backButton.setVisible(false);
                    nextButton.setVisible(true);
                    backTutorial.setVisible(true);
                }
				else if (button.getName() == "next"){
					if (aboutState < 3)
						aboutState++;
					loadBackground();
					repaint();
					tutorialButton.setVisible(false);
					aboutLabel.setVisible(false);
					backButton.setVisible(false);
					if (aboutState == 3){
						nextButton.setVisible(false);
						quitTutorial.setVisible(true);
					}
					backTutorial.setVisible(true);
				}
				else if (button.getName() == "backTutorial"){
					if (aboutState > 0)
						aboutState--;
					loadBackground();
					repaint();
                    backButton.setVisible(false);
                    nextButton.setVisible(true);
                    if (aboutState == 0){
                        tutorialButton.setVisible(true);
                        aboutLabel.setVisible(true);
                        backTutorial.setVisible(false);
                        nextButton.setVisible(false);
                        backButton.setVisible(true);
                    }
                    quitTutorial.setVisible(false);
                    
				}
				else if (button.getName() == "quitTutorial"){
					aboutState = 0;
					loadBackground();
					aboutLabel.setVisible(true);
					backButton.setVisible(true);
					backTutorial.setVisible(false);
					nextButton.setVisible(false);
					quitTutorial.setVisible(false);
					tutorialButton.setVisible(true);
					repaint();
				}
                
			}
		} // end of ActionPerformed
	} // end of MenuListener
}
