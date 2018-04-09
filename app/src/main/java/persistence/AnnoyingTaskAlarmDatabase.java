package persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

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
                    "AnnoyingTaskAlarmDatabase").build();
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

        addTask(task1);

        Task task2 = new Task();
        task1.setQuestion("1+13");
        task1.setCorrectAnswer("14");

        addTask(task2);


    }

}
