package com.example.lap10715.demoweek1.demo_content_provider;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.lap10715.demoweek1.R;

import java.util.ArrayList;
import java.util.Date;

public class ShowImageActivity extends AppCompatActivity {

    private static final Uri EXTERNAL_URI = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
    private static final String ID = MediaStore.Images.ImageColumns._ID;
    private static final String DATE_TAKEN = MediaStore.Images.ImageColumns.DATE_TAKEN;
    private static final String BUCKET_ID = MediaStore.Images.Media.BUCKET_ID;
    private static final String BUCKET_NAME = MediaStore.Images.Media.BUCKET_DISPLAY_NAME;

    private static final String DATA = MediaStore.Images.Media.DATA;
    private static final String[] IMAGE_PROJECTION_ALBUM =
            new String[]{
                    ID, DATE_TAKEN, BUCKET_NAME, BUCKET_ID, DATA
            };

    private ArrayList<ListPhotoSameDate> listPhotoByDate = new ArrayList<>();
    private ArrayList<Photo> listPhoto = new ArrayList<>();

    private RecyclerView recyclerView;
    private AdapterRecyclerView adapterRecyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_img);

        loadData();

        recyclerView = findViewById(R.id.rv_show_img);

        linearLayoutManager = new LinearLayoutManager(this);
        adapterRecyclerView = new AdapterRecyclerView(this, listPhotoByDate);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterRecyclerView);
    }

    public void loadData() {
        listPhoto.clear();

        // Cursor for query images.
        Cursor imgCursor = null;
        String SORT_ORDER = " DESC";

        imgCursor = this.getContentResolver().query(EXTERNAL_URI,
                IMAGE_PROJECTION_ALBUM, null, null, DATE_TAKEN + SORT_ORDER);

        final int idIndex = imgCursor.getColumnIndex(ID);
        final int dateIndex = imgCursor.getColumnIndex(DATE_TAKEN);

        ListPhotoSameDate lstPhoto = null;
        String date = null;

        while (imgCursor.moveToNext()) {
            final long id = imgCursor.getLong(idIndex);
            final long dateTaken = imgCursor.getLong(dateIndex);

            date = new Date(dateTaken).toString();
            date = date.substring(date.indexOf(" ") + 1, date.indexOf(" ") + 7) + " "
                    + date.substring(date.lastIndexOf(" ") + 1);

            Photo curPhoto = new Photo(id, date);
            listPhoto.add(curPhoto);

        }
        imgCursor.close();
        convertListPhoto2ListPhotoSameDate(listPhoto);
    }

    public void convertListPhoto2ListPhotoSameDate(ArrayList<Photo> listPhoto) {
        listPhotoByDate.clear();
        for (Photo photo : listPhoto) {
            ListPhotoSameDate curListPhotoByDate = checkDate(listPhotoByDate, photo.getDateTaken());
            if (curListPhotoByDate == null) {
                curListPhotoByDate = new ListPhotoSameDate(photo.getDateTaken());
                curListPhotoByDate.addPhoto(photo);
                listPhotoByDate.add(curListPhotoByDate);
            } else {
                curListPhotoByDate.addPhoto(photo);
            }
        }
    }

    private ListPhotoSameDate checkDate(ArrayList<ListPhotoSameDate> lstPhoto, String date) {
        for (ListPhotoSameDate lstPhotoItem : lstPhoto) {
            if (lstPhotoItem.getDate().equals(date)) {
                return lstPhotoItem;
            }
        }
        return null;
    }
}
