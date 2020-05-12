package com.example.custom;

public class Contacts {

    private String mName;
    private String mNumber;
    private int imageResourceId;
    private int songResourceId;

    public Contacts(String name,String number,int imageId, int songId){
        mName = name;
        mNumber = number;
        imageResourceId = imageId;
        songResourceId = songId;
    }

    public String getName(){ return mName; }

    public String getNumber(){
        return mNumber;
    }

    public int getImageId(){
        return imageResourceId;
    }

    public int getSongId(){ return songResourceId;}
}
