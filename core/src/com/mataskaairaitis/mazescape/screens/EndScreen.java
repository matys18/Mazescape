package com.mataskaairaitis.mazescape.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
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
	
	SpriteBatch batch;
	Label[] winLabels;
	Label[] lossLabels;
	Label textLabel;
	
	float fade;
	
	/**
	 * Screen constructor making the text objects and
	 * formatting them properly.
	 * @param game main Mazescape game object
	 * @param win  boolean - what text to display
	 */
	public EndScreen(Mazescape game, boolean win) {
		super(game, EndControl.class);
		this.win = win;
		fade = 0;
		
		FreeTypeFontGenerator winGen = new FreeTypeFontGenerator(Gdx.files.internal("fonts/autumn.ttf"));
		FreeTypeFontParameter winPar = new FreeTypeFontParameter();
		winPar.size = 96;
		winPar.color = Color.DARK_GRAY;
		BitmapFont bigWin = winGen.generateFont(winPar);
		winPar.size = 48;
		BitmapFont smallWin = winGen.generateFont(winPar);
		winGen.dispose(); // don't forget to dispose to avoid memory leaks!
		
		FreeTypeFontGenerator lossGen = new FreeTypeFontGenerator(Gdx.files.internal("fonts/punk.ttf"));
		FreeTypeFontParameter lossPar = new FreeTypeFontParameter();
		lossPar.size = 64;
		lossPar.color = Color.LIGHT_GRAY;
		BitmapFont bigLoss = lossGen.generateFont(lossPar);
		lossPar.size = 48;
		BitmapFont smallLoss = lossGen.generateFont(lossPar);
		lossGen.dispose(); // don't forget to dispose to avoid memory leaks!
		
		FreeTypeFontGenerator textGen = new FreeTypeFontGenerator(Gdx.files.internal("fonts/robo.ttf"));
		FreeTypeFontParameter textPar = new FreeTypeFontParameter();
		textPar.size = 24;
		textPar.color = Color.LIGHT_GRAY;
		BitmapFont textFont = textGen.generateFont(textPar);
		textGen.dispose();
		
		batch = new SpriteBatch();
		
		winLabels = new Label[] {
			new Label("Together", new Label.LabelStyle(bigWin, null)),
			new Label("they found the light.", new Label.LabelStyle(smallWin, null))
		};
		winLabels[0].setPosition(width/2 - winLabels[0].getWidth()/2, 400);
		winLabels[1].setPosition(width/2 - winLabels[1].getWidth()/2, 330);
		
		lossLabels = new Label[] {
			new Label("And they were", new Label.LabelStyle(smallLoss, Color.ORANGE)),
			new Label("forever lost", new Label.LabelStyle(bigLoss, Color.ORANGE))
		};
		lossLabels[0].setPosition(width/2 - lossLabels[0].getWidth()/2, 400);
		lossLabels[1].setPosition(width/2 - lossLabels[1].getWidth()/2, 300);
		
		textLabel = new Label("[press space to exit]", new Label.LabelStyle(textFont, null));
		
		textLabel.setPosition(width/2 - textLabel.getWidth()/2, 50);
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
			batch.begin();
			for(Label label : winLabels) {
				label.draw(batch, fade);
			}
			if(fade >= 1)
				textLabel.draw(batch, 1);
			batch.end();
		}
		else {
			Gdx.gl.glClearColor(0, 0, 0, 1); // Sets background to black
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clears the screen
			 batch.begin();
			for(Label label : lossLabels) {
				label.draw(batch, fade);
			}
			if(fade >= 1)
				textLabel.draw(batch, 1);
			batch.end();
		}
		if(fade < 1)
			fade += 0.01;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void show() {
		super.show();
		Gdx.graphics.setContinuousRendering(true);
	}

}
