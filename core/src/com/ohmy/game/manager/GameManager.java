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
import com.ohmy.game.GameState;
import com.ohmy.game.cards.Card;
import com.ohmy.game.GameInfos;
import com.ohmy.game.actor.DialogGroup;
import com.ohmy.game.cards.CardHolderGroup;
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
    private Hud hud;
    private GameInfos gameInfos;
    private MyAssetManager assetManager;
    private CardManager cardManager;
    private ArrayList<ScriptedEvent> scriptedEvents;//parcours et execute
    private boolean playerPlayedFirst;
    private Card playerCard;
    private Card monsterCard;
    private TextButton withdrawHandTB;
    private boolean isPlayer1Playing;


    public GameManager (MyOhMyGame game) {
        this.game = game;
        assetManager = new MyAssetManager(game);
        hud = new Hud(this);
        dialogManager = new DialogManager(assetManager);
        cardManager = new CardManager(this);
        playerInfo = new PlayerInfo();
        gameInfos = GameInfos.INSTANCE;

        gameScreen = new GameScreen(this);

    }

    public void initGame() {
        initPlayersDeck();
        initMonsterActor(0);
    }

    public void playCinematic(){
    }

    /**
     * Debut du combat
     * exemple: choix joueur qui commence aleatoire
     * ou ajout buff etc
     */
    public void startGame() {
        gameInfos.setCurrentState(GameState.GAME_TURN_BEGIN);
        executeTurn();
    }

    /**
     * Handle a turn depending on the
     * game current state
     */
    public void executeTurn() {
        Gdx.app.debug("GameManager", "executeTurn: "+gameInfos.getCurrentState());
        switch (gameInfos.getCurrentState()) {
            case GAME_INIT_BATTLE:
                initGame();
                playCinematic();
                startGame();
                break;
            case GAME_TURN_BEGIN:
                selectFirstToPlay();
                initHands();
            case PLAYER_TURN_BEGIN:
                isPlayer1Playing=true;
                //debug
//                executeTurn();
                break;
            case PLAYER_TURN_ACTION :
                hidePlayerDialogGroup();
//                resetPlayerDialogGroup();
                showPlayerDialogGroup();
                break;
            case PLAYER_TURN_END:
                isPlayer1Playing=false;
                break;
            case ENEMY_TURN_BEGIN:
                generateMonsterRespons();
                break;
            case ENEMY_TURN_ACTION:
                break;
            case ENEMY_TURN_END:
                break;
        }
    }

    /**
     * Alterne le premier joueur a jouer et
     * met à jour le gameInfos.currentState
     */
    public void selectFirstToPlay(){
        if (playerPlayedFirst) {
            gameInfos.setCurrentState(GameState.ENEMY_TURN_BEGIN);
            playerPlayedFirst=false;
        } else {
            gameInfos.setCurrentState(GameState.PLAYER_TURN_BEGIN);
            playerPlayedFirst = false;
        }
    }

    public void initPlayersDeck() {
        List<Card> playerDeck = cardManager.shuffleDeck(assetManager.getPlayerCardList());
        List<Card> enemyDeck = cardManager.shuffleDeck(assetManager.getEnemyCardList().get(gameInfos.getCurrentEnemyId()).getCardList());
        gameInfos.setPlayerDeck(playerDeck);
        gameInfos.setEnemyDeck(enemyDeck);

        // TODO: Replaced by cardManager.completPlayerHand & manual removing in gameManager
        //for (int i = 0; i< Constants.PLAYER_MAX_CARDS; i++) {
        //    Card card = cardManager.pickCard(gameInfos.getPlayerDeck());
        //    gameInfos.getPlayerDeck().remove(card);
        //    gameInfos.getPlayerHand().add(card);
        //}
        //for (int i = 0; i< Constants.ENEMY_MAX_CARDS; i++) {
        //    Card card = cardManager.pickCard(gameInfos.getEnemyDeck());
        //    gameInfos.getEnemyDeck().remove(card);
        //    gameInfos.getEnemyHand().add(card);
        //}
    }

    private void withdrawCard(DialogGroup dialogGroup){
        cardManager.withdrawCard(dialogGroup);
        cardManager.pickCard(gameInfos.getPlayerHand());
    }

    /**
     * Genere une ligne de dialog pour le joueur
     * @param card
     * @param positiony
     * @return
     */
    private Group generateDialogOption(Card card, int positiony) {
        Image image = new Image(assetManager.get("sprite/green.jpg",Texture.class));
        Image typeImage = new Image(assetManager.get("sprite/type"+ card.getType()+".png",Texture.class));
        DialogGroup dialogGroup = new DialogGroup(image,typeImage,assetManager.getSkin(), card, this, positiony);
        return dialogGroup;
    }

    /**
     * Reset le dialogHolderGroup et le repeuple avec
     * x DialogGroup aleatoire en fonction du currentState
     * TODO a revoir
     */
    //public void resetPlayerDialogGroup(){
    //    gameScreen.getDialogHolderGroup().clear();
    //    int index=0;
    //    if(gameInfos.getCurrentState()== GameState.) {
    //        for (int i = 0; i< Constants.PLAYER_NB_DIALOG_ATK; i++) {
    //            index = (int)(Math.random() * (assetManager.getPlayerAttackList().size()-1));
    //            Card cardDTO = assetManager.getPlayerAttackList().get(index);
    //            gameScreen.getDialogHolderGroup().addActor(generateDialogOption(cardDTO,i+(Constants.SCREEN_PLAYER_DIALOG_PADDING*(1+i))));
    //        }
    //    } else if (gameInfos.getCurrentState()==Constants.STATE_PLAYER_RESPOND) {
    //        for (int i = 0; i< Constants.PLAYER_NB_DIALOG_DEF; i++) {
    //            index = (int)(Math.random() * (assetManager.getPlayerDefendList().size()-1));
    //            Card cardDTO = assetManager.getPlayerAttackList().get(index);
    //            gameScreen.getDialogHolderGroup().addActor(generateDialogOption(cardDTO,i+(Constants.SCREEN_PLAYER_DIALOG_PADDING*(1+i))));
    //        }
    //    }
    //}

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
    public void initMonsterActor(int id) {
        gameInfos.setCurrentEnemyId(id);
        MonsterDTO monsterDTO = assetManager.getMonsterList().get(id);
        gameScreen.getMonsterActor().init(monsterDTO);
        gameInfos.setEnemyHP(monsterDTO.getHp());
        gameScreen.getHud().updateHpLabel();
    }

    public void initHands() {
        List<Card> cards = cardManager.completePlayerHand();
        gameInfos.getPlayerHand().addAll(cards);
        gameInfos.getPlayerDeck().removeAll(cards);

        cards = cardManager.completeEnemyHand();
        gameInfos.getEnemyHand().addAll(cards);
        gameInfos.getEnemyDeck().removeAll(cards);

        executeTurn();
    }

    public void validatePlayerChoice(final Card card) {
        Gdx.app.debug("GameManager", "validatePlayerChoice: "+ card.getId()+": "+ card.getText());

        playerCard = card;
        gameScreen.getDialogHolderGroup().setTouchable(Touchable.disabled);
        gameScreen.getDialogHolderGroup().addAction(Actions.sequence(Actions.delay(1f),Actions.moveBy(0f,-500f,1f), Actions.run(new Runnable() {
            @Override
            public void run() {
                resolveAction();}})));
    }

    public void swipeRight(Card card){
        Gdx.app.log("e","right");
    }

    public void swipeLeft(Card card){

    }

    public void validateMonsterChoice(Card card) {
        Gdx.app.debug("GameManager", "validateMonsterChoice: "+ card.getText());
        monsterCard = gameScreen.getMonsterActor().getCurrentCard();
        resolveAction();
    }

    /**
     * Resout l'action du tour en cours et execute le suivant
     */
    public void resolveAction() {
        int result=0;
        if (null!= playerCard && null!= monsterCard){
            result = dialogManager.resolveDialogDuel(monsterCard, playerCard);
            gameInfos.setCurrentState(GameState.PLAYER_TURN_BEGIN);
        } else if (null== monsterCard) {
            gameInfos.setCurrentState(GameState.ENEMY_TURN_BEGIN);
        }// else if (null== playerCard) {
        //  gameInfos.setCurrentState(Constants.STATE_PLAYER_RESPOND);
        //}
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

    public CardManager getCardManager() {
        return cardManager;
    }
}
