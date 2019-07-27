package com.izyver.biplanes.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.view.MotionEvent;

public class BiplaneGame extends GameCanvas {

    private Biplane biplane = new Biplane();

    @Override
    public void render(Canvas canvas) {
        canvas.drawColor(Color.BLUE);
        biplane.drawThemselves(canvas);
    }

    @Override
    public void update(long delta) {
        biplane.update(delta);
    }

    @Override
    public void initialize() {
        biplane.position = new Point(100, height() / 2);
    }

    @Override
    public final void onTouch(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (width() / 2 < event.getX()) {
                    onUpPartPressed();
                } else {
                    onDownPartPressed();
                }
                break;
            case MotionEvent.ACTION_UP:
                screenReleased();
                break;
        }
    }

    private void onUpPartPressed() {
        biplane.stabilizer = 1;
    }

    private void onDownPartPressed() {
        biplane.stabilizer = -1;
    }

    private void screenReleased() {
        biplane.stabilizer = 0;
    }
}


