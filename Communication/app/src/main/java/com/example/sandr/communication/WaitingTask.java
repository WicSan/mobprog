package com.example.sandr.communication;

import android.os.AsyncTask;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by sandr on 27.03.2018.
 */

public class WaitingTask extends AsyncTask<Void, Void, Void> {
    private Button outputButton;

    public WaitingTask(Button outputButton){
        this.outputButton = outputButton;
    }

    @Override
    protected void onPreExecute() {
        outputButton.setText("DemoThread läuft...");
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        outputButton.setText("DemoThread starten.");
        super.onPostExecute(aVoid);
    }
}
