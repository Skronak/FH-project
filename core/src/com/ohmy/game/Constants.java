package com.ohmy.game;

/**
 * Created by Skronak on 04/11/2017.
 */

public class Constants {
    public static final String CURRENT_VERSION = "build pre-alpha v1.0.1";

    // Taille virtuelle verticale de l'application
    public static final int V_WIDTH = 1280;

    // Taille virtuelle horizontale de l'application
    public static final int V_HEIGHT = 720;

    public static final int PLAYER_MAX_CARDS = 5;

    public static final int ENEMY_MAX_CARDS = 5;

    public static final int PLAYER_NB_DIALOG_DEF = 5;

    public static final int SCREEN_PLAYER_DIALOG_PADDING = 80;

    public static final int GAME_INIT_BATTLE = 0;
    public static final int PLAYER_TURN_BEGIN = 1;
    public static final int PLAYER_TURN_ACTION = 2;
    public static final int PLAYER_TURN_END = 3;
    public static final int ENEMY_TURN_BEGIN =4;
    public static final int ENEMY_TURN_ACTION = 5;
    public static final int ENEMY_TURN_END = 6;

    public static final int ASSET_LOAD_MISC=0;
    public static final int ASSET_LOAD_TEXTURE=1;
    public static final int ASSET_LOAD_JSON=2;
    public static final int ASSET_LOAD_SOUND=3;
    public static final int ASSET_LOAD_COMPLETE=4;

    public static final float PLAYER_CARD_HEIGHT = 55f;
    public static final int PLAYER_CARD_WIDTH = 300;
}
