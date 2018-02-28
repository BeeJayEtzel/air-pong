/**
*
* GamePanel.java - GamePanel class for Project 03
*
* @author Brian Etzel
* @version 1.0
*/
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.Collections;
import java.util.LinkedList;
import java.io.*;

public class GamePanel extends JPanel implements ActionListener, KeyListener, Serializable
{
	
	public static final int WINDOW_WIDTH = 507;
	public static final int WINDOW_HEIGHT = 537;
	
	private final int PADDLE_VELOCITY = 7;
	private final int PADDLE_ONE = 75; //Start location of a paddle one
	private final int PADDLE_TWO = 395; //Start location of a paddle two
	private final int MAX_SCORE = 7;
	
	private final boolean IS_LEFT_GOAL = true;
	
	private Ball ball = new Ball();
	private Paddle paddle = new Paddle(PADDLE_ONE);
	private Paddle secondPaddle = new Paddle(PADDLE_TWO);
	private Goal goal = new Goal(0);
	private Goal secondGoal = new Goal(470);
	private JLabel playerOneScore;
	private JLabel playerTwoScore;
	
	private String playerOneName;
	private String playerTwoName;
	private int playerTwoGoal = 0;
	private int playerOneGoal = 0;
	
	private long startTime;
	private long endTime;
	private HighScore playerScore;
	private LinkedList<HighScore> scoreList;
	
	private Timer time;
	
	/**
	* Constructs Game Panel
	*/
	public GamePanel(String _playerOneName, String _playerTwoName) throws IOException, ClassNotFoundException, InterruptedException
	{
		time = new Timer(16, this);
		time.start();
		
		startTime = System.currentTimeMillis();
		
		this.playerOneName = _playerOneName;
		this.playerTwoName = _playerTwoName;
		playerOneScore = new JLabel(playerOneName + ": 0", JLabel.LEFT);
		playerTwoScore = new JLabel(playerTwoName + ": 0", JLabel.RIGHT);
		JLabel buffer = new JLabel("                                              ");

		playerOneScore.setForeground(Color.RED);
		playerTwoScore.setForeground(Color.RED);
		playerOneScore.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
		playerTwoScore.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
		
		Font fnt = new Font("arial", Font.BOLD, 25);
		playerOneScore.setFont(fnt);
		playerTwoScore.setFont(fnt);
		scoreList = new LinkedList<HighScore>();
		scoreList = load();
		
		this.playerOneName = _playerOneName;
		this.playerTwoName = _playerTwoName;
		
		
		this.add(playerOneScore);
		
		this.add(buffer);

		this.add(playerTwoScore);
		
		this.addKeyListener(this);
		this.setFocusable(true);
		this.setVisible(true);
		
	}
	
	/**
	* Updates the action after requisite time passed
	*/
	private void update()
	{
		int winningPlayer = gameOver();
		if (winningPlayer != 1 && winningPlayer != 2){
			paddle.update();
			secondPaddle.update();
			ball.update();
		
			ball.checkCollision(paddle);
			ball.checkCollision(secondPaddle);
		
			if (ball.checkGoal(goal, IS_LEFT_GOAL)){
				playerTwoGoal++;
				playerTwoScore.setText(playerTwoName + ": " + String.valueOf(playerTwoGoal));
			}
		
			if (ball.checkGoal(secondGoal, !IS_LEFT_GOAL)){
				playerOneGoal++;
				playerOneScore.setText(playerOneName + ": " + String.valueOf(playerOneGoal));
			}
		}
		else {
			time.stop();
			endGame(winningPlayer);
		}
		
		
		
	}
	
	/**
	* Paints the field of play
	*/
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
		
		g.setColor(Color.WHITE);
		g.drawLine(0, 30, WINDOW_WIDTH, 30);                                  //Top Line Divider
		g.drawLine(WINDOW_WIDTH / 2, 30, WINDOW_WIDTH/2, WINDOW_HEIGHT - 60); //Midfield line
		g.drawLine(0, WINDOW_HEIGHT - 60, WINDOW_WIDTH, WINDOW_HEIGHT - 60);  //Bottom Line Divider
		
