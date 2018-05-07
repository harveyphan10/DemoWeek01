package com.example.lap10715.demoweek1.demo_service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.lap10715.demoweek1.R;

import java.util.Timer;
import java.util.TimerTask;

public class PlayMusicActivity extends AppCompatActivity {

    private ProgressBar pbPlayMusic;
    private Button btnhandler;
    private PlayMusicService playMusicService;
    boolean bound = false;
    private Timer autoCheckPlayTimeTimer;
    private boolean isChecked = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);

        pbPlayMusic = findViewById(R.id.pb_play_music);
        btnhandler = findViewById(R.id.btn_handler);

        btnhandler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isChecked = !isChecked;

                if(isChecked){
                    btnhandler.setText("Pause");
                    playMusicService.pause();
                }
                else{
                    btnhandler.setText("Resume");
                    playMusicService.resume();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, PlayMusicService.class);

        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        startService(intent);

        autoCheckPlayTimeTimer = new Timer();
        autoCheckPlayTimeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (bound) {
                    int duration = playMusicService.getDuration();
                    int curPos = playMusicService.getCurrentPosition();
                    pbPlayMusic.setMax(duration);
                    pbPlayMusic.setProgress(curPos);
                }
            }
        }, 1000, 1000);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(serviceConnection);
        bound = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            PlayMusicService.LocalBinder binder = (PlayMusicService.LocalBinder) service;
            playMusicService = binder.getService();
            bound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            bound = false;
        }
    };
}
