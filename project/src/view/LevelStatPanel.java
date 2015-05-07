package view;

import javax.imageio.ImageIO;
import javax.swing.*;

import model.CharacterInterface;
import model.Game;
import model.HealthPotion;
import model.Item;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LevelStatPanel extends JPanel
{

	private Game game;

	private Image noUnitImage;

	private JLabel unitImage, unitHealthLabel, unitManaLabel, unitNameLabel;
	private JLabel unitStrengthLabel, unitDefenceLabel;
	private JButton attackButton, endTurnButton, useItemButton, waitButton;
	private JButton actualAttackButton, skillButton, backButton;
	private JList<String> inventoryList;
	private DefaultListModel<String> itemList;
	private JScrollPane scroll;

	private Image attackImage, endTurnImage, useItemImage, waitImage;
	private Image skillImage, backImage; // actualAttackButton uses attackImage
	private Image statsScreen;

	private boolean attackSelected;
	
	private CharacterInterface previousCharacter;

	public LevelStatPanel(Game game)
	{
		define(game, 800, 100);
	}

	public LevelStatPanel(Game game, int width, int height)
	{
		define(game, width, height);
	}

	// This method is meant to set up the panel, and load images.
	private void define(Game game, int width, int height)
	{

		this.game = game;

		setPreferredSize(new Dimension(width, height));
		setLayout(null);
		setBackground(Color.BLACK);

		loadStatButtons();
		loadNoUnitImage();

		attackSelected = false;
		previousCharacter = null;

		unitHealthLabel = new JLabel();
		unitHealthLabel.setForeground(Color.WHITE);
		unitHealthLabel.setText("HP: ");

		unitManaLabel = new JLabel();
		unitManaLabel.setForeground(Color.WHITE);
		unitManaLabel.setText("Mana: ");

		unitStrengthLabel = new JLabel();
		unitStrengthLabel.setForeground(Color.WHITE);
		unitStrengthLabel.setText("STR: ");

		unitDefenceLabel = new JLabel();
		unitDefenceLabel.setForeground(Color.WHITE);
		unitDefenceLabel.setText("DEF: ");

		unitNameLabel = new JLabel();
		unitNameLabel.setForeground(Color.WHITE);
		unitNameLabel.setText("Selected: ");

		unitImage = new JLabel(new ImageIcon(noUnitImage));

		itemList = new DefaultListModel<String>();
		inventoryList = new JList<String>(itemList);
		inventoryList.setBackground(Color.DARK_GRAY);
		inventoryList.setForeground(Color.WHITE);
		inventoryList.setVisibleRowCount(3);
		inventoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		updateInventory();

		scroll = new JScrollPane(inventoryList);

		add(scroll);
		add(unitImage);
		add(unitNameLabel);
		add(unitHealthLabel);
		add(unitManaLabel);
		add(unitStrengthLabel);
		add(unitDefenceLabel);
	}

	// This method loads the Inventory JList that's at the bottom right of the
	// screen
	private void updateInventory()
	{
		itemList.removeAllElements();

		for (Item i : game.getPlayer().getInventory().getItems())
		{
			itemList.addElement(i.getName());
		}

	}

	// This method is used to load the button images and create the buttons
	// for the stats panel.
	private void loadStatButtons()
	{

		try
		{

			attackImage = ImageIO.read(new File(
					"res/sprites/statpanel/attack.png"));
			endTurnImage = ImageIO.read(new File(
					"res/sprites/statpanel/endTurn.png"));
			useItemImage = ImageIO.read(new File(
					"res/sprites/statpanel/use.png"));
			waitImage = ImageIO
					.read(new File("res/sprites/statpanel/wait.png"));
			skillImage = ImageIO.read(new File(
					"res/sprites/statpanel/skill.png"));
			backImage = ImageIO
					.read(new File("res/sprites/statpanel/back.png"));

			attackButton = new JButton(new ImageIcon(attackImage));
			endTurnButton = new JButton(new ImageIcon(endTurnImage));
			useItemButton = new JButton(new ImageIcon(useItemImage));
			actualAttackButton = new JButton(new ImageIcon(attackImage));
			waitButton = new JButton(new ImageIcon(waitImage));
			skillButton = new JButton(new ImageIcon(skillImage));
			backButton = new JButton(new ImageIcon(backImage));

			attackButton.setOpaque(false);
			attackButton.setContentAreaFilled(false);
			attackButton.setBorderPainted(false);
			attackButton.setRolloverIcon(new ImageIcon(ImageIO.read(new File(
					"res/sprites/statpanel/attack2.png"))));

			endTurnButton.setOpaque(false);
			endTurnButton.setContentAreaFilled(false);
			endTurnButton.setBorderPainted(false);
			endTurnButton.setRolloverIcon(new ImageIcon(ImageIO.read(new File(
					"res/sprites/statpanel/endTurn2.png"))));

			useItemButton.setOpaque(false);
			useItemButton.setContentAreaFilled(false);
			useItemButton.setBorderPainted(false);
			useItemButton.setRolloverIcon(new ImageIcon(ImageIO.read(new File(
					"res/sprites/statpanel/use2.png"))));

			actualAttackButton.setOpaque(false);
			actualAttackButton.setContentAreaFilled(false);
			actualAttackButton.setBorderPainted(false);
			actualAttackButton.setRolloverIcon(new ImageIcon(ImageIO
					.read(new File("res/sprites/statpanel/attack2.png"))));

			waitButton.setOpaque(false);
			waitButton.setContentAreaFilled(false);
			waitButton.setBorderPainted(false);
			waitButton.setRolloverIcon(new ImageIcon(ImageIO.read(new File(
					"res/sprites/statpanel/wait2.png"))));

			skillButton.setOpaque(false);
			skillButton.setContentAreaFilled(false);
			skillButton.setBorderPainted(false);
			skillButton.setRolloverIcon(new ImageIcon(ImageIO.read(new File(
					"res/sprites/statpanel/skill2.png"))));

			backButton.setOpaque(false);
			backButton.setContentAreaFilled(false);
			backButton.setBorderPainted(false);
			backButton.setRolloverIcon(new ImageIcon(ImageIO.read(new File(
					"res/sprites/statpanel/back2.png"))));

			attackButton.setName("attack");
			endTurnButton.setName("endTurn");
			useItemButton.setName("useItem");
			actualAttackButton.setName("actualAttack");
			waitButton.setName("wait");
			skillButton.setName("skill");
			backButton.setName("back");

			add(attackButton);
			add(endTurnButton);
			add(useItemButton);
			add(waitButton);
			add(actualAttackButton);
			add(skillButton);
			add(backButton);

			attackButton.addActionListener(new StatPanelListener());
			endTurnButton.addActionListener(new StatPanelListener());
			useItemButton.addActionListener(new StatPanelListener());
			waitButton.addActionListener(new StatPanelListener());
			actualAttackButton.addActionListener(new StatPanelListener());
			skillButton.addActionListener(new StatPanelListener());
			backButton.addActionListener(new StatPanelListener());

		}
		catch (IOException e)
		{
			System.out.println("Couldn't find a stat button image file.");
		}

	}

	// Loads and creates the image object for the image that is shown when no
	// unit
	// is selected.
	private void loadNoUnitImage()
	{
		try
		{
			noUnitImage = ImageIO.read(new File(
					"res/sprites/statpanel/noUnit.png"));
		}
		catch (IOException e)
		{
			System.out.println("Couldn't find noUnit image.");
		}
	}

	// This method draws the preview image for the selected unit.
	private void drawUnitImage()
	{

		try
		{
			if (game.isCharacterSelected())
			{
				Image unitPic = game.getSelectedCharacter().getTexture();
				unitImage.setIcon(new ImageIcon(unitPic.getScaledInstance(64,
						64, 0)));
			}
			else
			{
				unitImage.setIcon(new ImageIcon(noUnitImage));
			}
		}
		catch (Exception e)
		{
			System.out.println("Couldn't get unit image.");
		}

	}

	// This is the main method that draws everything on the panel.
	public void draw()
	{

		drawBackground();
		drawUnitImage();

		if (game != null)
		{

			if (game.isCharacterSelected())
			{
				setSelectedText();
				unitHealthLabel.setText("HP: "
						+ game.getSelectedCharacter().getHealth());
				unitManaLabel.setText("Mana: "
						+ game.getSelectedCharacter().getMana());
				unitStrengthLabel.setText("STR: "
						+ game.getSelectedCharacter().getStrength());
				unitDefenceLabel.setText("DEF: "
						+ game.getSelectedCharacter().getDefence());
			}
			else
			{
				unitHealthLabel.setToolTipText("HP: ");
				unitManaLabel.setText("Mana: ");
				unitStrengthLabel.setText("STR: ");
				unitDefenceLabel.setText("DEF: ");
				unitNameLabel.setText("Selected: ");
			}
		}

		scroll.setBounds(640, 12, 150, 75);
		unitImage.setBounds(40, 3, 64, 64);
		unitNameLabel.setBounds(25, 70, 200, 25);
		unitHealthLabel.setBounds(160, 30, 75, 25);
		unitManaLabel.setBounds(160, 70, 75, 25);
		unitStrengthLabel.setBounds(235, 30, 75, 25);
		unitDefenceLabel.setBounds(235, 70, 75, 25);

		if (attackSelected)
		{
			attackButton.setBounds(350, 15, 0, 0);
			endTurnButton.setBounds(335, 60, 0, 0);
			useItemButton.setBounds(560, 15, 0, 0);
			waitButton.setBounds(500, 60, 0, 0);
			actualAttackButton.setBounds(350, 15, attackImage.getWidth(null),
					attackImage.getHeight(null));
			backButton.setBounds(335, 60, backImage.getWidth(null),
					backImage.getHeight(null));
			skillButton.setBounds(500, 15, skillImage.getWidth(null),
					skillImage.getHeight(null));
		}
		else
		{
			actualAttackButton.setBounds(350, 15, 0, 0);
			backButton.setBounds(335, 60, 0, 0);
			skillButton.setBounds(500, 15, 0, 0);
			attackButton.setBounds(350, 15, attackImage.getWidth(null),
					attackImage.getHeight(null));
			endTurnButton.setBounds(335, 60, endTurnImage.getWidth(null),
					endTurnImage.getHeight(null));
			useItemButton.setBounds(560, 15, useItemImage.getWidth(null),
					useItemImage.getHeight(null));
			waitButton.setBounds(500, 60, waitImage.getWidth(null),
					waitImage.getHeight(null));
		}
	}

	// Sets the text for the currently selected unit
	private void setSelectedText()
	{
		unitNameLabel.setText("Selected: "
				+ game.getSelectedCharacter().getType());
		unitNameLabel.setBounds(25, 70, 200, 25);
	}

	// This method draws the images behind the buttons and labels, i.e. the
	// health bars
	// and possibly a background image if we decide to later.
	private void drawBackground()
	{

		// In case we want to add some kind of image background or border, we
		// can
		// use this graphics object to do so. Won't be used otherwise.
		statsScreen = createVolatileImage(getWidth(), getHeight());
		Graphics g = statsScreen.getGraphics();

		if (game != null)
		{
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, getWidth(), getHeight());

			g.setColor(Color.RED);
			g.fillRect(160, 20, 100, 7);
			g.setColor(Color.DARK_GRAY);
			g.fillRect(160, 60, 100, 7);

			if (game.isCharacterSelected())
			{
				g.setColor(Color.GREEN);
				g.fillRect(160, 20, (int) (game.getSelectedCharacter()
						.getPercentHealth() * 100), 7);
				g.setColor(Color.BLUE);

				g.fillRect(160, 60, (int) (game.getSelectedCharacter()
						.getPercentMana() * 100), 7);
			}
		}
		repaint();
		g.dispose();
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(statsScreen, 0, 0, null);
	}

	// This is an inner-class which is the listener for the stat panel.
	// Handles all the logic for when the user interacts with the panel.
	private class StatPanelListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			// TODO Auto-generated method stub

			if (e.getSource() instanceof JButton)
			{
				JButton button = (JButton) e.getSource();

				if (button.getName() == "attack")
				{
					attackSelected = true;
					draw();
				}
				else if (button.getName() == "endTurn")
				{
					if (game.isPlayersTurn() && !model.Animation.UNITMOVING)
						game.endTurn();
				}

				else if (button.getName() == "useItem")
				{
					if (!inventoryList.isSelectionEmpty()
							&& game.isCharacterSelected())
					{
						int i = inventoryList.getSelectedIndex();
						Item toUse = game.getPlayer().getInventory().getItems()
								.get(i);

						game.useItem(game.getSelectedCharacter(), toUse);
						updateInventory();
					}
				}
				else if (button.getName() == "wait")
				{
					if (game.isPlayersTurn() && game.isCharacterSelected())
					{
						if (game.getSelectedCharacter().getAvailable())
							game.wait(game.getSelectedCharacter());
					}
				}
				else if (button.getName() == "actualAttack")
				{
					if (game.isPlayersTurn() && game.isCharacterSelected())
					{
						List<CharacterInterface> attackableCharacters = game
								.attackableCharacterList(game
										.getSelectedCharacter());
						for (CharacterInterface c : attackableCharacters)
						{
							if (game.attack(game.getSelectedCharacter(), c))
							{
								System.out.println("attack successful!");
								// TODO: maybe uncomment these
								attackSelected = false;
								draw();
								break;
							}
						}
					}
				}
				else if (button.getName() == "skill")
				{
					if (game.isPlayersTurn() && game.isCharacterSelected())
					{
						List<CharacterInterface> magicUsableCharacters = game
								.magicUsableCharacterList(game
										.getSelectedCharacter());
						for (CharacterInterface c : magicUsableCharacters)
						{
							if (game.useMagic(game.getSelectedCharacter(), c))
							{
								System.out.println("magic successful");
								// TODO: maybe uncomment these
								attackSelected = false;
								draw();
								break;
							}
						}
					}
				}
				else if (button.getName() == "back")
				{
					attackSelected = false;
					draw();
				}

			}
		}// End of listener inner-class

	}// End of StatPanelListener inner class.

}// End of LevelStatPanel class
