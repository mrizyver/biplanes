package com.izyver.biplanes.engine.threads;

import com.izyver.biplanes.engine.Game;
import com.izyver.biplanes.engine.Log;

public class RenderThread extends Thread{

    private final int MAX_DELAY = 5000;
    private final Game game;
    private final int deltaMillis;

    public RenderThread(Game game, int timeToDelay) {
        this.game = game;
        this.deltaMillis = timeToDelay;
    }

    private boolean isPause = false;
    private boolean isRun = false;

    private long lastTimeUpdate;

    @Override
    public void run(){
        long workTime = 0;
        while (isRun){
            if (!isPause){
                long currentTime = System.currentTimeMillis();
                long currentDeltaTime = currentTime - lastTimeUpdate;
                lastTimeUpdate = currentTime;
                if (currentDeltaTime >= MAX_DELAY) {
                    currentDeltaTime = deltaMillis;
                    Log.w("time for update is very big " + currentDeltaTime);
                }
                game.update(currentDeltaTime);
                game.render();
                workTime = currentTime - System.currentTimeMillis();
            }
            try {
                sleep(workTime < deltaMillis ? deltaMillis - workTime : 0);
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
        game.onStartGame();
        isRun = true;
        super.start();
    }

    @Override
    public void interrupt() {
        isRun = false;
        super.interrupt();
    }
}
