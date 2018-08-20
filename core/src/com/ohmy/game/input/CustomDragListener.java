package com.ohmy.game.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.ohmy.game.DialogEntity;
import com.ohmy.game.manager.GameManager;

public class CustomDragListener extends InputListener {

    private Group parentGroup;
    private float tapSquareSize = 14, touchDownX = -1, touchDownY = -1, stageTouchDownX = -1, stageTouchDownY = -1;
    private int pressedPointer = -1;
    private int button;
    private boolean dragging;
    private float deltaX, deltaY;
    private float initialPosX, initialPosy;
    private DialogEntity dialogEntity;
    private GameManager gameManager;

    public CustomDragListener(Group group, GameManager gameManager, DialogEntity dialogEntity){
        this.parentGroup = group;
        this.initialPosX = group.getX();
        this.initialPosy = group.getY();
        this.gameManager = gameManager;
        this.dialogEntity = dialogEntity;
    }

    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        if (pressedPointer != -1) return false;
        if (pointer == 0 && this.button != -1 && button != this.button) return false;
        pressedPointer = pointer;
        touchDownX = x;
        touchDownY = y;
        stageTouchDownX = event.getStageX();
        stageTouchDownY = event.getStageY();

        event.stop();

        return true;
    }

    public void touchDragged (InputEvent event, float x, float y, int pointer) {
        if (pointer != pressedPointer) return;
        if (!dragging && (Math.abs(touchDownX - x) > tapSquareSize || Math.abs(touchDownY - y) > tapSquareSize)) {
            dragging = true;
            dragStart(event, x, y, pointer);
            deltaX = x;
            deltaY = y;
        }
        if (dragging) {
            deltaX -= x;
            deltaY -= y;
            drag(event, x, y, pointer);
            deltaX = x;
            deltaY = y;
        }
    }

    public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
        if (pointer == pressedPointer) {
            if (dragging) dragStop(event, x, y, pointer);
            cancel();
        }
    }

    public void dragStart (InputEvent event, float x, float y, int pointer) {
    }

    /**
     *
     * @param event
     * @param x
     * @param y
     * @param pointer
     */
    public void drag(InputEvent event, float x, float y, int pointer) {


        this.parentGroup.moveBy(x-touchDownX - this.parentGroup.getWidth() / 2, 0);
        float val = initialPosX-parentGroup.getX()>0?initialPosX-parentGroup.getX():parentGroup.getX()-initialPosX;
        float direction = initialPosX-parentGroup.getX()>0?-100:100;
        this.parentGroup.clearActions();

        if (val < 80) {
            this.parentGroup.getColor().a = 1f;
        } else if (val < 100) {
            this.parentGroup.getColor().a = 0.8f;
        } else if (val < 120) {
            this.parentGroup.getColor().a = 0.6f;
        } else if (val < 150) {
            this.parentGroup.getColor().a = 0.4f;
        } else {
            this.parentGroup.addAction(
                    Actions.sequence(
                            Actions.run(new Runnable() {
                                @Override
                                public void run() {
                                    gameManager.validatePlayerChoice(dialogEntity);
                                    cancel();
                                }}),
                            Actions.parallel(
                                Actions.moveBy(direction,0,0.5f),Actions.fadeOut(0.5f))
                    )
            );
        }
    }

    /**
     * Add actor to the stage
     * @param event
     * @param x
     * @param y
     * @param pointer
     */
    public void dragStop (InputEvent event, float x, float y, int pointer) {
         parentGroup.setPosition(initialPosX,initialPosy);
        this.parentGroup.getColor().a = 1f;
    }

    /* If a drag is in progress, no further drag methods will be called until a new drag is started. */
    public void cancel () {
        dragging = false;
        pressedPointer = -1;
    }

    /** Returns true if a touch has been dragged outside the tap square. */
    public boolean isDragging () {
        return dragging;
    }

}
