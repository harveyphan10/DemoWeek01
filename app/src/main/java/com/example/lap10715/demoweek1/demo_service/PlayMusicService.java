package com.example.lap10715.demoweek1.demo_service;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.lap10715.demoweek1.R;

public class PlayMusicService extends Service {

    private final IBinder idBinder = new LocalBinder();
    private MediaPlayer player;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        player.start();
        return idBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        player = MediaPlayer.create(this, R.raw.my_music);
        player.setVolume(100, 100);
    }


    public int onStartCommand(Intent intent, int flags, int startId) {
        player.start();
        return START_NOT_STICKY;
    }


    @Override
    public void onDestroy() {
        player.stop();
        player.release();
    }

    public int getDuration(){
        return player.getDuration();
    }

    public int getCurrentPosition(){
        return player.getCurrentPosition();
    }

    public void pause(){
        player.pause();
    }

    public void resume(){
        int length = player.getCurrentPosition();
        player.seekTo(length);
        player.start();
    }

    public class LocalBinder extends Binder {
        PlayMusicService getService(){
            return PlayMusicService.this;
        }
    }
}
