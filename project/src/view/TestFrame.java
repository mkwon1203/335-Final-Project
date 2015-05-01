package view;

import javax.swing.*;
import model.*;

/**
 * solely 
 * @author Min's PC
 *
 */
public class TestFrame extends JFrame
{
	Map map;
	// default value for blcksize
	private int blockSize = 32;
	
	public TestFrame(Map inputMap)
	{
		map = inputMap;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Gaem");
		setSize(blockSize*map.getLevelRow(), blockSize*map.getLevelCol());
		
		JPanel panel = new JPanel();
		panel.setSize(blockSize*map.getLevelRow(), blockSize*map.getLevelCol());
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		Block[][] array = map.getLevel();
		
		for (int i = 0; i < array.length; i ++)
		{
			JPanel panelY = new JPanel();
			panelY.setLayout(new BoxLayout(panelY, BoxLayout.X_AXIS));
			for (int j = 0; j < array[0].length; j ++)
			{
				Block b = array[i][j];
				panelY.add(new JLabel(new ImageIcon(b.getTexture())));
			}
			panel.add(panelY);
		}
		
		add(panel);
		setVisible(true);
		pack();
	}
	
}
