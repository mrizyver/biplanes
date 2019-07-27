package com.izyver.biplanes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.izyver.biplanes.engine.GameEngine;

public class GameActivity extends AppCompatActivity {

    private GameEngine gameEngine;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final GameScreen gameScreen = new GameScreen(this);
        setContentView(gameScreen);
        gameEngine = new GameEngine(60, gameScreen);
        gameEngine.startGame();
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, GameActivity.class);
    }
}
