package com.example.lap10715.demoweek1.demo_broadcast_receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.lap10715.demoweek1.R;

public class ShowAirplaneModeActivity extends AppCompatActivity {

    private  BroadcastReceiver airplaneModeBR;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_airplane_mode);

        final TextView tvShowResult = findViewById(R.id.tv_show_mode_result);


        airplaneModeBR = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                boolean mode = AirplaneModeBR.isAirplaneModeOn(context);
                if(mode){
                    tvShowResult.setText("Airplane mode on");
                }
                else{
                    tvShowResult.setText("Airplane mode off");
                }
            }
        };

        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        this.registerReceiver(airplaneModeBR, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(airplaneModeBR);
    }
}
