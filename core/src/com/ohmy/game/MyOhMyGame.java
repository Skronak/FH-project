package com.ohmy.game;

import com.badlogic.gdx.Game;
import com.ohmy.game.manager.GameManager;
import com.ohmy.game.manager.MyAssetManager;
import com.ohmy.game.screen.LoadingScreen;

public class MyOhMyGame extends Game {
	private MyAssetManager assetManager;
    private GameManager gameManager;

	@Override
	public void create () {
		gameManager = new GameManager(this);
		setScreen(new LoadingScreen(this,true));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
	}

	public MyAssetManager getAssetManager() {
		return assetManager;
	}

    public GameManager getGameManager() {
        return gameManager;
    }
}
