package com.example.pro;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Notification extends AppCompatActivity {

    Toolbar toolbar;
    TextView textView;
    ActionBarDrawerToggle myToggle;
    DrawerLayout d;
    FloatingActionButton floatingActionButton;
    private final String CHANNEL_ID = "pushnotifications";
    private final int NOTIFICATION_ID = 001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        toolbar = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_icons8_menu_filled);

        d = findViewById(R.id.drawer_layout2);
        myToggle = new ActionBarDrawerToggle(this, d, R.string.open, R.string.close);
        d.addDrawerListener(myToggle);
        myToggle.syncState();

        floatingActionButton = findViewById(R.id.fab1);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),"Check the notification drawer",Toast.LENGTH_SHORT).show();

                createNotificationChannel();
                NotificationCompat.Builder builder = new NotificationCompat.Builder(Notification.this,CHANNEL_ID);
                builder.setSmallIcon(R.drawable.ic_notification)
                        .setContentTitle("Simple Notification")
                        .setContentText("Hey There, This is a simple notification")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(Notification.this);
                notificationManagerCompat.notify(NOTIFICATION_ID,builder.build());

            }
        });

        textView = findViewById(R.id.noti_text);
        InputStream inputStream = getResources().openRawResource(R.raw.notification);
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
        textView.setText(byteArrayOutputStream.toString());

    }

    private void createNotificationChannel(){

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            CharSequence name = "personal notifications";
            String description = "include all the personal notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID,name,importance);
            notificationChannel.setDescription(description);

            NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
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

        if (id == android.R.id.home) {
            d.openDrawer(GravityCompat.START);
        }

        //noinspection SimplifiableIfStatement
        return super.onOptionsItemSelected(item);
    }
}