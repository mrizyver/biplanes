package com.izyver.biplanes.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import com.izyver.biplanes.Updatable;

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


class Biplane implements Updatable {
    public static final String TAG = "Biplane";

    public final int height;
    public final int width;
    public Paint paint = new Paint();
    public Point position;
    /**
     * value is greater then 0 - biplane is flying up
     * value is less then 0 - biplane is flying down
     * value is equals to 0 - biplane is flying straight
     */
    public byte stabilizer = 0;
    public float speed = 170;
    /**
     * angle in radians
     */
    public float angle = 0;
    private final float radianPerSecond = (float) Math.PI / 2;
    private final float maxAngle = (float) (2 * Math.PI);

    Biplane() {
        this(40, 100);
    }

    Biplane(int height, int width) {
        this.height = height;
        this.width = width;
        this.paint.setColor(Color.RED);
        this.paint.setStrokeWidth(10);
    }

    @Override
    public boolean update(long deltaMillis) {

        if (stabilizer < 0) {
            angle -= radianPerSecond * deltaMillis / 1000f;
            if (angle < 0) {
                angle = maxAngle - -angle;
            }
        } else if (stabilizer > 0) {
            angle += radianPerSecond * deltaMillis / 1000f;
            if (angle > maxAngle) {
                angle = angle - maxAngle;
            }
        }

        double distance = speed * deltaMillis / 1000f;

        position.x += distance * Math.cos(angle);
        position.y += distance * Math.sin(angle);
        Log.d(TAG, " angle - " + angle +
                ", distance - " + distance +
                ", x - " + position.x +
                ", y - " + position.y);
        return true;
    }

    public void drawThemselves(Canvas canvas) {
        float additionalX = (float) ((height * Math.cos(angle)) / 2);
        float additionalY = (float) ((height * Math.sin(angle)) / 2);
        canvas.drawLine(
                position.x - additionalX,
                position.y - additionalY,
                position.x + additionalX,
                position.y + additionalY,
                paint);
        Log.d(TAG, "drawThemselves: angle - " + angle + ", addX - " + additionalX + ", addY "+ additionalY);
    }
}

