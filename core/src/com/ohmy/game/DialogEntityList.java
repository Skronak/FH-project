package com.ohmy.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DialogEntityList implements Serializable {
    private int id;
    private List<DialogEntity> dialogEntityList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<DialogEntity> getDialogEntityList() {
        return dialogEntityList;
    }

    public void setDialogEntityList(List<DialogEntity> dialogEntityList) {
        this.dialogEntityList = dialogEntityList;
    }
}
