package com.ohmy.game.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.ohmy.game.Constants;
import com.ohmy.game.dto.CardDTO;
import com.ohmy.game.GameInfos;
import com.ohmy.game.actor.DialogGroup;
import com.ohmy.game.dto.MonsterDTO;
import com.ohmy.game.event.ScriptedEvent;
import com.ohmy.game.screen.GameScreen;
import com.ohmy.game.MyOhMyGame;
import com.ohmy.game.PlayerInfo;
import com.ohmy.game.screen.Hud;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private MyOhMyGame game;
    private PlayerInfo playerInfo;
    private GameScreen gameScreen;
    private DialogManager dialogManager;
    private TurnManager turnManager;
    private Hud hud;
    private GameInfos gameInfos;
    private MyAssetManager assetManager;
    private CardManager cardManager;
    private ArrayList<ScriptedEvent> scriptedEvents;//parcours et execute

    private CardDTO playerCardDTO;
    private CardDTO monsterCardDTO;
    private TextButton withdrawHandTB;

    public GameManager (MyOhMyGame game) {
        assetManager = new MyAssetManager(game);
        this.game = game;
        hud = new Hud(this);
        dialogManager = new DialogManager(assetManager);
        cardManager = new CardManager(this);

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
        initMonsterActor(0);
        initPlayerHand();

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
//                showPlayerDialogGroup();
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

    private void withdrawCard(DialogGroup dialogGroup){
        cardManager.withdrawCard(dialogGroup);
        cardManager.drawCard();
    }

    /**
     * Genere une ligne de dialog pour le joueur
     * @param cardDTO
     * @param positiony
     * @return
     */
    private Group generateDialogOption(CardDTO cardDTO, int positiony) {
        Image image = new Image(assetManager.get("sprite/green.jpg",Texture.class));
        Image typeImage = new Image(assetManager.get("sprite/type"+ cardDTO.getType()+".png",Texture.class));
        DialogGroup dialogGroup = new DialogGroup(image,typeImage,assetManager.getSkin(), cardDTO, this, positiony);
        return dialogGroup;
    }

    /**
     * Reset le dialogHolderGroup et le repeuple avec
     * x DialogGroup aleatoire en fonction du currentState
     * TODO a revoir
     */
    public void resetPlayerDialogGroup(){
        gameScreen.getDialogHolderGroup().clear();
        int index=0;
        if(gameInfos.getCurrentState()==Constants.STATE_PLAYER_ATK) {
            for (int i = 0; i< Constants.PLAYER_NB_DIALOG_ATK; i++) {
                index = (int)(Math.random() * (assetManager.getPlayerAttackList().size()-1));
                CardDTO cardDTO = assetManager.getPlayerAttackList().get(index);
                gameScreen.getDialogHolderGroup().addActor(generateDialogOption(cardDTO,i+(Constants.SCREEN_PLAYER_DIALOG_PADDING*(1+i))));
            }
        } else if (gameInfos.getCurrentState()==Constants.STATE_PLAYER_RESPOND) {
            for (int i = 0; i< Constants.PLAYER_NB_DIALOG_DEF; i++) {
                index = (int)(Math.random() * (assetManager.getPlayerDefendList().size()-1));
                CardDTO cardDTO = assetManager.getPlayerAttackList().get(index);
                gameScreen.getDialogHolderGroup().addActor(generateDialogOption(cardDTO,i+(Constants.SCREEN_PLAYER_DIALOG_PADDING*(1+i))));
            }
        }
    }

    private void initTextButton(){
        InputListener buttonListener = new ClickListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                hidePlayerDialogGroup();
                return false;
            }
        };
        withdrawHandTB.addListener(buttonListener);
        gameScreen.getStage().addActor(withdrawHandTB);
    }

    private void withdrawPlayerHand(){
        hidePlayerDialogGroup();
    }

    public void showPlayerDialogGroup(){
        gameScreen.getDialogHolderGroup().setPosition(gameScreen.getDialogHolderGroup().getX(),-500f);
        gameScreen.getDialogHolderGroup().addAction(Actions.sequence(Actions.moveBy(0f, 500f, 0.5f), Actions.run(new Runnable() {
            @Override
            public void run() {
                gameScreen.getDialogHolderGroup().setTouchable(Touchable.enabled);
            }
        })));
    }

    public void hidePlayerDialogGroup(){
        gameScreen.getDialogHolderGroup().addAction(Actions.sequence(Actions.moveBy(0f,-500f,0.5f),  Actions.run(new Runnable() {
            @Override
            public void run() {
                gameScreen.getDialogHolderGroup().setTouchable(Touchable.disabled);}})));
    }

    public void switchPlayerDialogGroup(){
        gameScreen.getDialogHolderGroup().setVisible(true);

        if (gameScreen.getDialogHolderGroup().isTouchable()) {
            hidePlayerDialogGroup();
        } else {
            showPlayerDialogGroup();
        }
    }


    public void generateMonsterRespons(){
        gameScreen.getMonsterActor().resetAtkText();
        gameScreen.getMonsterActor().switchText();
    }

    /**
     * Retrieve monsterActor and initialize
     * its on screen representation
     * @param id
     */
    public void initMonsterActor(int id){
        MonsterDTO monsterEntity = assetManager.getMonsterList().get(id);
        gameScreen.getMonsterActor().init(monsterEntity);
        gameInfos.setEnemyHP(monsterEntity.getHp());
        gameScreen.getHud().updateHpLabel();
    }

    public void initPlayerHand(){
        List<CardDTO> dialogEntities = dialogManager.initAtkDialogHand();
        gameInfos.setAvailableMonsterDefendList(dialogEntities);

    }

    public void validatePlayerChoice(final CardDTO cardDTO) {
        Gdx.app.debug("GameManager", "validatePlayerChoice: "+ cardDTO.getId()+": "+ cardDTO.getText());

        playerCardDTO = cardDTO;
        gameScreen.getDialogHolderGroup().setTouchable(Touchable.disabled);
        gameScreen.getDialogHolderGroup().addAction(Actions.sequence(Actions.delay(1f),Actions.moveBy(0f,-500f,1f), Actions.run(new Runnable() {
            @Override
            public void run() {
                resolveAction();}})));
    }

    public void swipeRight(CardDTO cardDTO){
        Gdx.app.log("e","right");
    }

    public void swipeLeft(CardDTO cardDTO){

    }

    public void validateMonsterChoice(CardDTO cardDTO) {
        Gdx.app.debug("GameManager", "validateMonsterChoice: "+ cardDTO.getText());
        monsterCardDTO = gameScreen.getMonsterActor().getCurrentCardDTO();
        resolveAction();
    }

    /**
     * Resout l'action du tour en cours et execute le suivant
     */
    public void resolveAction() {
        int result=0;
        if (null!= playerCardDTO && null!= monsterCardDTO){
            result = dialogManager.resolveDialogDuel(monsterCardDTO, playerCardDTO);
            gameInfos.setCurrentState(Constants.STATE_END_TURN);
        } else if (null== monsterCardDTO) {
            gameInfos.setCurrentState(Constants.STATE_ENEMY_RESPOND);
        } else if (null== playerCardDTO) {
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

    public GameInfos getGameInfos() {
        return gameInfos;
    }
}
