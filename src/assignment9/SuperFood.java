package assignment9;

import java.awt.Color;

import edu.princeton.cs.introcs.StdDraw;

public class SuperFood {
	private double superfoodSize;
	private double x;
	private double y;
	
	/**
	 * Creates a new Food at a random location
	 */
	public SuperFood() {
		this.x = Math.random();
		this.y = Math.random();
		this.superfoodSize = 0.01;
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public void setX(double x) {
		this.x = x;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getRadius() {
		return 0.02;
	}
	/**
	 * Draws the Food
	 */
	public void draw() {
		StdDraw.setPenColor(Color.PINK);
		StdDraw.filledCircle(x,  y, superfoodSize);
	}
	
}
