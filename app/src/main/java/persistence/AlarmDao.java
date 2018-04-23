package persistence;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface AlarmDao {

    @Query("SELECT * FROM AlarmEntity")
    List<Task> getAll();
}
