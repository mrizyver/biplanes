package com.izyver.biplanes.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import com.izyver.biplanes.Updatable;

public class BiplaneGame extends GameCanvas {

    private Biplane biplane = new Biplane();

    Paint paint = new Paint();
    @Override
    public void render(Canvas canvas) {
        canvas.drawColor(Color.BLUE);
        canvas.drawLine(
                biplane.position.x,
                biplane.position.y,
                biplane.position.x + biplane.height,
                biplane.position.y + biplane.width,
                paint);
    }

    @Override
    public void update(long delta) {
        biplane.update(delta);
    }

    @Override
    public void initialize() {
        biplane.position = new Point(100, height());
        paint.setColor(Color.RED);
        paint.setStrokeWidth(10);
    }

    @Override
    public final void onTouch(MotionEvent event) {
        switch (event.getAction()){
            case  MotionEvent.ACTION_DOWN:
                if (width() / 2 > event.getX()){
                    onUpPartPressed();
                }else {
                    onDownPartPressed();
                }
                break;
            case MotionEvent.ACTION_UP:
                screenReleased();
                break;
        }
    }

    private void onUpPartPressed(){
        biplane.stabilizer = 1;
    }

    private void onDownPartPressed(){
        biplane.stabilizer = -1;
    }

    private void screenReleased() {
        biplane.stabilizer = 0;
    }
}


class Biplane implements Updatable {
    public final int height;
    public final int width;

    Biplane(){
        this(40, 150);
    }

    Biplane(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public Point position;
    public float angle = 0;
    /**
     * value is greater then 0 - biplane is flying up
     * value is less then 0 - biplane is flying down
     * value is equals to 0 - biplane is flying straight
     */
    public byte stabilizer = 0;
    public float speed = 170;
    public float angleSpeed = 72;


    @Override
    public boolean update(long delta) {

        if (stabilizer < 0){
            angle -= angleSpeed * delta / 1000f;
        }else if (stabilizer > 0){
            angle += angleSpeed * delta / 1000f;
        }

        double distance = speed * delta / 1000f;

        double sinRad = Math.sin(angle);
        double additionalHeight = sinRad * distance;
        position.y += additionalHeight;
        position.x += Math.sqrt(Math.pow(distance, 2) - Math.pow(additionalHeight, 2));
        return true;
    }
}

