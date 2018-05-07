package com.example.lap10715.demoweek1.demo_broadcast_receiver;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

public class AirplaneModeBR extends BroadcastReceiver {

    private static final String TAG = AirplaneModeBR.class.getSimpleName();
    private boolean airplane_active = false;

    @Override
    public void onReceive(Context context, Intent intent) {

        airplane_active = isAirplaneModeOn(context);
    }

    @SuppressWarnings("deprecation")
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static boolean isAirplaneModeOn(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return Settings.System.getInt(context.getContentResolver(),
                    Settings.System.AIRPLANE_MODE_ON, 0) != 0;
        } else {
            return Settings.Global.getInt(context.getContentResolver(),
                    Settings.Global.AIRPLANE_MODE_ON, 0) != 0;
        }
    }

    public boolean getModeAirplanMode(){
        return airplane_active;
    }
}
