package com.ohmy.game.manager;

import com.badlogic.gdx.Game;
import com.ohmy.game.DialogEntity;

/**
 * Gere les actions propres aux dialoguesEntity
 */
public class DialogManager {
    private GameManager gameManager;

    public DialogManager(GameManager gameManager){
        this.gameManager=gameManager;
    }

    /**
     * ShiFuMi rules
     * 0 pierre
     * 1 feuille
     * 2 ciseaux
     *
     * return 0: draw
     * return 1: atk wins
     * return 2: def wins
     *
     * @param dialogEntityAtk
     * @param dialogEntityDef
     */
    public int resolveDialogDuel(DialogEntity dialogEntityAtk, DialogEntity dialogEntityDef){
        int atkValue = dialogEntityAtk.getVal();
        int defValue = dialogEntityDef.getVal();

        switch (dialogEntityAtk.getType()-dialogEntityAtk.getType()) {
            case -2:  return 1;
            case -1:  return 2;
            case 0:  return 0;
            case 1:  return 1;
            case 2:  return 1;
            default: return 0;
        }
    }

    public void resetDialogPool(){
    }

    public void withdrawDialog(){

    }

    public void addDialog(){

    }

    public void getDialog(){

    }

}
