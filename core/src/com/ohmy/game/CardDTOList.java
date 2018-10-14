package com.ohmy.game;

import com.ohmy.game.dto.CardDTO;

import java.io.Serializable;
import java.util.List;

public class CardDTOList implements Serializable {
    private int id;
    private List<CardDTO> cardDTOList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<CardDTO> getCardDTOList() {
        return cardDTOList;
    }

    public void setCardDTOList(List<CardDTO> cardDTOList) {
        this.cardDTOList = cardDTOList;
    }
}
