package com.mataskaairaitis.placeholder;

import com.badlogic.gdx.Screen;

/**
 * Abstract Screen class, which will combine common elements in
 * Screen subclasses, to avoid code duplication.
 * "ParentScreen" name can be changed - not the best name.
 * @author jarl
 */
public abstract class ParentScreen implements Screen {
	
	Mazescape game;
	InputReceiver control;
	float width, height;;
	
	/**
	 * Parent screen constructor, takes the main Game object
	 * as parameter and the class of the InputReceiver.
	 * @param game
	 * @param controlClass
	 */
	public ParentScreen(Mazescape game, Class<?> controlClass) {
		this.game = game;
		try {
			control = (InputReceiver)controlClass.getConstructor(getClass())
					.newInstance(getClass().cast(this));
		} catch (Exception e) {System.out.println(e);};
		width = 1280;
		height = 720;
	}
	
	@Override
	public void show() {
		game.userInput.setReceiver(control);
	}

	@Override
	public void hide() {}
	
	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void resize(int width, int height) {}

	@Override
	public void dispose() {}
	
}
