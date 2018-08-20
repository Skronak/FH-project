package com.ohmy.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ohmy.game.Constants;
import com.ohmy.game.manager.GameManager;

public class Hud implements Disposable {

    private GameManager gameManager;
    private Stage stage;
    private Viewport viewport;

    public Hud(GameManager gameManager) {
        this.gameManager = gameManager;
        OrthographicCamera camera = new OrthographicCamera();
        viewport = new FitViewport(Constants.V_WIDTH, Constants.V_HEIGHT, camera);
        stage = new Stage(viewport);

        initMenu();

    }


    public void initMenu(){
    }

    @Override
    public void dispose() {

    }
}
