package com.example.home.annoyingtaskalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class BatteryLevelReceiver extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, "Battery Level Low!", Toast.LENGTH_LONG).show();
        Log.e("", "BATTERY LOW!!");

    }
}
