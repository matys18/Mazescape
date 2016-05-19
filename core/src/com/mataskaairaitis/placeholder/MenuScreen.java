package com.mataskaairaitis.placeholder;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class MenuScreen extends ParentScreen {

	SpriteBatch batch;
	Label header;
	Label[] labels;
	
	public MenuScreen(Mazescape game) {
		super(game, MenuControl.class);
		
		batch = new SpriteBatch();
		BitmapFont font = new BitmapFont();
		font.getData().setScale(3);
		Label.LabelStyle headerstyle = new Label.LabelStyle(font, Color.ORANGE);
		
		header = new Label("Mazescape", headerstyle);
		header.setFontScale(3);
		header.setPosition(width/2 - header.getWidth()/2, 600);
		
		Label.LabelStyle style = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
		labels = new Label[]{
			new Label("New Game", new Label.LabelStyle(style)),
			new Label("Resume Game", new Label.LabelStyle(style)),
			new Label("Highscores", new Label.LabelStyle(style)),
			new Label("Exit Game", new Label.LabelStyle(style)),
		};
		
		labels[0].setPosition(width/2 - labels[0].getWidth()/2, 450);
		labels[1].setPosition(width/2 - labels[1].getWidth()/2, 350);
		labels[2].setPosition(width/2 - labels[2].getWidth()/2, 250);
		labels[3].setPosition(width/2 - labels[3].getWidth()/2, 150);
		
		labels[0].setColor(Color.ORANGE);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1); // Sets background to black
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clears the screen
		batch.begin();
		header.draw(batch, 1);
		for(Label label : labels) {
			label.draw(batch, 1);
		}
		batch.end();
	}	
	
	@Override
	public void show() {
		super.show();
		Gdx.graphics.setContinuousRendering(false);
		checkLabelState();
	}
	
	private void checkLabelState() {
		if(game.gameScreen == null)
			labels[1].setColor(Color.LIGHT_GRAY);
		else
			labels[1].setColor(Color.WHITE);
		labels[2].setColor(Color.LIGHT_GRAY);
	}

}
