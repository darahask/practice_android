package com.example.quakereport;

public class QuakeData {

    private String quakeIntensity;
    private String cityName;
    private String date;
    private String time;
    private String url;

    public QuakeData(String quakeIntensity,String cityName,String date,String time,String url){
        this.quakeIntensity = quakeIntensity;
        this.cityName = cityName;
        this.date = date;
        this.time = time;
        this.url = url;
    }

    public String getQuakeIntensity(){
        return quakeIntensity;
    }

    public String getCityName(){
        return  cityName;
    }

    public String getDate(){
        return date;
    }

    public String getTime(){
        return time;
    }

    public String getUrl(){
        return url;
    }
}
