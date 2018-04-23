package handler;

import android.content.Context;

import com.example.home.annoyingtaskalarm.GetAlarmStringsAsynchTask;

import java.util.LinkedList;
import java.util.List;

import persistence.AlarmEntity;
import persistence.AnnoyingTaskAlarmDatabase;

public class AlarmHandler {

    private static AlarmHandler instance;
    private static AnnoyingTaskAlarmDatabase annoyingTaskAlarmDatabase ;

    public static AlarmHandler getInstance(Context context){
        if (instance == null){
            instance = new AlarmHandler();
            annoyingTaskAlarmDatabase = AnnoyingTaskAlarmDatabase.getInstance(context);
        }
        return instance;
    }

    public List<AlarmEntity> getAllAlarms(){
        List<AlarmEntity> alrms = new LinkedList<>();




        return alrms;
    }
}
