/**
*
* Goal.java - Goal class for AirPong
*
* @author Brian Etzel
* @version 1.0
*/

import java.awt.Graphics;
import java.awt.Color;

public class Goal
{
	private final int GOAL_HEIGHT = 125;
	private final int GOAL_WIDTH = 30;
	
	private int x;
	private int y = 187;
	private int yVelocity = 0;
	
	/**
	* Constructor for goal class
	* 
	* @param x - the x position the goal is to be placed at
	*/
	public Goal(int _x)
	{
		this.x = _x;
	}
	
	/**
	* Returns the integer value of the goal width
	*/
	public int getGoalWidth()
	{
		return this.GOAL_WIDTH;
	}
	
	/**
	* Returns the integer value of the goal height
	*/
	public int getGoalHeight()
	{
		return this.GOAL_HEIGHT;
	}
	
	/**
	* Returns the starting x value of the goal
	*/
	public int getX()
	{
		return this.x;
	}
	
	/**
	* Returns the y position of the goal
	*/
	public int getY()
	{
		return this.y;
	}
	
	/**
	* Paints the goal object on the screen
	*
	* @param g - Graphics object
	*/
	public void paint(Graphics g)
	{
		g.setColor(Color.RED);
		g.fillRect(x, y, GOAL_WIDTH, GOAL_HEIGHT);
	}
	
}