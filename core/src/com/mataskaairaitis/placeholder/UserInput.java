package com.mataskaairaitis.placeholder;

import java.lang.reflect.Method;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

/**
 * Class which listens for user input, and sends the input to be
 * interpreted by an InputReceiver.
 * 
 * @author jarl
 * @version 1
 */
public class UserInput implements InputProcessor {

	private InputReceiver receiver;
	
	/**
	 * Constructor to hand the class its receiver.
	 * @param receiver
	 */
	public UserInput(InputReceiver receiver) {
		this.receiver = receiver;
	}
	/**
	 * Change the receiver if needed.
	 * @param receiver
	 */
	public void changeReceiver(InputReceiver receiver) {
		this.receiver = receiver;
	}
	
	/**
	 * Attemps to call a method in the InputReceiver object, if the object
	 * contains such a method. If there exists no such method, the exception
	 * is ignored.
	 * 
	 * @param function_name the name of the method to be called.
	 */
	private void callFunctionByName(String function_name) {
		InputThread key_thread = new InputThread(function_name);
		key_thread.start();
	}
	
	/**
	 * Local Thread class to search and execute desired methods.  
	 */
	private class InputThread extends Thread {
		private String function_name;
		
		public InputThread(String function_name) {
			this.function_name = function_name;
		}
		
		public void run() {
			try {
				Method method = receiver.getClass().getMethod(function_name);
				method.invoke(receiver);
			}
			catch (Exception e) {}
		}
	}
	
	/**
	 * Called each time a key is pressed, appending the string "keyPressed"
	 * to the name of the key, and runs it on the InputReceiver class, if
	 * such a method exists.
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
	 */
	@Override
	public boolean keyUp(int keycode) {
		String function_name = "keyReleased" + Input.Keys.toString(keycode);
		callFunctionByName(function_name);
		return false;
	}

	/**
	 * Unused inherited method from InputProcessor.
	 */
	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	/**
	 * Unused inherited method from InputProcessor.
	 */
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	/**
	 * Unused inherited method from InputProcessor.
	 */
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	/**
	 * Unused inherited method from InputProcessor.
	 */
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	/**
	 * Unused inherited method from InputProcessor.
	 */
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	/**
	 * Unused inherited method from InputProcessor.
	 */
	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
