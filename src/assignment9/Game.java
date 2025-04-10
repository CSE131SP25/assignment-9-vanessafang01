package assignment9;

import java.awt.Color;
import java.awt.event.KeyEvent;

import edu.princeton.cs.introcs.StdDraw;

public class Game {
	
	private Snake newSnake;
	private Food newFood;
	private SuperFood newSuperFood;
	
	public Game() {
		StdDraw.enableDoubleBuffering();
		newSnake = new Snake();
		newFood = new Food();
		newSuperFood = new SuperFood();
		//FIXME - construct new Snake and Food objects
	}
	
	public void play() {
		while (newSnake.isInbounds()==true && !newSnake.hasHitItself()) { //TODO: Update this condition to check if snake is in bounds
			int dir = getKeypress();
			//Testing only: you will eventually need to do more work here
			System.out.println("Keypress: " + dir);
			newSnake.changeDirection(dir);;
			newSnake.move();
			if(newSnake.eatFood(newFood)) {
				newFood = new Food();
			}
			if(newSnake.eatSuperFood(newSuperFood)) {
				newSuperFood = new SuperFood();
			}
			updateDrawing();
			
			/*
			 * 1. Pass direction to your snake
			 * 2. Tell the snake to move
			 * 3. If the food has been eaten, make a new one
			 * 4. Update the drawing
			 */
		}
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 80));
		StdDraw.text(0.5, 0.5, "GAME OVER!");
		StdDraw.show();
	}
	
	private int getKeypress() {
		if(StdDraw.isKeyPressed(KeyEvent.VK_W)) {
			return 1;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_S)) {
			return 2;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_A)) {
			return 3;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_D)) {
			return 4;
		} else {
			return -1;
		}
	}
	
	/**
	 * Clears the screen, draws the snake and food, pauses, and shows the content
	 */
	private void updateDrawing() {
		StdDraw.clear();
		StdDraw.setPenColor(229, 243, 253);
		StdDraw.filledSquare(1, 1, 1);
		newSnake.draw();
		newFood.draw();
		newSuperFood.draw();
		StdDraw.pause(50);
		StdDraw.show();
		
		/*
		 * 1. Clear screen
		 * 2. Draw snake and food
		 * 3. Pause (50 ms is good)
		 * 4. Show
		 */
	}
	
	public static void main(String[] args) {
		Game g = new Game();
		g.play();
	}
}
