package com.ohmy.game;

import com.ohmy.game.cards.Card;
import com.ohmy.game.manager.GameManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe de jeu sauvegardant les informations de la partie en cours
 */
public enum GameInfos {
    INSTANCE;

    // Current game data
    private GameState currentState;
    private List<Card> playerHand;
    private List<Card> enemyHand;
    private List<Card> playerDeck;
    private List<Card> enemyDeck;
    private List<PlayedCardHistory> playedCardHistory;
    private int enemyHP;
    private int playerHP;
    private int currentEnemyId;

    GameInfos(){
        currentState = GameState.GAME_INIT_BATTLE;
        playerHand = new ArrayList<Card>();
        enemyHand = new ArrayList<Card>();
        playedCardHistory = new ArrayList<PlayedCardHistory>();
        currentState=GameState.GAME_INIT_BATTLE;
    }

    public GameState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(GameState currentState) {
        this.currentState = currentState;
    }

    public int getEnemyHP() {
        return enemyHP;
    }

    public void setEnemyHP(int enemyHP) {
        this.enemyHP = enemyHP;
    }

    public int getPlayerHP() {
        return playerHP;
    }

    public void setPlayerHP(int playerHP) {
        this.playerHP = playerHP;
    }

    public List<Card> getPlayerHand() {
        return playerHand;
    }

    public void setPlayerHand(List<Card> playerHand) {
        this.playerHand = playerHand;
    }

    public List<Card> getEnemyHand() {
        return enemyHand;
    }

    public void setEnemyHand(List<Card> enemyHand) {
        this.enemyHand = enemyHand;
    }

    public int getCurrentEnemyId() {
        return currentEnemyId;
    }

    public void setCurrentEnemyId(int currentEnemyId) {
        this.currentEnemyId = currentEnemyId;
    }

    public List<Card> getPlayerDeck() {
        return playerDeck;
    }

    public void setPlayerDeck(List<Card> playerDeck) {
        this.playerDeck = playerDeck;
    }

    public List<Card> getEnemyDeck() {
        return enemyDeck;
    }

    public void setEnemyDeck(List<Card> enemyDeck) {
        this.enemyDeck = enemyDeck;
    }
}
