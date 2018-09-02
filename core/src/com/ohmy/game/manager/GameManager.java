package com.ohmy.game.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.ohmy.game.Constants;
import com.ohmy.game.DialogEntity;
import com.ohmy.game.GameInfos;
import com.ohmy.game.actor.DialogGroup;
import com.ohmy.game.actor.MonsterDTO;
import com.ohmy.game.event.ScriptedEvent;
import com.ohmy.game.screen.GameScreen;
import com.ohmy.game.MyOhMyGame;
import com.ohmy.game.PlayerInfo;
import com.ohmy.game.screen.Hud;

import java.util.ArrayList;

public class GameManager {
    private MyOhMyGame game;
    private PlayerInfo playerInfo;
    private GameScreen gameScreen;
    private DialogManager dialogManager;
    private TurnManager turnManager;
    private Hud hud;
    private GameInfos gameInfos;
    private MyAssetManager assetManager;
    private ArrayList<ScriptedEvent> scriptedEvents;//parcours et execute

    private DialogEntity playerDialogEntity;
    private DialogEntity monsterDialogEntity;

    private TextButton withdrawHandTB;

    public GameManager (MyOhMyGame game) {
        assetManager = new MyAssetManager(game);
        this.game = game;
        hud = new Hud(this);
        dialogManager = new DialogManager(this);
        playerInfo = new PlayerInfo();
        gameScreen = new GameScreen(this);
        turnManager = new TurnManager();
//        withdrawHandTB = new TextButton("Withdraw", assetManager.getSkin());
    }

    public void playCinematic(){

    }

    public void initGame() {
        gameInfos = new GameInfos(this);

        // INIT THE SCENE
        loadMonsterActor(0);

        // BEGIN THE GAME
        executeTurn();
    }

    /**
     * Handle a turn depending on the
     * game current state
     */
    public void executeTurn(){
        switch (gameInfos.getCurrentState()) {
            case Constants.STATE_PLAYER_ATK :
                hidePlayerDialogGroup();
                resetPlayerDialogGroup();
                break;
            case Constants.STATE_ENEMY_RESPOND:
                generateMonsterRespons();
                break;
            case Constants.STATE_PLAYER_RESPOND:
                break;
            case Constants.STATE_ENEMY_ATTK:
                break;
        }
    }

    private Group generateDialogOption(DialogEntity dialogEntity, int positiony) {
        Image image = new Image(assetManager.get("sprite/green.jpg",Texture.class));
        DialogGroup dialogGroup = new DialogGroup(image,assetManager.getSkin(), dialogEntity, this, positiony);
        return dialogGroup;
    }

    /**
     * Reset le dialogHolderGroup et le repeuple avec
     * x DialogGroup aleatoire en fonction du currentState
     */
    public void resetPlayerDialogGroup(){
        gameScreen.getDialogHolderGroup().clear();

        int index=0;
        if(gameInfos.getCurrentState()==Constants.STATE_PLAYER_ATK) {
            for (int i = 0; i< Constants.PLAYER_NB_DIALOG_ATK; i++) {
                index = (int)(Math.random() * (assetManager.getPlayerAttackList().size()-1));
                DialogEntity dialogEntity = assetManager.getPlayerAttackList().get(index);
                gameScreen.getDialogHolderGroup().addActor(generateDialogOption(dialogEntity,i+(Constants.SCREEN_PLAYER_DIALOG_PADDING*(1+i))));
            }
        } else if (gameInfos.getCurrentState()==Constants.STATE_PLAYER_RESPOND) {
            for (int i = 0; i< Constants.PLAYER_NB_DIALOG_DEF; i++) {
                index = (int)(Math.random() * (assetManager.getPlayerRespondList().size()-1));
                DialogEntity dialogEntity = assetManager.getPlayerAttackList().get(index);
                gameScreen.getDialogHolderGroup().addActor(generateDialogOption(dialogEntity,i+(Constants.SCREEN_PLAYER_DIALOG_PADDING*(1+i))));
            }
        }
    }

    private void initTextButton(){
        InputListener buttonListener = new ClickListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                withdrawPlayerHand();
                return false;
            }
        };
        withdrawHandTB.addListener(buttonListener);
        gameScreen.getStage().addActor(withdrawHandTB);
    }

    private void withdrawPlayerHand(){
        hidePlayerDialogGroup();
    }

    public void hidePlayerDialogGroup(){
        gameScreen.getDialogHolderGroup().addAction(Actions.sequence(Actions.moveBy(0f,-500f,0.5f),  Actions.run(new Runnable() {
            @Override
            public void run() {
                gameScreen.getDialogHolderGroup().setTouchable(Touchable.disabled);}})));
    }

    public void generateMonsterRespons(){
        gameScreen.getMonsterActor().resetAtkText();
        gameScreen.getMonsterActor().switchText();
    }

    public void loadMonsterActor(int id){
        MonsterDTO monsterEntity = assetManager.getMonsterList().get(id);
        gameScreen.getMonsterActor().init(monsterEntity);
    }

    public void validatePlayerChoice(final DialogEntity dialogEntity) {
        Gdx.app.debug("GameManager", "validatePlayerChoice: "+dialogEntity.getId()+": "+dialogEntity.getText());

        playerDialogEntity = dialogEntity;
        gameScreen.getDialogHolderGroup().setTouchable(Touchable.disabled);
        gameScreen.getDialogHolderGroup().addAction(Actions.sequence(Actions.delay(1f),Actions.moveBy(0f,-500f,1f), Actions.run(new Runnable() {
            @Override
            public void run() {
                resolveAction();}})));
    }

    public void validateMonsterChoice(DialogEntity dialogEntity) {
        Gdx.app.debug("GameManager", "validateMonsterChoice: "+dialogEntity.getText());
        monsterDialogEntity = gameScreen.getMonsterActor().getCurrentDialogEntity();
        resolveAction();
    }

    /**
     * Resout l'action du tour en cours et execute le suivant
     */
    public void resolveAction() {
        int result=0;
        if (null!=playerDialogEntity && null!=monsterDialogEntity){
            result = dialogManager.resolveDialogDuel(monsterDialogEntity,playerDialogEntity);
            gameInfos.setCurrentState(Constants.STATE_END_TURN);
        } else if (null==monsterDialogEntity) {
            gameInfos.setCurrentState(Constants.STATE_ENEMY_RESPOND);
        } else if (null==playerDialogEntity) {
            gameInfos.setCurrentState(Constants.STATE_PLAYER_RESPOND);
        }
        executeTurn();
    }

    public void winDuel(){

    }

    public void loseDuel(){

    }

    public void changeScene() {

    }

    public GameScreen getGameScreen() {
        return gameScreen;
    }

    public MyOhMyGame getGame() {
        return game;
    }

    public MyAssetManager getAssetManager() {
        return assetManager;
    }
}
