package com.mataskaairaitis.mazescape.desktop;

/**
 * Launches the game on a desktop computer.
 * @author Matas Kairaitis, Jarl Silven
 * @version 2016-05-26
 */

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mataskaairaitis.mazescape.Mazescape;;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1280;
		config.height = 720;
		config.fullscreen = true;
		new LwjglApplication(new Mazescape(), config);
	}
}
