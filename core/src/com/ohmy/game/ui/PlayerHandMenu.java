package com.ohmy.game.ui;

import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.ohmy.game.cards.CardHolderGroup;
import com.ohmy.game.manager.GameManager;

public class PlayerHandMenu extends VerticalGroup {

    public PlayerHandMenu(GameManager gameManager) {
    }

    public void generate(){
        space(5f);

        ScrollPane.ScrollPaneStyle paneStyle = new ScrollPane.ScrollPaneStyle();
        paneStyle.hScroll = paneStyle.hScrollKnob = paneStyle.vScroll = paneStyle.vScrollKnob;
//        paneStyle.vScrollKnob = new TextureRegionDrawable(new TextureRegion(gameManager.getAssetManager().getScrollTexture(), 10, 50));

        ScrollPane pane = new ScrollPane(this, paneStyle);
        pane.setScrollingDisabled(true, false);
    }

    public void addCard(CardHolderGroup cardHolderGroup){
        addActor(cardHolderGroup);
    }

    public void update(){

    }

}