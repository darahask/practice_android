package com.example.treasureh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class QuestionTwo extends AppCompatActivity {

    Button button;
    EditText edittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_two);

        button = findViewById(R.id.button6);
        edittext = findViewById(R.id.edd);
    }

    public void nextACC(View view)
    {
        String s = edittext.getText().toString();
        if(s.equalsIgnoreCase("gardenhose")) {
            Intent i = new Intent(this, ActivityFour.class);
            startActivity(i);
        }
        else
        {
            Toast.makeText(getBaseContext(),"WrongAnswer",Toast.LENGTH_SHORT).show();
        }
    }

}
