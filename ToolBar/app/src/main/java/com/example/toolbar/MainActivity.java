package com.example.toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Toolbar t;
    ImageView iv;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    MediaPlayer play1,play2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play1 = MediaPlayer.create(this,R.raw.bensoundsummer);
        play2 = MediaPlayer.create(this,R.raw.bensoundhey);

        t = findViewById(R.id.my_toolbar);
        setSupportActionBar(t);

        Spinner s = findViewById(R.id.spinner);
        s.setOnItemSelectedListener(this);

        iv = findViewById(R.id.img1);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.spinArray,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.mres,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int e = item.getItemId();

        if(e == R.id.item1)
            Toast.makeText(getBaseContext(),"HOME",Toast.LENGTH_SHORT).show();
        else
            dispatchTakePictureIntent();
        return true;
    }

    private void dispatchTakePictureIntent()
    {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(i.resolveActivity(getPackageManager()) != null)
        {
            startActivityForResult(i,REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String item = parent.getItemAtPosition(position).toString();

        if(item.equalsIgnoreCase("None"))
        {
            if(play2.isPlaying())
                play2.pause();
            if(play1.isPlaying())
                play1.pause();
        }
        if(item.equalsIgnoreCase("Song_1"))
        {
           if(play2.isPlaying())
               play2.pause();
            play1.start();
        }
        if(item.equalsIgnoreCase("Song_2"))
        {
            if(play1.isPlaying())
                play1.pause();
            play2.start();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bundle img = data.getExtras();
            Bitmap imgB = (Bitmap) img.get("data") ;
            iv.setImageBitmap(imgB);
        }
    }
}

