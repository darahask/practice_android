package com.example.custom;

import android.app.Activity;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactsAdapter extends ArrayAdapter<Contacts>{

    public ContactsAdapter(Activity context, ArrayList<Contacts> contactsArrayList){

        super(context,0,contactsArrayList);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View listItemView = convertView;
        if(listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_contacts,parent,false);
        }

        final Contacts currentContact = getItem(position);

        TextView myText = listItemView.findViewById(R.id.contact_name);
        myText.setText(currentContact.getName());

        TextView myText1 = listItemView.findViewById(R.id.contact_number);
        myText1.setText(currentContact.getNumber());

        ImageView imageView = listItemView.findViewById(R.id.contact_icon);
        imageView.setImageResource(currentContact.getImageId());

        return listItemView;
    }

}
