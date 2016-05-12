package com.mataskaairaitis.placeholder;

/**
 * GameControl class which at the moment only allows the player
 * to escape Game Mode by pressing escape, returning to Menu.
 * @author jarl
 */
public class GameControl implements InputReceiver {

	GameScreen screen;
	
	public GameControl(GameScreen screen) {
		this.screen = screen;
	}
	
	public void keyPressedEscape() {
		screen.game.setScreen(screen.game.menuScreen);
	}
	
}
