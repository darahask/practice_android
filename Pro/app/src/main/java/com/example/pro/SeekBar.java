package com.example.pro;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class SeekBar extends AppCompatActivity {

    DrawerLayout d;
    ActionBarDrawerToggle myToggle;
    android.widget.SeekBar seekBar,seekbar1;
    TextView myText,myText1,mytext1;
    MediaPlayer player;
    Switch aSwitch;
    AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek_bar);

        Toolbar t = findViewById(R.id.toolbar2);
        setSupportActionBar(t);

        d = findViewById(R.id.drawer_layout1);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_icons8_menu_filled);

        myToggle = new ActionBarDrawerToggle(this,d,R.string.open,R.string.close);
        d.addDrawerListener(myToggle);
        myToggle.syncState();

        seekBar = findViewById(R.id.seek1);
        myText = findViewById(R.id.seek_text);

        seekBar.setMax(100);
        seekBar.setProgress(20);
        myText.setText("Progress = 20");
        myText.setTextSize(20);

        seekBar.setOnSeekBarChangeListener(new android.widget.SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(android.widget.SeekBar seekBar, int progress, boolean fromUser) {
                String s = String.valueOf(progress);
                myText.setText("Progress = "+ s);
                myText.setTextSize(progress);
            }

            @Override
            public void onStartTrackingTouch(android.widget.SeekBar seekBar) {
                Toast.makeText(getApplicationContext(),"onStartTrackingTouch() Triggers",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(android.widget.SeekBar seekBar) {
                Toast.makeText(getApplicationContext(),"onStopTrackingTouch() Triggers",Toast.LENGTH_SHORT).show();
            }
        });

        aSwitch = findViewById(R.id.switch1);
        player = MediaPlayer.create(this,R.raw.bensoundhey);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    player.start();
                else
                    player.pause();
            }
        });

        myText1 = findViewById(R.id.codetxt);
        InputStream inputStream = getResources().openRawResource(R.raw.seekcodeone);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int i;
        try {
            i = inputStream.read();
            while (i != -1)
            {
                byteArrayOutputStream.write(i);
                i = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        myText1.setText(byteArrayOutputStream.toString());

        mytext1 = findViewById(R.id.codetxt1);
        InputStream inputStream1 = getResources().openRawResource(R.raw.seekcodetwo);
        ByteArrayOutputStream byteArrayOutputStream1 = new ByteArrayOutputStream();

        int j;
        try {
            j = inputStream1.read();
            while (j != -1)
            {
                byteArrayOutputStream1.write(j);
                j = inputStream1.read();
            }
            inputStream1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mytext1.setText(byteArrayOutputStream1.toString());

        initControls();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id == android.R.id.home)
        {
            d.openDrawer(GravityCompat.START);
        }

        //noinspection SimplifiableIfStatement
        return super.onOptionsItemSelected(item);
    }

    private void initControls()
    {
        try
        {
            seekbar1 = findViewById(R.id.seek2);
            audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            seekbar1.setMax(audioManager
                    .getStreamMaxVolume(AudioManager.STREAM_MUSIC));
            seekbar1.setProgress(audioManager
                    .getStreamVolume(AudioManager.STREAM_MUSIC));

            seekbar1.setOnSeekBarChangeListener(new android.widget.SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(android.widget.SeekBar seekBar, int progress, boolean fromUser) {
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                            progress, 0);
                }

                @Override
                public void onStartTrackingTouch(android.widget.SeekBar seekBar) {
                    Toast.makeText(getApplicationContext(),"onStartTrackingTouch() Triggers",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onStopTrackingTouch(android.widget.SeekBar seekBar) {
                    Toast.makeText(getApplicationContext(),"onStopTrackingTouch() Triggers",Toast.LENGTH_SHORT).show();
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
