package com.ohmy.game.screen;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.ohmy.game.Constants;
import com.ohmy.game.MyOhMyGame;
import com.ohmy.game.manager.MyAssetManager;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;

/**
 * Created by Skronak on 15/07/2017.
 */

public class LoadingScreen implements Screen {

    private Image splashImage;
    private MyOhMyGame game;
    private Camera camera;
    private StretchViewport viewport;
    private Stage stage;
    private boolean devMode;
    private MyAssetManager myAssetManager;

    public LoadingScreen(MyOhMyGame game, boolean devMode){
        this.game = game;
        this.myAssetManager = game.getGameManager().getAssetManager();
        splashImage=new Image(new Texture(Gdx.files.internal("sprite/logo2.png")));
        splashImage.setPosition(Gdx.graphics.getWidth()/2-splashImage.getWidth()/2,Gdx.graphics.getHeight()/2-splashImage.getHeight()/2);
        camera = new OrthographicCamera(Constants.V_WIDTH, Constants.V_HEIGHT);
        viewport = new StretchViewport(Constants.V_WIDTH, Constants.V_HEIGHT, camera);
        stage = new Stage(viewport);
        this.devMode = devMode;
    }

    @Override
    public void show() {
        stage.addActor(splashImage);

        if (devMode) {
            Gdx.app.setLogLevel(Application.LOG_DEBUG);
        } else {
            Gdx.app.setLogLevel(Application.LOG_NONE);
            splashImage.addAction(Actions.sequence(Actions.alpha(0)
                    , Actions.fadeIn(1.0f)));
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f,1f,1,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();

        if (myAssetManager.getAssetManager().update() && splashImage.getActions().size==0) {
            if(myAssetManager.getCurrentStatus()!=Constants.ASSET_LOAD_COMPLETE) {
                myAssetManager.setCurrentStatus(myAssetManager.getCurrentStatus()+1);
                switch (myAssetManager.getCurrentStatus()) {
                    case Constants.ASSET_LOAD_MISC:
                        myAssetManager.loadMisc();
                        Gdx.app.debug("SplashScreen", "Loading MISC");
                    case Constants.ASSET_LOAD_TEXTURE:
                        myAssetManager.loadTexture();
                        Gdx.app.debug("SplashScreen", "Loading Texture");
                        break;
                    case Constants.ASSET_LOAD_JSON:
                        myAssetManager.loadJSONFile();
                        Gdx.app.debug("SplashScreen", "Loading JSON");
                        break;
                    case Constants.ASSET_LOAD_SOUND:
                        myAssetManager.loadSound();
                        Gdx.app.debug("SplashScreen", "Loading SOUND");
                        break;
                    default:
                        myAssetManager.loadTexture();
                        break;
                }

            } else {
                Gdx.app.log("SplashScreen","Asset loaded !");
                if (devMode){
                    game.setScreen(game.getGameManager().getGameScreen());
                } else {
                    splashImage.addAction(Actions.sequence(Actions.delay(1f), Actions.fadeOut(1.0f), Actions.run(new Runnable() {
                        @Override
                        public void run() {
                            game.setScreen(game.getGameManager().getGameScreen());
                        }
                    })));
                }
            }
        }
    }

    @Override
    public void resize(int width, int height) {
    }
    @Override
    public void pause() {
    }
    @Override
    public void resume() {
    }
    @Override
    public void hide() {
        dispose();
    }
    @Override
    public void dispose() {
        stage.dispose();
    }
}
