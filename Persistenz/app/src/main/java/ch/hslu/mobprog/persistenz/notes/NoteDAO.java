package ch.hslu.mobprog.persistenz.notes;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.Cursor;

/**
 * Created by sandr on 19.03.2018.
 */
@Dao
public interface NoteDAO {
    @Insert
    public long[] insertNotes(Note... notes);

    @Update
    public int updateNotes(Note... notes);

    @Delete
    public int deletNotes(Note... notes);

    @Query("SELECT * FROM notes")
    public Cursor loadAllNotes();

    @Query("SELECT * FROM notes WHERE _id=:id")
    public Note getNote(int id);
}
