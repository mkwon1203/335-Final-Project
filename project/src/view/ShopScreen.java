package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
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
import model.DefenceUp;
import model.HealthPotion;
import model.Item;
import model.ManaPotion;
import model.RevivePotion;
import controller.Client;

public class ShopScreen extends JFrame{
	private JPanel panel;
	private JLabel playerCoins, healthPotion, manaPotion, revivePotion, strengthBuff, defenceBuff;
	private JLabel health, mana, revive, strength, defence;
	private JTextArea healthDescription, manaDescription, reviveDescription, strengthDescription, defenceDescription;
	private Border border = BorderFactory.createLineBorder(Color.BLUE, 5);
	private Border selectedBorder = BorderFactory.createLineBorder(Color.GREEN, 5);
	private JList inventory;
	private JScrollPane inventoryList;
	private JButton buyButton, backButton;
	private DefaultListModel<String> inventoryModel = new DefaultListModel<String>();
	private int selected = 0;
	
	// creating the items
	private HealthPotion healthPot = new HealthPotion();
	private ManaPotion manaPot = new ManaPotion();
	private RevivePotion revivePot = new RevivePotion();
	private AttackUp strBuff = new AttackUp();
	private DefenceUp defBuff = new DefenceUp();
	
	
	
	public ShopScreen(){
		if (Client.GAMESTATE == 3){
			makeGUI();
			registerListeners();
			this.pack();
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			this.setSize(800,612);
			this.setVisible(true);
		}
	}

