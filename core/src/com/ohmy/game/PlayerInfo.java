package com.ohmy.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Classe de jeu sauvegardant les informations du joueur
 */
public class PlayerInfo {

    private Preferences prefs;
    private Long lastLogin;
    private int stageId;

    public PlayerInfo() {
        prefs = Gdx.app.getPreferences("OhMyPreferences");

        if (!prefs.contains("lastLogin")) {
            Gdx.app.debug("GameInformation", "Initialisation du compte par defaut");
            lastLogin = System.currentTimeMillis();
            stageId = 0;
        }
    }
}
