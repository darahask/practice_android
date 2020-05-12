package com.example.bluetoothapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_ENABLE_BT = 111;
    private FloatingActionButton fab;
    public static BluetoothAdapter bluetoothAdapter;
    private static int REQUEST_CODE = 1;
    private Button onButton,offButton,cutButton;
    public static TextView textView;
    public static BluetoothDevice bluetoothDevice;
    public static BluetoothSocket btSocket = null;
    public static boolean isBtConnected = false;
    public static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.floatingActionButton);
        onButton = findViewById(R.id.button);
        offButton = findViewById(R.id.button2);
        textView = findViewById(R.id.textView2);
        cutButton = findViewById(R.id.button3);

        //Bluetooth Starting
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(bluetoothAdapter ==  null){
            Toast.makeText(this,"Bluetooth not available",Toast.LENGTH_SHORT).show();
        }
        if(!bluetoothAdapter.isEnabled()){
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent,REQUEST_ENABLE_BT);
        }else{
            Toast.makeText(this,"Bluetooth is on",Toast.LENGTH_SHORT).show();
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Devices.class);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });

        onButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btSocket!=null)
                {
                    try
                    {
                        btSocket.getOutputStream().write("TO".getBytes());
                    }
                    catch (IOException e)
                    {
                        textView.setText("Error");
                    }
                }
            }
        });

        offButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btSocket!=null)
                {
                    try
                    {
                        btSocket.getOutputStream().write("TF".getBytes());
                    }
                    catch (IOException e)
                    {
                        textView.setText("Error");
                    }
                }
            }
        });

        cutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btSocket!=null)
                {
                    try
                    {
                        btSocket.close();
                    }
                    catch (IOException e)
                    {
                        textView.setText("Error");
                    }
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_ENABLE_BT && resultCode == RESULT_OK){
            Toast.makeText(this,"Bluetooth is on",Toast.LENGTH_SHORT).show();
        }else if(requestCode == REQUEST_ENABLE_BT && resultCode == RESULT_CANCELED){
            Toast.makeText(this,"Bluetooth Connection rejected",Toast.LENGTH_SHORT).show();
        }else if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            bluetoothDevice = data.getParcelableExtra("Device_Data");
            ConnectBT connectBT = new ConnectBT();
            connectBT.execute();
        }
    }

    @Override
    protected  void onDestroy(){
        super.onDestroy();
        try {
            btSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

//ASYNC TAK LOADER//
class ConnectBT extends AsyncTask<Void, Void, Void> {

    private boolean ConnectSuccess = true; //if it's here, it's almost connected

    @Override
    protected void onPreExecute()
    {
        MainActivity.textView.setText("Connecting...");
    }

    @Override
    protected Void doInBackground(Void... devices) //while the progress dialog is shown, the connection is done in background
    {
        try
        {
            if (MainActivity.btSocket == null || !MainActivity.isBtConnected)
            {
                MainActivity.btSocket = MainActivity.bluetoothDevice.createInsecureRfcommSocketToServiceRecord(MainActivity.myUUID);//create a RFCOMM (SPP) connection
                BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                MainActivity.btSocket.connect();//start connection
            }
        }
        catch (IOException e)
        {
            ConnectSuccess = false;//if the try failed, you can check the exception here
        }
        return null;
    }
    @Override
    protected void onPostExecute(Void result) //after the doInBackground, it checks if everything went fine
    {
        super.onPostExecute(result);

        if (!ConnectSuccess)
        {
            MainActivity.textView.setText("Connection Failed. Is it a SPP Bluetooth? Try again.");
        }
        else
        {
            MainActivity.textView.setText("Connected.");
            MainActivity.isBtConnected = true;
        }
    }
}
