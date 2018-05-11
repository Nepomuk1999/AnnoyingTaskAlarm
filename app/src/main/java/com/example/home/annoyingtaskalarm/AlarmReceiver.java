package com.example.home.annoyingtaskalarm;

import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.content.WakefulBroadcastReceiver;

import handler.TaskHandler;

public class AlarmReceiver extends WakefulBroadcastReceiver implements PopupDialog.PopupDialogListener {

    private String answerFromDialog;
    private static Ringtone ringtone;

    public void onReceive(final Context context, Intent intent) {
        TaskHandler taskHandler = TaskHandler.getInstance(context);
        taskHandler.nextTask(context);
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (uri == null) {
            uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        ringtone = RingtoneManager.getRingtone(context, uri);
        ringtone.play();

        // pass context here to display question in popupQuestion-class?
        //openDialog(context);
        openQuestion(context);

        // to check the answer from the dialog we can use answerFromDialog and compare this with the db answer here!!

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



    @Override
    public void sendAnswer(String answer) {
        answerFromDialog = answer;
    }

}
