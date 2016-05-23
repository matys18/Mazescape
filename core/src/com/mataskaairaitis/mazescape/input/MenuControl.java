package com.mataskaairaitis.mazescape.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

import com.mataskaairaitis.mazescape.screens.*;

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
		int length = screen.getLabels().length;
		int next = (highlight + length - steps) % length;
		if(screen.getLabels()[next].getColor().equals(Color.DARK_GRAY))
			return moveUp(steps + 1);
		return next;
	}
	
	private int moveDown(int steps) {
		int next = (highlight + steps) % screen.getLabels().length;
		if(screen.getLabels()[next].getColor().equals(Color.DARK_GRAY))
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
		screen.getLabels()[prev].setColor(Color.WHITE);
		highlight = next;
		screen.getLabels()[next].setColor(Color.ORANGE);
	}

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
