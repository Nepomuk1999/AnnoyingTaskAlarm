package com.example.home.annoyingtaskalarm;

import java.util.Calendar;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;


public class AlarmActivity extends FragmentActivity{

    private static int timeHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    private static int timeMinute = Calendar.getInstance().get(Calendar.MINUTE);
    private TextView alarmTime;
    private static TextView textView2;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private String curTime;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarmactivity_main);
        alarmTime = (TextView)findViewById(R.id.showTimeDigital);
        curTime = String.format("%02d:%02d", timeHour, timeMinute);
        alarmTime.setText(curTime);
        textView2 = (TextView)findViewById(R.id.msg2);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(AlarmActivity.this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(AlarmActivity.this, 0, intent, 0);

        // listener for edit button -> sets/edits alarm
        OnClickListener listener1 = new OnClickListener() {
            public void onClick(View view) {
                textView2.setText("");
                Bundle bundle = new Bundle();
                bundle.putInt(MyAlarmConstants.HOUR, timeHour);
                bundle.putInt(MyAlarmConstants.MINUTE, timeMinute);
                MyDialogFragment fragment = new MyDialogFragment(new MyHandler());
                fragment.setArguments(bundle);
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(fragment, MyAlarmConstants.TIME_PICKER);
                transaction.commit();
            }
        };
        Button btnSetAlarmTime = (Button) findViewById(R.id.btnSetAlarmTime);
        btnSetAlarmTime.setOnClickListener(listener1);

        // listener for cancel button -> cancels alarm
        OnClickListener listener2 = new OnClickListener() {
            public void onClick(View view) {
                textView2.setText("");
                cancelAlarm();
                deleteAlarm(view);
            }
        };
        Button cancelAlarm = (Button) findViewById(R.id.cancelAlarm);
        cancelAlarm.setOnClickListener(listener2);

        // listener for save button
        OnClickListener listener3 = new OnClickListener() {
            public void onClick(View view) {
                // TODO: Save alarm data to db here or in saveAlarmToList method

                System.out.println("Index: 1" );
                System.out.println("Time: " + curTime);

                saveAlarmToList(view);
            }
        };
        Button saveAlarm = (Button) findViewById(R.id.saveAlarm);
        saveAlarm.setOnClickListener(listener3);
    }

    public static TextView getTextView2() {
        return textView2;
    }

    // set alarm time
    public void setAlarmTime(View view) {
        Bundle bundle = new Bundle();
        bundle.putInt(MyAlarmConstants.HOUR, timeHour);
        bundle.putInt(MyAlarmConstants.MINUTE, timeMinute);
        MyDialogFragment fragment = new MyDialogFragment(new MyHandler());
        fragment.setArguments(bundle);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(fragment, MyAlarmConstants.TIME_PICKER);
        transaction.commit();
    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage (Message msg){
            Bundle bundle = msg.getData();
            timeHour = bundle.getInt(MyAlarmConstants.HOUR);
            timeMinute = bundle.getInt(MyAlarmConstants.MINUTE);
            curTime = String.format("%02d:%02d", timeHour, timeMinute);
            alarmTime.setText(curTime);
            setAlarm();
        }
    }

    private void setAlarm(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, timeHour);
        calendar.set(Calendar.MINUTE, timeMinute);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }

    // canceling alarm
    private void cancelAlarm() {
        if (alarmManager!= null) {
            alarmManager.cancel(pendingIntent);
        }
    }

    // save alarm
    public void saveAlarmToList(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        //System.out.println(alarmManager);
        //alarmManager.cancel(pendingIntent);
    }

    // delete alarm
    public void deleteAlarm(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
