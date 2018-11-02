package com.ohmy.game;

import com.badlogic.gdx.Game;
import com.ohmy.game.manager.GameManager;
import com.ohmy.game.screen.LoadingScreen;

public class MyOhMyGame extends Game {
    private GameManager gameManager;
    private GameInfos gameInfos;

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

    public GameManager getGameManager() {
        return gameManager;
    }
}
