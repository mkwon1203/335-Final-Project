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
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.Knight;
import model.Spearman;
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
	private JLabel unitPreviewLabel, unitDescriptionLabel;
	private ButtonListener buttonListener;
	private ListListener listListener;
	private DefaultListModel<String> unitListModel, teamListModel;
	private List<Character> team;
	private TitleScreen title;

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
					"res/titleScreen/titleBackground.png"));
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
		addButton.setBounds(275, 70, addButtonImage.getWidth(null),
				addButtonImage.getHeight(null));
		removeButton.setBounds(275, 100, removeButtonImage.getWidth(null),
				removeButtonImage.getHeight(null));
		backButton.setBounds(50, 500, backButtonImage.getWidth(null),
				backButtonImage.getHeight(null));
		playButton.setBounds(500, 500, playButtonImage.getWidth(null),
				playButtonImage.getHeight(null));

	}

	private void loadLabel()
	{
		try
		{
			// TODO: change to default preview image
			unitPreviewImage = ImageIO.read(new File(
					"res/titleScreen/addUnit.png"));
			// TODO: change the paths for these images
			archerPreviewImage = ImageIO.read(new File(
					"res/unitScreen/Archer.png"));
			knightPreviewImage = ImageIO.read(new File(
					"res/unitScreen/Knight.png"));
			magePreviewImage = ImageIO.read(new File(
					"res/unitScreen/Mage.png"));
			priestPreviewImage = ImageIO.read(new File(
					"res/unitScreen/Priest.png"));
			spearmanPreviewImage = ImageIO.read(new File(
					"res/unitScreen/Spearman.png"));
		}
		catch (IOException ex)
		{
			System.out
					.println("Couldn't load unitPreviewImage inside UnitScreen");
		}

		// TODO: change the initial values of these labels?
		unitPreviewLabel = new JLabel(new ImageIcon(unitPreviewImage));
		unitDescriptionLabel = new JLabel("unit description goes here");
	}

	private void addLabel()
	{
		this.add(unitPreviewLabel);
		this.add(unitDescriptionLabel);

		// TODO: set bounds for the 2 labels above
		unitPreviewLabel.setBounds(275, 130, 300, 300);
		unitDescriptionLabel.setBounds(275, 400, 300, 100);
	}

	private void loadList()
	{
		unitList = new JList(unitListModel);
		unitList.setVisibleRowCount(unitListModel.size());
		unitList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		teamList = new JList(teamListModel);
		teamList.setVisibleRowCount(5);
		teamList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		teamListScrollPane = new JScrollPane(teamList);

		unitList.addListSelectionListener(listListener);
	}

	private void addList()
	{
		this.add(unitList);
		this.add(teamListScrollPane);

		// TODO: set bounds for the unitList and scrollPane
		unitList.setBounds(50, 70, 100, 135);
		teamListScrollPane.setBounds(600, 70, 100, 135);
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
				unitDescriptionLabel.setText(Archer.getUnitDescription());
				unitPreviewLabel.setIcon(new ImageIcon(archerPreviewImage));
			}
			else if (unitList.getSelectedValue().equals("Knight"))
			{
				unitDescriptionLabel.setText(Knight.getUnitDescription());
				unitPreviewLabel.setIcon(new ImageIcon(knightPreviewImage));
			}
			else if (unitList.getSelectedValue().equals("Mage"))
			{
				unitDescriptionLabel.setText(Mage.getUnitDescription());
				unitPreviewLabel.setIcon(new ImageIcon(magePreviewImage));
			}
			else if (unitList.getSelectedValue().equals("Priest"))
			{
				unitDescriptionLabel.setText(Priest.getUnitDescription());
				unitPreviewLabel.setIcon(new ImageIcon(priestPreviewImage));
			}
			else if (unitList.getSelectedValue().equals("Spearman"))
			{
				unitDescriptionLabel.setText(Spearman.getUnitDescription());
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
					if (unitList.getSelectedValue() != null)
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
					Client.GAMESTATE = 1;
				}
			}
		} // end of actionPerformed
	} // end of ButtonListener
}
