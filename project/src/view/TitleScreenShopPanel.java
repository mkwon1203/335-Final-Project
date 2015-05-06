package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import model.AttackUp;
import model.Character;
import model.DefenceUp;
import model.HealthPotion;
import model.Item;
import model.ManaPotion;
import model.Player;
import model.RevivePotion;
import controller.Client;

public class TitleScreenShopPanel extends JPanel
{
	private Image background;
	private Image buyButtonImage, backButtonImage;
	private JButton buyButton, backButton;
    
	private Image healthImage, manaImage, reviveImage, attackImage,
    defenceImage;
	private JLabel healthLabel, manaLabel, reviveLabel, attackLabel,
    defenceLabel;
	private JLabel healthName, manaName, reviveName, attackName, defenceName;
	private JTextArea healthDescription, manaDescription, reviveDescription,
    attackDescription, defenceDescription;
	private JLabel healthCost, manaCost, reviveCost, attackCost, defenceCost;
	private JLabel playerMoney, inventoryLabel;
	private JList<String> inventory;
	private JScrollPane inventoryScrollPane;
	private DefaultListModel<String> inventoryModel;
	private Border selectedBorder = BorderFactory.createLineBorder(Color.GREEN,
                                                                   5);
	private int selected = 0;
	private TitleScreen title;
    
	private ButtonListener buttonListener;
	
	private Player player;
    
	// creating the model items
	private HealthPotion healthPotion = new HealthPotion();
	private ManaPotion manaPotion = new ManaPotion();
	private RevivePotion revivePotion = new RevivePotion();
	private AttackUp attackUp = new AttackUp();
	private DefenceUp defenceUp = new DefenceUp();
    
	public TitleScreenShopPanel()
	{
		this(800, 612);
	}
    
	public TitleScreenShopPanel(int x, int y)
	{
		setPreferredSize(new Dimension(x, y));
		setLayout(null);
		// is background color neccesary at all?
		setBackground(Color.BLACK);
        
		buttonListener = new ButtonListener();
        
		title = TitleScreen.getTitleScreen();
		player = Player.getPlayer();
        
		loadBackground();
		initializeModelLists();
		loadButton();
		addButton();
		loadLabel();
		addLabel();
		loadList();
		addList();
		registerListeners();
	}
    
	private void loadBackground()
	{
		try
		{
			// TODO: change to new background image
			background = ImageIO.read(new File(
                                               "res/titleScreen/storeBackground.png"));
		}
		catch (IOException e)
		{
			System.out.println("Couldn't find shop panel background picture.");
		}
	}
    
	private void initializeModelLists()
	{
		inventoryModel = new DefaultListModel<String>();
	}
    
	private void loadButton()
	{
		try
		{
			// load up images for the buttons
			buyButtonImage = ImageIO.read(new File("res/titleScreen/buy.png"));
			backButtonImage = ImageIO
            .read(new File("res/titleScreen/back.png"));
            
			// initialize buttons
			buyButton = new JButton(new ImageIcon(buyButtonImage));
			backButton = new JButton(new ImageIcon(backButtonImage));
            
			// set button look and feel
			buyButton.setOpaque(false);
			buyButton.setContentAreaFilled(false);
			buyButton.setBorderPainted(false);
			buyButton.setRolloverIcon(new ImageIcon(ImageIO.read(new File(
                                                                          "res/titleScreen/buy2.png"))));
            
			backButton.setOpaque(false);
			backButton.setContentAreaFilled(false);
			backButton.setBorderPainted(false);
			backButton.setRolloverIcon(new ImageIcon(ImageIO.read(new File(
                                                                           "res/titleScreen/back2.png"))));
            
			buyButton.setName("buy");
			backButton.setName("back");
            
			// attach action listeners
			buyButton.addActionListener(buttonListener);
			backButton.addActionListener(buttonListener);
		}
		catch (IOException e)
		{
			System.out.println("Couldn't find button image.");
		}
	}
    
	private void addButton()
	{
		this.add(buyButton);
		this.add(backButton);
        
		buyButton.setBounds(650, 485, buyButtonImage.getWidth(null),
                            buyButtonImage.getHeight(null));
		backButton.setBounds(415, 485, backButtonImage.getWidth(null),
                             backButtonImage.getHeight(null));
	}
    
