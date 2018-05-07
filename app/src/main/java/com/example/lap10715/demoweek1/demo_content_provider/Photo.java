package com.example.lap10715.demoweek1.demo_content_provider;


import android.net.Uri;
import android.provider.MediaStore;
import java.io.Serializable;

public class Photo implements Serializable {
    private long idImg;
    private String pathUrl;
    private String dateTaken;
    private static final Uri EXTERNAL_URI = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;


    public Photo(long idImg, String dateTaken) {
        this.idImg = idImg;
        this.dateTaken = dateTaken;
        this.pathUrl = Uri.withAppendedPath(EXTERNAL_URI, Long.toString(idImg)).toString();
    }

    public long getIdImg() {
        return idImg;
    }

    public void setIdImg(long idImg) {
        this.idImg = idImg;
    }

    public String getDateTaken() {
        return dateTaken;
    }

    public void setDateTaken(String dateTaken) {
        this.dateTaken = dateTaken;
    }

    public String getUrl() {
        return this.pathUrl;
    }

}