package com.ohmy.game.manager;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.ohmy.game.Constants;
import com.ohmy.game.cards.Card;
import com.ohmy.game.actor.DialogGroup;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardManager {
    private GameManager gameManager;

    public CardManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public DialogGroup withdrawCard(final DialogGroup dialogGroup){
        // pick new dialog entity
        dialogGroup.getText().setText("withdrawed");
        dialogGroup.setWithdrawed(true);
        dialogGroup.getParent().setTouchable(Touchable.disabled);
        dialogGroup.addAction(Actions.sequence(Actions.parallel(Actions.moveBy(0,-200,0.5f), Actions.fadeOut(0.5f)),
                Actions.parallel(Actions.moveBy(0,200, 0.5f), Actions.fadeIn(0.5f)),Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        dialogGroup.getParent().setTouchable(Touchable.enabled);
                    }})));
        return null;
    }

    /**
     * Pick a first card from a given deck
     * @param deck
     * @return
     */
    public Card pickCard(List<Card> deck) {
        Card card = deck.get(0);
        return card;
    }

    /**
     * Return list of card to complete player hands
     * @return
     */
    public List<Card> completePlayerHand() {
        List<Card> newCards = new ArrayList<Card>();
        int nb = Constants.PLAYER_MAX_CARDS - gameManager.getGameInfos().getPlayerHand().size();
        for (int i=0;i<nb;i++) {
            newCards.add(gameManager.getGameInfos().getPlayerDeck().get(i));
        }
        return newCards;
    }

    public List<Card> completeEnemyHand() {
        List<Card> newCards = new ArrayList<Card>();
        int nb = Constants.ENEMY_MAX_CARDS - gameManager.getGameInfos().getEnemyHand().size();
        for (int i=0;i<nb;i++) {
            newCards.add(gameManager.getGameInfos().getEnemyDeck().get(i));
        }
        return newCards;
    }

    /**
     * to redo
     */
    public void replaceWithdrawedCard(){
        for (int i=0;i<=gameManager.getGameInfos().getPlayerHand().size();i++){
            // Detect card to replace
            //if (((DialogGroup) gameManager.getGameScreen().getPlayerHandMenu().getChildren().get(i)).isWithdrawed()){
            //    int index = (int)(Math.random() * (gameManager.getAssetManager().getPlayerAttackList().size()-1));
            //    Card card = gameManager.getAssetManager().getPlayerAttackList().get(index);
            //    DialogGroup dialogGroup = createDialogGroupFromEntity(card,i+(Constants.SCREEN_PLAYER_DIALOG_PADDING*(1+i)));
            //    gameManager.getGameScreen().getPlayerHandMenu().swapActor(dialogGroup,gameManager.getGameScreen().getDialogHolderGroup().getChildren().get(i));
            //}
        }
    }

    public List<Card> shuffleDeck(List<Card> deck) {
        Collections.shuffle(deck);
        return deck;
    }

    private DialogGroup createDialogGroupFromEntity(final Card card, int positiony) {
        Image image = new Image(gameManager.getAssetManager().get("sprite/green.jpg",Texture.class));
        Image typeImage = new Image(gameManager.getAssetManager().get("sprite/type"+ card.getType()+".png",Texture.class));
        Drawable withdrawDrawable = new TextureRegionDrawable(new TextureRegion(gameManager.getAssetManager().get("sprite/deleteIcon.png", Texture.class)));
        withdrawDrawable.setMinWidth(50);
        withdrawDrawable.setMinHeight(50);
        ImageButton withdrawBtn = new ImageButton(withdrawDrawable);
        final DialogGroup dialogGroup = new DialogGroup(image,typeImage,gameManager.getAssetManager().getSkin(), card, gameManager, positiony);

        withdrawBtn.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                withdrawCard(dialogGroup);
            };
        });

        return dialogGroup;
    }
}