package com.ohmy.game;

import com.ohmy.game.cards.AbstractCard;
import com.ohmy.game.dto.CardDTO;
import com.ohmy.game.manager.GameManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe de jeu sauvegardant les informations de la partie en cours
 */
public class GameInfos {
    // Current game data
    private int currentState;
    private List<CardDTO> availablePlayerAttackList;
    private List<CardDTO> availablePlayerDefendList;
    private List<CardDTO> availableMonsterAttackList;
    private List<CardDTO> availableMonsterDefendList;
    private GameManager gameManager;
    private List<AbstractCard> playerHand;
    private List<AbstractCard> enemyHand;
    private int enemyHP;
    private int playerHP;

    public GameInfos(GameManager gameManager) {
        this.gameManager = gameManager;
        currentState = 0;
        availablePlayerAttackList = new ArrayList<CardDTO>();
        availablePlayerDefendList = new ArrayList<CardDTO>();
        availableMonsterAttackList = new ArrayList<CardDTO>();
        availableMonsterDefendList = new ArrayList<CardDTO>();

        resetDialogList();
    }

    private void resetDialogList(){
        int index=0;
        for (int i = 0; i< Constants.PLAYER_NB_DIALOG_ATK; i++) {
            index = (int)(Math.random() * (gameManager.getAssetManager().getPlayerAttackList().size()-1));
            availablePlayerAttackList.add(gameManager.getAssetManager().getPlayerAttackList().get(index));
        }
        for (int i = 0; i< Constants.PLAYER_NB_DIALOG_DEF; i++) {
            index = (int)(Math.random() * (gameManager.getAssetManager().getPlayerAttackList().size()-1));
            availablePlayerDefendList.add(gameManager.getAssetManager().getPlayerDefendList().get(index));
        }
    }

    public int getCurrentState() {
        return currentState;
    }

    public void setCurrentState(int currentState) {
        this.currentState = currentState;
    }

    public List<CardDTO> getAvailablePlayerAttackList() {
        return availablePlayerAttackList;
    }

    public void setAvailablePlayerAttackList(List<CardDTO> availablePlayerAttackList) {
        this.availablePlayerAttackList = availablePlayerAttackList;
    }

    public List<CardDTO> getAvailablePlayerDefendList() {
        return availablePlayerDefendList;
    }

    public void setAvailablePlayerDefendList(List<CardDTO> availablePlayerDefendList) {
        this.availablePlayerDefendList = availablePlayerDefendList;
    }

    public List<CardDTO> getAvailableMonsterDefendList() {
        return availableMonsterDefendList;
    }

    public void setAvailableMonsterDefendList(List<CardDTO> availableMonsterDefendList) {
        this.availableMonsterDefendList = availableMonsterDefendList;
    }

    public List<CardDTO> getAvailableMonsterAttackList() {
        return availableMonsterAttackList;
    }

    public void setAvailableMonsterAttackList(List<CardDTO> availableMonsterAttackList) {
        this.availableMonsterAttackList = availableMonsterAttackList;
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
}
