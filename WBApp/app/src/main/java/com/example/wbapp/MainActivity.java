package com.example.wbapp;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import com.example.wbapp.extradata.LoginData;
import com.example.wbapp.extradata.RetrofitInterface;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "http://10.21.104.254:3000";
    int x = 0;
    Retrofit retrofit;
    RetrofitInterface retrofitInterface;
    ExtendedFloatingActionButton fab;
    EditText e1,e2,e3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        fab = findViewById(R.id.fab);
        e1 = findViewById(R.id.mat_Email);
        e2 = findViewById(R.id.mat_Pass);
        e3 = findViewById(R.id.mat_name);
        e3.setVisibility(View.GONE);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(x == 0){
                    doLogin();
                }else if(x == 1){
                    doSignUp();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            x = 1;
            e3.setVisibility(View.VISIBLE);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void doSignUp() {
        HashMap<String, String> map = new HashMap<>();
        map.put("email",e1.getText().toString());
        map.put("password",e2.getText().toString());
        map.put("name",e3.getText().toString());

        Call<Void> call = retrofitInterface.executeSignup(map);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                clearAll();
                if(response.code() == 200){
                    Toast.makeText(MainActivity.this,"Signed up successfully", Toast.LENGTH_LONG).show();
                }else if(response.code() == 400){
                    Toast.makeText(MainActivity.this, "Already Registered", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                clearAll();
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    private void doLogin() {
        HashMap<String, String> map = new HashMap<>();
        map.put("email",e1.getText().toString());
        map.put("password",e2.getText().toString());

        Call<LoginData> call = retrofitInterface.executeLogin(map);
        call.enqueue(new Callback<LoginData>() {
            @Override
            public void onResponse(Call<LoginData> call, Response<LoginData> response) {
                clearAll();
                if(response.code() == 200){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle(response.body().getName());
                    builder.setMessage(response.body().getEmail());
                    builder.show();
                }else if(response.code() == 404){
                    Toast.makeText(MainActivity.this, "Wrong Credentials", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginData> call, Throwable t) {
                clearAll();
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void clearAll(){
        x=0;
        e1.setText("");
        e2.setText("");
        e3.setText("");
        e3.setVisibility(View.GONE);
    }
}
