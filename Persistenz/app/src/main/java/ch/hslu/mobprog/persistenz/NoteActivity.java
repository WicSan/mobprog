package ch.hslu.mobprog.persistenz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import ch.hslu.mobprog.persistenz.notes.*;

/**
 * Created by sandr on 19.03.2018.
 */

public class NoteActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NotesDatabase db = NotesDatabaseSingleton.getInstance(getApplicationContext());
        NoteDAO noteDAO = db.noteDAO();

        int noteId = getIntent().getIntExtra("noteId", 0);
        if(noteId < 0) {
            setContentView(R.layout.activity_edit_note);
        } else{
            setContentView(R.layout.activity_show_note);
            Note note = noteDAO.getNote(noteId);
            TextView note_title = findViewById(R.id.note_title);
            TextView note_text = findViewById(R.id.note_text);
            note_title.setText(note.title);
            note_text.setText(note.text);
        }
    }

    public void onClickSave(View button){
        TextView note_title = findViewById(R.id.note_title);
        TextView note_text = findViewById(R.id.note_text);

        Intent answerData = new Intent();
        answerData.putExtra("title", note_title.getText().toString());
        answerData.putExtra("text", note_text.getText().toString());
        setResult(RESULT_OK, answerData);

        finish();
    }
}
