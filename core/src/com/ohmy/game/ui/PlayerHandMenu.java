package com.ohmy.game.ui;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.ohmy.game.Constants;
import com.ohmy.game.cards.CardHolderGroup;
import com.ohmy.game.manager.GameManager;

public class PlayerHandMenu extends Group {

    private GameManager gameManager;
    public VerticalGroup verticalGroup;

    public PlayerHandMenu(GameManager gameManager) {
        this.gameManager = gameManager;
    }


    public void init() {
        verticalGroup = new VerticalGroup();
        verticalGroup.space(Constants.PLAYER_CARD_HEIGHT);
//        ScrollPane.ScrollPaneStyle paneStyle = new ScrollPane.ScrollPaneStyle();
//        paneStyle.hScroll = paneStyle.hScrollKnob = paneStyle.vScroll = paneStyle.vScrollKnob;
//        paneStyle.vScrollKnob = new TextureRegionDrawable(new TextureRegion(gameManager.getAssetManager().getScrollTexture(), 10, 50));
 //       Label label = new Label("HELLO", gameManager.getAssetManager().getSkin());
 //       ScrollPane pane = new ScrollPane(verticalGroup, paneStyle);
 //       pane.setScrollingDisabled(true, false);

//        this.addActor(pane);
//        this.setPosition(Constants.V_WIDTH/2-pane.getWidth()/2,Constants.V_HEIGHT/3);
        this.setPosition(Constants.V_WIDTH/2-Constants.PLAYER_CARD_WIDTH/2,Constants.V_HEIGHT/3);
        this.addActor(verticalGroup);

        this.debugAll();
    }

    public void addCard(CardHolderGroup cardHolderGroup){
        // play nice animation
        verticalGroup.addActor(cardHolderGroup);
    }

    public void update(){

    }
}