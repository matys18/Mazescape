package com.mataskaairaitis.mazescape.input;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

/**
 * Class which listens for user input, and sends the input to be
 * interpreted by an InputReceiver.
 * 
 * @author Jarl Silvén
 * @version 23/05/2016
 */
public class UserInput implements InputProcessor {

	private InputReceiver receiver;
	
	/**
	 * Constructor that sets current object to main InputProcessor.
	 * @param receiver
	 */
	public UserInput() {
		Gdx.input.setInputProcessor(this);
	}
	/**
	 * Set and Change the receiver of input.
	 * @param receiver
	 */
	public void setReceiver(InputReceiver receiver) {
		this.receiver = receiver;
	}
	
	/**
	 * Attemps to call a method in the InputReceiver object, if the object
	 * contains such a method. If there exists no such method, the exception
	 * is ignored.
	 * @param function_name the name of the method to be called.
	 */
	private void callFunctionByName(String function_name) {
		try {
			Method method = receiver.getClass().getMethod(function_name);
			method.invoke(receiver);
		}
		catch (NoSuchMethodException noMethod) {}
		// invTarget if something wrong happens in invoked method.
		catch (InvocationTargetException invTarget) {
			System.out.println("Error when calling method: " + invTarget.getCause());
		}
		catch (Exception e) {System.out.println("Error on call" + e);}
	}
	
	/**
	 * Called each time a key is pressed, appending the string "keyPressed"
	 * to the name of the key, and runs it on the InputReceiver class, if
	 * such a method exists.
	 * {@inheritDoc}
	 */
	@Override
	public boolean keyDown(int keycode) {
		String function_name = "keyPressed" + Input.Keys.toString(keycode);
		callFunctionByName(function_name);
		return false;
	}

	/**
	 * Called each time a key is released, appending the string "keyReleased"
	 * to the name of the key, and runs it on the InputReceiver class, if
	 * such a method exists.
	 * {@inheritDoc}
	 */
	@Override
	public boolean keyUp(int keycode) {
		String function_name = "keyReleased" + Input.Keys.toString(keycode);
		callFunctionByName(function_name);
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
