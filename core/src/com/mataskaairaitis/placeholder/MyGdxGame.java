package com.mataskaairaitis.placeholder;

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

import java.util.Random;

/**
 * Main game class. Holds the screens and user input, and passes
 * itself to all screens so that they may reach each other.
 */
public class MyGdxGame extends Game {

	GameScreen gameScreen;
	MenuScreen menuScreen;
	UserInput userInput;

	@Override
	public void create () {
		gameScreen = new GameScreen(this);
		menuScreen = new MenuScreen(this);
		userInput = new UserInput();
		this.setScreen(menuScreen);
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose() {

	}
}
