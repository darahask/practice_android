package com.example.bluetoothapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class DeviceArrayAdapter extends ArrayAdapter<DeviceInfo> {

    public DeviceArrayAdapter(@NonNull Context context) {
        super(context,0);
    }

    @Override
    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        View twoLineListView = convertView;

        if(twoLineListView == null) {
            twoLineListView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
        }

        DeviceInfo device = getItem(position);

        TextView textView1,textView2;
        textView1 = twoLineListView.findViewById(android.R.id.text1);
        textView2 = twoLineListView.findViewById(android.R.id.text2);
        textView1.setText(device.getName());
        textView2.setText(device.getMacAddress());

        return twoLineListView;
    }
}
