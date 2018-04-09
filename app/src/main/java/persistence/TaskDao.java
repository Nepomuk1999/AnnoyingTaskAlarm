package persistence;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

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

@Dao
public interface TaskDao {

    @Query("SELECT * FROM task")
    List<Task> getAll();

    @Query("SELECT * FROM task WHERE lastUsed = (SELECT min(lastUsed) FROM task)")
    Task getNext();

    @Query("UPDATE task set lastUsed = :newTime where taskId = :id")
    void updateTime(int id, int newTime);

    @Insert
    void insertAll(Task...tasks);

    @Delete
    void delete(Task task);
}
