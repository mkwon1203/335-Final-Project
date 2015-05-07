package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Block;
import controller.Client;

public class TitleScreenLevelPanel extends JPanel
{
	private Image background, mapPreviewImage;
	private Image backButtonImage, nextButtonImage;
	private JButton backButton, nextButton;
	private JLabel mapPreviewLabel, levelsLabel, scenarioLabel;
	private JTextArea mapDescription;
	private JList<String> mapList, scenarioList;
	private DefaultListModel<String> mapListModel, scenarioListModel;
	private MenuListener listener;
	private ListListener listListener;
	private TitleScreen title;

	private Image preview;

	private String SELECTED;
	
	public static int selectedScenario;

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
					"res/titleScreen/levelSelectBackground.png"));
		}
		catch (IOException e)
		{
			System.out.println("Couldn't find title picture.");
		}
	}

	private void initializeModelList()
	{
		mapListModel = new DefaultListModel<String>();
		String fileName;

		File folder = new File("res/levels");
		File[] fileList = folder.listFiles();
		for (int i = 0; i < fileList.length; i++)
		{
			if (fileList[i].getName().endsWith("lvl"))
			{
				fileName = fileList[i].getName().toString();
				fileName = fileName.replace(".lvl", "");
				// System.out.println(fileName);
				mapListModel.addElement(fileName);
			}
		}
		
		scenarioListModel = new DefaultListModel<String>();
		scenarioListModel.addElement("Normal scenario");
		scenarioListModel.addElement("Melee only scenario");
		scenarioListModel.addElement("Ranged only scenario");
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
		backButton.setBounds(120, 545, backButtonImage.getWidth(null),
				backButtonImage.getHeight(null));
		nextButton.setBounds(590, 545, nextButtonImage.getWidth(null),
				nextButtonImage.getHeight(null));
	}

	private void loadLabel()
	{

		try
		{
			// TODO: change to default preview image
			mapPreviewImage = ImageIO.read(new File(
					"res/titleScreen/noLevelSelected.png"));
			// TODO: populate mapPreviewImageList with images
			// by using mapListModel to get name of paths
//			for (int i = 0; i < mapListModel.size(); i++)
//			{
//				String fileName = mapListModel.getElementAt(i);
//				mapPreviewImageList.add(ImageIO.read(new File("res/levels/"
//						+ fileName + ".png")));
//			}
		}
		catch (IOException ex)
		{
			System.out.println("Couldn't labels inside LevelPanel");
		}

		mapPreviewLabel = new JLabel(new ImageIcon(mapPreviewImage));

		Font labelFont = UIManager.getFont("Label.font");
		labelFont = labelFont.deriveFont(24f);
		levelsLabel = new JLabel("Levels");
		levelsLabel.setFont(labelFont);
		scenarioLabel = new JLabel("Scenarios");
		scenarioLabel.setFont(labelFont);

		// TODO: change defeault description here
		mapDescription = new JTextArea("DEFAULT DESCRIPTION LABEL");
		mapDescription.setLineWrap(true);
		mapDescription.setWrapStyleWord(true);
		mapDescription.setOpaque(false);
		mapDescription.setEditable(false);
		mapDescription.setForeground(Color.BLACK);

	}

	private void addLabel()
	{
		this.add(levelsLabel);
		this.add(scenarioLabel);
		this.add(mapPreviewLabel);
		this.add(mapDescription);

		// TODO; correct the bounds
		levelsLabel.setBounds(120, 27, 100, 20);
		scenarioLabel.setBounds(100,350, 140, 20);
		mapPreviewLabel.setBounds(490, 50, 256, 256);
		mapDescription.setBounds(518, 390, 210, 80);
	}

	private void loadList()
	{
		mapList = new JList<String>(mapListModel);
		// TODO: maybe change this later
		mapList.setVisibleRowCount(6);
		mapList.setFont(UIManager.getFont("List.font").deriveFont(16f));
		mapList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		mapList.setOpaque(false);
		mapList.setBackground(Color.DARK_GRAY);
		mapList.setForeground(Color.WHITE);
		mapList.setSelectionBackground(Color.DARK_GRAY);
		mapList.setSelectionForeground(Color.CYAN);
		
		scenarioList = new JList<String>(scenarioListModel);
		scenarioList.setVisibleRowCount(3);
		scenarioList.setFont(UIManager.getFont("List.font").deriveFont(16f));
		scenarioList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scenarioList.setOpaque(false);
		scenarioList.setBackground(Color.DARK_GRAY);
		scenarioList.setForeground(Color.WHITE);
		scenarioList.setSelectionBackground(Color.DARK_GRAY);
		scenarioList.setSelectionForeground(Color.CYAN);

		mapList.addListSelectionListener(listListener);
		scenarioList.addListSelectionListener(listListener);
	}

	private void addList()
	{
		this.add(mapList);
		this.add(scenarioList);
		mapList.setBounds(60, 50, 200, 200);
		scenarioList.setBounds(60, 380, 200, 100);
	}

	private synchronized void changeLevelDescription(String fileName)
	{
		try
		{
			mapDescription.setText(model.LoadGame
					.loadLevelDescription(fileName));
		}
		catch (Exception e)
		{
			mapDescription.setText("No description available.");
		}
	}

	// This method draws the preview for the map.
	private synchronized void drawLevelPreview(String fileName)
	{

		SELECTED = mapList.getSelectedValue();

		Block[][] blocks = model.Map.loadBlocks(fileName);

		BufferedImage testImage = new BufferedImage(blocks[0].length
				* Client.BLOCKSIZE, blocks.length * Client.BLOCKSIZE,
				BufferedImage.TYPE_INT_RGB);

		Graphics g = testImage.getGraphics();

		// These for loops paint the map to an image before being scaled.
		for (int row = 0; row < blocks.length; row++)
			for (int col = 0; col < blocks[0].length; col++)
				g.drawImage(blocks[row][col].getTexture(), col
						* Client.BLOCKSIZE, row * Client.BLOCKSIZE, null);

		// These last few lines will scale the map image, add the image to the
		// label,
		// then dispose of the Graphics object.
		// System.out.println("Width: " + testImage.getWidth() + " Height: " +
		// testImage.getHeight());
		// double ratio = (double)testImage.getWidth() /
		// (double)testImage.getHeight();
		// int newWidth = (int)(256/ratio);
		// System.out.println("Width: " + newWidth + " Height: " + 256 +
		// " Ratio: " + ratio);

		preview = testImage.getScaledInstance(256, 256,
				Image.SCALE_AREA_AVERAGING);

		ImageIcon i = new ImageIcon(preview);
		mapPreviewLabel.setIcon(i);
		g.dispose();

		mapPreviewLabel.setBounds(490, 50, i.getIconWidth(), i.getIconHeight());
	}

	@Override
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
				if (!mapList.getSelectedValue().equals(SELECTED))
				{
					String mapName = mapList.getSelectedValue();
					drawLevelPreview(mapName);
					changeLevelDescription(mapName);
				}
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
					if (mapList.isSelectionEmpty() || scenarioList.isSelectionEmpty())
					{
						// TODO: notify player that map must be selected to continue
					}
					else
					{
						if (scenarioList.getSelectedValue().equals("Normal scenario"))
							selectedScenario = 1;
						else if (scenarioList.getSelectedValue().equals("Melee only scenario"))
							selectedScenario = 2;
						else if (scenarioList.getSelectedValue().equals("Ranged only scenario"))
							selectedScenario = 3;
						
						MainView.getMainView().getGame().initializeMap(mapList.getSelectedValue());
						TitleScreen.TITLESTATE = 5;
						title.draw();
					}
				}
			}
		} // end of ActionPerformed
	} // end of MenuListener

}
