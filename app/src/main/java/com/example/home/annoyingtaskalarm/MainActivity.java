package com.example.home.annoyingtaskalarm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    private boolean appPropperlyClosed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        appPropperlyClosed = true;
    }
}
