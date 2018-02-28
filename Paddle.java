/**
*
* Paddle.java - Creates a Paddle for AirPong
*
* @author Brian Etzel
* @version 1.0
*/
import java.awt.Graphics;
import java.awt.Color;

public class Paddle
{
	//private final int X = 675;
	private final int PADDLE_HEIGHT = 50;
	private final int PADDLE_WIDTH = 10;
	
	private int x;
	private int y = 250;
	private int yVelocity = 0;
	
	
	/**
	* Constructor for Paddle Class
	*/
	public Paddle(int paddlePos)
	{
		this.x = paddlePos;
	}
	
	/**
	* Updates the location of the paddle on the screen
	*/
	public void update()
	{
		y = y + yVelocity;
	}
	
	/**
	* Paints the paddle
	*/
	public void paint(Graphics g)
	{
		g.setColor(Color.BLUE);
		g.fillRect(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
	}
	
	/**
	* Updates the movement velocity of a paddle
	* 
	* @param newVelocity - The velocity to be updated
	*/
	public void updateVelocity(int newVelocity)
	{
		yVelocity = newVelocity;
	}
	
	/**
	* Returns the Y location of Paddle
	*/
	public int getY()
	{
		return this.y;
	}
	
	/**
	* Returns the X location of Paddle
	*/
	public int getX()
	{
		return this.x;
	}
	
	/**
	* Returns the Paddle Height
	*/
	public int getPaddleHeight()
	{
		return this.PADDLE_HEIGHT;
	}
	
	/**
	* Returns the Paddle width
	*/
	public int getPaddleWidth()
	{
		return this.PADDLE_WIDTH;
	}
	
}