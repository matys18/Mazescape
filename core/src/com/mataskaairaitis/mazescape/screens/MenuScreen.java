package com.mataskaairaitis.mazescape.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mataskaairaitis.mazescape.Mazescape;
import com.mataskaairaitis.mazescape.input.MenuControl;

/**
 * The starting Screen, holds objects that can be
 * highlighted anctivated, along with animations and
 * game title.
 * @author Jarl Silvén
 * @version 23/05/2016
 */
public class MenuScreen extends ParentScreen {

	SpriteBatch batch;
	Label title;
	Sprite image;
	Label[] labels;
	OrthographicCamera camera;
	
	/**
	 * Constructs a new MenuScreen
	 * @param game  The Game of which this Screen is a part of
	 */
	public MenuScreen(Mazescape game) {
		super(game, MenuControl.class);

		camera = new OrthographicCamera(width, height);
		camera.position.set(width * 0.5f, height * 0.5f, 0);
		camera.update();

		batch = new SpriteBatch();
		batch.setProjectionMatrix(camera.combined);
//		image = new Sprite(new Texture(Gdx.files.internal("images/skellington.gif")));
//		image.setPosition(100, 50);
		
		FreeTypeFontGenerator titleGen = new FreeTypeFontGenerator(Gdx.files.internal("fonts/punk.ttf"));
		FreeTypeFontParameter titlePar = new FreeTypeFontParameter();
		titlePar.size = 96;
		titlePar.color = Color.LIGHT_GRAY;
		BitmapFont titleFont = titleGen.generateFont(titlePar);
		titleGen.dispose(); // don't forget to dispose to avoid memory leaks!
		
		Label.LabelStyle titleStyle = new Label.LabelStyle(titleFont, Color.ORANGE);
		title = new Label("Mazescape", titleStyle);
		title.setPosition(width/2 - title.getWidth()/2, 550);
		
		FreeTypeFontGenerator menuGen = new FreeTypeFontGenerator(Gdx.files.internal("fonts/robo.ttf"));
		FreeTypeFontParameter menuPar = new FreeTypeFontParameter();
		menuPar.size = 24;
		menuPar.color = Color.LIGHT_GRAY;
		BitmapFont menuFont = menuGen.generateFont(menuPar);
		menuGen.dispose(); // don't forget to dispose to avoid memory leaks!
		
		Label.LabelStyle menuStyle = new Label.LabelStyle(menuFont, Color.WHITE);
		labels = new Label[]{
			new Label("New Game", new Label.LabelStyle(menuStyle)),
			new Label("Resume Game", new Label.LabelStyle(menuStyle)),
//			new Label("Highscores", new Label.LabelStyle(menuStyle)),
			new Label("Exit Game", new Label.LabelStyle(menuStyle)),
		};
		
		float line = 2*(width/3);
		labels[0].setPosition(line, 400);
		labels[1].setPosition(line, 300);
		labels[2].setPosition(line, 200);
		labels[0].setColor(Color.ORANGE);
	}

	/**
	 * Renders the menu buttons, title and animations
	 * {@inheritDoc}
	 */
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1); // Sets background to black
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clears the screen
		batch.begin();
		title.draw(batch, 1);
//		image.draw(batch);
		for(Label label : labels) {
			label.draw(batch, 1);
		}
		batch.end();
	}	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void show() {
		super.show();
		Gdx.graphics.setContinuousRendering(false);
		checkLabelState();
	}
	
	/**
	 * Get the button item array of the menu. 
	 * @return array of buttons
	 */
	public Label[] getLabels() { return labels; }
	
	/**
	 * Check the current state of the game, and
	 * disable buttons depending it.
	 */
	private void checkLabelState() {
		if(game.getGameScreen() == null)
			labels[1].setColor(Color.DARK_GRAY);
		else
			labels[1].setColor(Color.WHITE);
	}

}
