package com.ohmy.game;

import java.io.Serializable;
import java.util.ArrayList;

public class DialogEntity implements Serializable {
    private int id;
    private int counterId;
    private ArrayList<String> text;
    private int type;
    private int val;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public int getId() {
        return id;
    }

    public ArrayList<String> getText() {
        return text;
    }

    public void setText(ArrayList<String> text) {
        this.text = text;
    }

    public void setId(int id) {
        this.id = id;
    }
}
