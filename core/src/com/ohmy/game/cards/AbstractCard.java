package com.ohmy.game.cards;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.ohmy.game.Constants;
import com.ohmy.game.dto.CardDTO;
import com.ohmy.game.input.CustomDragListener;
import com.ohmy.game.manager.GameManager;

/**
 * Defini la representation 2d d'une carte
 * son fils defini la logique
 */
public abstract class AbstractCard extends Group {
    private Image image;
    private Image typeImage;
    private Label text;
    private GameManager gameManager;
    private boolean withdrawed;

    private int value;
    private int type;

    public void initialize(Image image, Image typeImage, ImageButton imgButton, Skin skin, CardDTO cardDTO, GameManager gameManager, int positiony){
        Image fontImg = image;
        ImageButton imageButton = imgButton;
        imageButton.setPosition(fontImg.getWidth()-imageButton.getWidth(), 0);
        imageButton.setHeight(fontImg.getHeight());
        text = new Label(cardDTO.getText().get(0), skin);
        text.setWrap(true);
        text.setSize(image.getWidth(),image.getHeight());
        typeImage.setSize(20,20);
        text.setAlignment(1,1);
        this.addActor(fontImg);
        this.addActor(text);
        this.addActor(typeImage);
        this.addActor(imageButton);

        this.setPosition(Constants.V_WIDTH/2-image.getWidth()/2, positiony);
        this.addListener(new CustomDragListener(this, gameManager, cardDTO));
        this.setDebug(true);
    }

    public Label getText() {
        return text;
    }

    public void setText(Label text) {
        this.text = text;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public boolean isWithdrawed() {
        return withdrawed;
    }

    public void setWithdrawed(boolean withdrawed) {
        this.withdrawed = withdrawed;
    }
}
