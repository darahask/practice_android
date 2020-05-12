package com.example.android10;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Help extends AppCompatActivity {

    Toolbar t;
    ConstraintLayout l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        t = findViewById(R.id.toolbar2);
        setSupportActionBar(t);
        l=findViewById(R.id.helLay);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu3,menu);
        return true;
    }

    public void fltList(View view){
        Snackbar s = Snackbar.make(l,"DontSend",Snackbar.LENGTH_SHORT);
        s.setActionTextColor(Color.parseColor("#D81B60"));
        s.setAction("Undo", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        s.show();
    }

    public void mitemListener(MenuItem item)
    {
        finish();
    }
}
