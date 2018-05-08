package persistence;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class AlarmEntity implements Serializable{

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "alarmId")
    private int id;

    @ColumnInfo(name = "Time")
    private String time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString(){
        return time;
    }
}
