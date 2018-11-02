package com.ohmy.game.actor;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.ohmy.game.Constants;
import com.ohmy.game.cards.Card;
import com.ohmy.game.input.CardDragListener;
import com.ohmy.game.manager.GameManager;

/**
 * Classe container for player dialog options
 */
public class DialogGroup extends Group {
    private Image image;
    private Image typeImage;
    private Label text;
    private GameManager gameManager;
    private boolean withdrawed;

    public DialogGroup (Image image, Image typeImage, Skin skin, Card card, GameManager gameManager, int positiony) {
        Image fontImg = image;
        text = new Label(card.getText().get(0), skin);
        text.setWrap(true);
        text.setSize(image.getWidth(),image.getHeight());
        typeImage.setSize(20,20);
        text.setAlignment(1,1);
        this.addActor(fontImg);
        this.addActor(text);
        this.addActor(typeImage);

        this.setPosition(Constants.V_WIDTH/2-image.getWidth()/2, positiony);
        this.addListener(new CardDragListener(this, gameManager, card));
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