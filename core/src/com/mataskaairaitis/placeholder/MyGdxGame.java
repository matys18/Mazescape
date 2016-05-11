package com.mataskaairaitis.placeholder;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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
public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Sprite sprite;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		Texture img = new Texture("badlogic.jpg");
		sprite = new Sprite(img);
		sprite.setPosition(Gdx.graphics.getWidth()/2 - sprite.getWidth()/2, 
				Gdx.graphics.getHeight()/2 - sprite.getHeight()/2);
		SpriteControl receiver = new SpriteControl(sprite);
		UserInput player = new UserInput(receiver);
		Gdx.input.setInputProcessor(player);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		sprite.draw(batch);
		batch.end();
	}
}
