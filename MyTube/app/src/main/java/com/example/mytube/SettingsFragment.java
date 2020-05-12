package com.example.mytube;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class SettingsFragment extends Fragment {

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Top Stories");
        arrayList.add("For you");
        arrayList.add("Favorites");
        arrayList.add("Saved Searches");
        arrayList.add("India");
        arrayList.add("World");
        arrayList.add("Local Stories");
        arrayList.add("Business");
        arrayList.add("Technology");
        arrayList.add("Entertainment");
        arrayList.add("Sports");
        arrayList.add("Science");
        arrayList.add("Health");

        MyAdapter myAdapter = new MyAdapter(getActivity(),arrayList);

        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        ListView listView = view.findViewById(R.id.list_view);
        listView.setAdapter(myAdapter);
        return view;
    }
}
