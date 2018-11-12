package com.ohmy.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ohmy.game.Constants;
import com.ohmy.game.GameInfos;
import com.ohmy.game.manager.GameManager;

import java.util.Random;

public class Hud implements Disposable {

    private GameManager gameManager;
    private Stage stage;
    private Viewport viewport;
    private Label hpLabel;

    public Hud(GameManager gameManager) {
        this.gameManager = gameManager;
        OrthographicCamera camera = new OrthographicCamera();
        viewport = new FitViewport(Constants.V_WIDTH, Constants.V_HEIGHT, camera);
        stage = new Stage(viewport);

        hpLabel = new Label("",gameManager.getAssetManager().getSkin());
        Table hudTable = new Table();
        hudTable.top();
        hudTable.setFillParent(true);
        hudTable.add(hpLabel).align(Align.left).top();
        stage.addActor(hudTable);
    }


    public void updateHpLabel(){
        hpLabel.setText(String.valueOf(GameInfos.INSTANCE.getEnemyHP())+" HP");
    }
    /**
     * Methode draw specifique
     */
    public void draw () {
        stage.draw();
    }
    @Override
    public void dispose() {

    }
}