	private void registerListeners() {
		// button listeners
		ActionListener listen = new ButtonListener();
		buyButton.addActionListener(listen);
//		healthPotion.addMouseListener(mouseListener);
		
		healthPotion.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		        System.out.println("Clicked!");
		        selected = 1;
		        System.out.println(selected);
		        
		        // set the correct borders
				healthPotion.setBorder(selectedBorder);
				manaPotion.setBorder(border);
				revivePotion.setBorder(border);
				strengthBuff.setBorder(border);
				defenceBuff.setBorder(border);
		    }
		});
		
		manaPotion.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		        System.out.println("Clicked!");
		        selected = 2;
		        System.out.println(selected);
		        
		        // set the correct borders
				manaPotion.setBorder(selectedBorder);
				healthPotion.setBorder(border);
				revivePotion.setBorder(border);
				strengthBuff.setBorder(border);
				defenceBuff.setBorder(border);
		    }
		});
		
		revivePotion.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		        System.out.println("Clicked!");
		        selected = 3;
		        System.out.println(selected);
		        
		        // set the correct borders
				revivePotion.setBorder(selectedBorder);
				healthPotion.setBorder(border);
				manaPotion.setBorder(border);
				strengthBuff.setBorder(border);
				defenceBuff.setBorder(border);
		    }
		});
		
		strengthBuff.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		        System.out.println("Clicked!");
		        selected = 4;
		        System.out.println(selected);
		        
		        // set the correct borders
				strengthBuff.setBorder(selectedBorder);
				healthPotion.setBorder(border);
				manaPotion.setBorder(border);
				revivePotion.setBorder(border);
				defenceBuff.setBorder(border);
		    }
		});
		
		defenceBuff.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		        System.out.println("Clicked!");
		        selected = 5;
		        System.out.println(selected);
		        
		        // set the correct borders
				defenceBuff.setBorder(selectedBorder);
				healthPotion.setBorder(border);
				manaPotion.setBorder(border);
				revivePotion.setBorder(border);
				strengthBuff.setBorder(border);
		    }
		});
			
		
	}
	
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == buyButton){
				if (selected == 1){
					inventoryModel.addElement(healthPot.getName());
				}	
				if (selected == 2)
					inventoryModel.addElement(manaPot.getName());
				if (selected == 3)
					inventoryModel.addElement(revivePot.getName());
				if (selected == 4)
					inventoryModel.addElement(strBuff.getName());
				if (selected == 5)
					inventoryModel.addElement(defBuff.getName());
			}
		}	
	}
	


	private void makeGUI() {
		if (Client.GAMESTATE == 3){
			createPanel();
			setLayoutAndAddComponentsToFrame();
		}
		
	}

	private void createPanel() {
	
		
		
		panel = new JPanel();
		panel.setSize(800, 612);
		panel.setLayout(null);
		inventory = new JList(inventoryModel);
		buyButton = new JButton("Buy Item");
		backButton = new JButton("Back");
		inventoryList = new JScrollPane(inventory);
		
		createLabels();
		createDescriptions();
		addToPanel();
		setLocationInPanel();
	}

	private void setLocationInPanel() {
		// 
		playerCoins.setBounds(0, 0, 100, 30);
		playerCoins.setBorder(border)
		;
		
		// setting the health potion components
		healthPotion.setBounds(50, 50, 100, 100);
		healthPotion.setBorder(border);
		healthDescription.setBounds(175, 90, 150, 60);
		healthDescription.setBorder(border);
		health.setBounds(175, 50, 150, 30);
		health.setBorder(border);
		
		
		
		//setting the mana potion components
		manaPotion.setBounds(375, 50, 100, 100);
		manaPotion.setBorder(border);
		manaDescription.setBounds(500, 90, 150, 60);
		manaDescription.setBorder(border);
		mana.setBounds(500, 50, 150, 30);
		mana.setBorder(border);
		
		// setting revive potion components
		revivePotion.setBounds(50, 175, 100, 100);
		revivePotion.setBorder(border);
		reviveDescription.setBounds(175, 215, 150, 60);
		reviveDescription.setBorder(border);
		revive.setBounds(175, 175, 150, 30);
		revive.setBorder(border);
		
		// setting strength buff components
		strengthBuff.setBounds(375, 175, 100, 100);
		strengthBuff.setBorder(border);
		strengthDescription.setBounds(500, 215, 150, 60);
		strengthDescription.setBorder(border);
		strength.setBounds(500, 175, 150, 30);
		strength.setBorder(border);
		
		// setting defence buff components
		defenceBuff.setBounds(375, 300, 100, 100);
		defenceBuff.setBorder(border);
		defenceDescription.setBounds(500, 340, 150, 60);
		defenceDescription.setBorder(border);
		defence.setBounds(500, 300, 150, 30);
		defence.setBorder(border);
		
		// setting buttons
		buyButton.setBounds(375, 425, 300, 50);
		backButton.setBounds(375, 500, 300, 50);
		
		// setting inventory list
		inventoryList.setBounds(50, 300, 275, 275);
		
		
	}

	private void addToPanel() {
		// adding the items
		panel.add(playerCoins);
		panel.add(healthPotion);
		panel.add(manaPotion);
		panel.add(revivePotion);
		panel.add(strengthBuff);
		panel.add(defenceBuff);
		
		// adding the description labels
		panel.add(healthDescription);
		panel.add(manaDescription);
		panel.add(reviveDescription);
		panel.add(strengthDescription);
		panel.add(defenceDescription);
		
		// adding the names of Items
		panel.add(health);
		panel.add(mana);
		panel.add(revive);
		panel.add(strength);
		panel.add(defence);
		
		// adding buttons
		panel.add(buyButton);
		panel.add(backButton);
		
		// adding the list
		panel.add(inventoryList);
	}

	private void createDescriptions() {
		// health descripton
		healthDescription = new JTextArea(healthPot.getDescription());
		healthDescription.setEditable(false);
		healthDescription.setWrapStyleWord(true);
		healthDescription.setLineWrap(true);
		healthDescription.setOpaque(false);
		
		// mana description
		manaDescription = new JTextArea(manaPot.getDescription());
		manaDescription.setEditable(false);
		manaDescription.setWrapStyleWord(true);
		manaDescription.setLineWrap(true);
		manaDescription.setOpaque(false);
		
		// revive description
		reviveDescription = new JTextArea(revivePot.getDescription());
		reviveDescription.setEditable(false);
		reviveDescription.setWrapStyleWord(true);
		reviveDescription.setLineWrap(true);
		reviveDescription.setOpaque(false);
		
		// strength description
		strengthDescription = new JTextArea(strBuff.getDescription());
		strengthDescription.setEditable(false);
		strengthDescription.setWrapStyleWord(true);
		strengthDescription.setLineWrap(true);
		strengthDescription.setOpaque(false);
		
		// defence description
		defenceDescription = new JTextArea(defBuff.getDescription());
		defenceDescription.setEditable(false);
		defenceDescription.setWrapStyleWord(true);
		defenceDescription.setLineWrap(true);
		defenceDescription.setOpaque(false);
		
	}

	private void createLabels() {
		playerCoins = new JLabel("Player Coins");
		healthPotion = new JLabel("Health Potion");
		manaPotion = new JLabel("Mana Potion");
		revivePotion = new JLabel("Revive Potion");
		strengthBuff = new JLabel("Strength Buff");
		defenceBuff = new JLabel ("Defence Buff");
		
		health = new JLabel(healthPot.getName());
		mana = new JLabel(manaPot.getName());
		revive = new JLabel(revivePot.getName());
		strength = new JLabel(strBuff.getName());
		defence = new JLabel(defBuff.getName());
	}

	private void setLayoutAndAddComponentsToFrame() {
		this.setLayout(null);
		//panel.setBackground(Color.BLUE);
		this.add(panel);
		this.setResizable(false);
	}
	
	
	public static void main(String[] arg){
		Client.GAMESTATE = 3;
		new ShopScreen();
	}
}
