package com.example.sandr.communication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickSimpleHTTP(View button){
        Intent callActivity = new Intent(this, HttpDemosActivity.class);
        startActivity(callActivity);
    }

    public void onClickDemoThread(View button){
        WaitingTask task = new WaitingTask((Button)button);
        task.execute();
    }

    public void onClickMultiAsync(View button) {
        MultiAsyncTask task = new MultiAsyncTask(this);
        try {
            URL url0 = new URL("http://wherever.ch/hslu/title0.txt");
            URL url1 = new URL("http://wherever.ch/hslu/title1.txt");
            URL url2 = new URL("http://wherever.ch/hslu/title2.txt");
            URL url3 = new URL("http://wherever.ch/hslu/title3.txt");
            URL url4 = new URL("http://wherever.ch/hslu/title4.txt");

            task.execute(url0, url1, url2, url3, url4);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
