package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Archer;
import model.Knight;
import model.Mage;
import model.Priest;
import model.Spearman;
import controller.Client;

public class TitleScreenLevelPanel extends JPanel
{
	private Image background, mapPreviewImage;
	private List<Image> mapPreviewImageList;
	private Image backButtonImage, nextButtonImage;
	private JButton backButton, nextButton;
	private JLabel mapPreviewLabel, mapDescriptionLabel;
	private JList<String> mapList;
	private DefaultListModel<String> mapListModel;
	private MenuListener listener;
	private ListListener listListener;
	private TitleScreen title;

	public TitleScreenLevelPanel()
	{
		this(800, 612);
	}

	public TitleScreenLevelPanel(int x, int y)
	{
		setPreferredSize(new Dimension(x, y));
		setLayout(null);
		// is background color neccesary at all?
		setBackground(Color.RED);

		listener = new MenuListener();
		listListener = new ListListener();

		title = TitleScreen.getTitleScreen();

		loadBackground();
		initializeModelList();
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
			// TODO: change the background image
			background = ImageIO.read(new File(
					"res/titleScreen/titleBackground.png"));
		}
		catch (IOException e)
		{
			System.out.println("Couldn't find title picture.");
		}
	}

	private void initializeModelList()
	{
		mapListModel = new DefaultListModel<String>();

		File folder = new File("res/levels");
		File[] fileList = folder.listFiles();
		for (int i = 0; i < fileList.length; i++)
		{
			if (fileList[i].getName().endsWith("lvl"))
			{
				System.out.println(fileList[i].getName());
				mapListModel.addElement(fileList[i].getName());
			}
		}
	}

	private void loadButton()
	{
		try
		{
			backButtonImage = ImageIO
					.read(new File("res/titleScreen/back.png"));
			nextButtonImage = ImageIO
					.read(new File("res/titleScreen/next.png"));

			backButton = new JButton(new ImageIcon(backButtonImage));
			nextButton = new JButton(new ImageIcon(nextButtonImage));

			backButton.setOpaque(false);
			backButton.setContentAreaFilled(false);
			backButton.setBorderPainted(false);
			backButton.setRolloverIcon(new ImageIcon(ImageIO.read(new File(
					"res/titleScreen/back2.png"))));

			nextButton.setOpaque(false);
			nextButton.setContentAreaFilled(false);
			nextButton.setBorderPainted(false);
			nextButton.setRolloverIcon(new ImageIcon(ImageIO.read(new File(
					"res/titleScreen/next2.png"))));

			backButton.setName("back");
			nextButton.setName("next");

			backButton.addActionListener(listener);
			nextButton.addActionListener(listener);
		}
		catch (IOException e)
		{
			System.out.println("Couldn't load buttons.");
		}
	}

	private void addButton()
	{
		this.add(backButton);
		this.add(nextButton);

		// TODO: finalize the location of the buttons
		backButton.setBounds(50, 500, backButtonImage.getWidth(null),
				backButtonImage.getHeight(null));
		nextButton.setBounds(500, 500, nextButtonImage.getWidth(null),
				nextButtonImage.getHeight(null));
	}

	private void loadLabel()
	{
		mapPreviewImageList = new ArrayList<Image>();

		try
		{
			// TODO: change to default preview image
			mapPreviewImage = ImageIO.read(new File(
					"res/titleScreen/addUnit.png"));
			// TODO: populate mapPreviewImageList with images
			// by using mapListModel to get name of paths
			for (int i = 0; i < mapListModel.size(); i++)
			{
				String fileName = mapListModel.getElementAt(i);
				fileName.trim();
				// chop off the .lvl part
				fileName = fileName.substring(0, fileName.length() - 4);
				mapPreviewImageList.add(ImageIO.read(new File("res/levels/"
						+ fileName + ".png")));
			}
		}
		catch (IOException ex)
		{
			System.out.println("Couldn't labels inside LevelPanel");
		}

		mapPreviewLabel = new JLabel(new ImageIcon(mapPreviewImage));
		// TODO: change defeault description here
		mapDescriptionLabel = new JLabel("DEFAULT DESCRIPTION LABEL");
	}

	private void addLabel()
	{
		this.add(mapPreviewLabel);
		this.add(mapDescriptionLabel);

		// TODO; correct the bounds
		mapPreviewLabel.setBounds(500, 50, mapPreviewImage.getWidth(null),
				mapPreviewImage.getHeight(null));
		mapDescriptionLabel.setBounds(500, 350, 200, 200);
	}

	private void loadList()
	{
		mapList = new JList<String>(mapListModel);
		// TODO: maybe change this later
		mapList.setVisibleRowCount(6);
		mapList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		mapList.addListSelectionListener(listListener);
	}

	private void addList()
	{	
		this.add(mapList);
		mapList.setBounds(20, 20, 300, 300);
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);
	}

	private class ListListener implements ListSelectionListener
	{
		@Override
		public void valueChanged(ListSelectionEvent e)
		{
			if (!mapList.isSelectionEmpty())
			{
				String mapName = (String) mapList.getSelectedValue();
				int mapIndex = 0;
				// TODO: change the map preview and description labels
				// accordingly
				for (int i = 0; i < mapListModel.size(); i++)
				{
					if (mapListModel.getElementAt(i).equals(mapName))
					{
						mapIndex = i;
						break;
					}
				}
				mapPreviewLabel.setIcon(new ImageIcon(mapPreviewImageList
						.get(mapIndex)));
			}
		} // end of actionPerformed
	} // end of ListListener

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
					TitleScreen.TITLESTATE = 1;
					title.draw();
				}

				else if (button.getName() == "next")
				{
					// TODO: figure out what to do here
					TitleScreen.TITLESTATE = 5;
					title.draw();
				}
			}
		} // end of ActionPerformed
	} // end of MenuListener

}
