package handler;

import android.content.Context;
import android.os.AsyncTask;

import com.example.home.annoyingtaskalarm.GetAlarmStringsAsynchTask;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import persistence.AlarmEntity;
import persistence.AnnoyingTaskAlarmDatabase;

public class AlarmHandler {

    private static AlarmHandler instance;
    private static AnnoyingTaskAlarmDatabase annoyingTaskAlarmDatabase ;
    private static Context context;

    public static AlarmHandler getInstance(Context context) {
        if (instance == null) {
            instance = new AlarmHandler();
            annoyingTaskAlarmDatabase = AnnoyingTaskAlarmDatabase.getInstance(context);
            setContext(context);
        }
        return instance;
    }

    public static void setContext(Context context) {
        AlarmHandler.context = context;
    }

    public List<AlarmEntity> getAllAlarms(){
        List<AlarmEntity> alarms = new LinkedList<>();
        GetAlarmStringsAsynchTask getAlarmStringsAsynchTask = new GetAlarmStringsAsynchTask(context);
        try {
            alarms = getAlarmStringsAsynchTask.execute(this).get(1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return alarms;
    }


    public static List<AlarmEntity> getAll() {
        return annoyingTaskAlarmDatabase.alarmDao().getAll();
    }

    public  void updateAlarm(AlarmEntity alarmEntity){
        new UpdateAsynchTask().execute(alarmEntity);
    }

    public class UpdateAsynchTask extends AsyncTask<AlarmEntity, AnnoyingTaskAlarmDatabase, AlarmEntity>{


        @Override
        protected AlarmEntity doInBackground(AlarmEntity... alarmEntities) {
            AlarmEntity entity = alarmEntities[0];
            annoyingTaskAlarmDatabase.alarmDao().updateTime(entity.getId(), entity.getTime());

            return null;
        }
    }
}
