package com.mataskaairaitis.mazescape;

import box2dLight.PointLight;
import box2dLight.RayHandler;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mataskaairaitis.mazescape.screens.*;
import com.mataskaairaitis.mazescape.input.*;

import java.util.Random;

/**
 * Main misc class. Holds the screens and user input, and passes
 * itself to all screens so that they may reach each other.
 * @author Matas Kairaitis
 * @version 2016-06-19
 */
public class Mazescape extends Game {

	GameScreen gameScreen;
	MenuScreen menuScreen;
	EndScreen endScreen;
	UserInput userInput;

	public GameScreen getGameScreen() { return gameScreen; }
	public void setGameScreen(GameScreen gameScreen) { this.gameScreen = gameScreen; }
	public MenuScreen getMenuScreen() { return menuScreen; }
	public EndScreen getEndScreen() { return endScreen; }
	public void setEndScreen(EndScreen endScreen) { this.endScreen = endScreen; }
	public UserInput getUserInput() { return userInput; }
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void create () {
		menuScreen = new MenuScreen(this);
		userInput = new UserInput();
		this.setScreen(menuScreen);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void render () {
		super.render();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dispose() {
		menuScreen.dispose();
	}
}
