/**
*
* Pong.java - Sets the stage for a game of AirPong
*
* @author Brian Etzel
* @version 1.0
*/
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.io.*;

public class AirPong extends JFrame
{
	private final int WINDOW_WIDTH = 507;
	private final int WINDOW_HEIGHT = 537;
	
	/**
	* Creates an instance of a game of Air Pong
	* 
	* @param - playerOneName - The name of Player One
	* @param - playerTwoName - The name of Player Two
	*
	*/
	public AirPong(String playerOneName, String playerTwoName) throws IOException, ClassNotFoundException, InterruptedException
	{
		setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		add(new GamePanel(playerOneName, playerTwoName));
		setVisible(true);
	}
	
}