package ch.hslu.mobprog.persistenz.notes;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

/**
 * Created by sandr on 19.03.2018.
 */

@Database(entities = {Note.class}, version = 1, exportSchema = false)
@TypeConverters({Converter.class})
public abstract class NotesDatabase extends RoomDatabase {
    public abstract NoteDAO noteDAO();
}
