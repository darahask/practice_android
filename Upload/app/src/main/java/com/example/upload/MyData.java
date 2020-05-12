package com.example.upload;

public class MyData {

    private String imageUri;
    private String link;
    private String title;

    public MyData() {
    }

    public MyData(String imageUri, String link, String title) {
        this.imageUri = imageUri;
        this.link = link;
        this.title = title;
    }

    public String getImageUri() {
        return imageUri;
    }

    public String getLink() {
        return link;
    }

    public String getTitle() {
        return title;
    }
}
