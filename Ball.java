/**
*
* Ball.java - Ball class for AirPong
*
* @author Brian Etzel
* @version 1.0
*/
import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;

public class Ball
{
	private final int MAX_RANGE = 500;
	private final int VELOCITY = 10;
	
	private Random rand = new Random();
	
	private int x = rand.nextInt(250) + 50;
	private int y = rand.nextInt(250) + 50;
	
	private int xVelocity = VELOCITY;
	private int yVelocity = VELOCITY;
	
	private int size = 20;
	
	/**
	* Updates the location of the ball
	*/
	public void update()
	{
		x = x + xVelocity;
		y = y + yVelocity;
		
		if (x < 0) {
			xVelocity *= -1;
		}
		else if (x + size > MAX_RANGE) {
			xVelocity *= -1;
		}
		
		if (y < 30){
			yVelocity = rand.nextInt(5) + 1;
		}
		else if (y + size > MAX_RANGE - 30){
			yVelocity = (rand.nextInt(5) + 1) * -1;
		}
	}
	
	/**
	* Fills in the color of the ball
	*/
	public void paint(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.fillOval(x, y, size, size);
	}
	
	/**
	* Checks collision with player paddle
	*
	* @param paddle - The paddle against which to check collision
	*/
	public void checkCollision(Paddle paddle){
		
		if (this.x + size > paddle.getX() && this.x < paddle.getX() + paddle.getPaddleWidth()){
			if (this.y > paddle.getY() && this.y < paddle.getY() + paddle.getPaddleHeight()){
				reverseVelocity();
			}
		}
	}
	
	/**
	* Checks to see if a goal has been scored
	*
	* @param goal - Goal class instance to be checked
	* @param leftGoal - boolean value signifying if the goal is on the left of the screen
	*/
	public boolean checkGoal(Goal goal, boolean leftGoal)
	{
		boolean goalScored = false;
		
		if (leftGoal) {
			if (this.x + size > goal.getX() && this.x < goal.getX() + 1){
				if (this.y > goal.getY() && this.y < goal.getY() + goal.getGoalHeight()){
					goalScored = true;
					y = rand.nextInt(GamePanel.WINDOW_WIDTH / 2);
					x = GamePanel.WINDOW_WIDTH / 2;
					reverseVelocity();
				}
			}
		}
		else {
			if (this.x > goal.getX() && this.x < goal.getX() + goal.getGoalWidth()){
				if (this.y > goal.getY() && this.y < goal.getY() + goal.getGoalHeight()){
					goalScored = true;
					y = rand.nextInt(GamePanel.WINDOW_WIDTH / 2);
					x = GamePanel.WINDOW_WIDTH / 2;
				}
			}
		}
		
		return goalScored;
	}
	
	/**
	* Reverses the direction of the ball
	*/
	private void reverseVelocity()
	{
		xVelocity *= -1;
	}
	
	
	/**
	* Returns the location of X coordinate
	*
	* @return integer location of x
	*/
	public int getX()
	{
		return this.x;
	}
	
	/**
	* Returns the location of Y coordinate
	*
	* @return integer location of y
	*/
	public int getY()
	{
		return this.y;
	}
}