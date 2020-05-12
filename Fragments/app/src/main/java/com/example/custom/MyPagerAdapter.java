package com.example.custom;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyPagerAdapter extends FragmentPagerAdapter {

    private String[] tabTitles = new String[]{"Tab1","Tab2"};

    public MyPagerAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position){
            if(position == 0)
                return new NumbersFragment();
            else
                return new PeopleFragment();
    }

    @Override
    public int getCount(){
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position){
        return tabTitles[position];
    }


}
