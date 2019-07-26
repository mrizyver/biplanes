package com.izyver.engine.threads;


import com.izyver.engine.Game;

public class RenderThread extends GameThread{

    public RenderThread(Game game, int timeToDelay) {
        super(game, timeToDelay);
    }

    @Override
    void update() {
        game.renderGame();
    }
}
