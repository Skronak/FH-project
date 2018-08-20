package com.ohmy.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ohmy.game.Constants;
import com.ohmy.game.MyOhMyGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new MyOhMyGame(), config);
		config.height = Constants.V_HEIGHT;
		config.width = Constants.V_WIDTH;
	}
}
