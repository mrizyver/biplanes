package com.izyver.engine.threads;

import com.izyver.engine.Game;
import com.izyver.engine.Log;

public class UpdatedThread  extends GameThread{

    private long lastTimeUpdate;

    public UpdatedThread(Game game, int timeToDelay) {
        super(game, timeToDelay);
    }

    @Override
    void update() {
        long currentTime = System.currentTimeMillis();
        long currentDeltaTime = currentTime - lastTimeUpdate;
        lastTimeUpdate = currentTime;
        if (currentDeltaTime >= MAX_DELAY){
            currentDeltaTime = 0;
            Log.w("time for update is very big " + currentDeltaTime);
        }
        if (!game.isDrawing){
            game.update(currentDeltaTime);
        }
    }
}
