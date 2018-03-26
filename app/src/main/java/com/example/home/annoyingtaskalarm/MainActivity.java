package com.example.home.annoyingtaskalarm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import persistence.AnnoyingTaskAlarmDatabase;
import taskHandler.TaskHandler;


public class MainActivity extends AppCompatActivity {

    private boolean appPropperlyClosed;
    private TaskHandler taskHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        taskHandler.getInstance();
        taskHandler.getAllTasks(this);



    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        appPropperlyClosed = true;
    }
}
