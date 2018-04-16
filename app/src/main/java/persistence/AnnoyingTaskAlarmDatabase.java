package persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.provider.ContactsContract;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Christoph Bauer on 26.03.2018.
 *
 *          ,/
 *        ./(\
 * -`___-'  |`
 * ''-(  -`--)
 *     7/`
 *     \\
 */

@Database(entities = {Task.class}, version = 1)
public abstract class AnnoyingTaskAlarmDatabase extends RoomDatabase {

    private static AnnoyingTaskAlarmDatabase INSTANCE;

    public abstract TaskDao taskDao();

    public static AnnoyingTaskAlarmDatabase getInstance(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AnnoyingTaskAlarmDatabase.class,
                    "AnnoyingTaskAlarmDatabase").allowMainThreadQueries().build();
            fillDatabase();
        }
        return INSTANCE;
    }

    private static void addTask(Task task){
        INSTANCE.taskDao().insertAll(task);
    }

    private static void fillDatabase() {
        List<Task> tasks = new LinkedList<>();

        Task task1 = new Task();
        task1.setQuestion("15+13");
        task1.setCorrectAnswer("28");
        task1.setLastUsed((int)System.currentTimeMillis());

        addTask(task1);

        Task task2 = new Task();
        task2.setQuestion("1+13");
        task2.setCorrectAnswer("14");
        task2.setLastUsed((int)System.currentTimeMillis());

        addTask(task2);


    }

}
