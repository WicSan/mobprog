package com.example.sandr.communication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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
        final Button btn = (Button)button;
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        btn.setText("DemoThread läuft...");
                    }
                });

                try {
                    Thread.sleep(7000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        btn.setText("DemoThread starten.");
                    }
                });
            }
        });
        t.start();
    }

    public void onClickMultiAsync(View button) {
        MultiAsyncTask task = new MultiAsyncTask(this);
        List<URL> urls = new ArrayList<>();
        try {
            for(int i = 0; i < 5; i++) {
                urls.add(new URL("http://wherever.ch/hslu/title" + i + ".txt"));
            }

            task.execute(urls.toArray(new URL[urls.size()]));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
