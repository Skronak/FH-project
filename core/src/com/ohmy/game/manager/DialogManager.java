package com.ohmy.game.manager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.ohmy.game.Constants;
import com.ohmy.game.DialogEntity;
import com.ohmy.game.actor.DialogGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Gere les actions propres aux dialoguesEntity
 */
public class DialogManager {
    private MyAssetManager assetManager;

    public DialogManager(MyAssetManager assetManager){
        this.assetManager=assetManager;
    }

    public List<DialogEntity> initAtkDialogHand() {
       List<DialogEntity> dialogEntities=new ArrayList<DialogEntity>();
       int index =0;
       for (int i = 0; i< Constants.PLAYER_NB_DIALOG_ATK; i++) {
           index = (int)(Math.random() * (assetManager.getPlayerAttackList().size()-1));
           DialogEntity dialogEntity = assetManager.getPlayerAttackList().get(index);
           dialogEntities.add(dialogEntity);
       }
       return dialogEntities;
    }

    public List<DialogEntity> initDefDialogHand() {
        List<DialogEntity> dialogEntities=new ArrayList<DialogEntity>();
        int index =0;
        for (int i = 0; i< Constants.PLAYER_NB_DIALOG_DEF; i++) {
            index = (int)(Math.random() * (assetManager.getPlayerDefendList().size()-1));
            DialogEntity dialogEntity = assetManager.getPlayerDefendList().get(index);
            dialogEntities.add(dialogEntity);
        }
        return dialogEntities;
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
