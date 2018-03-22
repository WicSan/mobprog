package ch.hslu.mobprog.persistenz;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Arrays;

import ch.hslu.mobprog.persistenz.notes.Note;
import ch.hslu.mobprog.persistenz.notes.NoteDAO;
import ch.hslu.mobprog.persistenz.notes.NotesDatabase;
import ch.hslu.mobprog.persistenz.notes.NotesDatabaseSingleton;

public class MainActivity extends AppCompatActivity {
    private final String COUNTER_KEY = "counter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView)findViewById(R.id.txtCount);
        textView.setText(String.format(getString(R.string.counterText), 0));
    }

    @Override
    protected void onResume() {
        final SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
        final int newResumeCount = preferences.getInt(COUNTER_KEY, 0) + 1;
        final SharedPreferences.Editor editor = preferences.edit();
        TextView textView = (TextView)findViewById(R.id.txtCount);
        textView.setText(String.format(getString(R.string.counterText), newResumeCount));
        editor.putInt(COUNTER_KEY, newResumeCount);
        editor.apply();

        setPreferencesText();
        super.onResume();
    }

    public void openTeaPreferences(View button){
        Intent lifecycleLogCall = new Intent(this, AppPreferenceActivity.class);
        startActivity(lifecycleLogCall);
    }

    public void setDefaultTeaPrefs(View button) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("teaPreferred", "Lipton/Pfefferminztee");
        editor.putString("teaSweetener", "natural");
        editor.putBoolean("teaWithSugar", true);
        editor.apply();

        setPreferencesText();
    }

    private void setPreferencesText(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String teaType = preferences.getString("teaPreferred", "Lipton/Pfefferminztee");
        String sweetener = preferences.getString("teaSweetener", "natural");
        if(preferences.getBoolean("teaWithSugar", true ))
            sweetener = String.format(getString(R.string.teaSweetener),getSweetenerTranslation(sweetener));
        else
            sweetener = "ungesüsst";
        TextView textView = (TextView)findViewById(R.id.txtPrefs);
        textView.setText(String.format(getString(R.string.teaPrefText),teaType,sweetener));
    }

    private String getSweetenerTranslation(String specificValue){
        String[] values = getResources().getStringArray(R.array.teaSweetenerValues);
        String[] translations = getResources().getStringArray(R.array.teaSweetener);

        for(int i = 0; i < values.length; i++) {
            if(specificValue.equals(values[i])) {
                return translations[i];
            }
        }
        return "";
    }

    public void saveContent(View button){
        CheckBox checkBox = findViewById(R.id.cbExternal);
        if(checkBox.isChecked() &&
                Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
            saveToExtFileWithPermission();
        else
            saveToFile();
    }

    private void saveToFile(){
        writeFile(getApplicationContext().getFilesDir());
    }

    private void saveToExtFileWithPermission(){
        int grant = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(grant != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE }, 24);
        } else {
            writeFile(getExternalStorage());
        }
    }

    private void writeFile(File fileDir){
        File file = new File(fileDir, "appText");
        EditText editText = findViewById(R.id.txtFile);
        Writer writer = null;
        try{
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(editText.getText().toString());
        } catch (final IOException e){
            Log.e("HSLU MOBPROG", e.getMessage());
        } finally {
            try {
                if(writer != null)
                    writer.close();
            } catch (IOException e) {
            }
        }
    }

    public void loadContent(View button){
        CheckBox checkBox = findViewById(R.id.cbExternal);
        if(checkBox.isChecked() &&
                Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
            loadFromExtFileWithPermission();
        else
            loadFromFile();
    }

    private void loadFromExtFileWithPermission(){
        int grant = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
        if(grant != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[] { Manifest.permission.READ_EXTERNAL_STORAGE }, 34);
        } else {
            readFile(getExternalStorage());
        }
    }

    private File getExternalStorage(){
        return Environment.getExternalStorageDirectory();
    }

    private void loadFromFile(){
        readFile(getApplicationContext().getFilesDir());
    }

    private void readFile(File fileDir){
        File file = new File(fileDir, "appText");
        Reader reader = null;
        StringBuilder readData = new StringBuilder();
        try{
            reader = new BufferedReader(new FileReader(file));
            int readChar;
            while((readChar = reader.read()) != -1)
                readData.append((char)readChar);
        } catch (final IOException e){
            Log.e("HSLU MOBPROG", e.getMessage());
        } finally {
            try {
                if(reader != null)
                    reader.close();
            } catch (IOException e) {
            }

            updateContent(readData.toString());
        }
    }

    private void updateContent(String text){
        EditText editText = findViewById(R.id.txtFile);
        editText.setText(text.toString());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 24:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    writeFile(getExternalStorage());
                }
                break;
            case 34:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    readFile(getExternalStorage());
                }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void onClickCreateNotes(View button){
        Intent notesActivity = new Intent(this, NoteActivity.class);
        notesActivity.putExtra("noteId", -1);
        startActivityForResult(notesActivity, 10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Use return data and print to log
        if (requestCode == 10 && resultCode == RESULT_OK) {
            final Note note = new Note();
            note.text = data.getExtras().getString("text");
            note.title = data.getExtras().getString("title");

            new AsyncTask<Void, Void, Void>(){
                @Override
                protected Void doInBackground(Void... voids) {
                    NotesDatabase db = NotesDatabaseSingleton.getInstance(getApplicationContext());
                    NoteDAO noteDAO = db.noteDAO();
                    noteDAO.insertNotes(note);
                    publishProgress();
                    return null;
                }
            }.execute();
        }
    }

    public void onClickShowNotes(View button){
        Intent selectActivity = new Intent(this, SelectNoteActivity.class);
        startActivity(selectActivity);
    }
}
