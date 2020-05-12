package com.example.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class QuakeDataAdapter extends ArrayAdapter<QuakeData> {

    public  QuakeDataAdapter(Context context, ArrayList<QuakeData> quakeDataArrayList){

        super(context,0,quakeDataArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        final String LOCATION_SEPARATOR = " of ";

        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        QuakeData currentQuakeData = getItem(position);
        String original = currentQuakeData.getCityName();
        String primaryLocation;
        String locationOffset;

        if(original.contains(LOCATION_SEPARATOR)){
            String[] parts = original.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        }
        else
        {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = original;
        }

        TextView textView1,textView2,textView3,textView4,textView21;
        textView1 = listItemView.findViewById(R.id.quake_intensity);
        textView1.setText(currentQuakeData.getQuakeIntensity());
        textView2 = listItemView.findViewById(R.id.city_dist);
        textView2.setText(locationOffset);
        textView21 = listItemView.findViewById(R.id.city_name);
        textView21.setText(primaryLocation);
        textView3 = listItemView.findViewById(R.id.date);
        textView3.setText(currentQuakeData.getDate());
        textView4 = listItemView.findViewById(R.id.time);
        textView4.setText(currentQuakeData.getTime());

        GradientDrawable magnitudeCircle = (GradientDrawable) textView1.getBackground();
        int magnitudeColor = getMagnitudeColor(Double.parseDouble(currentQuakeData.getQuakeIntensity()));
        magnitudeCircle.setColor(magnitudeColor);

        return  listItemView;
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

}
