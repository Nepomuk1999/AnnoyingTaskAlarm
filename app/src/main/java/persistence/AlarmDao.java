package persistence;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface AlarmDao {

    @Query("SELECT * FROM AlarmEntity")
    List<AlarmEntity> getAll();

    @Insert
    void insertAll(AlarmEntity...alarms);

    @Query("UPDATE alarmentity set  time = :newTime where  alarmId = :id")
    void updateTime(int id, int newTime);

    @Query("SELECT COUNT(*) FROM AlarmEntity")
    int countEntries();
}
