package com.example.home.annoyingtaskalarm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import persistence.DatabaseInitializer;

import android.view.View;


public class MainActivity extends AppCompatActivity {

    private boolean appPropperlyClosed;
    //private TaskHandler taskHandler = new TaskHandler().getInstance(this);
    public static final String EXTRA_MESSAGE = "com.example.annoyingtaskalarm.MESSAGE";
    private String[] allAlarms;
    private ArrayAdapter<String> adapter;
    //private TableLayout testTable;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("in onCreate!!!!");
        DatabaseInitializer initializer = new DatabaseInitializer();
        initializer.initializeDB(getApplicationContext());
        initializer.populateAsync(getApplicationContext());
        //get all Alarms from db
        allAlarms = new String[] {"alarm 1", "alarm 2", "alarm 3"};

        ListView lv = (ListView) findViewById(R.id.lViewAllAlarms);

        // possible to get all alarms from db by using the method
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, allAlarms);


        //showContent();
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
}
