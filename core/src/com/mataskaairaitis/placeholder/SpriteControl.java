package com.mataskaairaitis.placeholder;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Test implementation of InputReceiver on the sprite
 * testclass Mazescape.
 * 
 * @author jarl
 */
public class SpriteControl implements InputReceiver {
	
	private Sprite sprite;
	private final int sprite_speed = 5; //milliseconds between wait
	
	boolean movingUp;
	boolean movingDown;
	boolean movingLeft;
	boolean movingRight;
	
	public SpriteControl(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public void keyPressedUp() {
		movingUp = true;
		while (movingUp) {
			sprite.translateY(1);
			sleep();
		}
	}
	
	public void keyReleasedUp() {
		movingUp = false;
	}
	
	public void keyPressedDown() {
		movingDown = true;
		while (movingDown) {
			sprite.translateY(-1);
			sleep();
		}
	}
	
	public void keyReleasedDown() {
		movingDown = false;
	}
	
	public void keyPressedLeft() {
		movingLeft = true;
		while (movingLeft) {
			sprite.translateX(-1);
			sleep();
		}
	}
	
	public void keyReleasedLeft() {
		movingLeft = false;
	}
	
	public void keyPressedRight() {
		movingRight = true;
		while (movingRight) {
			sprite.translateX(1);
			sleep();
		}
	}
	
	public void keyReleasedRight() {
		movingRight = false;
	}
	
	private void sleep() {
		try {
			Thread.sleep(sprite_speed);
		} catch (Exception e) {}
	}
	
}
