package persistence;

import android.content.Context;
import android.os.AsyncTask;

public class DatabaseInitializer {

    public static DatabaseInitializer INSTANCE;
    public boolean isPopulated = false;

    public static DatabaseInitializer getInstance() {
        if (INSTANCE == null){
            INSTANCE = new DatabaseInitializer();
        }
        return INSTANCE;
    }

    public void initializeDB(Context context){
        if (!isPopulated) {
            InitializeDatabase db = new InitializeDatabase();
            db.execute(context);
            populateAsync(context);
            isPopulated = true;
        }
    }

    private void populateAsync(Context context) {
        AnnoyingTaskAlarmDatabase atadb = AnnoyingTaskAlarmDatabase.getInstance(context);
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
        if(db.alarmDao().countEntries() == 0) {

            for (int i = 0; i < 6; i++) {
                AlarmEntity alarm = new AlarmEntity();
                alarm.setTime("00:00");
                addAlarm(db, alarm);
            }

            Task task1 = new Task();
            task1.setQuestion("15+13");
            task1.setCorrectAnswer("28");
            task1.setLastUsed((int) System.currentTimeMillis());

            addTask(db, task1);

            Task task2 = new Task();
            task2.setQuestion("1+13");
            task2.setCorrectAnswer("14");
            task2.setLastUsed((int) System.currentTimeMillis());

            addTask(db, task2);
        }
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
