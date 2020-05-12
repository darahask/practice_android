package com.example.android10;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Toolbar t;
    DrawerLayout d;
    NavigationView nv;
    TextView txt;
    RadioGroup r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t=findViewById(R.id.toolbar);
        setSupportActionBar(t);
        txt = findViewById(R.id.txt2);

        r = findViewById(R.id.rgrp1);
        r.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.chk:
                        txt.setTypeface(Typeface.DEFAULT);
                        break;
                    case R.id.chk1:
                        txt.setTypeface(ResourcesCompat.getFont(MainActivity.this,R.font.amarante));
                        break;
                    case R.id.chk2:
                        txt.setTypeface(ResourcesCompat.getFont(MainActivity.this,R.font.annie_use_your_telescope));
                }
            }
        });

        d=findViewById(R.id.drawer_layout);
        nv = findViewById(R.id.nav_view);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int e = menuItem.getItemId();
                if(e == R.id.grp2){
                    Intent i = new Intent(MainActivity.this,Help.class);
                     startActivity(i);
                }
                if(e == R.id.grp1){
                    Intent i = new Intent(MainActivity.this,MainActivity.class);
                    startActivity(i);
                }
                if(e == R.id.grp3){
                    Intent i = new Intent(MainActivity.this,Rate.class);
                    startActivity(i);
                }
                if(e == R.id.grp4){
                    Snackbar.make(d,"just to show",Snackbar.LENGTH_SHORT).show();
                }
                if(e == R.id.grp5){
                    Snackbar s = Snackbar.make(d,"Just to show",Snackbar.LENGTH_SHORT);
                    View snackView = s.getView();
                    TextView textView = snackView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.GREEN);
                    s.show();
                }
                return true;
            }
        });

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int e = item.getItemId();

        if(e == android.R.id.home)
        {
           d.openDrawer(GravityCompat.START);
        }

        if(e == R.id.mItem1)
        {
            Intent i = new Intent(this,About.class);
            startActivity(i);
        }

        if(e == R.id.mItem2)
        {
            Intent i = new Intent(this,Help.class);
            startActivity(i);
        }
        return true;
    }

}
