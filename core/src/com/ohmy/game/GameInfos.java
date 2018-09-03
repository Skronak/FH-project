package com.ohmy.game;

import com.ohmy.game.manager.GameManager;

import java.util.ArrayList;
import java.util.List;

public class GameInfos {
    // Current game data
    private int currentState;
    private List<DialogEntity> availablePlayerAttackList;
    private List<DialogEntity> availablePlayerDefendList;
    private List<DialogEntity> availableMonsterAttackList;
    private List<DialogEntity> availableMonsterDefendList;
    private GameManager gameManager;

    public GameInfos(GameManager gameManager) {
        this.gameManager = gameManager;
        currentState = 0;
        availablePlayerAttackList = new ArrayList<DialogEntity>();
        availablePlayerDefendList = new ArrayList<DialogEntity>();
        availableMonsterAttackList = new ArrayList<DialogEntity>();
        availableMonsterDefendList = new ArrayList<DialogEntity>();

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

    public List<DialogEntity> getAvailablePlayerAttackList() {
        return availablePlayerAttackList;
    }

    public void setAvailablePlayerAttackList(List<DialogEntity> availablePlayerAttackList) {
        this.availablePlayerAttackList = availablePlayerAttackList;
    }

    public List<DialogEntity> getAvailablePlayerDefendList() {
        return availablePlayerDefendList;
    }

    public void setAvailablePlayerDefendList(List<DialogEntity> availablePlayerDefendList) {
        this.availablePlayerDefendList = availablePlayerDefendList;
    }

    public List<DialogEntity> getAvailableMonsterDefendList() {
        return availableMonsterDefendList;
    }

    public void setAvailableMonsterDefendList(List<DialogEntity> availableMonsterDefendList) {
        this.availableMonsterDefendList = availableMonsterDefendList;
    }

    public List<DialogEntity> getAvailableMonsterAttackList() {
        return availableMonsterAttackList;
    }

    public void setAvailableMonsterAttackList(List<DialogEntity> availableMonsterAttackList) {
        this.availableMonsterAttackList = availableMonsterAttackList;
    }

}
