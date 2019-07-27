package com.izyver.biplanes;

import android.graphics.Canvas;

public abstract class GameCanvas {

    abstract void render(Canvas canvas);

    abstract void update(long delta);

}
