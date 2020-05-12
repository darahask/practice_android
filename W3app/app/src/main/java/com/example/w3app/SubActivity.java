package com.example.w3app;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SubActivity extends AppCompatActivity {

    public static int cnttv = 0;
    public static int cntfb = 0;

    EditText e;
    TextView t;
    ConstraintLayout l;
    SharedPreferences s;
    Button b;
    Typeface fontchange;
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        s = getPreferences(Context.MODE_PRIVATE);
        str = s.getString("olditem","please enter status");


        e = findViewById(R.id.ed1);
        t = findViewById(R.id.textView);
        l = findViewById(R.id.lay1);
        b = findViewById(R.id.button);

        e.setHint(str);
    }

    public void txtFn(View v)
    {
        if(cnttv==4)
            cnttv = 0;

        if(cnttv == 0){
            fontchange = ResourcesCompat.getFont(this,R.font.remachinescript_personal_use);
        }
        else if(cnttv ==1){
            fontchange = ResourcesCompat.getFont(this,R.font.amatic_regular);
        }
        else if(cnttv ==2){
            fontchange = ResourcesCompat.getFont(this,R.font.bearskin_demo);
        }
        else if(cnttv == 3){
            fontchange = Typeface.SANS_SERIF;
        }

        cnttv++;

        e.setTypeface(fontchange);
        t.setTypeface(fontchange);
    }

    public void fltFn(View v)
    {
        if(cntfb == 4)
            cntfb = 0;

        if(cntfb == 0) {
            l.setBackgroundColor(getResources().getColor(R.color.turquoise));
            b.setBackgroundColor(getResources().getColor(R.color.turquoise));
            e.setBackgroundColor(getResources().getColor(R.color.turquoise));
        }
        else if(cntfb == 1) {
            l.setBackgroundColor(getResources().getColor(R.color._light_green));
            b.setBackgroundColor(getResources().getColor(R.color._light_green));
            e.setBackgroundColor(getResources().getColor(R.color._light_green));
        }
        else if(cntfb ==2 ) {
            l.setBackgroundColor(getResources().getColor(R.color.dark_orchid));
            b.setBackgroundColor(getResources().getColor(R.color.dark_orchid));
            e.setBackgroundColor(getResources().getColor(R.color.dark_orchid));
        }
        else if(cntfb == 3){
            l.setBackgroundColor(getResources().getColor(R.color.antique_white));
            b.setBackgroundColor(getResources().getColor(R.color.antique_white));
            e.setBackgroundColor(getResources().getColor(R.color.antique_white));
        }

        cntfb++;

    }

    public void  butFn(View v)
    {
        SharedPreferences.Editor editor = s.edit();
        editor.putString("olditem",e.getText().toString());
        editor.apply();

        Snackbar p = Snackbar.make(l,"SAVED",Snackbar.LENGTH_SHORT);
        p.show();
        p.setAction("OK",new View.OnClickListener(){

            @Override
            public void onClick(View v){

                Toast.makeText(getBaseContext(),"IAM A TOAST",Toast.LENGTH_SHORT).show();
            }

        });
    }

}
