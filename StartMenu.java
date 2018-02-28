/*
* StartMenu.java- StartMenu for AirPong
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

public class StartMenu {
	
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
	* Constructor for StartMenu class
	*/
	public StartMenu() throws IOException, ClassNotFoundException, InterruptedException
	{
		prepareGUI();
	}
	
	/**
	* Sets the stage for the Start Menu GUI
	*/
	public void prepareGUI() throws IOException, ClassNotFoundException, InterruptedException
	{
		mainFrame = new JFrame("Air Pong - Project 3");
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
		textPanel.add(headerLabel);
		
		JButton newGame = new JButton("New Game");
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 1;
		buttonPanel.add(newGame, gbc);
		
		JButton rules = new JButton("Rules of Play");
		gbc.gridx = 2;
		gbc.gridy = 5;
		gbc.gridwidth = 1;
		buttonPanel.add(rules, gbc);
		
		
		JButton exit = new JButton("Exit");
		gbc.gridx = 3;
		gbc.gridy = 5;
		gbc.gridwidth = 1;
		buttonPanel.add(exit, gbc);


		newGame.setActionCommand("newGame");
		rules.setActionCommand("rules");
		exit.setActionCommand("exit");
	
		newGame.addActionListener(new ButtonClickListener());
		rules.addActionListener(new ButtonClickListener());
		exit.addActionListener(new ButtonClickListener());
		
		
		mainFrame.add(textPanel);
		mainFrame.add(buttonPanel);
		mainFrame.setVisible(true);
		
	}
	
	/**
	* Prompts user for player names
	*
	* @param playerNum - The string containg information about the player for which you are getting a name
	* @return String containing players name entered by user
	*/
	private String getPlayerName(String playerNum) 
	{
        String playerName = "";
		
		Object[] options1 = { "OK",
                "Cancel" };

        JPanel panel = new JPanel();
        panel.add(new JLabel("Enter name for " + playerNum));
        JTextField textField = new JTextField(10);
        panel.add(textField);

        int accepted = JOptionPane.showOptionDialog(null, panel, "Enter name for " + playerNum,
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options1, null);
        if (accepted == JOptionPane.YES_OPTION){
			playerName = textField.getText();
        }
		
		return playerName;
    }
	
	/**
	* Trims the size of the name down to the maximum length if necessary
	* 
	* @param longName - The name to be trimmed down
	* @return new name string of the maximum length
	*/
	private String trimName(String longName)
	{
		String trimmedName = "";
		for (int i = 0; i < MAX_NAME_LENGTH; i++){
			trimmedName += longName.charAt(i);
		}
		
		return trimmedName;
	}
	
    /**
	* Button Click Listener for Start Menu
	*/
    private class ButtonClickListener implements ActionListener{
		
	   /**
	   * Creats an instance of the ButtonClickListener
	   */
	   public ButtonClickListener()
	   {
		
	   }
	   
	    /**
		* Performs requitse action upon button click
		*/
		public void actionPerformed(ActionEvent e){
			String command = e.getActionCommand();  
			if( command.equals( "newGame" ))  {
				String playerOneName = getPlayerName("player one");
				String playerTwoName = getPlayerName("player two");
				if (playerOneName.length() > MAX_NAME_LENGTH){
					playerOneName = trimName(playerOneName);
				}
				if (playerTwoName.length() > MAX_NAME_LENGTH){
					playerTwoName = trimName(playerTwoName);
				}
				try {
				AirPong pong = new AirPong(playerOneName, playerTwoName);
				mainFrame.setVisible(false);
				} catch (IOException exception){
					
				} catch (ClassNotFoundException classException) {
					
				} catch (InterruptedException interException) {
					
				}
			}
			else if (command.equals("rules")){
				Instructions rules = new Instructions();
			}
			else if (command.equals("exit")){
				System.exit(0);
			}
		}
	}
} 