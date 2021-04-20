package com.ghev.game.desktop;


import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ghev.game.GameApp;

public class DesktopLauncher {


	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.resizable=false;
		config.height=225;
		config.width=420;
		config.title="Snake";


		GameApp.SCREEN_HEIGHT=config.height-1;
		GameApp.SCREEN_WIDTH=config.width-1;

		new LwjglApplication(new GameApp(), config);
	}
}
