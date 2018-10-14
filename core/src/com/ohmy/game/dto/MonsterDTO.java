package com.ohmy.game.dto;

import com.badlogic.gdx.scenes.scene2d.ui.Image;


import java.util.List;

/**
 * DTO class representing monster
 * from json file
 */
public class MonsterDTO {
    private int id;
    private String name;
    private List<String> idleFrames;
    private List<String> angerFrames;
    private String dialogImage;
    private int dialogTxtId;
    private int hp;
    private int calmRate;
    private int angerRate;
    private int pleasedRate;
    private int angryTouch; // Nb touch before getting angry, to implement
    private float posX,posY;
    private int height;
    private int width;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getIdleFrames() {
        return idleFrames;
    }

    public void setIdleFrames(List<String> idleFrames) {
        this.idleFrames = idleFrames;
    }

    public List<String> getAngerFrames() {
        return angerFrames;
    }

    public void setAngerFrames(List<String> angerFrames) {
        this.angerFrames = angerFrames;
    }

    public String getDialogImage() {
        return dialogImage;
    }

    public void setDialogImage(String dialogImage) {
        this.dialogImage = dialogImage;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getCalmRate() {
        return calmRate;
    }

    public void setCalmRate(int calmRate) {
        this.calmRate = calmRate;
    }

    public int getAngerRate() {
        return angerRate;
    }

    public void setAngerRate(int angerRate) {
        this.angerRate = angerRate;
    }

    public int getPleasedRate() {
        return pleasedRate;
    }

    public void setPleasedRate(int pleasedRate) {
        this.pleasedRate = pleasedRate;
    }

    public int getAngryTouch() {
        return angryTouch;
    }

    public void setAngryTouch(int angryTouch) {
        this.angryTouch = angryTouch;
    }

    public int getDialogTxtId() {
        return dialogTxtId;
    }

    public void setDialogTxtId(int dialogTxtId) {
        this.dialogTxtId = dialogTxtId;
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}