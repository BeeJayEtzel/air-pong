/*
* Instructions.java - Displays rules of play for Air Pong
*
* @author Brian Etzel
* @version 1.0
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Stack;
import java.awt.Font;
import java.io.*;

public class Instructions {
	
	private JFrame mainFrame;
	private JPanel textPanel;
	private JPanel buttonPanel;
	private JLabel headerLabel;
	private JLabel answerLabel;
	private JTextArea answerBox;
	
	private final int WINDOW_WIDTH = 507;
	private final int WINDOW_HEIGHT = 537;
	private final int MAX_NAME_LENGTH = 10;

	/**
	* Constructor for Instructions class
	*/
	public Instructions()
	{
		prepareGUI();
	}
	
	/**
	* Sets the stage for the Rules of Play GUI
	*/
	public void prepareGUI()
	{
		mainFrame = new JFrame("Rules of Play - Air Pong - Project 3");
		mainFrame.setLayout(new GridLayout(2,1));
		mainFrame.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
		mainFrame.setBackground(Color.BLACK);

		textPanel = new JPanel();
		textPanel.setBackground(Color.BLACK);
		buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.BLACK);
		
		textPanel.setLayout(new GridBagLayout());
	  
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
	        System.exit(0);
			}        
		});    
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5,1,5,1);
		
		Font font = new Font("monospace", Font.BOLD, 50);
		
		
		headerLabel = new JLabel("Air Pong",JLabel.CENTER );
		headerLabel.setFont(font);
		headerLabel.setForeground(Color.RED);
		gbc.gridx = 0;
		gbc.gridy = 0;
		textPanel.add(headerLabel, gbc);
		
		answerLabel = new JLabel("<html><body><br>Player 1: Position on Left side of screen"
								+ "<br>Controls: W key: UP" + "<br>S key: Down" 
								+ "<br><br>Player 2: Position on Right side of screen"
								+ "<br>Controls: Up Arrow key: UP" + "<br>Down Arrow key: Down" );
		answerLabel.setForeground(Color.RED);
		gbc.gridx = 1;
		gbc.gridy = 1;
		textPanel.add(answerLabel, gbc);
		
		JButton exit = new JButton("Return to Start");
		gbc.gridx = 3;
		gbc.gridy = 5;
		gbc.gridwidth = 1;
		buttonPanel.add(exit, gbc);

		exit.setActionCommand("exit");
	
		exit.addActionListener(new ButtonClickListener());
		
		
		mainFrame.add(textPanel);
		mainFrame.add(buttonPanel);
		mainFrame.setVisible(true);
		
	}
	
	/**
	* Button Click Listener class for Instructions.java
	*/
    private class ButtonClickListener implements ActionListener{
		
	   
	   public ButtonClickListener()
	   {
		
	   }
	   
	   /**
	   * Invokes requisite reaction upon a button click
	   */ 
		public void actionPerformed(ActionEvent e){
			String command = e.getActionCommand();  
			if( command.equals( "exit" ))  {
				mainFrame.setVisible(false);
			}			
		}
	}
} 