package com.example.home.annoyingtaskalarm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import handler.AlarmHandler;
import persistence.AlarmEntity;
import android.view.View;
import java.io.Serializable;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private boolean appPropperlyClosed;
    private static ListView lViewAlarms;
    private AlarmHandler alarmHandler;
    private ArrayAdapter<AlarmEntity> adapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("in onCreate!!!!");

        lViewAlarms = (ListView) findViewById(R.id.lViewAllAlarms);

        alarmHandler = alarmHandler.getInstance(this.getApplicationContext());
        List<AlarmEntity> alarms = alarmHandler.getAllAlarms();
        ListView lViewAlarms = MainActivity.getlViewAlarms();
        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, alarms);
        lViewAlarms.setAdapter(adapter);

        lViewAlarms.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), EditAlarmActivity.class);
                intent.putExtra("AlarmEntity",(Serializable) adapter.getItem(position));
                startActivity(intent);

            }
        });

        Button btnInvite = (Button) findViewById(R.id.btnInvite);
        // listener for invite somebody over whatsapp button
        btnInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Checkout my Annoying alarm APP!!!");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        appPropperlyClosed = true;
    }

    public void addNewAlarm(View view) {
        Intent intent = new Intent(this, EditAlarmActivity.class);
        startActivity(intent);
    }

    public static ListView getlViewAlarms() {
        return lViewAlarms;
    }
}
