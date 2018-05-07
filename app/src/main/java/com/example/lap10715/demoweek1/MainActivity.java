package com.example.lap10715.demoweek1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.lap10715.demoweek1.demo_activity.DemoStartActivity;
import com.example.lap10715.demoweek1.demo_broadcast_receiver.ShowAirplaneModeActivity;
import com.example.lap10715.demoweek1.demo_content_provider.ShowImageActivity;
import com.example.lap10715.demoweek1.demo_service.PlayMusicActivity;
import com.example.lap10715.demoweek1.demo_service.PlayMusicService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int MY_PERMISSION_EXTERNAL_STORAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                Toast.makeText(this, "Application have to need permission to access photo!",
                        Toast.LENGTH_SHORT).show();

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSION_EXTERNAL_STORAGE);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSION_EXTERNAL_STORAGE);
            }
        } else {
            initViews();
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSION_EXTERNAL_STORAGE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    initViews();
                } else {
                    Toast.makeText(this, "Application don't access any data to show!",
                            Toast.LENGTH_SHORT).show();
                    finish();
                }
        }
    }

    private void initViews() {
        // Find layouts.
        setOnClick(R.id.btn_start_activity,
                R.id.btn_play_music, R.id.btn_show_img, R.id.btn_start_airplane_br);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start_activity:
                Intent intent = new Intent(this, DemoStartActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_play_music:
                Intent intentMusic = new Intent(this, PlayMusicActivity.class);
                startActivity(intentMusic);
                break;

            case R.id.btn_show_img:
                Intent intentImg = new Intent(this, ShowImageActivity.class);
                startActivity(intentImg);
                break;

            case R.id.btn_start_airplane_br:
                Intent intentAirplane = new Intent(this, ShowAirplaneModeActivity.class);
                startActivity(intentAirplane);
                break;
        }
    }

    private void setOnClick(int... ids) {
        for (int id : ids) {
            findViewById(id).setOnClickListener(this);
        }
    }
}
