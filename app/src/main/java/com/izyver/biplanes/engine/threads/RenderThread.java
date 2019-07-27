package com.izyver.biplanes.engine.threads;


import com.izyver.biplanes.engine.Game;

public class RenderThread extends GameThread{

    public RenderThread(Game game, int timeToDelay) {
        super(game, timeToDelay);
    }

    @Override
    void update() {
        game.render();
    }
}
