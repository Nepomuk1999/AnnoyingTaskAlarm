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

            Task task3 = new Task();
            task3.setQuestion("25-8");
            task3.setCorrectAnswer("17");
            task3.setLastUsed((int) System.currentTimeMillis());

            addTask(db, task3);

            Task task4 = new Task();
            task4.setQuestion("60+40");
            task4.setCorrectAnswer("100");
            task4.setLastUsed((int) System.currentTimeMillis());

            addTask(db, task4);

            Task task5 = new Task();
            task5.setQuestion("78-34");
            task5.setCorrectAnswer("44");
            task5.setLastUsed((int) System.currentTimeMillis());

            addTask(db, task5);

            Task task6 = new Task();
            task6.setQuestion("89+5");
            task6.setCorrectAnswer("94");
            task6.setLastUsed((int) System.currentTimeMillis());

            addTask(db, task6);

            Task task7 = new Task();
            task7.setQuestion("98-75+12-5");
            task7.setCorrectAnswer("30");
            task7.setLastUsed((int) System.currentTimeMillis());

            addTask(db, task7);

            Task task8 = new Task();
            task8.setQuestion("30+50-28-3+19");
            task8.setCorrectAnswer("68");
            task8.setLastUsed((int) System.currentTimeMillis());

            addTask(db, task8);
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
