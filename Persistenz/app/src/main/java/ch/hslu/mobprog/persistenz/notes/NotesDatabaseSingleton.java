package ch.hslu.mobprog.persistenz.notes;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.net.Uri;

/**
 * Created by sandr on 19.03.2018.
 */

public class NotesDatabaseSingleton {
    private static NotesDatabase db = null;

    private NotesDatabaseSingleton(){
    }

    public static NotesDatabase getInstance(Context context){
        if(db == null) {
           RoomDatabase.Builder<NotesDatabase> builder = Room.databaseBuilder(
                    context,
                    NotesDatabase.class, "notes");
           builder.allowMainThreadQueries();
           db = builder.build();
        }

        return db;
    }
}
