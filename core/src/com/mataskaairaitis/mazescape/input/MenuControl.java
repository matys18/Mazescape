package com.mataskaairaitis.mazescape.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

import com.mataskaairaitis.mazescape.screens.*;

/**
 * Menu control class navigating the menu.
 * @author Jarl Silvén
 * @version 23/05/2016
 */
public class MenuControl implements InputReceiver {
	
	MenuScreen screen;
	int highlight = 0;
	
	/**
	 * Initialize control of screen
	 * @param screen Menu to be controlled
	 */
	public MenuControl(MenuScreen screen) {
		this.screen = screen;
	}
	
	/**
	 * Move highlight up when key is pressed up
	 */
	public void keyPressedUp() {
		switchColor(highlight, moveUp(1));
	}
	
	/**
	 * Move highlight down when key is pressed down
	 */
	public void keyPressedDown() {
		switchColor(highlight, moveDown(1));
	}
	
	/**
	 * When moving highlight, skip greyed out objects.
	 * Also move to bottom when at top. 
	 * @param steps how many steps to move
	 * @return the number of the next highlight
	 */
	private int moveUp(int steps) {
		int length = screen.getLabels().length;
		int next = (highlight + length - steps) % length;
		if(screen.getLabels()[next].getColor().equals(Color.DARK_GRAY))
			return moveUp(steps + 1);
		return next;
	}
	
	/**
	 * When moving highlight, skip greyed out objects.
	 * Also move to top when at bottom. 
	 * @param steps how many steps to move
	 * @return the number of the next highlight
	 */
	private int moveDown(int steps) {
		int next = (highlight + steps) % screen.getLabels().length;
		if(screen.getLabels()[next].getColor().equals(Color.DARK_GRAY))
			return moveDown(steps + 1);
		return next;
	}
	
	/**
	 * Trigger object action upon pressing space
	 */
	public void keyPressedSpace() {
		activateKey();	
	}
	
	/**
	 * Trigger object action upon pressing enter
	 */
	public void keyPressedEnter() {
		activateKey();
	}
	
	/**
	 * Switch the color of previous highlight and
	 * next highlight.
	 * @param prev previous highlight
	 * @param next next highlight
	 */
	public void switchColor(int prev, int next) {
		screen.getLabels()[prev].setColor(Color.WHITE);
		highlight = next;
		screen.getLabels()[next].setColor(Color.ORANGE);
	}

	/**
	 * Actions depending on which object is highlighted
	 * case 0: (New Game) starts new game
	 * case 1: (Resume) resumes game from previous state
	 * case 2: (Exit Game) exits the game
	 */
	private void activateKey() {
		switch (highlight) {
		case 0:
			screen.getGame().setGameScreen(new GameScreen(screen.getGame()));
			screen.getGame().setScreen(screen.getGame().getGameScreen());
			break;
		case 1:
			screen.getGame().setScreen(screen.getGame().getGameScreen());
			break;
		case 2:
			Gdx.app.exit();
		}
	}
	
}
