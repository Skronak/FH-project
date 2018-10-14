package com.ohmy.game.cards;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.ohmy.game.dto.CardDTO;
import com.ohmy.game.manager.GameManager;

import java.util.ArrayList;

public class AttackCard extends AbstractCard implements Card {

    private int id;
    private int counterId;
    private ArrayList<String> text;
    private int type;
    private int val;

    public AttackCard(CardDTO cardDTO) {
        this.id=cardDTO.id;
        this.counterId=cardDTO.counterId;
        this.text=cardDTO.text;
        this.type=cardDTO.type;
        this.val=cardDTO.val;
    }

    public AttackCard(Image image, Image typeImage, ImageButton imgButton, Skin skin, CardDTO cardDTO, GameManager gameManager, int positiony) {
    }
}
