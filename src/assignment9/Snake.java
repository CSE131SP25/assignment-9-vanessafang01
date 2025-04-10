package assignment9;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import edu.princeton.cs.introcs.StdDraw;

public class Snake {

	private static final double SEGMENT_SIZE = 0.02;
	private static final double MOVEMENT_SIZE = SEGMENT_SIZE * 1.5;
	private LinkedList<BodySegment> segments;
	private double deltaX;
	private double deltaY;
	private Color snakeColor;
	
	public Snake() {
		segments = new LinkedList<BodySegment>();
		segments.add(new BodySegment(0.5, 0.5, SEGMENT_SIZE, this.snakeColor));
		deltaX = 0;
		deltaY = 0;
		snakeColor = Color.green;
	}
	
	public void changeDirection(int direction) {
		if(direction == 1) { //up
			deltaY = MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 2) { //down
			deltaY = -MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 3) { //left
			deltaY = 0;
			deltaX = -MOVEMENT_SIZE;
		} else if (direction == 4) { //right
			deltaY = 0;
			deltaX = MOVEMENT_SIZE;
		}
	}
	
	/**
	 * Moves the snake by updating the position of each of the segments
	 * based on the current direction of travel
	 */
	public void move() {
		BodySegment head = segments.getFirst();
		for (int i = segments.size()-1; i>0; i--) {
			BodySegment segment = segments.get(i);
			BodySegment nextSegment = segments.get(i-1);
			segment.setX(nextSegment.getX());
			segment.setY(nextSegment.getY());
		}
		head.increaseX(deltaX);
		head.increaseY(deltaY);
		for (int i = 1; i < segments.size(); i++) {
	        segments.get(i).setColor(this.snakeColor);  // Update the color of each body segment
	    }
	}
	
	/**
	 * Draws the snake by drawing each segment
	 */
	public void draw() {
		StdDraw.setPenColor(snakeColor);
		for (int i = 0; i < segments.size(); i++) {
			segments.get(i).draw();
		}
		 BodySegment head = segments.getFirst();
		    double headX = head.getX();
		    double headY = head.getY();
		    double r = head.getRadius();

		    // Eyes
		    double eyeOffsetX = r / 2.5;
		    double eyeOffsetY = r / 2.5;
		    double eyeWhiteRadius = r / 4;
		    double pupilRadius = r / 8;

		    // Left eye (white)
		    StdDraw.setPenColor(255, 255, 255);
		    StdDraw.filledCircle(headX - eyeOffsetX, headY + eyeOffsetY, eyeWhiteRadius);

		    // Left eye (pupil)
		    StdDraw.setPenColor(0, 0, 0);
		    StdDraw.filledCircle(headX - eyeOffsetX, headY + eyeOffsetY, pupilRadius);

		    // Right eye (white)
		    StdDraw.setPenColor(255, 255, 255);
		    StdDraw.filledCircle(headX + eyeOffsetX, headY + eyeOffsetY, eyeWhiteRadius);

		    // Right eye (pupil)
		    StdDraw.setPenColor(0, 0, 0);
		    StdDraw.filledCircle(headX + eyeOffsetX, headY + eyeOffsetY, pupilRadius);
		    
		 // Eyebrows
		    StdDraw.setPenColor(0, 0, 0);
		    double browLength = r / 2.5;
		    double browOffsetY = r / 1.2; // how high above the eye
		    double browTilt = r / 4;      // how much tilt to give

		    // Left eyebrow (angled down toward center)
		    StdDraw.line(headX - eyeOffsetX - browTilt, headY + browOffsetY,
		                 headX - eyeOffsetX + browTilt, headY + browOffsetY + r / 10);

		    // Right eyebrow (angled down toward center)
		    StdDraw.line(headX + eyeOffsetX + browTilt, headY + browOffsetY,
		                 headX + eyeOffsetX - browTilt, headY + browOffsetY + r / 10);
		    
		    // Optional tongue
		    StdDraw.setPenColor(255, 0, 0); // red tongue
		    StdDraw.filledRectangle(headX, headY - r, r / 6, r / 6);
		    
		    
	}
	
	/**
	 * The snake attempts to eat the given food, growing if it does so successfully
	 * @param f the food to be eaten
	 * @return true if the snake successfully ate the food
	 */
	public boolean eatFood(Food f) {
		BodySegment head = segments.getFirst();
		double distance = Math.sqrt((Math.pow((head.getX()-f.getX()), 2)) + Math.pow(head.getY()-f.getY(), 2));
		if (distance <= head.getRadius() + f.getRadius()) {
			segments.add(new BodySegment(segments.getLast().getX() - deltaX, segments.getLast().getY() - deltaY, SEGMENT_SIZE, this.snakeColor));
			return true;

		}
		return false;
		
	}
	public boolean hasHitItself() {
	    BodySegment head = segments.getFirst();
	    double headX = head.getX();
	    double headY = head.getY();
	    double radius = head.getRadius();

	    // Start from index 1 to skip checking head against itself
	    for (int i = 1; i < segments.size(); i++) {
	        BodySegment segment = segments.get(i);
	        double dx = headX - segment.getX();
	        double dy = headY - segment.getY();
	        double distance = Math.sqrt(dx * dx + dy * dy);

	        if (distance < radius * 1.5) {  // if it overlaps
	            return true;
	        }
	    }

	    return false;
	}
	
	public boolean eatSuperFood(SuperFood f) {
		BodySegment head = segments.getFirst();
		double distance = Math.sqrt((Math.pow((head.getX()-f.getX()), 2)) + Math.pow(head.getY()-f.getY(), 2));
		if (distance <= head.getRadius() + f.getRadius()) {
			segments.add(new BodySegment(segments.getLast().getX() - deltaX, segments.getLast().getY() - deltaY, SEGMENT_SIZE, this.snakeColor));
			segments.add(new BodySegment(segments.getLast().getX() - deltaX, segments.getLast().getY() - deltaY, SEGMENT_SIZE, this.snakeColor));
			this.snakeColor = ColorUtils.solidColor();
			return true;
		}
		
		return false;
		
	}
	/**
	 * Returns true if the head of the snake is in bounds
	 * @return whether or not the head is in the bounds of the window
	 */
	public boolean isInbounds() {
		BodySegment head = segments.getFirst();
		if (head.getX() < 0 || head.getY() > 1) {
			return false;
		}
		if (head.getY() < 0 || head.getX() > 1) {
			return false;
		}
		return true;
	}
}
