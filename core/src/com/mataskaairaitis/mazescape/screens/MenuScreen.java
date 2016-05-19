package com.mataskaairaitis.mazescape.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mataskaairaitis.mazescape.input.MenuControl;
import com.mataskaairaitis.mazescape.game.*;

public class MenuScreen extends ParentScreen {

	SpriteBatch batch;
	Label title;
	Sprite image;
	Label[] labels;
	
	public MenuScreen(Mazescape game) {
		super(game, MenuControl.class);
		batch = new SpriteBatch();
		
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/punk.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 96;
		parameter.color = Color.LIGHT_GRAY;
		BitmapFont titleFont = generator.generateFont(parameter); // font size 12 pixels
		generator.dispose(); // don't forget to dispose to avoid memory leaks!
		
		Label.LabelStyle titleStyle = new Label.LabelStyle(titleFont, Color.ORANGE);
		title = new Label("Mazescape", titleStyle);
		title.setPosition(width/2 - title.getWidth()/2, 550);
		
		image = new Sprite(new Texture(Gdx.files.internal("images/skellington.gif")));
		image.setPosition(100, 50);
		
		Label.LabelStyle style = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
		labels = new Label[]{
			new Label("New Game", new Label.LabelStyle(style)),
			new Label("Resume Game", new Label.LabelStyle(style)),
			new Label("Highscores", new Label.LabelStyle(style)),
			new Label("Exit Game", new Label.LabelStyle(style)),
		};
		
		float line = 2*(width/3);
		labels[0].setPosition(line, 450);
		labels[1].setPosition(line, 350);
		labels[2].setPosition(line, 250);
		labels[3].setPosition(line, 150);
		
		labels[0].setColor(Color.ORANGE);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1); // Sets background to black
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clears the screen
		batch.begin();
		title.draw(batch, 1);
		image.draw(batch);
		for(Label label : labels) {
			label.draw(batch, 1);
		}
		batch.end();
	}	
	
	@Override
	public void show() {
		super.show();
		Gdx.graphics.setContinuousRendering(true);
		checkLabelState();
	}
	
	public Label[] getLabels() { return labels; }
	
	private void checkLabelState() {
		if(game.getGameScreen() == null)
			labels[1].setColor(Color.LIGHT_GRAY);
		else
			labels[1].setColor(Color.WHITE);
		labels[2].setColor(Color.LIGHT_GRAY);
	}

}
