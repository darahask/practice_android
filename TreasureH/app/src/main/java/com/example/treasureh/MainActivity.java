package com.example.treasureh;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void nextActivity(View view)
    {
        Intent i = new Intent(this,ActivityTwo.class);
        Switch s = findViewById(R.id.switch1);
        if(s.isChecked())
            startActivity(i);
        else
            Toast.makeText(getBaseContext(),"Please Accept to Play",Toast.LENGTH_SHORT).show();
    }

}
