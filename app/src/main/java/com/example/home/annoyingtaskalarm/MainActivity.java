package com.example.home.annoyingtaskalarm;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import handler.AlarmHandler;
import handler.TaskHandler;
import persistence.AlarmEntity;
import persistence.DatabaseInitializer;

import android.view.View;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private boolean appPropperlyClosed;
    public static final String EXTRA_MESSAGE = "com.example.annoyingtaskalarm.MESSAGE";
    private static ListView lViewAlarms;
    private AlarmHandler alarmHandler;
    private ArrayAdapter<AlarmEntity> adapter;
    //private TableLayout testTable;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("in onCreate!!!!");

        lViewAlarms = (ListView) findViewById(R.id.lViewAllAlarms);

        // possible to get all alarms from db by using the
        //adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, allAlarms);
        //lViewAlarms.setAdapter(adapter);

        alarmHandler = alarmHandler.getInstance(this.getApplicationContext());
        List<AlarmEntity> alarms = alarmHandler.getAllAlarms();
        ListView lViewAlarms = MainActivity.getlViewAlarms();
        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, alarms);
        lViewAlarms.setAdapter(adapter);

        lViewAlarms.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), AlarmActivity.class);
                intent.putExtra("alarm", adapter.getItemId(position));
                startActivity(intent);
            }
        });

        //showAllAlarms();

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        appPropperlyClosed = true;

    }

    public void showAllAlarms() {


        /*System.out.println("Taskhandler: " + taskHandler.getInstance());
        Intent intent = getIntent();
        Task currentTask = taskHandler.nextTask(this);
        String message =currentTask.getQuestion();
        System.out.println("Question is: " + message);
        intent.putExtra(EXTRA_MESSAGE, message);
        String question = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = findViewById(R.id.editText);
        textView.setText(question);
        */


    }

    public void addNewAlarm(View view) {
        Intent intent = new Intent(this, AlarmActivity.class);
        startActivity(intent);
    }



    public static ListView getlViewAlarms() {
        return lViewAlarms;
    }


}
