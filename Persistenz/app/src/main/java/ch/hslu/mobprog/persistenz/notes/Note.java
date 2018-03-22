package ch.hslu.mobprog.persistenz.notes;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;


/**
 * Created by sandr on 19.03.2018.
 */

@Entity(tableName = "notes")
public class Note {
    @PrimaryKey(autoGenerate = true)
    public long _id;

    @ColumnInfo(index = true)
    public String title;
    public String text;
    public Date modificationDate;
}
