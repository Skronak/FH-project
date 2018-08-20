package com.ohmy.game.manager;

import com.ohmy.game.Constants;

/**
 * Classe gerant les informations des
 * tours de jeu
 */
public class TurnManager {
    private int[] turnSequence={Constants.STATE_PLAYER_ATK, Constants.STATE_ENEMY_RESPOND, Constants.STATE_ENEMY_ATTK, Constants.STATE_ENEMY_RESPOND};

    public TurnManager(){

    }

    public void getNexTurn(){

    }
}
