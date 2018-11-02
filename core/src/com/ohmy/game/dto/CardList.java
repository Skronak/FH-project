package com.ohmy.game.dto;

import com.ohmy.game.cards.Card;

import java.io.Serializable;
import java.util.List;

public class CardList implements Serializable {
    private int id;
    private List<Card> cardList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public void setCardList(List<Card> cardList) {
        this.cardList = cardList;
    }
}
