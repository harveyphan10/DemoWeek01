package com.example.lap10715.demoweek1.demo_content_provider;

import java.io.Serializable;
import java.util.ArrayList;

public class ListPhotoSameDate implements Serializable {
    private ArrayList<Photo> lstPhotoHaveSameDate;
    private String date;

    public ListPhotoSameDate(String date) {
        this.date = date;
        this.lstPhotoHaveSameDate = new ArrayList<>();
    }

    public ArrayList<Photo> getLstPhotoHaveSameDate() {
        return lstPhotoHaveSameDate;
    }

    public void setLstPhotoHaveSameDate(ArrayList<Photo> lstPhotoHaveSameDate) {
        this.lstPhotoHaveSameDate = lstPhotoHaveSameDate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void addPhoto(Photo photo){
        lstPhotoHaveSameDate.add(photo);
    }

}
