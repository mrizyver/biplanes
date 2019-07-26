package com.izyver.engine;

public abstract class Game {
    public volatile boolean isDrawing = false;

    public final void renderGame(){
        isDrawing = true;
        render();
        isDrawing = false;
    }

    public void onStart(){}
    public abstract void render();
    public abstract void update(long deltaTime);

}
