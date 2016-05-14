package com.mataskaairaitis.placeholder;

import com.badlogic.gdx.Gdx;;

/**
 * Sample Menu control class, for testing purposes.
 * @author jarl
 */
public class MenuControl implements InputReceiver {
	
	MenuScreen screen;
	
	public MenuControl(MenuScreen screen) {
		this.screen = screen;
	}
	
	public void keyPressedUp() {
		screen.highlightedItem = (screen.highlightedItem + screen.menuItems.length - 1) % screen.menuItems.length;
	}
	
	public void keyPressedDown() {
		screen.highlightedItem = (screen.highlightedItem + 1) % screen.menuItems.length;
	}
	
	public void keyPressedSpace() {
		switch (screen.highlightedItem) {
		case 0:
			screen.game.setScreen(screen.game.gameScreen);
			break;
		case 1:
			break;
		case 2:
			Gdx.app.exit();
		}	
	}

}
