package com.izyver.engine;

import com.izyver.engine.threads.GameThread;
import com.izyver.engine.threads.RenderThread;
import com.izyver.engine.threads.UpdatedThread;

public class GameEngine {

    private final int FPS;
    private final Game game;
    private final int oneFrameTimeMillis;
    private final int oneUpdateTimeMillis;
    private GameThread updateThread;
    private GameThread renderThread;

    public GameEngine(int fps, Game game) {
        this(fps, game, 120);
    }

    public GameEngine(int fps, Game game, int updatesPerSecond) {
        this.FPS = fps;
        this.game = game;
        this.oneUpdateTimeMillis = 1000 / updatesPerSecond;
        this.oneFrameTimeMillis = 1000 / fps;
    }

    public void startGame(){
        if (updateThread == null || updateThread.isInterrupted()){
            updateThread = new UpdatedThread(game, oneUpdateTimeMillis);
            updateThread.start();
        }
        if (renderThread == null || renderThread.isInterrupted()){
            renderThread = new RenderThread(game, oneFrameTimeMillis);
            renderThread.start();
        }
    }
}

