package persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

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

    public abstract TaskDao taskDao();
}
