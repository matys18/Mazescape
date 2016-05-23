package com.mataskaairaitis.mazescape.input;

import java.util.LinkedList;

import com.badlogic.gdx.math.Vector2;

import com.mataskaairaitis.mazescape.screens.*;

/**
 * GameControl class which allows the user to control a player object through
 * the arrow keys, and return to the Menu screen by pressing escape.
 * @author Jarl Silvén
 * @version 23/05/2016
 */
public class GameControl implements InputReceiver {

	GameScreen screen;
	int playerSpeed = 80;

	LinkedList<Direction> directionStack;
	private enum Direction {UP, DOWN, RIGHT, LEFT;}
	
	/**
	 * Initialize control of screen, and set up the Stack used to prioritize direction.
	 * @param screen
	 */
	public GameControl(GameScreen screen) {
		this.screen = screen;
		directionStack = new LinkedList<Direction>();
	}
	
	/**
	 * Add a direction to the directionstack, and update movement.
	 * @param dir Direction
	 */
	private void startTurn(Direction dir) {
		directionStack.addFirst(dir);
		updateMovement();
	}
	
	/**
	 * Remove a direction from the directionstack, and update movement.
	 * @param dir Direction
	 */
	private void stopTurn(Direction dir) {
		directionStack.remove(dir);
		updateMovement();
	}
	
	/**
	 * Resets the player speed, then sets new speed accoring to the stack.
	 */
	private void updateMovement() {
		screen.getPlayer().setVelocity(new Vector2());
		if(directionStack.size() == 0) {
			return;
		}
		Direction primary = directionStack.peek();
		if (directionStack.size() == 1) {
			addSpeed(primary, playerSpeed);
		}
		else {
			Direction secondary = directionStack.get(1);
			// this mess is to check if two last keys are opposites.
			if(( (primary == Direction.UP || primary == Direction.DOWN) &&
				(secondary == Direction.UP || secondary == Direction.DOWN)) || (
				(primary == Direction.RIGHT || primary == Direction.LEFT) &&
				(secondary == Direction.RIGHT || secondary == Direction.LEFT)
				)) {
				addSpeed(primary, playerSpeed);
			}
			else {
				// trigonometry maintains speed when moving diagonally
				double diagonal = Math.sin(Math.PI/4);
				addSpeed(primary, (int)(diagonal*playerSpeed));
				addSpeed(secondary, (int)(diagonal*playerSpeed));
			}
		}
	}
	
	/**
	 * Add player velocity in the given direction.
	 * @param dir Direction
	 * @param speed int
	 */
	private void addSpeed(Direction dir, int speed) {
		Vector2 old = screen.getPlayer().getVelocity();
		switch(dir) {
		case UP:
			screen.getPlayer().setVelocity(new Vector2(old.x, speed));
			break;
		case DOWN:
			screen.getPlayer().setVelocity(new Vector2(old.x, -speed));
			break;
		case RIGHT:
			screen.getPlayer().setVelocity(new Vector2(speed, old.y));
			break;
		case LEFT:
			screen.getPlayer().setVelocity(new Vector2(-speed, old.y));
			break;
		}
	}
	
	/**
	 * Initialize upward movement when pressing up arrow key
	 */
	public void keyPressedUp() {
		startTurn(Direction.UP);
	}
	
	/**
	 * Stop upward movement when releasing up arrow key
	 */
	public void keyReleasedUp() {
		stopTurn(Direction.UP);
	}
	
	/**
	 * Initialize downward movement when pressing down arrow key
	 */
	public void keyPressedDown() {
		startTurn(Direction.DOWN);
	}
	
	/**
	 * Stop downward movement when releasing down arrow key
	 */
	public void keyReleasedDown() {
		stopTurn(Direction.DOWN);
	}

	/**
	 * Initialize rightward movement when pressing right arrow key
	 */
	public void keyPressedRight() {
		startTurn(Direction.RIGHT);
	}
	
	/**
	 * Stop rightward movement when releasing rightarrow key
	 */
	public void keyReleasedRight() {
		stopTurn(Direction.RIGHT);
	}
	
	/**
	 * Initialize leftward movement when pressing left arrow key
	 */
	public void keyPressedLeft() {
		startTurn(Direction.LEFT);
	}
	
	/**
	 * Stop leftward movement when releasing left arrow key
	 */
	public void keyReleasedLeft() {
		stopTurn(Direction.LEFT);
	}
	
	/**
	 * Set player speed and direction to zero so that upon resuming,
	 * the player stands still. 
	 * Then switch to Menu screen, and highlight the Resume Game button.
	 */
	public void keyPressedEscape() {
		screen.getPlayer().setVelocity(new Vector2());
		directionStack = new LinkedList<Direction>();
		screen.getGame().setScreen(screen.getGame().getMenuScreen());
		MenuControl menu = (MenuControl)screen.getGame().getMenuScreen().getControl();
		menu.switchColor(menu.highlight, 1);
	}
	
}
