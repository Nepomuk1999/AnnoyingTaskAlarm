package com.example.home.annoyingtaskalarm;

import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.content.WakefulBroadcastReceiver;

import handler.TaskHandler;

public class AlarmReceiver extends WakefulBroadcastReceiver{

    public void onReceive(final Context context, Intent intent) {
        // POP-UP HERE!!!!!!!!!
        TaskHandler taskHandler = TaskHandler.getInstance(context);
        taskHandler.nextTask(context);
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        Ringtone ringtone = RingtoneManager.getRingtone(context, uri);
        ringtone.play();
    }
}
