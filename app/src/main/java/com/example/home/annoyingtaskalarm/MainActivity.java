package com.example.home.annoyingtaskalarm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.List;

import taskHandler.TaskHandler;

import persistence.AnnoyingTaskAlarmDatabase;


public class MainActivity extends AppCompatActivity {

    private boolean appPropperlyClosed;
    private TaskHandler taskHandler = new TaskHandler().getInstance();
    public static final String EXTRA_MESSAGE = "com.example.annoyingtaskalarm.MESSAGE";
    //private TableLayout testTable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //taskHandler.getInstance();
        System.out.println("in onCreate!!!!");
        //taskHandler.getAllTasks(this);
        //System.out.println("got tasks");
        showContent();
        // setup the table

      // testTable = (TableLayout) findViewById(R.id.dynamicTest);
      // testTable.setStretchAllColumns(true);
      //  showAllAlarms();

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        appPropperlyClosed = true;
    }

    public void showAllAlarms() {

        System.out.println("Works!!!!");
        //Intent intent = getIntent();

    }

    public void showContent() {
        /*Intent intent = new Intent(this, TaskHandler.class);
        TextView editText = (TextView) findViewById(R.id.txtTest);
        String message = taskHandler.getNextTask().getQuestion();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
        */
        System.out.println("Taskhandler: " + taskHandler.getInstance());
        Intent intent = getIntent();
        taskHandler.getAllTasks(this);
        String message = taskHandler.getNextTask().getQuestion();
        System.out.println("Question is: " + message);
        intent.putExtra(EXTRA_MESSAGE, message);
        String question = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = findViewById(R.id.txtViewTest);
        textView.setText(question);
    }
}
