package com.mataskaairaitis.mazescape.input;

import com.mataskaairaitis.mazescape.screens.EndScreen;

/**
 * End Control class, the which disables the user's
 * control over the player object, and allows the
 * user to exit to menu by pressing escape, space 
 * or enter.
 * @author Jarl Silvén
 * @version 23/05/2016
 */
public class EndControl implements InputReceiver {

	EndScreen screen;
	
	/**
	 * Simple end screen constructor.
	 * @param screen EndScreen
	 */
	public EndControl(EndScreen screen) {
		this.screen = screen;
	}
	
	/**
	 * Pressing Enter exits the endscreen and returns to menu.
	 */
	public void keyPressedEnter() {
		exitToMenu();
	}
	
	/**
	 * Pressing Escape exits the endscreen and returns to menu.
	 */
	public void keyPressedEscape() {
		exitToMenu();
	}

	/**
	 * Pressing Space exits the endscreen and returns to menu.
	 */
	public void keyPressedSpace() {
		exitToMenu();
	}
	
	/**
	 * Terminates the current GameScreen, and returns
	 * to the Menu. 
	 */
	private void exitToMenu() {
		screen.getGame().getGameScreen().dispose();
		screen.getGame().setGameScreen(null);
		screen.getGame().setScreen(screen.getGame().getMenuScreen());
		MenuControl menu = (MenuControl)screen.getGame().getMenuScreen().getControl();
		menu.switchColor(menu.highlight, 0);
	}
	
}
