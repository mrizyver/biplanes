package com.izyver.biplanes.engine;

import com.izyver.biplanes.engine.threads.RenderThread;

public class GameEngine {

    private final int FPS;
    private final Game game;
    private final int oneFrameTimeMillis;
    private RenderThread renderThread;

    public GameEngine(int fps, Game game) {
        this.FPS = fps;
        this.game = game;
        this.oneFrameTimeMillis = 1000 / fps;
    }

    public void startGame(){
        if (renderThread == null || renderThread.isInterrupted()){
            renderThread = new RenderThread(game, oneFrameTimeMillis);
            renderThread.start();
        }
    }

    public void pause(){
        if (renderThread!= null){
            renderThread.pause(true);
            Log.d("game is paused");
        }
    }

    public void resume(){
        if (renderThread != null){
            renderThread.pause(false);
            Log.d("game is resumed");
        }
    }

    public void stop(){
        if (renderThread != null){
            renderThread.interrupt();
            Log.d("game is stopped");
        }
    }
}

