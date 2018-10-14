package com.ohmy.game.manager;

import com.ohmy.game.Constants;
import com.ohmy.game.dto.CardDTO;

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

    public List<CardDTO> initAtkDialogHand() {
       List<CardDTO> dialogEntities=new ArrayList<CardDTO>();
       int index =0;
       for (int i = 0; i< Constants.PLAYER_NB_DIALOG_ATK; i++) {
           index = (int)(Math.random() * (assetManager.getPlayerAttackList().size()-1));
           CardDTO cardDTO = assetManager.getPlayerAttackList().get(index);
           dialogEntities.add(cardDTO);
       }
       return dialogEntities;
    }

    public List<CardDTO> initDefDialogHand() {
        List<CardDTO> dialogEntities=new ArrayList<CardDTO>();
        int index =0;
        for (int i = 0; i< Constants.PLAYER_NB_DIALOG_DEF; i++) {
            index = (int)(Math.random() * (assetManager.getPlayerDefendList().size()-1));
            CardDTO cardDTO = assetManager.getPlayerDefendList().get(index);
            dialogEntities.add(cardDTO);
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
     * @param cardDTOAtk
     * @param cardDTODef
     */
    public int resolveDialogDuel(CardDTO cardDTOAtk, CardDTO cardDTODef){
        int atkValue = cardDTOAtk.getVal();
        int defValue = cardDTODef.getVal();

        switch (cardDTOAtk.getType()- cardDTOAtk.getType()) {
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
