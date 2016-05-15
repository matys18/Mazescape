package com.mataskaairaitis.placeholder;

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
	 * Stops if the directionstack is empty, otherwise it turns in the 
	 * direction of the first element (last pressed arrow key) in the stack.
	 */
	private void updateMovement() {
		Direction dir = directionStack.peek();
		if(dir == null) {
			screen.player.setVelocity(new Vector2());
			return;
		}
		switch(dir) {
		case UP:
			screen.player.setVelocity(new Vector2(0, playerSpeed));
			break;
		case DOWN:
			screen.player.setVelocity(new Vector2(0, -playerSpeed));
			break;
		case RIGHT:
			screen.player.setVelocity(new Vector2(playerSpeed, 0));
			break;
		case LEFT:
			screen.player.setVelocity(new Vector2(-playerSpeed, 0));
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
	}
	
}
