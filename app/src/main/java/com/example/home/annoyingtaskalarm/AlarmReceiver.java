package com.example.home.annoyingtaskalarm;

import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.content.WakefulBroadcastReceiver;

public class AlarmReceiver extends WakefulBroadcastReceiver {

    private static Ringtone ringtone;

    public void onReceive(final Context context, Intent intent) {
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (uri == null) {
            uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        ringtone = RingtoneManager.getRingtone(context, uri);
        ringtone.play();

        openQuestion(context);
    }

    public void openQuestion(Context context) {
        Intent i = new Intent();
        i.setClassName("com.example.home.annoyingtaskalarm", "com.example.home.annoyingtaskalarm.QuestionActivity");
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

    public static void stopRingtone(){
        ringtone.stop();
    }
}
