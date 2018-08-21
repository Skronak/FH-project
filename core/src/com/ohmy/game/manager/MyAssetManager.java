package com.ohmy.game.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Json;
import com.ohmy.game.DialogEntity;
import com.ohmy.game.MyOhMyGame;

import java.util.ArrayList;

import javax.xml.soap.Text;

public class MyAssetManager implements Disposable {

    private AssetManager assetManager;
    private MyOhMyGame game;
    private Skin skin;
    private int currentStatus;

    private ArrayList<DialogEntity> playerAttackList;
    private ArrayList<DialogEntity> playerRespondList;
    private ArrayList<DialogEntity> monsterAttackList;
    private ArrayList<DialogEntity> monsterRespondList;

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
        assetManager.load("sprite/monster1_anger.png", Texture.class);
        assetManager.load("sprite/arrowIcon.png", Texture.class);
    }

    public void loadJSONFile(){
        Json json = new Json();
        playerAttackList = new ArrayList<DialogEntity>();
        playerAttackList = json.fromJson(ArrayList.class, DialogEntity.class, Gdx.files.internal("json/player_attack.json"));

        playerRespondList = new ArrayList<DialogEntity>();
        playerRespondList = json.fromJson(ArrayList.class, DialogEntity.class, Gdx.files.internal("json/player_respond.json"));

        monsterAttackList = new ArrayList<DialogEntity>();
        monsterAttackList = json.fromJson(ArrayList.class, DialogEntity.class, Gdx.files.internal("json/monster_attack.json"));
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

    public ArrayList<DialogEntity> getPlayerAttackList() {
        return playerAttackList;
    }

    public ArrayList<DialogEntity> getPlayerRespondList() {
        return playerRespondList;
    }

    public ArrayList<DialogEntity> getMonsterAttackList() {
        return monsterAttackList;
    }

    public int getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(int currentStatus) {
        this.currentStatus = currentStatus;
    }
}
