package view;

import model.Archer;
import model.Character;
import model.Mage;
import model.Priest;
import model.Spearman;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Knight;
import controller.Client;

public class TitleScreenUnitPanel extends JPanel
{
	private Image background, unitPreviewImage;
	private Image archerPreviewImage, knightPreviewImage, magePreviewImage,
			priestPreviewImage, spearmanPreviewImage;
	private Image addButtonImage, removeButtonImage, backButtonImage,
			playButtonImage;
	private JButton addButton, removeButton, backButton, playButton;
	private JList unitList, teamList;
	private JScrollPane teamListScrollPane;
	private JLabel unitPreviewLabel, unitListLabel, teamListLabel;
	private ButtonListener buttonListener;
	private ListListener listListener;
	private DefaultListModel<String> unitListModel, teamListModel;
	private List<Character> team;
	private TitleScreen title;
	private JTextArea unitDescription;
	private int listWidth, listHeight;

	public TitleScreenUnitPanel()
	{
		this(800, 612);
	}

	public TitleScreenUnitPanel(int x, int y)
	{
		setPreferredSize(new Dimension(x, y));
		setLayout(null);
		// is background color neccesary at all?
		setBackground(Color.BLACK);

		buttonListener = new ButtonListener();
		listListener = new ListListener();

		title = TitleScreen.getTitleScreen();

		loadBackground();
		initializeModelLists();
		loadButton();
		addButton();
		loadLabel();
		addLabel();
		loadList();
		addList();
	}

	private void loadBackground()
	{
		try
		{
			// TODO: change to new background image
			background = ImageIO.read(new File(
					"res/titleScreen/unitSelectBackground.png"));
		}
		catch (IOException e)
		{
			System.out.println("Couldn't find title picture.");
		}
	}

	private void initializeModelLists()
	{
		unitListModel = new DefaultListModel<String>();
		teamListModel = new DefaultListModel<String>();
		team = new ArrayList<Character>();

		unitListModel.addElement("Archer");
		unitListModel.addElement("Knight");
		unitListModel.addElement("Mage");
		unitListModel.addElement("Priest");
		unitListModel.addElement("Spearman");
	}

	// This method is just to load in the images for the title screen's buttons
	private void loadButton()
	{
		try
		{
			// load up images for the buttons
			addButtonImage = ImageIO.read(new File(
					"res/titleScreen/addUnit.png"));
			removeButtonImage = ImageIO.read(new File(
					"res/titleScreen/removeUnit.png"));
			backButtonImage = ImageIO
					.read(new File("res/titleScreen/back.png"));
			playButtonImage = ImageIO
					.read(new File("res/titleScreen/play.png"));

			// initialize buttons
			addButton = new JButton(new ImageIcon(addButtonImage));
			removeButton = new JButton(new ImageIcon(removeButtonImage));
			backButton = new JButton(new ImageIcon(backButtonImage));
			playButton = new JButton(new ImageIcon(playButtonImage));

			// set button look and feel
			addButton.setOpaque(false);
			addButton.setContentAreaFilled(false);
			addButton.setBorderPainted(false);
			addButton.setRolloverIcon(new ImageIcon(ImageIO.read(new File(
					"res/titleScreen/addUnit2.png"))));

			removeButton.setOpaque(false);
			removeButton.setContentAreaFilled(false);
			removeButton.setBorderPainted(false);
			removeButton.setRolloverIcon(new ImageIcon(ImageIO.read(new File(
					"res/titleScreen/removeUnit2.png"))));

			backButton.setOpaque(false);
			backButton.setContentAreaFilled(false);
			backButton.setBorderPainted(false);
			backButton.setRolloverIcon(new ImageIcon(ImageIO.read(new File(
					"res/titleScreen/back2.png"))));

			playButton.setOpaque(false);
			playButton.setContentAreaFilled(false);
			playButton.setBorderPainted(false);
			playButton.setRolloverIcon(new ImageIcon(ImageIO.read(new File(
					"res/titleScreen/play2.png"))));

			addButton.setName("add");
			removeButton.setName("remove");
			backButton.setName("back");
			playButton.setName("play");

			// attach action listeners
			addButton.addActionListener(buttonListener);
			removeButton.addActionListener(buttonListener);
			backButton.addActionListener(buttonListener);
			playButton.addActionListener(buttonListener);
		}
		catch (IOException e)
		{
			System.out.println("Couldn't find button image.");
		}
	}

	public void addButton()
	{
		// add the buttons to panel
		this.add(addButton);
		this.add(removeButton);
		this.add(backButton);
		this.add(playButton);

		// set button locations
		// TODO: set the (correct) coordinates for the buttons
		addButton.setBounds(325, 70, addButtonImage.getWidth(null),
				addButtonImage.getHeight(null));
		removeButton.setBounds(302, 100, removeButtonImage.getWidth(null),
				removeButtonImage.getHeight(null));
		backButton.setBounds(100, 545, backButtonImage.getWidth(null),
				backButtonImage.getHeight(null));
		playButton.setBounds(625, 545, playButtonImage.getWidth(null),
				playButtonImage.getHeight(null));

	}

