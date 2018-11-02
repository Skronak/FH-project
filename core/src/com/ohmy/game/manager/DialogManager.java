package com.ohmy.game.manager;

import com.ohmy.game.Constants;
import com.ohmy.game.cards.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Gere les actions propres aux dialoguesEntity
 * TODO: Useless now that i completely changed the rules...
 */
public class DialogManager {
    private MyAssetManager assetManager;

    public DialogManager(MyAssetManager assetManager){
        this.assetManager=assetManager;
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
     * @param cardAtk
     * @param cardDef
     */
    public int resolveDialogDuel(Card cardAtk, Card cardDef){
        int atkValue = cardAtk.getVal();
        int defValue = cardDef.getVal();

        switch (cardAtk.getType()- cardAtk.getType()) {
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
