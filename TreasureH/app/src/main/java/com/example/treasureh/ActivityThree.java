package com.example.treasureh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityThree extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);
    }

    public void nextActivity2(View view)
    {
        Intent i = new Intent(this,QuestionTwo.class);
        EditText e = findViewById(R.id.editText4);
        String ans = e.getText().toString();
        if(ans.equalsIgnoreCase("pillow"))
            startActivity(i);
        else
            Toast.makeText(getBaseContext(),"Wrong Answer",Toast.LENGTH_SHORT).show();
    }

}
