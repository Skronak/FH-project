package com.ohmy.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.ohmy.game.Constants;
import com.ohmy.game.actor.AnimatedBaseActor;
import com.ohmy.game.actor.EnemyActor;
import com.ohmy.game.manager.GameManager;

import java.util.ArrayList;

import static com.badlogic.gdx.Gdx.files;

/**
 * Created by Skronak on 20/11/2017.
 */

public class GameScreen implements Screen {

    private Stage stage;
    private OrthographicCamera camera;
    private FitViewport viewport;
    private InputMultiplexer inputMultiplexer;
    private GameManager gameManager;
    private Group dialogHolderGroup;
    private Group enemyDialogHolderGroup;
    private EnemyActor enemyActor;

    public GameScreen(GameManager gameManager){
        this.gameManager = gameManager;
        viewport = new FitViewport(Constants.V_WIDTH, Constants.V_HEIGHT);
        stage = new Stage(viewport);
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(stage);

        dialogHolderGroup = new Group();
        enemyDialogHolderGroup = new Group();


        Gdx.input.setInputProcessor(stage);
    }

    public void initBattle() {
    }

    @Override
    public void show() {
       viewport.apply(true);

       enemyActor = new EnemyActor(gameManager);
       enemyActor.setPosition(Constants.V_WIDTH*.6f,200);
       enemyActor.setScale(0.75f,0.75f);

        stage.addActor(enemyActor);
        stage.addActor(enemyDialogHolderGroup);
        stage.addActor(dialogHolderGroup);

        gameManager.playCinematic();
        gameManager.initGame();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
        Gdx.gl.glClearColor( 1,1,1,1 );

        stage.draw();
        stage.act();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public Stage getStage() {
        return stage;
    }

    public Group getDialogHolderGroup() {
        return dialogHolderGroup;
    }

    public Group getEnemyDialogHolderGroup() {
        return enemyDialogHolderGroup;
    }

    public EnemyActor getEnemyActor() {
        return enemyActor;
    }
}
