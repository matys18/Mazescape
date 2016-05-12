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
 * Testgame class.
 * 
 * VERSION 1:
 * Extending the testgame with some more stuff:
 * 1: Sprite, turning the samplepicture texture into a movable sprite
 * 2: Input, moving the sample picture around the window
 * 3: setting the position to the middle of window at start.
 * TODO: organize input better in a separate class, with better
 * logic. Doing an if-statement for each key seems suboptimal.
 * VERSION 2:
 * All input is now handled by UserInput class, which also uses
 * reflection to direct input to methods instead of if-statements.
 */
public class MyGdxGame extends Game {

	GameScreen gameScreen;

	@Override
	public void create () {
		gameScreen  = new GameScreen(this);
		this.setScreen(gameScreen);
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose() {

	}
}