	private void loadLabel()
	{
		// load images first
		try
		{
			healthImage = ImageIO.read(new File("res/sprites/items/healthpotion.png"));
			manaImage = ImageIO.read(new File("res/sprites/items/manapotion.png"));
			reviveImage = ImageIO.read(new File("res/sprites/items/revivepotion.png"));
			attackImage = ImageIO.read(new File("res/sprites/items/attackup.png"));
			defenceImage = ImageIO.read(new File("res/sprites/items/defenceup.png"));
		}
		catch(IOException ex)
		{
			System.out.println("couldn't load shopPanel item label images");
		}
		
		healthLabel = new JLabel(new ImageIcon(healthImage));
		manaLabel = new JLabel(new ImageIcon(manaImage));
		reviveLabel = new JLabel(new ImageIcon(reviveImage));
		attackLabel = new JLabel(new ImageIcon(attackImage));
		defenceLabel = new JLabel(new ImageIcon(defenceImage));
		
		healthName = new JLabel(healthPotion.getName());
		manaName = new JLabel(manaPotion.getName());
		reviveName = new JLabel(revivePotion.getName());
		attackName = new JLabel(attackUp.getName());
		defenceName = new JLabel(defenceUp.getName());
		
		healthCost = new JLabel(healthPotion.getCost() + "");
		manaCost = new JLabel(manaPotion.getCost() + "");
		reviveCost = new JLabel(revivePotion.getCost() + "");
		attackCost = new JLabel(attackUp.getCost() + "");
		defenceCost = new JLabel(defenceUp.getCost() + "");
		
		// health descripton
		healthDescription = new JTextArea(healthPotion.getDescription());
		healthDescription.setEditable(false);
		healthDescription.setWrapStyleWord(true);
		healthDescription.setLineWrap(true);
		healthDescription.setOpaque(false);
        
		// mana description
		manaDescription = new JTextArea(manaPotion.getDescription());
		manaDescription.setEditable(false);
		manaDescription.setWrapStyleWord(true);
		manaDescription.setLineWrap(true);
		manaDescription.setOpaque(false);
        
		// revive description
		reviveDescription = new JTextArea(revivePotion.getDescription());
		reviveDescription.setEditable(false);
		reviveDescription.setWrapStyleWord(true);
		reviveDescription.setLineWrap(true);
		reviveDescription.setOpaque(false);
        
		// strength description
		attackDescription = new JTextArea(attackUp.getDescription());
		attackDescription.setEditable(false);
		attackDescription.setWrapStyleWord(true);
		attackDescription.setLineWrap(true);
		attackDescription.setOpaque(false);
        
		// defence description
		defenceDescription = new JTextArea(defenceUp.getDescription());
		defenceDescription.setEditable(false);
		defenceDescription.setWrapStyleWord(true);
		defenceDescription.setLineWrap(true);
		defenceDescription.setOpaque(false);
		
		// TODO: change it to player object's money
		playerMoney = new JLabel(player.getMoney() + "");
		
		inventoryLabel = new JLabel("Inventory List");
	}
    
	private void addLabel()
	{
		this.add(healthLabel);
		this.add(healthDescription);
		this.add(healthName);
		this.add(healthCost);
		this.add(manaLabel);
		this.add(manaDescription);
		this.add(manaName);
		this.add(manaCost);
		this.add(reviveLabel);
		this.add(reviveDescription);
		this.add(reviveName);
		this.add(reviveCost);
		this.add(attackLabel);
		this.add(attackDescription);
		this.add(attackName);
		this.add(attackCost);
		this.add(defenceLabel);
		this.add(defenceDescription);
		this.add(defenceName);
		this.add(defenceCost);
		this.add(playerMoney);
		this.add(inventoryLabel);
		
		healthLabel.setBounds(52, 52, 80, 80);
		healthName.setBounds(214, 38, 150, 30);
		healthDescription.setBounds(190, 65, 138, 60);
		healthCost.setBounds(72, 133, 35, 20);
        
		manaLabel.setBounds(425, 52, 80, 80);
		manaName.setBounds(588, 38, 150, 30);
		manaDescription.setBounds(560, 65, 138, 60);
		manaCost.setBounds(442, 133, 35, 20);
        
		reviveLabel.setBounds(52, 175, 80, 80);
		reviveName.setBounds(214, 158, 150, 30);
		reviveDescription.setBounds(190, 185, 138, 60);
		reviveCost.setBounds(72, 253, 35, 20);
        
		attackLabel.setBounds(425, 175, 80, 80);
		attackName.setBounds(588, 158, 150, 30);
		attackDescription.setBounds(560, 185, 138, 60);
		attackCost.setBounds(442, 253, 35, 20);
        
		defenceLabel.setBounds(425, 296, 80, 80);
		defenceName.setBounds(588, 278, 150, 30);
		defenceDescription.setBounds(560, 305, 138, 60);
		defenceCost.setBounds(442, 375, 35, 20);
		
		playerMoney.setBounds(35, 5, 78, 30);
		playerMoney.setForeground(Color.WHITE);
		
		inventoryLabel.setBounds(150, 300, 100, 30);
		inventoryLabel.setForeground(Color.WHITE);
	}
    
