package com.ohmy.game.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
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
import com.ohmy.game.actor.EnemyEntity;
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


    private DialogEntity currentPlayerDialogEntity;
    private DialogEntity currentEnemyDialogEntity;

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
        loadEnemyActor(0);

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
                resetEnemyActorDialog();
                hidePlayerDialogGroup();
                resetPlayerDialogGroup();
                showPlayerDialogGroup();
                break;
            case Constants.STATE_ENEMY_RESPOND:
                resetEnemyActorDialog();
                showEnemyDialogGroup();
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
        showPlayerDialogGroup();
    }

    public void showPlayerDialogGroup(){
        if (gameScreen.getDialogHolderGroup().getY()<0) {
            gameScreen.getDialogHolderGroup().addAction(Actions.sequence(Actions.moveBy(0f, 500f, 0.5f), Actions.run(new Runnable() {
                @Override
                public void run() {
                    gameScreen.getDialogHolderGroup().setTouchable(Touchable.enabled);
                }
            })));
        }
    }

    public void hidePlayerDialogGroup(){
        gameScreen.getDialogHolderGroup().addAction(Actions.sequence(Actions.moveBy(0f,-500f,0.5f),  Actions.run(new Runnable() {
            @Override
            public void run() {
                gameScreen.getDialogHolderGroup().setTouchable(Touchable.disabled);}})));
    }

    public void resetEnemyActorDialog(){
        int index=0;
        index = (int)(Math.random() * (assetManager.getMonsterAttackList().size()-1));
        DialogEntity dialogEntity = assetManager.getMonsterAttackList().get(index);
        gameScreen.getEnemyActor().setCurrentDialogEntity(dialogEntity);
        gameScreen.getEnemyActor().speak();
    }

    public void showEnemyDialogGroup(){
        gameScreen.getDialogHolderGroup().setVisible(true);
    }

    public void loadEnemyActor(int id){
        EnemyEntity enemyEntity = assetManager.getMonsterList().get(id);
        gameScreen.getEnemyActor().init(enemyEntity);
    }

    public void validatePlayerChoice(DialogEntity dialogEntity) {
        Gdx.app.debug("GameManager", "validatePlayerChoice: "+dialogEntity.getText());
        currentPlayerDialogEntity = dialogEntity;
        gameScreen.getDialogHolderGroup().setTouchable(Touchable.disabled);
        gameScreen.getDialogHolderGroup().addAction(Actions.sequence(Actions.delay(1f),Actions.moveBy(0f,-500f,1f), Actions.run(new Runnable() {
            @Override
            public void run() {
                resolveAction();}})));
    }

    public void validateEnemyChoice(DialogEntity dialogEntity) {
        Gdx.app.debug("GameManager", "validateEnemyChoice: "+dialogEntity.getText());
        // IA
        currentEnemyDialogEntity = gameInfos.getAvailableEnemyAttackList().get(2);
        resolveAction();
    }

    public void resolveAction() {
        if (null!=currentPlayerDialogEntity && null!=currentEnemyDialogEntity){
            gameInfos.setCurrentState(Constants.STATE_END_TURN);
        } else if (null==currentEnemyDialogEntity) {
            gameInfos.setCurrentState(Constants.STATE_ENEMY_RESPOND);
        } else if (null==currentPlayerDialogEntity) {
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
