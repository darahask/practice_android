package com.example.ourchat;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MessageAdapter extends ArrayAdapter<Message> {

    public MessageAdapter(@NonNull Context context, int resource, ArrayList<Message> messages) {
        super(context, resource,messages);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_layout, parent, false);
        }

        ImageView photoImageView =  convertView.findViewById(R.id.photoImageView);
        TextView messageTextView = convertView.findViewById(R.id.messageTextView);
        TextView authorTextView = convertView.findViewById(R.id.nameTextView);

        Message message = getItem(position);

        boolean isPhoto = message.getPhotoUrl() != null;
        if (isPhoto) {
            messageTextView.setVisibility(View.GONE);
            photoImageView.setVisibility(View.VISIBLE);
            Glide.with(photoImageView.getContext())
                    .load(message.getPhotoUrl())
                    .into(photoImageView);
        } else {
            messageTextView.setVisibility(View.VISIBLE);
            photoImageView.setVisibility(View.GONE);
            messageTextView.setText(message.getText());
        }
        authorTextView.setText(message.getName());

        return convertView;
    }
}
