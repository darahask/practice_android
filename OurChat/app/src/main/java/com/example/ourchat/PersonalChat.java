package com.example.ourchat;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PersonalChat extends Fragment {

    private DatabaseReference mMessagesDatabaseReference;
    private ChildEventListener childEventListener;
    private ArrayAdapter<String> arrayAdapter;
    private FloatingActionButton floatingActionButton;
    private Map<String,String> users;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal, container, false);

        arrayAdapter = new ArrayAdapter<>(getContext(),R.layout.support_simple_spinner_dropdown_item);
        ListView listView = view.findViewById(R.id.list_view);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getContext(),ChatUI.class);
                String name = arrayAdapter.getItem(position);
                String myName = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getDisplayName();
                i.putExtra("TransactionId",myName+name);
                i.putExtra("TransactionId2",name+myName);
                startActivity(i);
            }
        });

        final ProgressBar progressBar = view.findViewById(R.id.progbar);

        //DATABASE
        users = new HashMap<>();
        mMessagesDatabaseReference = FirebaseDatabase.getInstance().getReference().child("users");
        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                progressBar.setVisibility(View.GONE);
                String name = dataSnapshot.getValue(String.class);
                assert name != null;
                users.put(name,"Username");
                arrayAdapter.clear();
                for(String keys : users.keySet()){
                    if(!keys.equalsIgnoreCase(Objects.
                            requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getDisplayName()))
                    arrayAdapter.add(keys);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        //DATABASE

        floatingActionButton = view.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMessagesDatabaseReference.addChildEventListener(childEventListener);
                Toast.makeText(getActivity(),"Click on the user to chat",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mMessagesDatabaseReference.addChildEventListener(childEventListener);
    }
}
