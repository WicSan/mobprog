package ch.hslu.mobprog.intentwigdet;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String text = getIntent().getStringExtra("text");
        if(text != null) {
            TextView textView = findViewById(R.id.startText);
            textView.setText(text);
        }
    }

    public void onClickOpenBrowser(View button){
        Intent browser = new Intent(Intent.ACTION_VIEW);
        browser.setData(Uri.parse("https://www.hslu.ch/de-ch/"));
        startActivity(browser);
    }

    public void onClickListIntents(View button) {
        Intent browser = new Intent(Intent.ACTION_VIEW);
        browser.setData(Uri.parse("https://www.hslu.ch/de-ch/"));
        final List<ResolveInfo> resolveInfoList = getPackageManager()
                .queryIntentActivities(browser, PackageManager.MATCH_ALL);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Film Titel");

        ArrayAdapter adapter = new ResolveInfoAdapter(this, resolveInfoList);
        dialogBuilder.setAdapter(adapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }});
        Dialog d = dialogBuilder.create();
        d.show();
    }

    public void onClickCustomStart(View button){
        Intent myApp = new Intent();
        myApp.setAction("ch.hslu.mobpro.actions.SOW_TEXT");
        myApp.addCategory(Intent.CATEGORY_DEFAULT);
        myApp.addCategory(Intent.CATEGORY_LAUNCHER);
        final String text = "Activity gestart durch folgedne Intent-ACTION:\n" +
                "'ch.hslu.mobpro.actions.SOW_TEXT' \n Jetzt = " + new Date();
        myApp.putExtra("text", text);
        startActivity(myApp);
    }
}
