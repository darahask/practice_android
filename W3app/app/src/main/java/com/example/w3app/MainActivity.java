package com.example.w3app;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView t;
    ImageView i;
    Animation top,bottom;
    private static int SPLASH_SCREEN_TIME_OUT=5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t = findViewById(R.id.txt);
        i = findViewById(R.id.img);

        top = AnimationUtils.loadAnimation(this,R.anim.trans1);
        bottom = AnimationUtils.loadAnimation(this,R.anim.trans);

        t.setAnimation(top);
        i.setAnimation(bottom);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this,SubActivity.class);
                startActivity(i);
                finish();
            }
        },SPLASH_SCREEN_TIME_OUT);
    }
}
