package com.example.home.annoyingtaskalarm;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import handler.AlarmHandler;
import persistence.AlarmEntity;

public class GetAlarmStringsAsynchTask extends AsyncTask<AlarmHandler, List<AlarmEntity>, List<AlarmEntity>> {

    private Context context;

    public GetAlarmStringsAsynchTask(Context context){
        this.context = context;
    }


    @Override
    protected List<AlarmEntity> doInBackground(AlarmHandler... alarmHandlers) {
        AlarmHandler alarmHandler = alarmHandlers[0];
        return alarmHandler.getAllAlarms();
    }

    @Override
    protected void onPostExecute(List<AlarmEntity> alarms){
        if (alarms.isEmpty()){
            AlarmEntity tempAlarm = new AlarmEntity();
            tempAlarm.setId(0);
            tempAlarm.setTime("No alarms have been saved!");
        } else {
            ArrayList<String> temp = new ArrayList<>();
            for (AlarmEntity alarmEntity : alarms){
                temp.add(alarmEntity.getTime());
            }
            String[] current = (String[]) temp.toArray();
            ListView lViewAlarms = MainActivity.getlViewAlarms();

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, current);
            lViewAlarms.setAdapter(adapter);
        }
    }
}
