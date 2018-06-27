package ch.hslu.mobprog.ui_demo;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.preference.DialogPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int counter = 0;
    private final String KEY_COUNTER = "counter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button counterButton = (Button)findViewById(R.id.btnCounter);
        counterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                Toast.makeText(getApplicationContext(), "Zaehler = " + counter, Toast.LENGTH_LONG).show();
            }
        });

        Spinner dialogSpinner = findViewById(R.id.spDialog);
        if(dialogSpinner != null) {
            dialogSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                    Dialog dialog;
                    switch (pos) {
                        case 1:
                            dialog = buildSimpleDialog();
                            dialog.show();
                            break;
                        case 2:
                            dialog = buildListDialog();
                            dialog.show();
                            break;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }

    private Dialog buildSimpleDialog(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Aktualisierung")
                .setMessage("Soll die Aktualisierung durchgeführt werden?")
                .setPositiveButton("Ja", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int itemPos) {
                        Toast.makeText(getApplicationContext(), "Aktualisierung wird installiert.", Toast.LENGTH_LONG).show();
                    }
                }).setNeutralButton("Später", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int itemPos) {
                        Toast.makeText(getApplicationContext(), "Erneute Erinnerung.", Toast.LENGTH_LONG).show();
                    }
                }).setNegativeButton("Nein", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int itemPos) {
                        Toast.makeText(getApplicationContext(), "Aktualisierung wird nicht durchgeführt.", Toast.LENGTH_LONG).show();
                    }
                });
        return dialogBuilder.create();
    }

    private Dialog buildListDialog(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Wähle ein Getränk")
                .setItems(R.array.Drinks, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int itemPos) {
                        String[] drinks = getResources().getStringArray(R.array.Drinks);
                        Toast.makeText(getApplicationContext(), drinks[itemPos], Toast.LENGTH_LONG).show();
                    }
                });
        return dialogBuilder.create();
    }

    public void startDemoViewActivity(View button){
        Intent lifecycleLogCall = new Intent(this, ViewsDemoActivity.class);
        startActivity(lifecycleLogCall);
    }

    public void onRadioButtonClicked(View radioButton){
        Intent callLayoutDemo = new Intent(this, LayoutDemoActivity.class);
        switch(radioButton.getId()) {
            case R.id.rbRelative:
                callLayoutDemo.putExtra("layout", "relative");
                break;
            case R.id.rbLinear:
                callLayoutDemo.putExtra("layout", "linear");
                break;
        }
        startActivity(callLayoutDemo);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(KEY_COUNTER, counter);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        counter = savedInstanceState.getInt(KEY_COUNTER);
    }
}
