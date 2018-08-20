package com.ohmy.game;

import com.ohmy.game.manager.GameManager;

import java.util.ArrayList;

public class GameInfos {
    // Current game data
    private int currentState;
    private ArrayList<DialogEntity> availablePlayerAttackList;
    private ArrayList<DialogEntity> availablePlayerRespondList;
    private ArrayList<DialogEntity> availableEnemyAttackList;
    private ArrayList<DialogEntity> availableEnemyRespondList;
    private GameManager gameManager;

    public GameInfos(GameManager gameManager) {
        this.gameManager = gameManager;
        currentState = 0;
        availablePlayerAttackList = new ArrayList<DialogEntity>();
        availablePlayerRespondList = new ArrayList<DialogEntity>();
        availableEnemyAttackList = new ArrayList<DialogEntity>();
        availableEnemyRespondList = new ArrayList<DialogEntity>();

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
            availablePlayerRespondList.add(gameManager.getAssetManager().getPlayerRespondList().get(index));
        }
    }

    public int getCurrentState() {
        return currentState;
    }

    public void setCurrentState(int currentState) {
        this.currentState = currentState;
    }

    public ArrayList<DialogEntity> getAvailablePlayerAttackList() {
        return availablePlayerAttackList;
    }

    public void setAvailablePlayerAttackList(ArrayList<DialogEntity> availablePlayerAttackList) {
        this.availablePlayerAttackList = availablePlayerAttackList;
    }

    public ArrayList<DialogEntity> getAvailablePlayerRespondList() {
        return availablePlayerRespondList;
    }

    public void setAvailablePlayerRespondList(ArrayList<DialogEntity> availablePlayerRespondList) {
        this.availablePlayerRespondList = availablePlayerRespondList;
    }

    public ArrayList<DialogEntity> getAvailableEnemyAttackList() {
        return availableEnemyAttackList;
    }

    public void setAvailableEnemyAttackList(ArrayList<DialogEntity> availableEnemyAttackList) {
        this.availableEnemyAttackList = availableEnemyAttackList;
    }

    public ArrayList<DialogEntity> getAvailableEnemyRespondList() {
        return availableEnemyRespondList;
    }

    public void setAvailableEnemyRespondList(ArrayList<DialogEntity> availableEnemyRespondList) {
        this.availableEnemyRespondList = availableEnemyRespondList;
    }
}
