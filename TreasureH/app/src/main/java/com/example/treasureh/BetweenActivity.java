package com.example.treasureh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class BetweenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_between);

    }

    public void nextActivityB(View view)
    {
        Intent a = new Intent(this,ActivityThree.class);
        EditText ed1,ed2;
        ed1 = findViewById(R.id.editText1);
        ed2 = findViewById(R.id.editText2);

        String s1 = ed1.getText().toString();
        String s2 = ed2.getText().toString();

        if(s1.isEmpty()|| s2.isEmpty())
            Toast.makeText(getBaseContext(),"Please enter both Participants",Toast.LENGTH_SHORT).show();
        else
            startActivity(a);
    }



}
