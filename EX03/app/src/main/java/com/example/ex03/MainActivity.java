package com.example.ex03;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MediaPlayer ringtone = MediaPlayer.create(MainActivity.this,R.raw.you_are_my_sunshine);
        ringtone.start();

        NotifyIntentService.startActionQuote(MainActivity.this);
    }
}
