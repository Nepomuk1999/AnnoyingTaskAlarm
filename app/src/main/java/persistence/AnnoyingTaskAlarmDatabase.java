package persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

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
        }
        return INSTANCE;
    }

}
