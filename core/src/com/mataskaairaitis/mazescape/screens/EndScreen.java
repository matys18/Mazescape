package com.mataskaairaitis.mazescape.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.mataskaairaitis.mazescape.Mazescape;
import com.mataskaairaitis.mazescape.input.EndControl;

/**
 * End screen class, displaying when the player has either reached
 * the goal or is out of time.
 * @author Jarl Silvén
 * @version 24/05/2016
 */
public class EndScreen extends ParentScreen {

	boolean win;
	
	/**
	 * Screen constructor making the text objects and
	 * formatting them properly.
	 * @param game main Mazescape game object
	 * @param win  boolean - what text to display
	 */
	public EndScreen(Mazescape game, boolean win) {
		super(game, EndControl.class);
		this.win = win;
	}

	/**
	 * Depending on boolean win, sets the background to white or black
	 * and displays victory or gameover text depending on if win is
	 * true or false.
	 */
	@Override
	public void render(float delta) {
		if(win) {
			Gdx.gl.glClearColor(1, 1, 1, 1); // Sets background to white
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clears the screen
			// TODO
		}
		else {
			Gdx.gl.glClearColor(0, 0, 0, 1); // Sets background to black
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clears the screen
			// TODO
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void show() {
		super.show();
		Gdx.graphics.setContinuousRendering(false);
	}

}
