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
    public abstract AlarmDao alarmDao();

    public static AnnoyingTaskAlarmDatabase getInstance(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AnnoyingTaskAlarmDatabase.class,
                    "AnnoyingTaskAlarmDatabase").build();

        }
        return INSTANCE;
    }


}
