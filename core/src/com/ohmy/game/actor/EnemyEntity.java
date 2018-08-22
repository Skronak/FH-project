package com.ohmy.game.actor;

import com.badlogic.gdx.scenes.scene2d.ui.Image;


import java.util.List;

/**
 * Actor renderer on screen corresponding
 * to the actual enemy
 *
 */
public class EnemyEntity {
    private int id;
    private String name;
    private List<String> idleFrames;
    private List<String> angerFrames;
    private String dialogImage;
    private int hp;
    private int calmRate;
    private int angerRate;
    private int pleasedRate;

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
}
