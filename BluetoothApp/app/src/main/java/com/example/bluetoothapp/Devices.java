package com.example.bluetoothapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Set;

public class Devices extends AppCompatActivity {

    ListView listView;
    Set<BluetoothDevice> bluetoothDevices;
    BluetoothAdapter bluetoothAdapter;
    DeviceArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devices);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        listView = findViewById(R.id.list_view);
        adapter = new DeviceArrayAdapter(this);
        listView.setAdapter(adapter);
        bluetoothDevices = bluetoothAdapter.getBondedDevices();

        if (bluetoothDevices.size() > 0){
            for (BluetoothDevice device : bluetoothDevices){
                adapter.add(new DeviceInfo(device.getName(),device.getAddress()));
            }
        }

        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(receiver, filter);

        bluetoothAdapter.startDiscovery();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                String address = adapter.getItem(position).getMacAddress();
                intent.putExtra("Device_Data",bluetoothAdapter.getRemoteDevice(address));
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                adapter.add(new DeviceInfo(device.getName(),device.getAddress()));
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}