		ball.paint(g);
		paddle.paint(g);
		goal.paint(g);
		secondGoal.paint(g);
		secondPaddle.paint(g);
		
	}
	
	/**
	* Determines if a game has been won
	*
	* @return integer value of 1 if player one has won and 2 if player two has won
	*/
	private int gameOver()
	{
		int gameOver = 0;
		
		if (playerOneGoal == MAX_SCORE){
			gameOver = 1;
			endTime = System.currentTimeMillis();
		}
		else if (playerTwoGoal == MAX_SCORE){
			gameOver = 2;
			endTime = System.currentTimeMillis();
		}
		
		return gameOver;
	}
	
	/**
	* Starts the process of ending a game
	*/
	private void endGame(int winningPlayerNum)
	{
		boolean rematch = false;
		
		if (winningPlayerNum == 1){
			playerScore = new HighScore(playerOneName,calculateScore());
			scoreList.add(playerScore);
			Collections.sort(scoreList);
			try {
				save(scoreList);
			} catch (Exception ex){
			
			}
			HighScoreWindow scoreWindow = new HighScoreWindow();
			rematch = scoreWindow.showHighScores(scoreList);
		}
		else if (winningPlayerNum == 2){
			playerScore = new HighScore(playerTwoName,calculateScore());
			scoreList.add(playerScore);
			Collections.sort(scoreList);
			try {
				save(scoreList);
			} catch (Exception ex){
			
			}
			HighScoreWindow scoreWindow = new HighScoreWindow();
			rematch = scoreWindow.showHighScores(scoreList);
		}
		
		if (rematch){
			rematch();
		}
		
	}
	
	private void rematch(){
		playerOneGoal = 0;
		playerTwoGoal = 0;
		playerOneScore.setText(playerOneName + ": " + String.valueOf(playerOneGoal));
		playerTwoScore.setText(playerTwoName + ": " + String.valueOf(playerTwoGoal));
		startTime = System.currentTimeMillis();
		time.start();
		
	}
	
	/**
	* Calculates the high score of a game of Air Pong
	*
	* @return an integer containing the winning players score
	*/
	private int calculateScore()
	{
		int score;
		
		int timeDifference = (int)endTime - (int)startTime;
		timeDifference = timeDifference / 100;
		
		score = timeDifference * 7;
		
		return score;
	}
	
	/**
	* Saves a LinkedList of HighScore as a Serializable file
	*
	* @param scores - LinkedList<HighScore>
	*/
	private void save(LinkedList<HighScore> scores) throws IOException, ClassNotFoundException
	{
		FileOutputStream fos = new FileOutputStream("highscore");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(scores);
		oos.flush();
		oos.close();
	}
	
	/**
	* Loads a Serializable instance of a LinkedList<HighScore>
	*
	* @return LinkedList<HighScore>
	*/
	@SuppressWarnings("unchecked")
	private LinkedList<HighScore> load() throws IOException, ClassNotFoundException, InterruptedException
	{
		LinkedList<HighScore> scores = new LinkedList<HighScore>();
			try {
				FileInputStream fis = new FileInputStream("highscore");
				ObjectInputStream ois = new ObjectInputStream(fis);
				scores = (LinkedList<HighScore>) ois.readObject();
				fis.close();
				ois.close();
			
			} catch (Exception e) {
				//return null object if not found
			}
		
		return scores;
	}
	
	/**
	* Invoked upon action being performed
	*/
	public void actionPerformed(ActionEvent e)
	{
		update();
		repaint();
	}
	
	/**
	* Invoked when a key has been pressed
	*/
	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_W){
			paddle.updateVelocity(-PADDLE_VELOCITY);
		}
		else if (e.getKeyCode() == KeyEvent.VK_S){
			paddle.updateVelocity(PADDLE_VELOCITY);
		}
		
		else if (e.getKeyCode() == KeyEvent.VK_UP){
			secondPaddle.updateVelocity(-PADDLE_VELOCITY);
		}
		else if (e.getKeyCode() == KeyEvent.VK_DOWN){
			secondPaddle.updateVelocity(PADDLE_VELOCITY);
		}
	}
	
	/**
	* Invoked when a key has been released
	*/
	public void keyReleased(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_W){
			paddle.updateVelocity(0);
		}
		else if (e.getKeyCode() == KeyEvent.VK_S){
			paddle.updateVelocity(0);
		}
		
		else if (e.getKeyCode() == KeyEvent.VK_UP){
			secondPaddle.updateVelocity(0);
		}
		else if (e.getKeyCode() == KeyEvent.VK_DOWN){
			secondPaddle.updateVelocity(0);
		}
	}
	
	/**
	* Invoked when a key has been typed.
	*/
	public void keyTyped(KeyEvent e)
	{
		
	}
	
}