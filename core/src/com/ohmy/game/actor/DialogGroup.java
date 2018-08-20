package com.ohmy.game.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.ohmy.game.DialogEntity;
import com.ohmy.game.input.CustomDragListener;
import com.ohmy.game.manager.GameManager;

/**
 * Classe container for player dialog options
 */
public class DialogGroup extends Group {
    private Image image;
    private Label label;
    private GameManager gameManager;

    public DialogGroup (Image image, Skin skin, DialogEntity dialogEntity, GameManager gameManager, int positiony) {
        this.image=image;
        Label text = new Label(dialogEntity.getText().get(0), skin);
        text.setWrap(true);
        text.setSize(image.getWidth(),image.getHeight());
        text.setAlignment(1,1);
        this.addActor(image);
        this.addActor(text);
        this.setPosition(Gdx.graphics.getWidth()/2-image.getWidth()/2, positiony);
        this.addListener(new CustomDragListener(this, gameManager, dialogEntity));
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setLabel(Label label) {
        this.label = label;
    }
}
