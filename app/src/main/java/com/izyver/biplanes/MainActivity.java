package com.izyver.biplanes;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonStartGame = findViewById(R.id.button_start_game);
        buttonStartGame.setOnClickListener (v -> startActivity(GameActivity.newIntent(this)));
    }
}
