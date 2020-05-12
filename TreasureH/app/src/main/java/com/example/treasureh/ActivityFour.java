package com.example.treasureh;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;

public class ActivityFour extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);


        VideoView v = findViewById(R.id.videoView);
        v.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.heelo));
        v.start();

    }

    public void killerFn(View view)
    {
        finishAffinity();
    }
}
