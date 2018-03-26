package com.example.home.annoyingtaskalarm;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import persistence.AnnoyingTaskAlarmDatabase;

public class MainActivity extends AppCompatActivity {

    private AnnoyingTaskAlarmDatabase annoyingTaskAlarmDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        annoyingTaskAlarmDatabase = AnnoyingTaskAlarmDatabase.getInstance(this);
    }
}
