package com.bird.uf.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.bird.uf.UFBird;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = UFBird.WIDTH;
		config.height = UFBird.HEIGHT;
		config.title = UFBird.TITLE;
		new LwjglApplication(new UFBird(), config);
	}
}
