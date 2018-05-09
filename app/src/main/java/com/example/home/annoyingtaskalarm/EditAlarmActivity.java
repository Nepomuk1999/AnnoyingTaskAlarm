package com.example.home.annoyingtaskalarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
import java.util.GregorianCalendar;

import handler.AlarmHandler;
import persistence.AlarmEntity;


public class EditAlarmActivity extends AppCompatActivity {

    private static int timeHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    private static int timeMinute = Calendar.getInstance().get(Calendar.MINUTE);
    private TextView alarmTime;
    private static TextView textView2;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private String curTime;
    private AlarmHandler alarmHandler;
    public AlarmEntity alarmEntity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.editalarmactivity);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = getIntent();
        pendingIntent = PendingIntent.getBroadcast(EditAlarmActivity.this, 0, intent, 0);
        alarmEntity = (AlarmEntity) intent.getSerializableExtra("AlarmEntity");
        alarmTime = (TextView)findViewById(R.id.setTimeDigital);
        curTime = String.format("%02d:%02d", timeHour, timeMinute);

        alarmTime.setText(alarmEntity.getTime());

        alarmHandler.getInstance(getApplicationContext());
        System.out.println(alarmEntity.getId()+"--------"+ alarmEntity.getTime());
        textView2 = (TextView)findViewById(R.id.msg2);

        // listener for edit button -> sets/edits alarm
        View.OnClickListener listener1 = new View.OnClickListener() {
            public void onClick(View view) {
                textView2.setText("");
                Bundle bundle = new Bundle();
                bundle.putInt(MyAlarmConstants.HOUR, timeHour );
                bundle.putInt(MyAlarmConstants.MINUTE, timeMinute);
                MyDialogFragment fragment = new MyDialogFragment(new MyHandler());
                fragment.setArguments(bundle);
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(fragment, MyAlarmConstants.TIME_PICKER);
                transaction.commit();

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, timeHour);
                calendar.set(Calendar.MINUTE, timeMinute);

                Intent myIntent = new Intent(EditAlarmActivity.this, AlarmReceiver.class);
                pendingIntent = PendingIntent.getBroadcast(EditAlarmActivity.this, 0, myIntent, 0);
                alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
            }
        };
        Button btnSetAlarmTime = (Button) findViewById(R.id.btnSetAlarmTime);
        btnSetAlarmTime.setOnClickListener(listener1);

        // listener for cancel button -> cancels alarm
        View.OnClickListener listener2 = new View.OnClickListener() {
            public void onClick(View view) {
                textView2.setText("");
                cancelAlarm();
                deleteAlarm(view);
            }
        };
        Button cancelAlarm = (Button) findViewById(R.id.cancelAlarm);
        cancelAlarm.setOnClickListener(listener2);


        Button saveAlarm = (Button) findViewById(R.id.saveAlarm);
        // listener for save button
        saveAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alarmEntity.setTime(curTime);
                alarmHandler = AlarmHandler.getInstance(getApplicationContext());
                alarmHandler.updateAlarm(alarmEntity);
                saveAlarmToList(view);
            }
        });
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
        alarmHandler.updateAlarm(alarmEntity);
        System.out.println("in save AlarmToList" + alarmEntity.getTime());
        startActivity(intent);
    }

    // delete alarm
    public void deleteAlarm(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }



}
