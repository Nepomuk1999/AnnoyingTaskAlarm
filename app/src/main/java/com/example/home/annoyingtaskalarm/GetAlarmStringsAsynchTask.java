package com.example.home.annoyingtaskalarm;

import android.os.AsyncTask;

import java.util.List;

import handler.AlarmHandler;
import persistence.AlarmEntity;

public class GetAlarmStringsAsynchTask extends AsyncTask<AlarmHandler, List<AlarmEntity>, List<AlarmEntity>> {


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
            //TODO implement update of alarms
        }
    }
}
