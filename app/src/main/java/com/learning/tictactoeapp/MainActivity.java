package com.learning.tictactoeapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnOfflinePlay, btnOnlinePlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOfflinePlay = findViewById(R.id.btnOfflinePlay);
        btnOnlinePlay = findViewById(R.id.btnOnlinePlay);

        btnOfflinePlay.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, OfflinePlayActivity.class);
            startActivity(intent);
        });

        btnOnlinePlay.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, OnlineCodeGeneratorActivity.class);
            startActivity(intent);
        });

    }
}