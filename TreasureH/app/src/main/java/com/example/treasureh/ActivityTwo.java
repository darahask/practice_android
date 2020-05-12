package com.example.treasureh;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class ActivityTwo extends AppCompatActivity {

    public static int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        final MediaPlayer media = MediaPlayer.create(this, R.raw.record);
        if (count == 0) {
            media.start();
            count++;
        }

    }

    public void nextActivity1(View view)
    {
        Intent intent = new Intent(this,BetweenActivity.class);
        CheckBox ch = findViewById(R.id.checkBox);
        if(ch.isChecked())
            startActivity(intent);
        else
            Toast.makeText(getBaseContext(),"Accept Rules",Toast.LENGTH_SHORT).show();
    }
}

