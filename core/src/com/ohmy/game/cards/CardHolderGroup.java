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
    private int positionY;

    public CardHolderGroup(Card card){
        this.card = card;
    }

    public void initialize(Image image, Image typeImage, GameManager gameManager, int positiony){
        Image fontImg = image;
        text = new Label(card.getText().get(0), gameManager.getAssetManager().getSkin());
        text.setWrap(true);
        text.setSize(image.getWidth(),image.getHeight());
        typeImage.setSize(text.getWidth(),50);
        text.setAlignment(1,1);
        this.addActor(fontImg);
        this.addActor(text);
        this.addActor(typeImage);
//        this.setPosition(Constants.V_WIDTH/2-image.getWidth()/2, positiony);
        this.addListener(new CardDragListener(this, gameManager, card));
    }

    public void reinitialize(){
    }

}
