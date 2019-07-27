package com.izyver.biplanes.game;

import android.graphics.Canvas;
import android.view.MotionEvent;
import com.izyver.biplanes.engine.exceptions.NotInitializedValue;

public abstract class GameCanvas {

    private int width = -1;
    private int height = -1;

    protected final int width() {
        if (width < 0) {
            throw new NotInitializedValue("value 'width' must be greater then 0");
        }
        return width;
    }

    protected final int height() {
        if (height < 0){
            throw new NotInitializedValue("value 'height' must be greater then 0");
        }
        return height;
    }


    public abstract void render(Canvas canvas);

    public abstract void update(long delta);

    public abstract void initialize();

    public void onTouch(MotionEvent event) {
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