	private void loadList()
	{
		inventory = new JList<String>(inventoryModel);
		inventory.setBackground(Color.DARK_GRAY);
		inventory.setForeground(Color.WHITE);
		inventoryScrollPane = new JScrollPane(inventory);
	}
    
	private void addList()
	{
		this.add(inventoryScrollPane);
		
		inventoryScrollPane.setBounds(82, 330, 213, 235);
	}
    
	private void registerListeners()
	{
		healthLabel.addMouseListener(new MouseAdapter()
                                     {
			public void mouseClicked(MouseEvent e)
			{
				selected = 1;
                
				// set the correct borders
				healthLabel.setBorder(selectedBorder);
				manaLabel.setBorder(null);
				reviveLabel.setBorder(null);
				attackLabel.setBorder(null);
				defenceLabel.setBorder(null);
			}
		});
        
		manaLabel.addMouseListener(new MouseAdapter()
                                   {
			public void mouseClicked(MouseEvent e)
			{
				selected = 2;
                
				// set the correct borders
				healthLabel.setBorder(null);
				manaLabel.setBorder(selectedBorder);
				reviveLabel.setBorder(null);
				attackLabel.setBorder(null);
				defenceLabel.setBorder(null);
			}
		});
        
		reviveLabel.addMouseListener(new MouseAdapter()
                                     {
			public void mouseClicked(MouseEvent e)
			{
				selected = 3;
                
				// set the correct borders
				healthLabel.setBorder(null);
				manaLabel.setBorder(null);
				reviveLabel.setBorder(selectedBorder);
				attackLabel.setBorder(null);
				defenceLabel.setBorder(null);
			}
		});
        
		attackLabel.addMouseListener(new MouseAdapter()
                                     {
			public void mouseClicked(MouseEvent e)
			{
				selected = 4;
                
				// set the correct borders
				healthLabel.setBorder(null);
				manaLabel.setBorder(null);
				reviveLabel.setBorder(null);
				attackLabel.setBorder(selectedBorder);
				defenceLabel.setBorder(null);
			}
		});
        
		defenceLabel.addMouseListener(new MouseAdapter()
                                      {
			public void mouseClicked(MouseEvent e)
			{
				selected = 5;
                
				// set the correct borders
				healthLabel.setBorder(null);
				manaLabel.setBorder(null);
				reviveLabel.setBorder(null);
				attackLabel.setBorder(null);
				defenceLabel.setBorder(selectedBorder);
			}
		});
        
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);
	}
    
	private class ButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() instanceof JButton)
			{
				JButton button = (JButton) e.getSource();
                
				if (button.getName() == "buy")
				{
					// TODO: fill this out
					// need to check for player money and also update that accordingly
					if (selected == 1)
					{
						boolean buyItem = player.setMoney(-healthPotion.getCost());
						if (buyItem)
							inventoryModel.addElement(healthPotion.getName());
					}
					else if (selected == 2)
					{
						boolean buyItem = player.setMoney(-manaPotion.getCost());
						if (buyItem)
							inventoryModel.addElement(manaPotion.getName());
					}
					else if (selected == 3)
					{
						boolean buyItem = player.setMoney(-revivePotion.getCost());
						if (buyItem)
							inventoryModel.addElement(revivePotion.getName());
					}
					else if (selected == 4)
					{
						boolean buyItem = player.setMoney(-attackUp.getCost());
						if (buyItem)
							inventoryModel.addElement(attackUp.getName());
					}
					else if (selected == 5)
					{
						boolean buyItem = player.setMoney(-defenceUp.getCost());
						if (buyItem)
							inventoryModel.addElement(defenceUp.getName());
					}
					
					playerMoney.setText(player.getMoney() + "");
				}
				else if (button.getName() == "back")
				{
					// have to populate player inventory
					List<String> inventoryList = new ArrayList<String>();
					for (int i = 0; i < inventoryModel.size(); i ++)
					{
						inventoryList.add(inventoryModel.getElementAt(i));
					}
					player.addToInventory(inventoryList);
					TitleScreen.TITLESTATE = 0;
					title.draw();
				}
			}
		} // end of actionPerformed
	} // end of ButtonListener
}