	private void loadLabel()
	{
		try
		{
			// TODO: change to default preview image
			unitPreviewImage = ImageIO.read(new File(
					"res/sprites/statPanel/noUnit.png"));
			unitPreviewImage = unitPreviewImage.getScaledInstance(128, 128, 0);
			// TODO: change the paths for these images
			
			archerPreviewImage = model.Archer.returnTexture().getScaledInstance(128, 128, 0);
			knightPreviewImage = model.Knight.returnTexture().getScaledInstance(128, 128, 0);
			magePreviewImage = model.Mage.returnTexture().getScaledInstance(128, 128, 0);
			priestPreviewImage = model.Priest.returnTexture().getScaledInstance(128, 128, 0);
			spearmanPreviewImage = model.Spearman.returnTexture().getScaledInstance(128, 128, 0);
		}
		catch (IOException ex)
		{
			System.out
					.println("Couldn't load unitPreviewImage inside UnitScreen");
		}

		// TODO: change the initial values of these labels?
		unitPreviewLabel = new JLabel(new ImageIcon(unitPreviewImage));
		unitDescription = new JTextArea("Unit Description");
		unitListLabel = new JLabel("Units");
		unitListLabel.setFont(new Font("Arial", Font.PLAIN, 30));
		unitListLabel.setForeground(Color.WHITE);
		teamListLabel = new JLabel("My Team");
		teamListLabel.setFont(new Font("Arial", Font.PLAIN, 30));
		teamListLabel.setForeground(Color.WHITE);
	}

	private void addLabel()
	{
		this.add(unitPreviewLabel);
		this.add(unitDescription);
		this.add(unitListLabel);
		this.add(teamListLabel);

		// TODO: set bounds for the 2 labels above
		unitPreviewLabel.setBounds(325, 190, 145, 135);
		unitDescription.setBounds(325, 405, 150, 120);
		unitListLabel.setBounds(50, 0, 100, 135);
		
		teamListLabel.setBounds(560, 0, 150, 135);
		
		// adding description text area settings
		unitDescription.setEditable(false);
		unitDescription.setWrapStyleWord(true);
		unitDescription.setLineWrap(true);
		unitDescription.setOpaque(false);
	}

	private void loadList()
	{
		unitList = new JList(unitListModel);
		unitList.setVisibleRowCount(unitListModel.size());
		unitList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		unitList.setBackground(Color.DARK_GRAY);
		unitList.setForeground(Color.WHITE);
		unitList.setSelectionBackground(Color.DARK_GRAY);
		unitList.setSelectionForeground(Color.CYAN);

		teamList = new JList(teamListModel);
		teamList.setVisibleRowCount(5);
		teamList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		teamList.setBackground(Color.DARK_GRAY);
		teamList.setForeground(Color.WHITE);
		teamList.setSelectionBackground(Color.DARK_GRAY);
		teamList.setSelectionForeground(Color.RED);
		
		teamListScrollPane = new JScrollPane(teamList);

		unitList.addListSelectionListener(listListener);
	}

	private void addList()
	{
		this.add(unitList);
		this.add(teamListScrollPane);

		// TODO: set bounds for the unitList and scrollPane
		
		// setting size of list
		listWidth = 200;
		listHeight = 350;
		
		// setting cell size
		unitList.setFixedCellHeight(listHeight/5);
		teamList.setFixedCellHeight(listHeight/5);
		
		// setting font
		unitList.setFont(new Font("Arial", Font.PLAIN, 20));
		teamList.setFont(new Font("Arial", Font.PLAIN, 20));
		
		// setting bounds
		teamListScrollPane.setBounds(560, 100, listWidth, listHeight);
		unitList.setBounds(40, 100, listWidth, listHeight);
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);
	}

	// public BufferedImage scaleImage()
	// { // getScaledInstance() instead of this shit!
	// BufferedImage scaledImage = new BufferedImage(previewImage.getWidth(),
	// previewImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
	// AffineTransform at = new AffineTransform();
	// at.scale(0.9, 0.9);
	// AffineTransformOp scale = new AffineTransformOp(at,
	// AffineTransformOp.TYPE_BILINEAR);
	// scaledImage = scale.filter(previewImage, scaledImage);
	// return scaledImage;
	// }

	private class ListListener implements ListSelectionListener
	{
		@Override
		public void valueChanged(ListSelectionEvent e)
		{
			// TODO: set unitPreviewLabel images
			if (unitList.getSelectedValue().equals("Archer"))
			{
				unitDescription.setText(Archer.getUnitDescription());
				unitPreviewLabel.setIcon(new ImageIcon(archerPreviewImage));
			}
			else if (unitList.getSelectedValue().equals("Knight"))
			{
				unitDescription.setText(Knight.getUnitDescription());
				unitPreviewLabel.setIcon(new ImageIcon(knightPreviewImage));
			}
			else if (unitList.getSelectedValue().equals("Mage"))
			{
				unitDescription.setText(Mage.getUnitDescription());
				unitPreviewLabel.setIcon(new ImageIcon(magePreviewImage));
			}
			else if (unitList.getSelectedValue().equals("Priest"))
			{
				unitDescription.setText(Priest.getUnitDescription());
				unitPreviewLabel.setIcon(new ImageIcon(priestPreviewImage));
			}
			else if (unitList.getSelectedValue().equals("Spearman"))
			{
				unitDescription.setText(Spearman.getUnitDescription());
				unitPreviewLabel.setIcon(new ImageIcon(spearmanPreviewImage));
			}
		} // end of actionPerformed
	} // end of ListListener

	private class ButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() instanceof JButton)
			{
				JButton button = (JButton) e.getSource();

				if (button.getName() == "add")
				{
					if (unitList.getSelectedValue() != null && teamListModel.size() < 5)
						teamListModel.addElement((String) unitList
								.getSelectedValue());
				}

				else if (button.getName() == "remove")
				{
					if (teamList.getSelectedValue() != null)
						teamListModel.removeElement((String) teamList
								.getSelectedValue());
				}

				else if (button.getName() == "back")
				{
					TitleScreen.TITLESTATE = 4;
					title.draw();
				}

				else if (button.getName() == "play")
				{
					//TODO: Call a method somewhere to create and start game based off of chosen team.
					Client.GAMESTATE = 1;
				}
			}
		} // end of actionPerformed
	} // end of ButtonListener
}
