package com.ohmy.game.cards;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.ohmy.game.Constants;
import com.ohmy.game.input.CardDragListener;
import com.ohmy.game.manager.GameManager;

/**
 * Materialisation 2d d'une carte
 *
 */
public class CardHolderGroup extends Group {
    private Image image;
    private Image typeImage;
    private Label text;
    private GameManager gameManager;
    private boolean withdrawed;
    private Card card;
    private int value;
    private int type;

    public CardHolderGroup(Card card){
        this.card = card;
    }

    public void initialize(Image image, Image typeImage, ImageButton imgButton, Skin skin, Card card, GameManager gameManager, int positiony){
        Image fontImg = image;
        ImageButton imageButton = imgButton;
        imageButton.setPosition(fontImg.getWidth()-imageButton.getWidth(), 0);
        imageButton.setHeight(fontImg.getHeight());
        text = new Label(card.getText().get(0), skin);
        text.setWrap(true);
        text.setSize(image.getWidth(),image.getHeight());
        typeImage.setSize(20,20);
        text.setAlignment(1,1);
        this.addActor(fontImg);
        this.addActor(text);
        this.addActor(typeImage);
        this.addActor(imageButton);

        this.setPosition(Constants.V_WIDTH/2-image.getWidth()/2, positiony);
        this.addListener(new CardDragListener(this, gameManager, card));
        this.setDebug(true);
    }

    public void reinitialize(){

    }

}
