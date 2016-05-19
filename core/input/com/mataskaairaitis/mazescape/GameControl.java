package com.mataskaairaitis.mazescape;

import java.util.LinkedList;

import com.badlogic.gdx.math.Vector2;

/**
 * GameControl class which allows the user to control a player object through
 * the arrow keys, and return to the Menu screen by pressing escape.
 * @author jarl
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
		screen.player.setVelocity(new Vector2());
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
		Vector2 old = screen.player.getVelocity();
		switch(dir) {
		case UP:
			screen.player.setVelocity(new Vector2(old.x, speed));
			break;
		case DOWN:
			screen.player.setVelocity(new Vector2(old.x, -speed));
			break;
		case RIGHT:
			screen.player.setVelocity(new Vector2(speed, old.y));
			break;
		case LEFT:
			screen.player.setVelocity(new Vector2(-speed, old.y));
			break;
		}
	}
	
	public void keyPressedUp() {
		startTurn(Direction.UP);
	}
	
	public void keyReleasedUp() {
		stopTurn(Direction.UP);
	}
	
	public void keyPressedDown() {
		startTurn(Direction.DOWN);
	}
	
	public void keyReleasedDown() {
		stopTurn(Direction.DOWN);
	}

	public void keyPressedRight() {
		startTurn(Direction.RIGHT);
	}
	
	public void keyReleasedRight() {
		stopTurn(Direction.RIGHT);
	}
	public void keyPressedLeft() {
		startTurn(Direction.LEFT);
	}
	
	public void keyReleasedLeft() {
		stopTurn(Direction.LEFT);
	}
	
	public void keyPressedEscape() {
		screen.game.setScreen(screen.game.menuScreen);
		MenuControl menu = (MenuControl)screen.game.menuScreen.control;
		menu.switchColor(menu.highlight, 1);
	}
	
}
