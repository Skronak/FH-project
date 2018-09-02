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
import java.util.List;

/**
 * Actor renderer on screen corresponding
 * to the actual monster
 *
 */
public class MonsterActor extends Group {
    private int id;
    private String name;
    private Animation idleAnimation;
    private Animation angerAnimation;
    private int calm;
    private int anger;
    private int pleased;
    private int thresholdHp;
    private int currentHp;
    private AnimatedBaseActor monsterBaseActor;
    private Image dialogImage;
    private int currentAttitude;
    private GameManager gameManager;
    private Label text;
    private List<DialogEntity> atkDialogList;
    private List<DialogEntity> respDialogList;
    private DialogEntity currentDialogEntity;
    private int nextText;
    private Image nextImage;
    private Group dialogGroup;

    public MonsterActor(GameManager gameManager){
        this.gameManager = gameManager;

        monsterBaseActor = new AnimatedBaseActor();

        dialogImage = new Image(gameManager.getAssetManager().get("sprite/bulle.png",Texture.class));
        dialogImage.setPosition(250,this.getHeight()+350);

        text = new Label("...", gameManager.getAssetManager().getSkin());
        text.setWrap(true);
        text.setSize(dialogImage.getWidth()-70,dialogImage.getHeight()/2);
        text.setAlignment(1,1);
        text.setPosition(dialogImage.getX()+dialogImage.getWidth()/2-text.getWidth()/2, dialogImage.getY()+dialogImage.getHeight()/2-text.getHeight()/6);

        nextImage = new Image(new TextureRegion(gameManager.getAssetManager().get("sprite/arrowIcon.png", Texture.class)));
        nextImage.setSize(30,50);
        nextImage.setPosition(text.getX()+text.getWidth(), text.getY()+text.getHeight()/4);

        dialogGroup = new Group();
        dialogGroup.addActor(dialogImage);
        dialogGroup.addActor(text);
        dialogGroup.addActor(nextImage);

        this.addActor(monsterBaseActor);
        this.addActor(dialogGroup);

        InputListener touchListener = new ClickListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (currentDialogEntity.getText().size()>1) {
                    speak(true);
                } else {
                    speak(false);
                }
                return false;
            }
        };

        this.addListener(touchListener);
    }

    public void init(MonsterDTO monsterEntity) {
        calm=0;
        anger=0;
        pleased=0;
        currentHp=20;
        nextText=0;
        thresholdHp = 50;

        Array<TextureRegion> monsterIdleFramesList=new Array<TextureRegion>();
        for (String frame :monsterEntity.getIdleFrames()){
            monsterIdleFramesList.add(new TextureRegion(gameManager.getAssetManager().get("sprite/monster/"+frame, Texture.class)));
        }
        idleAnimation = new Animation(0.5f,monsterIdleFramesList, Animation.PlayMode.LOOP_PINGPONG);
        monsterBaseActor.storeAnimation("idle",idleAnimation);

        Array<TextureRegion> monsterAngerFramesList=new Array<TextureRegion>();
        for (String frame :monsterEntity.getAngerFrames()){
            monsterAngerFramesList.add(new TextureRegion(gameManager.getAssetManager().get("sprite/monster/"+frame, Texture.class)));
        }
        angerAnimation = new Animation(0.5f,monsterAngerFramesList, Animation.PlayMode.LOOP_PINGPONG);
        monsterBaseActor.storeAnimation("anger", angerAnimation);
        atkDialogList = new ArrayList<DialogEntity>();
        atkDialogList = gameManager.getAssetManager().getDialogEntityList().get(id).getDialogEntityList();

        monsterBaseActor.setActiveAnimation("idle");

        resetAtkText();
        text.setText(currentDialogEntity.getText().get(0));
    }

    /**
     * Change current dialogEntity from monster actor
     */
    public void resetAtkText(){
        int index=0;
        index = (int)(Math.random() * (atkDialogList.size()-1));
        currentDialogEntity = atkDialogList.get(index);

        if (currentDialogEntity.getText().size()>1){
            nextImage.setVisible(true);
        } else {
            nextImage.setVisible(false);
        }
    }

    /**
     * Switch text from dialog box with animation
     */
    public void switchText(){
        dialogGroup.addAction(Actions.sequence(Actions.fadeOut(0.5f), Actions.run(new Runnable() {
            @Override
            public void run() {
                text.setText(currentDialogEntity.getText().get(0));
            }
        }),Actions.fadeIn(0.5f)));
    }

    public void resetRespText(){
        int index=0;
        index = (int)(Math.random() * (respDialogList.size()-1));
        currentDialogEntity = atkDialogList.get(index);
        dialogGroup.addAction(Actions.sequence(Actions.fadeOut(0.5f), Actions.run(new Runnable() {
            @Override
            public void run() {
                text.setText(currentDialogEntity.getText().get(0));
            }
        }),Actions.fadeIn(0.5f)));

        if (currentDialogEntity.getText().size()>1){
            nextImage.setVisible(true);
        } else {
            nextImage.setVisible(false);
        }
    }

    /**
     * Switch text within dialog box
     */
    public void speak(boolean disapear) {
        text.addAction(Actions.sequence(disapear?Actions.fadeOut(0.1f):Actions.delay(0), Actions.run(new Runnable() {
            @Override
            public void run() {
                text.setText(currentDialogEntity.getText().get(nextText));
            }
        }),Actions.fadeIn(0.3f)));
        if (nextText<currentDialogEntity.getText().size()-1) {
            nextText++;
        } else {
            nextText=0;
        }
    }

    public void updateMood(){
        if (currentHp<thresholdHp) {
            monsterBaseActor.setActiveAnimation("anger");
        } else {
            monsterBaseActor.setActiveAnimation("idle");
        }
    }

    public void setCurrentDialogEntity(DialogEntity currentDialogEntity) {
        this.currentDialogEntity = currentDialogEntity;

        if (currentDialogEntity.getText().size()>1) {
            nextImage.setVisible(true);
        } else {
            nextImage.setVisible(false);
        }
    }

    public DialogEntity getCurrentDialogEntity() {
        return currentDialogEntity;
    }
}
