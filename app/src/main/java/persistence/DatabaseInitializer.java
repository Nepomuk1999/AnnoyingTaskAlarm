package persistence;

import android.content.Context;
import android.os.AsyncTask;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class DatabaseInitializer {

    public void initializeDB(Context context){
        InitializeDatabase db = new InitializeDatabase();
        db.execute(context);
    }


    public void populateAsync(Context context) {
        InitializeDatabase db = new InitializeDatabase();
        AnnoyingTaskAlarmDatabase atadb = null;
        try {
            atadb = db.execute(context).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        PopulateDbAsync task = new PopulateDbAsync(atadb);
        task.execute();
    }


    private static void addTask(final AnnoyingTaskAlarmDatabase db, Task task){
        db.taskDao().insertAll(task);
    }

    private static void addAlarm(final AnnoyingTaskAlarmDatabase db, AlarmEntity alarm){
        db.alarmDao().insertAll(alarm);
    }


    private static void populateWithData(AnnoyingTaskAlarmDatabase db) {

        for (int i = 0; i < 6; i++){
            AlarmEntity alarm = new AlarmEntity();
            alarm.setTime("00:00");
            addAlarm(db, alarm);
        }

        Task task1 = new Task();
        task1.setQuestion("15+13");
        task1.setCorrectAnswer("28");
        task1.setLastUsed((int)System.currentTimeMillis());

        addTask(db, task1);

        Task task2 = new Task();
        task2.setQuestion("1+13");
        task2.setCorrectAnswer("14");
        task2.setLastUsed((int)System.currentTimeMillis());

        addTask(db, task2);
    }

    private static class InitializeDatabase extends AsyncTask<Context, Void, AnnoyingTaskAlarmDatabase>{

        @Override
        protected AnnoyingTaskAlarmDatabase doInBackground(Context... contexts) {
            AnnoyingTaskAlarmDatabase db = AnnoyingTaskAlarmDatabase.getInstance(contexts[0]);
            return db;
        }
    }


    private static class PopulateDbAsync extends AsyncTask<Context, Void, Void> {

        private final AnnoyingTaskAlarmDatabase mDb;

        PopulateDbAsync(AnnoyingTaskAlarmDatabase db) {
            mDb = db;
        }

        @Override
        protected Void doInBackground(Context... contexts) {
            System.out.println("DB established");
            populateWithData(mDb);
            return null;
        }
    }
}
