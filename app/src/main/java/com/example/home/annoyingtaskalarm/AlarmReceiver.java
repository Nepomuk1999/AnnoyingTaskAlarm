package com.example.home.annoyingtaskalarm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.WakefulBroadcastReceiver;

import handler.TaskHandler;

public class AlarmReceiver extends WakefulBroadcastReceiver implements PopupDialog.PopupDialogListener {

    private String answerFromDialog;

    public void onReceive(final Context context, Intent intent) {
        TaskHandler taskHandler = TaskHandler.getInstance(context);
        taskHandler.nextTask(context);
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (uri == null) {
            uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        Ringtone ringtone = RingtoneManager.getRingtone(context, uri);
        ringtone.play();

        // pass context here to display question in popupQuestion-class?
        openDialog(context);

        // to check the answer from the dialog we can use answerFromDialog and compare this with the db answer here!!
    }



    public void openDialog(Context context) {
        PopupDialog popupDialog = new PopupDialog();
        FragmentActivity activity = (FragmentActivity) context;
        // TODO: we need getSupportFragmentManager() method so that popupQuestion will work!!!
        popupDialog.show(activity.getSupportFragmentManager(), "answerQuestion");


    }

    @Override
    public void sendAnswer(String answer) {
        answerFromDialog = answer;
    }

}
