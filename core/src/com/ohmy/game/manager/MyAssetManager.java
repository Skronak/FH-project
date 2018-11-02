package com.ohmy.game.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Json;
import com.ohmy.game.cards.Card;
import com.ohmy.game.dto.MonsterDTO;
import com.ohmy.game.dto.CardList;
import com.ohmy.game.MyOhMyGame;


import java.util.ArrayList;

public class MyAssetManager implements Disposable {

    private AssetManager assetManager;
    private MyOhMyGame game;
    private Skin skin;
    private int currentStatus;
    private ArrayList<Card> playerCardList;
    private ArrayList<CardList> enemyCardList;
    private ArrayList<MonsterDTO> monsterList;

    public MyAssetManager(MyOhMyGame game) {
        this.game = game;
        assetManager = new AssetManager();
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        currentStatus=0;
    }

    public void loadTexture(){
        assetManager.load("sprite/city.png", Texture.class);
        assetManager.load("sprite/blue.jpg", Texture.class);
        assetManager.load("sprite/green.jpg", Texture.class);
        assetManager.load("sprite/yellow.jpg", Texture.class);
        assetManager.load("sprite/bulle.png", Texture.class);
        assetManager.load("sprite/monster/monster0.png", Texture.class);
        assetManager.load("sprite/monster/monster1.png", Texture.class);
        assetManager.load("sprite/monster/monster2.png", Texture.class);
        assetManager.load("sprite/monster/monster3.png", Texture.class);
        assetManager.load("sprite/monster/monster1_anger.png", Texture.class);
        assetManager.load("sprite/arrowIcon.png", Texture.class);
        assetManager.load("sprite/type1.png", Texture.class);
        assetManager.load("sprite/type2.png", Texture.class);
        assetManager.load("sprite/type3.png", Texture.class);
        assetManager.load("sprite/type4.png", Texture.class);
        assetManager.load("sprite/type5.png", Texture.class);
    }

    public void loadJSONFile(){
        Json json = new Json();

        playerCardList = new ArrayList<Card>();
        playerCardList = json.fromJson(ArrayList.class, Card.class, Gdx.files.internal("json/player_attack.json"));

        enemyCardList = new ArrayList<CardList>();
        enemyCardList = json.fromJson(ArrayList.class, CardList.class, Gdx.files.internal("json/monsterAtk1.json"));

        monsterList = new ArrayList<MonsterDTO>();
        monsterList = json.fromJson(ArrayList.class, MonsterDTO.class, Gdx.files.internal("json/monster.json"));
    }

    public void loadSound(){
    }

    public void loadMisc(){
    }

    public synchronized <T> T get (String fileName, Class<T> type) {
        T asset = assetManager.get(fileName, type);
        return asset;
    }

    public Skin getSkin(){
        return skin;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    @Override
    public void dispose() {
        assetManager.dispose();
    }

    public int getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(int currentStatus) {
        this.currentStatus = currentStatus;
    }

    public ArrayList<MonsterDTO> getMonsterList() {
        return monsterList;
    }

    public ArrayList<Card> getPlayerCardList() {
        return playerCardList;
    }

    public void setPlayerCardList(ArrayList<Card> playerCardList) {
        this.playerCardList = playerCardList;
    }

    public ArrayList<CardList> getEnemyCardList() {
        return enemyCardList;
    }

    public void setEnemyCardList(ArrayList<CardList> enemyCardList) {
        this.enemyCardList = enemyCardList;
    }

    public void setMonsterList(ArrayList<MonsterDTO> monsterList) {
        this.monsterList = monsterList;
    }
}
