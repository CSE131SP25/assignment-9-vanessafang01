package assignment9;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import edu.princeton.cs.introcs.StdDraw;

public class BodySegment {

	private double x, y, size;
	private Color snakeColor;
	
	public BodySegment(double x, double y, double size, Color color) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.snakeColor = color;	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setX(double x) {
		this.x=x;
	}
	
	public void setY(double y) {
		this.y=y;
	}
	
	public double getRadius() {
		return size;
	}
	
	/**
	 * Draws the segment
	 */
	public void draw() {
		if (snakeColor==null) {
			StdDraw.setPenColor(130, 214, 130);
		}
		else {
		StdDraw.setPenColor(snakeColor);
		}
		StdDraw.filledSquare(x, y, size);
	}
	public void increaseX(double x) {
		this.x += x;
	}
	public void increaseY(double y) {
		this.y += y;
	}

	public void setColor(Color color) {
		this.snakeColor = color;
		// TODO Auto-generated method stub
		
	}
	
}
