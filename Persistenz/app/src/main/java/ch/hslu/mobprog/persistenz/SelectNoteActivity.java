package ch.hslu.mobprog.persistenz;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import ch.hslu.mobprog.persistenz.notes.NoteDAO;
import ch.hslu.mobprog.persistenz.notes.NotesDatabase;
import ch.hslu.mobprog.persistenz.notes.NotesDatabaseSingleton;

/**
 * Created by sandr on 19.03.2018.
 */

public class SelectNoteActivity extends ListActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NotesDatabase db = NotesDatabaseSingleton.getInstance(getApplicationContext());
        NoteDAO noteDAO = db.noteDAO();
        Cursor cursor = noteDAO.loadAllNotes();

        final String[] columns = new String[] {"_id", "title", "text"};
        final int[] viewIds = new int[]{R.id.note_id, R.id.note_title, R.id.note_text};

        setListAdapter(new SimpleCursorAdapter(this, R.layout.item_layout, cursor, columns, viewIds, 0));
    }

    public void onClickNoteTitle(View view){
        LinearLayout parent = (LinearLayout)view.getParent();
        TextView txtId = parent.findViewById(R.id.note_id);
        Intent notesActivity = new Intent(this.getBaseContext(), NoteActivity.class);
        notesActivity.putExtra("noteId", Integer.valueOf(txtId.getText().toString()));
        startActivity(notesActivity);
    }
}
