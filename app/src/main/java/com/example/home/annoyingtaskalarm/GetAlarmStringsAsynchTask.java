package com.example.home.annoyingtaskalarm;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
        List<AlarmEntity> alarms = AlarmHandler.getAll();
        return alarms;
    }

    @Override
    protected void onPostExecute(List<AlarmEntity> alarms){
        ListView lViewAlarms = MainActivity.getlViewAlarms();

        ArrayAdapter<AlarmEntity> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, alarms);
        lViewAlarms.setAdapter(adapter);
    }
}
