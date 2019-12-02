package com.example.ex2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MediaPlayer ringtone = MediaPlayer.create(MainActivity.this,R.raw.greys_anatomy);
        ringtone.start();

        Button alive = (Button)findViewById(R.id.button1);
        alive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AliveActivity.class);
                startActivity(intent);
            }
        });

        Button died = (Button)findViewById(R.id.button2);
        died.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DeadActivity.class);
                startActivity(intent);
            }
        });
    }
}
