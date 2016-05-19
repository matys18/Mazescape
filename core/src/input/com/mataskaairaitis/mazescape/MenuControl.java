package com.mataskaairaitis.mazescape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

/**
 * Sample Menu control class, for testing purposes.
 * @author jarl
 */
public class MenuControl implements InputReceiver {
	
	MenuScreen screen;
	int highlight = 0;
	
	public MenuControl(MenuScreen screen) {
		this.screen = screen;
	}
	
	public void keyPressedUp() {
		switchColor(highlight, moveUp(1));
	}
	
	public void keyPressedDown() {
		switchColor(highlight, moveDown(1));
	}
	
	private int moveUp(int steps) {
		int length = screen.labels.length;
		int next = (highlight + length - steps) % length;
		if(screen.labels[next].getColor().equals(Color.LIGHT_GRAY))
			return moveUp(steps + 1);
		return next;
	}
	
	private int moveDown(int steps) {
		int next = (highlight + steps) % screen.labels.length;
		if(screen.labels[next].getColor().equals(Color.LIGHT_GRAY))
			return moveDown(steps + 1);
		return next;
	}
	
	public void keyPressedSpace() {
		activateKey();	
	}
	
	public void keyPressedEnter() {
		activateKey();
	}
	
	public void switchColor(int prev, int next) {
		screen.labels[prev].setColor(Color.WHITE);
		highlight = next;
		screen.labels[next].setColor(Color.ORANGE);
	}

	private void activateKey() {
		switch (highlight) {
		case 0:
			screen.game.gameScreen = new GameScreen(screen.game);
			screen.game.setScreen(screen.game.gameScreen);
			break;
		case 1:
			screen.game.setScreen(screen.game.gameScreen);
			break;
		case 2:
			break;
		case 3:
			Gdx.app.exit();
		}
	}
	
}
