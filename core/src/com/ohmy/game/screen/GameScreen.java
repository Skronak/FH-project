package com.ohmy.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.ohmy.game.Constants;
import com.ohmy.game.actor.MonsterActor;
import com.ohmy.game.manager.GameManager;

/**
 * Created by Skronak on 20/11/2017.
 */

public class GameScreen implements Screen {

    private Stage stage;
    private InputMultiplexer inputMultiplexer;
    private GameManager gameManager;
    private Group dialogHolderGroup;
    private MonsterActor monsterActor;
    private Image backgroundImage;
    private Image characterImage;//TODO A remplacer par animatedActor

    public GameScreen(GameManager gameManager){
        this.gameManager = gameManager;
        ExtendViewport viewport = new ExtendViewport(Constants.V_WIDTH, Constants.V_HEIGHT);
        stage = new Stage(viewport);

        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(stage);

        dialogHolderGroup = new Group();

        Gdx.input.setInputProcessor(stage);
    }

    public void initBattle() {
    }

    @Override
    public void show() {
        stage.getViewport().apply(true);

        backgroundImage = new Image(new TextureRegion(new Texture(Gdx.files.internal("sprite/bg2.png"))));
        backgroundImage.setScale(1.25f);
        backgroundImage.setTouchable(Touchable.disabled);

        characterImage = new Image((new TextureRegion(new Texture(Gdx.files.internal("sprite/char1.png")))));
        characterImage.setScale(1.5f);
        characterImage.setPosition(50,-50);
        characterImage.setTouchable(Touchable.disabled);

        monsterActor = new MonsterActor(gameManager);
        monsterActor.setPosition(Constants.V_WIDTH*.65f,200);
        monsterActor.setScale(0.7f,0.8f);

        stage.addActor(monsterActor);
        stage.addActor(backgroundImage);
        stage.addActor(characterImage);
        stage.addActor(dialogHolderGroup);
        stage.setDebugAll(true);

        gameManager.playCinematic();
        gameManager.initGame();

        // Debug test
        TextButton textButton = new TextButton("Clik ON ME", gameManager.getAssetManager().getSkin());
        textButton.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                monsterActor.updateMood();
                gameManager.switchPlayerDialogGroup();
                return false;
            }
        });
        stage.addActor(textButton);
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
        stage.getViewport().update(width, height, true);
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

    public MonsterActor getMonsterActor() {
        return monsterActor;
    }
}
