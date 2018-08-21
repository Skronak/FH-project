package com.ohmy.game.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.ohmy.game.DialogEntity;
import com.ohmy.game.manager.GameManager;

import java.util.ArrayList;

public class EnemyActor extends Group {
    private int calm;
    private int anger;
    private int pleased;
    private int hp;

    private AnimatedBaseActor animatedBaseActor;
    private Image dialogImage;
    private int currentAttitude;
    private GameManager gameManager;
    private Label text;
    private DialogEntity currentDialogEntity;
    private int currentText;
    private Image nextImage;

    public EnemyActor(GameManager gameManager){
        this.gameManager = gameManager;

        AnimatedBaseActor enemyBaseActor = new AnimatedBaseActor();
        Array<TextureRegion> framesEnemyList=new Array<TextureRegion>();
        framesEnemyList.add(new TextureRegion(gameManager.getAssetManager().get("sprite/monster/monster0.png", Texture.class)));
        framesEnemyList.add(new TextureRegion(gameManager.getAssetManager().get("sprite/monster/monster1.png", Texture.class)));
        framesEnemyList.add(new TextureRegion(gameManager.getAssetManager().get("sprite/monster/monster2.png", Texture.class)));
        framesEnemyList.add(new TextureRegion(gameManager.getAssetManager().get("sprite/monster/monster3.png", Texture.class)));
        Animation enemyIdleAnimation = new Animation(0.5f,framesEnemyList, Animation.PlayMode.LOOP_PINGPONG);
        enemyBaseActor.storeAnimation("idle",enemyIdleAnimation);

        dialogImage = new Image(gameManager.getAssetManager().get("sprite/bulle.png",Texture.class));
        dialogImage.setPosition(250,this.getHeight()+350);

        text = new Label("default text", gameManager.getAssetManager().getSkin());
        text.setWrap(true);
        text.setSize(dialogImage.getWidth()-70,dialogImage.getHeight()/2);
        text.setAlignment(1,1);
        text.setPosition(dialogImage.getX()+dialogImage.getWidth()/2-text.getWidth()/2, dialogImage.getY()+dialogImage.getHeight()/2-text.getHeight()/6);

        nextImage = new Image(new TextureRegion(gameManager.getAssetManager().get("sprite/arrowIcon.png", Texture.class)));
        nextImage.setSize(30,50);
        nextImage.setPosition(text.getX()+text.getWidth(), text.getY()+text.getHeight()/4);

        this.addActor(enemyBaseActor);
        this.addActor(dialogImage);
        this.addActor(nextImage);
        this.addActor(text);

        InputListener touchListener = new ClickListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                speak();
                return false;
            }
        };

        this.addListener(touchListener);
    }

    public void init() {
        calm=0;
        anger=0;
        pleased=0;
        hp=0;
        currentText=0;
    }

    public void speak() {
        text.addAction(Actions.sequence(Actions.fadeOut(0.1f), Actions.run(new Runnable() {
            @Override
            public void run() {
                text.setText(currentDialogEntity.getText().get(currentText));
            }
        }),Actions.fadeIn(0.3f)));

        if (currentText<currentDialogEntity.getText().size()-1) {
            currentText++;
        } else {
            currentText=0;
        }

    }

    public void playAnimation(){
    }

    public void setCurrentDialogEntity(DialogEntity currentDialogEntity) {
        this.currentDialogEntity = currentDialogEntity;

        if (currentDialogEntity.getText().size()>1) {
            nextImage.setVisible(true);
        } else {
            nextImage.setVisible(false);
        }
    }
}
