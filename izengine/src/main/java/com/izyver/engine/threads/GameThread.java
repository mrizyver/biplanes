package com.izyver.engine.threads;

import com.izyver.engine.Game;

public abstract class GameThread extends Thread{

    protected final int MAX_DELAY = 5000;
    protected final Game game;
    protected final int deltaMillis;

    GameThread(Game game, int timeToDelay) {
        this.game = game;
        this.deltaMillis = timeToDelay;
    }

    protected boolean isPause = false;
    protected boolean isRun = false;


    abstract void update();

    @Override
    public void run(){
        while (isRun){
            if (!isPause){
                update();
            }
            try {
                sleep(deltaMillis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void pause(boolean pause) {
        isPause = pause;
    }

    public boolean isPause() {
        return isPause;
    }

    @Override
    public synchronized void start() {
        if (isRun) return;
        game.onStart();
        isRun = true;
        super.start();
    }

    @Override
    public void interrupt() {
        isRun = false;
        super.interrupt();
    }
}
